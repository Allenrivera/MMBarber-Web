package com.mmbarber.proyect.admin.controller.productos;

import com.mmbarber.proyect.admin.model.productos.Productos;
import com.mmbarber.proyect.admin.repository.GestionProductosRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
public class GestionProductosController {

    @Autowired
    private GestionProductosRepository productosRepository;


    // Mostrar la lista de productos
    @GetMapping("/admin/pages/productos_page")
    public String listarProductos(Model model) {
        List<Productos> productos = productosRepository.findAll();
        model.addAttribute("productos", productos);
        return "admin/pages/productos_page";
    }

    // Mostrar el formulario de creación de productos
    @GetMapping("/admin/pages/crear_producto")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("producto", new Productos());
        return "admin/pages/crear_producto";
    }

    // Procesar el formulario de creación de productos
    @PostMapping("/admin/pages/crear_producto")
    public String crearProducto(@ModelAttribute("producto") Productos producto, RedirectAttributes redirectAttributes) {
        productosRepository.save(producto);

        redirectAttributes.addFlashAttribute("alertMessage", "El producto se ha creado correctamente.");

        return "redirect:/admin/pages/productos_page";
    }

    // Mostrar el formulario de edición de productos
    @GetMapping("/admin/pages/editar_producto/{id}")
    public String mostrarFormularioEdicion(@PathVariable("id") Long id, Model model) {
        Optional<Productos> productoOptional = productosRepository.findById(id);
        if (productoOptional.isPresent()) {
            Productos producto = productoOptional.get();
            model.addAttribute("producto", producto);
            return "admin/pages/editar_producto";
        } else {
            // Manejar el caso en que el producto no se encuentre
            return "redirect:/admin/pages/productos_page";
        }
    }

    // Procesar el formulario de edición de productos
    @PostMapping("/admin/pages/editar_producto/{id}")
    public String editarProducto(@PathVariable("id") Long id, @ModelAttribute("producto") Productos producto) {
        producto.setId_producto(id);
        productosRepository.save(producto);
        return "redirect:/admin/pages/productos_page";
    }

    @GetMapping("/productos/pdf")
    public void exportarProductosPdf(HttpServletResponse response) throws IOException {
        // Configura la respuesta HTTP para descargar el PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=productos.pdf");

        // Crea un nuevo documento PDF
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);

        // Abre el contenido del PDF
        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        // Agrega los datos de la tabla al PDF
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

        float margin = 50;
        float yStart = page.getMediaBox().getHeight() - margin;
        float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
        float yPosition = yStart;
        int rows = 0;
        float rowHeight = 20;
        float tableHeight = rowHeight * 20;
        float tableMargin = 10;

        // Definir encabezados de columnas
        String[] headers = new String[]{"ID", "Nombre", "Descripción", "Precio"};

        // Ajustar posición y escribir encabezados
        yPosition -= rowHeight;
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        for (String header : headers) {
            contentStream.showText(header);
            contentStream.newLineAtOffset(tableWidth / headers.length, 0);
        }
        contentStream.endText();
        yPosition -= tableMargin;

        // Obtener la lista de productos desde la base de datos (reemplaza esto con tu lógica)
        List<Productos> productos = productosRepository.findAll();

        // Iterar sobre la lista de productos y agrégalos al PDF
        for (Productos producto : productos) {
            yPosition -= rowHeight;
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText(Long.toString(producto.getId_producto())); // Reemplaza con el atributo correcto
            contentStream.newLineAtOffset(tableWidth / headers.length, 0);
            contentStream.showText(producto.getNombre_producto()); // Reemplaza con el atributo correcto
            contentStream.newLineAtOffset(tableWidth / headers.length, 0);
            contentStream.showText(producto.getDescripcion_producto()); // Reemplaza con el atributo correcto
            contentStream.newLineAtOffset(tableWidth / headers.length, 0);
            contentStream.endText();

            rows++;

            // Agregar una nueva página si es necesario
            if (yPosition <= tableMargin) {
                document.addPage(page);
                yPosition = yStart;
            }
        }

        contentStream.close();

        // Escribe el PDF en la respuesta HTTP
        document.save(response.getOutputStream());
        document.close();
    }


}
