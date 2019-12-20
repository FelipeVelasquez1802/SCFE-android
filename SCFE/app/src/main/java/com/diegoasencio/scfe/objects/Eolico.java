package com.diegoasencio.scfe.objects;

import android.support.annotation.NonNull;

import com.diegoasencio.scfe.tools.Constant;

public class Eolico {

    private int id;
    private String departamento;
    private String municipio;
    private double velocidad;
    private String rosa_vientos;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getMunicipio() {
        return municipio;
    }

    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }

    public double getVelocidad() {
        return velocidad;
    }

    public String getVelocidadFormat() {
        return Constant.FORMAT_SPEED.format(getVelocidad());
    }

    public void setVelocidad(double velocidad) {
        this.velocidad = velocidad;
    }

    public String getRosa_vientos() {
        return rosa_vientos;
    }

    public void setRosa_vientos(String rosa_vientos) {
        this.rosa_vientos = rosa_vientos;
    }

    @NonNull
    @Override
    public String toString() {
        return getMunicipio();
    }
}
