package com.mmbarber.proyect.model;

import jakarta.persistence.*;


@Entity
@Table (name = "servicios")
public class Servicios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id_servicio;

    private String nombre_servicio;
    private int Id_tipo_Servicio;
    private String Descripcion_Servicio;
    private int precio;

    public long getId_servicio() {
        return Id_servicio;
    }

    public void setId_servicio(long id_servicio) {
        Id_servicio = id_servicio;
    }

    public String getNombre_servicio() {
        return nombre_servicio;
    }

    public void setNombre_servicio(String nombre_servicio) {
        this.nombre_servicio = nombre_servicio;
    }

    public int getId_tipo_Servicio() {
        return Id_tipo_Servicio;
    }

    public void setId_tipo_Servicio(int id_tipo_Servicio) {
        Id_tipo_Servicio = id_tipo_Servicio;
    }

    public String getDescripcion_Servicio() {
        return Descripcion_Servicio;
    }

    public void setDescripcion_Servicio(String descripcion_Servicio) {
        Descripcion_Servicio = descripcion_Servicio;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

}

