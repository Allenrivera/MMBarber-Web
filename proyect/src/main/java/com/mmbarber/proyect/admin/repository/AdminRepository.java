package com.mmbarber.proyect.admin.repository;

import com.mmbarber.proyect.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface AdminRepository extends JpaRepository<Admin, Integer> {

    Admin findByCorreo(String correo);

    List<String> findRolesByCorreo(String correo);

}
