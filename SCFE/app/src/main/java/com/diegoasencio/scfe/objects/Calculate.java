package com.diegoasencio.scfe.objects;

public class Calculate {

    private double energy;
    private City city;
    private Panel panel;
    private Inversor inversor;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Panel getPanel() {
        return panel;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public Inversor getInversor() {
        return inversor;
    }

    public void setInversor(Inversor inversor) {
        this.inversor = inversor;
    }

    public Calculate(double energy, City city, Panel panel, Inversor inversor) {
        this.energy = energy;
        this.city = city;
        this.panel = panel;
        this.inversor = inversor;
    }

    public double getPp() {
        return energy / getCity().getHora_solar_pico();
    }

    public double getModulos() {
        return Math.round(getPp() / getPanel().getPotencia());
    }

    public double getArea() {
        String[] dim = getPanel().getDimension().split("x");
        return getModulos() * Double.valueOf(dim[0]) * Double.valueOf(dim[1]);
    }

    public double getStrings() {
        return getModulos() / getInversor().getNumero_controladores();
    }

    public double getVstrings() {
        return getStrings() * getInversor().getVoltaje_entrada();
    }

    public double getPagoFacturaMensualSinSF() {
        return (energy / 1000) * 30 * 550;
    }

    public double getPagoFacturaMensualConSF() {
        return ((energy - getPp()) / 1000) * 30 * 550;
    }

    public double getAhorroMensual() {
        return getPagoFacturaMensualSinSF() - getPagoFacturaMensualConSF();
    }


}
