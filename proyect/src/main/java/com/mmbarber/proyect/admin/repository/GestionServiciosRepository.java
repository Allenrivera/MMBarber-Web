package com.mmbarber.proyect.admin.repository;

import com.mmbarber.proyect.admin.model.servicios.GestionServicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GestionServiciosRepository extends JpaRepository <GestionServicios,Long> {
}
