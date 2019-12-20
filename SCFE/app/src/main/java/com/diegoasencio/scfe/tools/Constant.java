package com.diegoasencio.scfe.tools;

import com.google.gson.Gson;

import java.text.DecimalFormat;

public class Constant {

    public static final String PREFERENCE_NAME = "scfe_preferences";

    public static String URL_DOMAIN = "http://192.168.0.28:8000/";
    public static final String URL_STATE = "departamentos/";
    public static final String URL_GENERAL = "general/";
    public static final String URL_EOLICO = "eolico/";
    public static final Gson GSON = new Gson();
    public static final String INVERSOR = "inversor";
    public static final String PANEL = "panel";
    public static final String MODULOS = "modulos";
    public static final String CALCULATE = "calculate";
    public static final DecimalFormat FORMAT_MONEY = new DecimalFormat("$#,###");
    public static final DecimalFormat FORMAT_HOURS = new DecimalFormat("#.# h");
    public static final DecimalFormat FORMAT_POWER = new DecimalFormat("#.# W");
    public static final DecimalFormat FORMAT_VOLTAGE = new DecimalFormat("#.# V");
    public static final DecimalFormat FORMAT_INTENSITY = new DecimalFormat("#.# A");
    public static final DecimalFormat FORMAT_COUNT = new DecimalFormat("#.#");
    public static final DecimalFormat FORMAT_PERCENTAGE = new DecimalFormat("#.# %");
    public static final DecimalFormat FORMAT_BATTERY = new DecimalFormat("#.# ah");
    public static final DecimalFormat FORMAT_SPEED = new DecimalFormat("#.# m/s");

}
