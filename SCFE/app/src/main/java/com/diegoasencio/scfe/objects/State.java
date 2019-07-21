package com.diegoasencio.scfe.objects;

public class State {

    private int id;
    private String nombre;
    private City[] municipio;

    public City[] getMunicipio() {
        return municipio;
    }

    public void setMunicipio(City[] municipio) {
        this.municipio = municipio;
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
        //return super.toString();
        return getNombre();
    }

}
