package com.diegoasencio.scfe.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.interfaces.Initials;
import com.diegoasencio.scfe.objects.Battery;
import com.diegoasencio.scfe.objects.City;
import com.diegoasencio.scfe.objects.General;
import com.diegoasencio.scfe.objects.Inversor;
import com.diegoasencio.scfe.objects.Panel;
import com.diegoasencio.scfe.objects.State;
import com.diegoasencio.scfe.tools.Constant;

public class FormularioBateriaActivity extends AppCompatActivity implements Initials, AdapterView.OnItemSelectedListener {

    private General general;
    private State[] states;
    private City[] cities;
    private Panel[] panels;
    private Inversor[] inversors;
    private Battery[] batteries;

    private Spinner state;
    private Spinner city;
    private Spinner panel;
    private Spinner inversor;
    private Spinner battery;

    private TextView peak_solar;
    private TextView price_panel;
    private TextView potencia_modulo;
    private TextView vmpp;
    private TextView lmpp;
    private TextView lsc_panel;
    private TextView dimension;
    private TextView controllers;
    private TextView involtage;
    private TextView system_voltage;
    private TextView ldc;
    private TextView lsc_inversor;
    private TextView efficiency;
    private TextView price_inversor;
    private TextView capacity_battery;
    private TextView voltage;
    private TextView profundity_discharge;
    private TextView price_battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_bateria);
        initElements();
        initObjects();
    }

    @Override
    public void initElements() {
        state = findViewById(R.id.spinner_state);
        city = findViewById(R.id.spinner_city);
        panel = findViewById(R.id.spinner_panel);
        inversor = findViewById(R.id.spinner_inversor);
        battery = findViewById(R.id.spinner_battery);

        peak_solar = findViewById(R.id.textview_peak_solar);
        price_panel = findViewById(R.id.textview_price_panel);
        potencia_modulo = findViewById(R.id.textview_potencia_modulo);
        vmpp = findViewById(R.id.textview_vpmm);
        lmpp = findViewById(R.id.textview_lmpp);
        lsc_panel = findViewById(R.id.textview_lsc_panel);
        dimension = findViewById(R.id.textview_dimension);
        controllers = findViewById(R.id.textview_controllers);
        involtage = findViewById(R.id.textview_involtage);
        system_voltage = findViewById(R.id.textview_system_voltage);
        ldc = findViewById(R.id.textview_ldc);
        lsc_inversor = findViewById(R.id.textview_lsc_inversor);
        efficiency = findViewById(R.id.textview_efficiency);
        price_inversor = findViewById(R.id.textview_price_inversor);
        capacity_battery = findViewById(R.id.textview_capacity_battery);
        voltage = findViewById(R.id.textview_voltage);
        profundity_discharge = findViewById(R.id.textview_profundity_discharge);
        price_battery = findViewById(R.id.textview_price_battery);

        request(Constant.URL_GENERAL);
    }

    @Override
    public void initObjects() {
        general = null;
        states = null;
        cities = null;
        panels = null;
        inversors = null;
    }

    private void request(final String path) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constant.URL_DOMAIN + path;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        switch (path) {
                            case Constant.URL_GENERAL:
                                general = Constant.GSON.fromJson(response, General.class);
                                states = general.getDepartamentos();
                                panels = general.getPaneles();
                                inversors = general.getInversores();
                                batteries = general.getBaterias();
                                dumpdataState();
                                dumpdataPanel();
                                dumpdataInversor();
                                dumpdataBattery();
                                break;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FormularioBateriaActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(stringRequest);
    }

    private void dumpdataState() {
        ArrayAdapter<State> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(arrayAdapter);
        state.setOnItemSelectedListener(this);
    }

    private void dumpdataCity() {
        ArrayAdapter<City> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, cities);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(arrayAdapter);
        city.setOnItemSelectedListener(this);
    }

    private void dumpdataPanel() {
        ArrayAdapter<Panel> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, panels);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        panel.setAdapter(arrayAdapter);
        panel.setOnItemSelectedListener(this);
    }

    private void dumpdataInversor() {
        ArrayAdapter<Inversor> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, inversors);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        inversor.setAdapter(arrayAdapter);
        inversor.setOnItemSelectedListener(this);
    }

    private void dumpdataBattery() {
        ArrayAdapter<Battery> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, batteries);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        battery.setAdapter(arrayAdapter);
        battery.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinner_state:
                cities = states[i].getMunicipio();
                dumpdataCity();
                break;
            case R.id.spinner_city:
                peak_solar.setText(cities[i].getHora_solar_pico());
                break;
            case R.id.spinner_panel:
                Panel panel = panels[i];
                price_panel.setText(panel.getPrecio() + "");
                potencia_modulo.setText(panel.getPotencia() + "");
                vmpp.setText(panel.getVmpp() + "");
                lmpp.setText(panel.getLmpp() + "");
                lsc_panel.setText(panel.getLsc() + "");
                dimension.setText(panel.getDimension());
                break;
            case R.id.spinner_inversor:
                Inversor inversor = inversors[i];
                controllers.setText(inversor.getNumero_controladores() + "");
                involtage.setText(inversor.getVoltaje_entrada() + "");
                system_voltage.setText(inversor.getVoltaje_sistema() + "");
                ldc.setText(inversor.getLdc() + "");
                lsc_inversor.setText(inversor.getLsc() + "");
                efficiency.setText(inversor.getEficiencia() + "");
                price_inversor.setText(inversor.getPrecio() + "");
                break;
            case R.id.spinner_battery:
                Battery battery = batteries[i];
                capacity_battery.setText(battery.getCapacidad() + "");
                voltage.setText(battery.getVoltaje() + "");
                profundity_discharge.setText(battery.getProfundidad_descarga() + "");
                price_battery.setText(battery.getPrecio() + "");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
