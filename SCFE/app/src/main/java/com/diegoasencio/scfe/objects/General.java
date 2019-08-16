package com.diegoasencio.scfe.objects;

public class General {

    private State[] departamentos;
    private Panel[] paneles;
    private Inversor[] inversores;
    private Battery[] baterias;

    public Battery[] getBaterias() {
        return baterias;
    }

    public void setBaterias(Battery[] baterias) {
        this.baterias = baterias;
    }

    public Inversor[] getInversores() {
        return inversores;
    }

    public void setInversores(Inversor[] inversores) {
        this.inversores = inversores;
    }

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
