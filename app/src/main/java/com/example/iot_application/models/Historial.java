package com.example.iot_application.models;

public class Historial {
    boolean estado;
    long hora;

    public Historial() {
    }

    public Historial(boolean estado, long hora) {
        this.estado = estado;
        this.hora = hora;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public long getHora() {
        return hora;
    }

    public void setHora(long hora) {
        this.hora = hora;
    }
}
