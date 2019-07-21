package com.diegoasencio.scfe.objects;

public class City {

    private int id;
    private String nombre;
    private Station[] estacion;

    public Station[] getEstacion() {
        return estacion;
    }

    public void setEstacion(Station[] estacion) {
        this.estacion = estacion;
    }

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

    @Override
    public String toString() {
        return getNombre();
        //return super.toString();
    }
}
