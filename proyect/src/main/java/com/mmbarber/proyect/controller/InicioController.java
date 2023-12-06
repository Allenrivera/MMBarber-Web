package com.mmbarber.proyect.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioController {

    @GetMapping("/index")
    public String index(){

        return "index"; // Esto hace referencia a "index.html" en la carpeta "templates"
    }
}
