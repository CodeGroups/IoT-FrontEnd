package com.example.iot_application.models;

public class Casilla {
    private Historial historial;
    private Horario horario;
    private Medicina medicina;

    public Casilla() {
    }

    public Casilla(Historial historial, Horario horario, Medicina medicina) {
        this.historial = historial;
        this.horario = horario;
        this.medicina = medicina;
    }

    public Historial getHistorial() {
        return historial;
    }

    public void setHistorial(Historial historial) {
        this.historial = historial;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Medicina getMedicina() {
        return medicina;
    }

    public void setMedicina(Medicina medicina) {
        this.medicina = medicina;
    }
}
