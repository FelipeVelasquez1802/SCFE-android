package com.diegoasencio.scfe.objects;

public class Inversor {

    private int id;
    private String nombre;
    private int numero_controladores;
    private double voltaje_entrada;
    private double voltaje_sistema;
    private double ldc;
    private double lsc;
    private double eficiencia;
    private double precio;

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

    public int getNumero_controladores() {
        return numero_controladores;
    }

    public void setNumero_controladores(int numero_controladores) {
        this.numero_controladores = numero_controladores;
    }

    public double getVoltaje_entrada() {
        return voltaje_entrada;
    }

    public void setVoltaje_entrada(double voltaje_entrada) {
        this.voltaje_entrada = voltaje_entrada;
    }

    public double getVoltaje_sistema() {
        return voltaje_sistema;
    }

    public void setVoltaje_sistema(double voltaje_sistema) {
        this.voltaje_sistema = voltaje_sistema;
    }

    public double getLdc() {
        return ldc;
    }

    public void setLdc(double ldc) {
        this.ldc = ldc;
    }

    public double getLsc() {
        return lsc;
    }

    public void setLsc(double lsc) {
        this.lsc = lsc;
    }

    public double getEficiencia() {
        return eficiencia;
    }

    public void setEficiencia(double eficiencia) {
        this.eficiencia = eficiencia;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
