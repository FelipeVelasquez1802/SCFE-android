package com.diegoasencio.scfe.objects;

public class Station {

    private int id;
    private String nombre;
    private double radiacion;

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

    public double getRadiacion() {
        return radiacion;
    }

    public void setRadiacion(double radiacion) {
        this.radiacion = radiacion;
    }

    @Override
    public String toString() {
        return getNombre();
        //return super.toString();
    }
}
