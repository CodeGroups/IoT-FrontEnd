package com.example.iot_application.models;

public class Horario {
    private long tiempo_inicio;
    private long intervalo;

    public Horario() {
    }

    public Horario(long tiempo_inicio, long intervalo) {
        this.tiempo_inicio = tiempo_inicio;
        this.intervalo = intervalo;
    }

    public long getTiempo_inicio() {
        return tiempo_inicio;
    }

    public void setTiempo_inicio(long tiempo_inicio) {
        this.tiempo_inicio = tiempo_inicio;
    }

    public long getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(long intervalo) {
        this.intervalo = intervalo;
    }
}
