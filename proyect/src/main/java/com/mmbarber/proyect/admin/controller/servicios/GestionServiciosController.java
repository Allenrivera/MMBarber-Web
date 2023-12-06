package com.mmbarber.proyect.admin.controller.servicios;

import com.mmbarber.proyect.admin.model.servicios.CrearServicio;
import com.mmbarber.proyect.admin.model.servicios.GestionServicios;
import com.mmbarber.proyect.admin.repository.GestionServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class GestionServiciosController {

    @Autowired
    private GestionServiciosRepository gestionServiciosRepository;

    private String alertMessage; // Variable para almacenar el mensaje de alerta

    @ModelAttribute("alertMessage")
    public String getAlertMessage() {
        return alertMessage;
    }


    @GetMapping("/admin/pages/servicios_page")
    public String servicios(Model model) {
        //Obtener la lista de servicios de la BD
        List<GestionServicios> servicios = gestionServiciosRepository.findAll();
        model.addAttribute("servicios", servicios);


        return "admin/pages/servicios_page";
    }

    @GetMapping("/admin/pages/crear_servicio")
    public String mostrarFormularioCreacion(Model model) {
        model.addAttribute("crearServicio", new CrearServicio());

        return "admin/pages/crear_servicio"; // Redirigir a la página de creación de servicio
    }

    @PostMapping("/admin/pages/crear_servicio")
    public String crearServicio(@ModelAttribute("crearServicio") CrearServicio crearServicio, Model model, RedirectAttributes redirectAttributes) {
        // Paso 1: Validación de datos (ejemplo de validación básica)
        if (crearServicio.getNombre_servicio() == null || crearServicio.getNombre_servicio().isEmpty()) {
            model.addAttribute("error", "El nombre del servicio es obligatorio.");
            return "admin/pages/crear_servicio";
        }


        // Mapeo de datos
        GestionServicios nuevoServicio = new GestionServicios();
        nuevoServicio.setNombre_servicio(crearServicio.getNombre_servicio());
        nuevoServicio.setId_tipo_Servicio(crearServicio.getId_tipo_Servicio());
        nuevoServicio.setDescripcion_Servicio(crearServicio.getDescripcion_Servicio());
        nuevoServicio.setPrecio(crearServicio.getPrecio());

        // Persistencia en la base de datos
        gestionServiciosRepository.save(nuevoServicio);

        // Establecer el mensaje de alerta
        redirectAttributes.addFlashAttribute("alertMessage", "El producto se ha creado correctamente.");

        return "redirect:/admin/pages/servicios_page";



    }

}
