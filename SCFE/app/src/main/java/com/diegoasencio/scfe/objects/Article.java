package com.diegoasencio.scfe.objects;

import com.diegoasencio.scfe.tools.Constant;

public class Article {

    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInstrumento() {
        return instrumento;
    }

    public void setInstrumento(String instrumento) {
        this.instrumento = instrumento;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public String getCaracteristica() {
        return caracteristica;
    }

    public void setCaracteristica(String caracteristica) {
        this.caracteristica = caracteristica;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getCantidadFormat() {
        return Constant.FORMAT_COUNT.format(getCantidad());
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getPrecio() {
        return precio;
    }

    public String getPrecioFormat() {
        return Constant.FORMAT_MONEY.format(getPrecio());
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    private String instrumento;
    private double velocidad;
    private String caracteristica;
    private int cantidad;
    private int precio;

}
