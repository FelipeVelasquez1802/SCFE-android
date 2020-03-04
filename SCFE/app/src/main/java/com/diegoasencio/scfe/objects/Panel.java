package com.diegoasencio.scfe.objects;

import com.diegoasencio.scfe.tools.Constant;

public class Panel extends Product {

    private double potencia;
    private String dimension;
    private double vmpp;
    private double impp;
    private double isc;

    public double getImpp() {
        return impp;
    }

    public String getImpp_format() {
        return Constant.FORMAT_INTENSITY.format(getImpp());
    }


    public void setImpp(double impp) {
        this.impp = impp;
    }

    public double getIsc() {
        return isc;
    }

    public String getIsc_format() {
        return Constant.FORMAT_INTENSITY.format(getIsc());
    }

    public void setIsc(double isc) {
        this.isc = isc;
    }

    public double getVmpp() {
        return vmpp;
    }

    public String getVmpp_format() {
        return Constant.FORMAT_VOLTAGE.format(getVmpp());
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


    public double getPotencia() {
        return potencia;
    }

    public String getPotencia_format() {
        return Constant.FORMAT_POWER.format(getPotencia());
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
