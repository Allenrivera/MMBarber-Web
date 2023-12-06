package com.mmbarber.proyect.admin.controller.servicios;

import com.mmbarber.proyect.admin.repository.GestionServiciosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class EliminarServiciosController {

    @Autowired
    private GestionServiciosRepository gestionServiciosRepository;

    @DeleteMapping("/admin/pages/eliminar_servicio/{id}")
    @ResponseBody
    public ResponseEntity<Map<String, String>> eliminarServicio(@PathVariable long id) {
        Map<String, String> response = new HashMap<>();
        try {
            gestionServiciosRepository.deleteById(id);
            response.put("success", "true");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", "false");
            response.put("message", "Error al eliminar el servicio: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }

    }
}

