package com.mmbarber.proyect.admin.controller;

import com.mmbarber.proyect.admin.model.servicios.GestionServicios;
import com.mmbarber.proyect.admin.repository.GestionServiciosRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/pdf")
public class PdfController {

    @Autowired
    private GestionServiciosRepository gestionServiciosRepository;

    @GetMapping("/export")
    public void exportPdf(HttpServletResponse response) throws IOException {
        // Configura la respuesta HTTP para descargar el PDF
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=servicios.pdf");

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
        String[] headers = new String[]{"Id_servicio", "Nombre_servicio", "Tipo_servicio", "Descripcion_servicio", "Precio"};

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

        // Obtener la lista de servicios desde la base de datos
        List<GestionServicios> servicios = gestionServiciosRepository.findAll();

        // Iterar sobre la lista de servicios y agrégalos al PDF
        for (GestionServicios servicio : servicios) {
            yPosition -= rowHeight;
            contentStream.beginText();
            contentStream.setFont(PDType1Font.HELVETICA, 12);
            contentStream.newLineAtOffset(margin, yPosition);
            contentStream.showText(Long.toString(servicio.getId_servicio()));
            contentStream.newLineAtOffset(tableWidth / headers.length, 0);
            contentStream.showText(servicio.getNombre_servicio());
            contentStream.newLineAtOffset(tableWidth / headers.length, 0);
            contentStream.showText(Integer.toString(servicio.getId_tipo_Servicio()));
            contentStream.newLineAtOffset(tableWidth / headers.length, 0);
            contentStream.showText(servicio.getDescripcion_Servicio());
            contentStream.newLineAtOffset(tableWidth / headers.length, 0);
            contentStream.showText(Integer.toString(servicio.getPrecio())); // Convierte el precio a cadena
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
        document.close();}

    }
