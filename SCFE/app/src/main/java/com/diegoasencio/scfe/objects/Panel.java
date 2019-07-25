package com.diegoasencio.scfe.objects;

public class Panel {

    private int id;
    private String nombre;
    private double precio;
    private double potencia;
    private String dimension;
    private double vmpp;
    private double impp;
    private double isc;

    public double getImpp() {
        return impp;
    }

    public void setImpp(double impp) {
        this.impp = impp;
    }

    public double getIsc() {
        return isc;
    }

    public void setIsc(double isc) {
        this.isc = isc;
    }

    public double getVmpp() {
        return vmpp;
    }

    public void setVmpp(double vmpp) {
        this.vmpp = vmpp;
    }

    public String getDimension() {
        return dimension;
    }

    public void setDimension(String dimension) {
        this.dimension = dimension;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public double getPotencia() {
        return potencia;
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
