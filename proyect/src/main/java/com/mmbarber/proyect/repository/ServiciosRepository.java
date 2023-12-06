package com.mmbarber.proyect.repository;

import com.mmbarber.proyect.model.Servicios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiciosRepository extends JpaRepository <Servicios,Long> {
}
