package com.diegoasencio.scfe.objects;

public class Battery extends Product{

    private int capacidad;
    private int profundidad_descarga;
    private double voltaje;

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public int getProfundidad_descarga() {
        return profundidad_descarga;
    }

    public void setProfundidad_descarga(int profundidad_descarga) {
        this.profundidad_descarga = profundidad_descarga;
    }

    public double getVoltaje() {
        return voltaje;
    }

    public void setVoltaje(double voltaje) {
        this.voltaje = voltaje;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
