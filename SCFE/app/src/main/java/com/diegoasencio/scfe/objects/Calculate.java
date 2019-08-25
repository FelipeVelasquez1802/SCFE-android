package com.diegoasencio.scfe.objects;

import com.diegoasencio.scfe.tools.Constant;

public class Calculate {

    private double energy;
    private double days;
    private City city;
    private Panel panel;
    private Inversor inversor;
    private Battery battery;

    public Battery getBattery() {
        return battery;
    }

    public void setBattery(Battery battery) {
        this.battery = battery;
    }

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

    public Calculate(double energy, City city, Panel panel, Inversor inversor, Battery battery, double days) {
        this.energy = energy;
        this.days = days;
        this.city = city;
        this.panel = panel;
        this.inversor = inversor;
        this.battery = battery;
    }

    public double getPp() {
        return Math.round(energy / getCity().getHora_solar_pico());
    }

    public double getModulos() {
        return Math.round(getPp() / getPanel().getPotencia());
    }

    public double getArea() {
        String[] dim = getPanel().getDimension().split("x");
        return getModulos() * Double.valueOf(dim[0]) * Double.valueOf(dim[1]);
    }

    public double getStrings() {
        return Math.round(getModulos() / getInversor().getNumero_controladores());
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
        return Math.round(getPagoFacturaMensualSinSF() - getPagoFacturaMensualConSF());
    }

    public double getEnergy20() {
        return energy * 1.2;
    }

    public double getBankCapacity() {
        return (getEnergy20() * days) / (getInversor().getEficiencia() * getInversor().getVoltaje_sistema() * getBattery().getProfundidad_descarga());
    }

    public double getSerialBattery() {
        return Math.round(getInversor().getVoltaje_sistema() / getBattery().getVoltaje());
    }

    public double getParallelBattery() {
        return Math.round(getBankCapacity() / getBattery().getCapacidad());
    }

    public double getTotalBattery() {
        return Math.round(getSerialBattery() * getParallelBattery());
    }

    public String getTotalBattery_format() {
        return Constant.FORMAT_COUNT.format(Math.round(getSerialBattery() * getParallelBattery()));
    }

    public int getSerialModule() {
        return (int) (getInversor().getVoltaje_entrada() / getPanel().getVmpp());
    }

    public double getParallelModule() {
        return getModulos() / getSerialModule();
    }

    public boolean isCorrectInversor() {
        return inversor.getPotencia() >= getPp();
    }

    public boolean isSoportInversor() {
        return getVstrings() < inversor.getVoltaje_entrada();
    }

}
