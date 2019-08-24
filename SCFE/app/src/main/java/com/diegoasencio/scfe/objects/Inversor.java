package com.diegoasencio.scfe.objects;

import com.diegoasencio.scfe.tools.Constant;

public class Inversor extends Product {

    private double potencia;
    private int numero_controladores;
    private double voltaje_entrada;
    private double voltaje_sistema;
    private double idc;
    private double isc;
    private double eficiencia;

    public double getPotencia() {
        return potencia;
    }

    public String getPotencia_format() {
        return Constant.FORMAT_POWER.format(getPotencia());
    }

    public void setPotencia(double potencia) {
        this.potencia = potencia;
    }

    public int getNumero_controladores() {
        return numero_controladores;
    }

    public String getNumero_controladores_format() {
        return Constant.FORMAT_COUNT.format(getNumero_controladores());
    }

    public void setNumero_controladores(int numero_controladores) {
        this.numero_controladores = numero_controladores;
    }

    public double getVoltaje_entrada() {
        return voltaje_entrada;
    }

    public String getVoltaje_entrada_format() {
        return Constant.FORMAT_VOLTAGE.format(getVoltaje_entrada());
    }

    public void setVoltaje_entrada(double voltaje_entrada) {
        this.voltaje_entrada = voltaje_entrada;
    }

    public double getVoltaje_sistema() {
        return voltaje_sistema;
    }

    public String getVoltaje_sistema_format() {
        return Constant.FORMAT_VOLTAGE.format(getVoltaje_sistema());
    }

    public void setVoltaje_sistema(double voltaje_sistema) {
        this.voltaje_sistema = voltaje_sistema;
    }

    public double getIdc() {
        return idc;
    }

    public String getIdc_format() {
        return Constant.FORMAT_INTENSITY.format(getIdc());
    }

    public void setIdc(double idc) {
        this.idc = idc;
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

    public double getEficiencia() {
        return eficiencia;
    }

    public String getEficiencia_format() {
        return Constant.FORMAT_PERCENTAGE.format(getEficiencia());
    }

    public void setEficiencia(double eficiencia) {
        this.eficiencia = eficiencia;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
