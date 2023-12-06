package com.mmbarber.proyect.admin.controller;

import com.mmbarber.proyect.admin.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/correo")
public class CorreoController {

    @Autowired
    private EmailService emailService;
    private RedirectAttributes redirectAttributes;

    @PostMapping("/enviar-correo")
    public String enviarCorreo(RedirectAttributes redirectAttributes,Model model, String destinatario, String asunto, String mensaje) {

        emailService.enviarCorreo(destinatario, asunto, mensaje);

        redirectAttributes.addFlashAttribute("alertMessage", "El correo se ha enviado correctamente.");
        return "redirect:/admin/pages/landing_page";
    }
}
