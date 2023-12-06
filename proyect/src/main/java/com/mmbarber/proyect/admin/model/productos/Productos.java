package com.mmbarber.proyect.admin.model.productos;

import jakarta.persistence.*;

@Entity
@Table(name = "producto")
public class Productos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_producto;

    private String descripcion_producto;
    private String nombre_producto;
    @Column(name = "imagen_producto", columnDefinition = "BLOB")
    @Lob
    private byte[] imagen_producto;

    public Long getId_producto() {
        return id_producto;
    }

    public void setId_producto(Long id_producto) {
        this.id_producto = id_producto;
    }

    public String getDescripcion_producto() {
        return descripcion_producto;
    }

    public void setDescripcion_producto(String descripcion_producto) {
        this.descripcion_producto = descripcion_producto;
    }

    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }

    public byte[] getImagen_producto() {
        return imagen_producto;
    }

    public void setImagen_producto(byte[] imagen_producto) {
        this.imagen_producto = imagen_producto;
    }
}
