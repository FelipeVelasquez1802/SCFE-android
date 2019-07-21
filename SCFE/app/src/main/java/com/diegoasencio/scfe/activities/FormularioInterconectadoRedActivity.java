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
import com.diegoasencio.scfe.objects.City;
import com.diegoasencio.scfe.objects.General;
import com.diegoasencio.scfe.objects.Panel;
import com.diegoasencio.scfe.objects.State;
import com.diegoasencio.scfe.objects.Station;
import com.diegoasencio.scfe.tools.Constant;

public class FormularioInterconectadoRedActivity extends AppCompatActivity implements Initials, AdapterView.OnItemSelectedListener {

    private General general;
    private State[] states;
    private City[] cities;
    private Station[] stations;
    private Panel[] panels;

    private Spinner state;
    private Spinner city;
    private Spinner station;
    private Spinner panel;

    private TextView solar_radiation;
    private TextView precio;
    private TextView potencia_modulo;
    private TextView rendimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_interconectado_red);
        initElements();
        initObjects();
        //request = new VolleyRequest();
        //request.request(this, Constant.URL_STATE);

    }

    @Override
    public void initElements() {
        state = findViewById(R.id.spinner_state);
        city = findViewById(R.id.spinner_city);
        station = findViewById(R.id.spinner_station);
        panel = findViewById(R.id.spinner_panel);

        solar_radiation = findViewById(R.id.textview_solar_radiation);
        precio = findViewById(R.id.textview_precio);
        potencia_modulo = findViewById(R.id.textview_potencia_modulo);
        rendimiento = findViewById(R.id.textview_rendimiento);

        request(Constant.URL_GENERAL);
    }

    @Override
    public void initObjects() {
        general = null;
        states = null;
        cities = null;
        stations = null;
        panels = null;
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
                                dumpdataState();
                                dumpdataPanel();
                                break;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FormularioInterconectadoRedActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();
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

    private void dumpdataStation() {
        ArrayAdapter<Station> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, stations);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        station.setAdapter(arrayAdapter);
        station.setOnItemSelectedListener(this);
    }

    private void dumpdataPanel() {
        ArrayAdapter<Panel> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, panels);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        panel.setAdapter(arrayAdapter);
        panel.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinner_state:
                cities = states[i].getMunicipio();
                dumpdataCity();
                break;
            case R.id.spinner_city:
                stations = cities[i].getEstacion();
                dumpdataStation();
                break;
            case R.id.spinner_station:
                Station station = stations[i];
                solar_radiation.setText(station.getRadiacion() + "");
                break;
            case R.id.spinner_panel:
                Panel panel = panels[i];
                precio.setText(panel.getPrecio() + "");
                potencia_modulo.setText(panel.getPotencia() + "");
                rendimiento.setText(panel.getRendimiento() + "");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
