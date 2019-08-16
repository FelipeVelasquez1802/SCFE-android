package com.diegoasencio.scfe.objects;

public class City {

    private int id;
    private String nombre;
    private double hora_solar_pico;

    public double getHora_solar_pico() {
        return hora_solar_pico;
    }

    public void setHora_solar_pico(double hora_solar_pico) {
        this.hora_solar_pico = hora_solar_pico;
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
