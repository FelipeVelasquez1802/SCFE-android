package com.diegoasencio.scfe.objects;

public class Battery {

    private int id;
    private String nombre;
    private double precio;
    private int capacidad;
    private int profundidad_descarga;
    private double voltaje;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

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
