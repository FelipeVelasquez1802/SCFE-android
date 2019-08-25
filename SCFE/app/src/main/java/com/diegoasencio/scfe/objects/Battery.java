package com.diegoasencio.scfe.objects;

import com.diegoasencio.scfe.tools.Constant;

public class Battery extends Product {

    private int capacidad;
    private double profundidad_descarga;
    private double voltaje;

    public int getCapacidad() {
        return capacidad;
    }

    public String getCapacidad_format() {
        return Constant.FORMAT_BATTERY.format(getCapacidad());
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public double getProfundidad_descarga() {
        return profundidad_descarga;
    }

    public String getProfundidad_descarga_format() {
        return Constant.FORMAT_PERCENTAGE.format(getProfundidad_descarga());
    }

    public void setProfundidad_descarga(double profundidad_descarga) {
        this.profundidad_descarga = profundidad_descarga;
    }

    public double getVoltaje() {
        return voltaje;
    }

    public String getVoltaje_format() {
        return Constant.FORMAT_VOLTAGE.format(getVoltaje());
    }

    public void setVoltaje(double voltaje) {
        this.voltaje = voltaje;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
