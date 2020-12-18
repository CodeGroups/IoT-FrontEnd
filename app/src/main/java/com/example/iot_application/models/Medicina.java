package com.example.iot_application.models;

public class Medicina {
    private int cantidad;
    private String descripcion;
    private String nombre;

    public Medicina() {
    }

    public Medicina(int cantidad, String descripcion, String nombre) {
        this.cantidad = cantidad;
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
