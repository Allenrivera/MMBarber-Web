package com.mmbarber.proyect.admin.controller;
import com.mmbarber.proyect.admin.model.productos.Productos;
import com.mmbarber.proyect.admin.repository.GestionProductosRepository;
import org.springframework.ui.Model;
import com.mmbarber.proyect.admin.model.servicios.GestionServicios;
import com.mmbarber.proyect.admin.repository.GestionServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class DahsboardController {

    @Autowired
    private GestionServiciosRepository gestionServiciosRepository;
    @Autowired
    private GestionProductosRepository productosRepository;


    @GetMapping("/admin/pages/landing_page")
        public String dashboard(Model model){
        // Obtener la lista de servicios de la BD
        List<GestionServicios> servicios = gestionServiciosRepository.findAll();
        List<Productos> productos = productosRepository.findAll();

        // Obtener el número de servicios
        int numeroDeServicios = servicios.size();
        int numeroDeProductos =productos.size();

        // Agregar el número de servicios al modelo
        model.addAttribute("numeroDeServicios", numeroDeServicios);
        model.addAttribute("numeroDeProductos", numeroDeProductos);


        return "admin/pages/landing_page"; // Esto hace referencia a "admin/index.html" en la carpeta "templates"
    }



}
