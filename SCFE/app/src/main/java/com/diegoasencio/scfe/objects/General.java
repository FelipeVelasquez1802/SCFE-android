package com.diegoasencio.scfe.objects;

public class General {

    private State[] departamentos;
    private Panel[] paneles;

    public State[] getDepartamentos() {
        return departamentos;
    }

    public void setDepartamentos(State[] departamentos) {
        this.departamentos = departamentos;
    }

    public Panel[] getPaneles() {
        return paneles;
    }

    public void setPaneles(Panel[] paneles) {
        this.paneles = paneles;
    }
}
