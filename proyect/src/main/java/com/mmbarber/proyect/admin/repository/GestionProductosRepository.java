package com.mmbarber.proyect.admin.repository;

import com.mmbarber.proyect.admin.model.productos.Productos;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GestionProductosRepository extends JpaRepository <Productos, Long> {
}
