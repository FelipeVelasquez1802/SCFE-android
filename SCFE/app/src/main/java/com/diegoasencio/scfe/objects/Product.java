package com.diegoasencio.scfe.objects;

import com.diegoasencio.scfe.tools.Constant;

public class Product {
    private int id;
    private double precio;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getPrecio() {
        return precio;
    }

    public String getPrecio_format() {
        return Constant.FORMAT_MONEY.format(getPrecio());
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
