package com.mmbarber.proyect.admin.controller;

import com.mmbarber.proyect.admin.model.Admin;
import com.mmbarber.proyect.admin.repository.AdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.ui.Model;

@Controller
@SessionAttributes("usuario")
public class LoginController {
    private final AdminRepository adminRepository;

    public LoginController(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @GetMapping("/admin/index")
    public String index() {
        return "admin/index"; // Esto hace referencia a "admin/index.html" en la carpeta "templates"
    }

    @PostMapping("/admin/index")
    public String loginSubmit(@RequestParam String correo, @RequestParam String password, Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        Admin admin = adminRepository.findByCorreo(correo);

        if (admin != null && admin.getPassword().equals(password)) {
            model.addAttribute("usuario", admin);
            return "redirect:/admin/pages/landing_page"; // Redirige al dashboard si la autenticación es exitosa
        } else {
            redirectAttributes.addFlashAttribute("error", "Credenciales inválidas"); // Agrega un mensaje de error
            return "redirect:/admin/index"; // Redirige al formulario de inicio de sesión con un mensaje de error
        }
    }

}