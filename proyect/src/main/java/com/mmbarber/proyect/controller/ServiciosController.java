package com.mmbarber.proyect.controller;


import com.mmbarber.proyect.admin.model.servicios.GestionServicios;
import com.mmbarber.proyect.admin.repository.GestionServiciosRepository;
import com.mmbarber.proyect.model.Servicios;
import com.mmbarber.proyect.repository.ServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ServiciosController {
    @Autowired
    private ServiciosRepository ServiciosRepository;



    @GetMapping("/servicios")
    public String servicios(Model model) {
        //Obtener la lista de servicios de la BD
        List<Servicios> servicios = ServiciosRepository.findAll();
        model.addAttribute("servicios", servicios);
        return "servicios";
    }
    }
