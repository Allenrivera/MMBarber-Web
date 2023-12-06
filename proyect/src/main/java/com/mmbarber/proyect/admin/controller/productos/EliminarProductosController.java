package com.mmbarber.proyect.admin.controller.productos;

import com.mmbarber.proyect.admin.repository.GestionProductosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;


@Controller
public class EliminarProductosController {

    @Autowired
    private GestionProductosRepository productosRepository;

    // Eliminar un producto
    @DeleteMapping("/admin/pages/eliminar_producto/{id}")
    @ResponseBody

    public ResponseEntity<Map<String, String>> eliminarProducto(@PathVariable long id) {
        Map<String, String> response = new HashMap<>();
        try {
            productosRepository.deleteById(id);
            response.put("success", "true");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", "false");
            response.put("message", "Error al eliminar el producto: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}