package com.diegoasencio.scfe.objects;

public class EolicoGeneral {
    private Eolico velocidad[];
    private Autogenerador autogenerador[];

    public Autogenerador[] getAutogenerador() {
        return autogenerador;
    }

    public void setAutogenerador(Autogenerador[] autogenerador) {
        this.autogenerador = autogenerador;
    }

    public Eolico[] getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Eolico[] velocidad) {
        this.velocidad = velocidad;
    }
}
