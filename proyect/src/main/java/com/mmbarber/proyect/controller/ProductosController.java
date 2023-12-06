package com.mmbarber.proyect.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductosController {

    @GetMapping("/productos")
    public String productos(){
        return "productos"; // Esto hace referencia a "productos.html" en la carpeta "templates"
    }
}
