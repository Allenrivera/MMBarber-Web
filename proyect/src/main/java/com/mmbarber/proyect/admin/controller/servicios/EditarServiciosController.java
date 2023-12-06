package com.mmbarber.proyect.admin.controller.servicios;

import com.mmbarber.proyect.admin.model.servicios.EditarServicio;
import com.mmbarber.proyect.admin.model.servicios.GestionServicios;
import com.mmbarber.proyect.admin.repository.GestionServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class EditarServiciosController {

    @Autowired
    private GestionServiciosRepository gestionServiciosRepository;

    @GetMapping("/admin/pages/editar_servicio/{id}")
    public String mostrarFormularioEdicion(@PathVariable ("id") long id, Model model) {
        // Buscar el servicio a editar por su ID
        Optional<GestionServicios> servicioOptional = gestionServiciosRepository.findById(id);

        if (servicioOptional.isPresent()) {
            GestionServicios servicio = servicioOptional.get();

            // Crear un objeto EditarServicio y copiar los datos del servicio a editar
            EditarServicio editarServicio = new EditarServicio();
            editarServicio.setIdServicio(servicio.getId_servicio());
            editarServicio.setNombreServicio(servicio.getNombre_servicio());
            editarServicio.setIdTipoServicio(servicio.getId_tipo_Servicio());
            editarServicio.setDescripcionServicio(servicio.getDescripcion_Servicio());
            editarServicio.setPrecio(servicio.getPrecio());

            model.addAttribute("editarServicio", editarServicio);

            return "admin/pages/editar_servicio";
        } else {
            // Manejar el caso en que el servicio no se encuentre
            return "redirect:/admin/pages/servicios_page";
        }
    }

    @PostMapping("/admin/pages/editar_servicio/{id}")
    public String editarServicio(@PathVariable long id, @ModelAttribute("editarServicio") EditarServicio editarServicio) {
        // Buscar el servicio a editar por su ID
        Optional<GestionServicios> servicioOptional = gestionServiciosRepository.findById(id);

        if (servicioOptional.isPresent()) {
            GestionServicios servicio = servicioOptional.get();

            // Actualizar los datos del servicio con los valores de editarServicio
            servicio.setNombre_servicio(editarServicio.getNombreServicio());
            servicio.setId_tipo_Servicio(editarServicio.getIdTipoServicio());
            servicio.setDescripcion_Servicio(editarServicio.getDescripcionServicio());
            servicio.setPrecio(editarServicio.getPrecio());

            // Guardar los cambios en la base de datos
            gestionServiciosRepository.save(servicio);

            return "redirect:/admin/pages/servicios_page";
        } else {
            // Manejar el caso en que el servicio no se encuentre
            return "redirect:/admin/pages/servicios_page";
        }
    }
}
