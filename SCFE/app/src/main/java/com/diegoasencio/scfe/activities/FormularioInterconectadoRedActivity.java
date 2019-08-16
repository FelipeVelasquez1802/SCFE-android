package com.diegoasencio.scfe.activities;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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
import com.diegoasencio.scfe.dialogs.CalculateDialog;
import com.diegoasencio.scfe.interfaces.Initials;
import com.diegoasencio.scfe.objects.City;
import com.diegoasencio.scfe.objects.General;
import com.diegoasencio.scfe.objects.Inversor;
import com.diegoasencio.scfe.objects.Panel;
import com.diegoasencio.scfe.objects.State;
import com.diegoasencio.scfe.tools.Constant;

public class FormularioInterconectadoRedActivity extends AppCompatActivity implements Initials, AdapterView.OnItemSelectedListener, View.OnClickListener, CalculateDialog.AlertDialogListener {

    private double pp;
    private double modulos;
    private double area;
    private double strings;
    private double vstrings;

    private General general;
    private State[] states;
    private City[] cities;
    private City cityObj;
    private Panel[] panels;
    private Panel panelObj;
    private Inversor[] inversors;
    private Inversor inversorObj;

    private Spinner state;
    private Spinner city;
    private Spinner panel;
    private Spinner inversor;

    private EditText energy;
    private TextView peak_solar;
    private TextView price_panel;
    private TextView potencia_modulo;
    private TextView vmpp;
    private TextView impp;
    private TextView isc_panel;
    private TextView dimension;
    private TextView controllers;
    private TextView involtage;
    private TextView system_voltage;
    private TextView idc;
    private TextView isc_inversor;
    private TextView efficiency;
    private TextView price_inversor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_interconectado_red);
        initElements();
        initObjects();

    }

    @Override
    public void initElements() {
        state = findViewById(R.id.spinner_state);
        city = findViewById(R.id.spinner_city);
        cityObj = new City();
        panel = findViewById(R.id.spinner_panel);
        panelObj = new Panel();
        inversor = findViewById(R.id.spinner_inversor);
        inversorObj = new Inversor();

        energy = findViewById(R.id.edittext_energy);
        peak_solar = findViewById(R.id.textview_peak_solar);
        price_panel = findViewById(R.id.textview_price_panel);
        potencia_modulo = findViewById(R.id.textview_potencia_modulo);
        vmpp = findViewById(R.id.textview_vpmm);
        impp = findViewById(R.id.textview_lmpp);
        isc_panel = findViewById(R.id.textview_lsc_panel);
        dimension = findViewById(R.id.textview_dimension);
        controllers = findViewById(R.id.textview_controllers);
        involtage = findViewById(R.id.textview_involtage);
        system_voltage = findViewById(R.id.textview_system_voltage);
        idc = findViewById(R.id.textview_ldc);
        isc_inversor = findViewById(R.id.textview_lsc_inversor);
        efficiency = findViewById(R.id.textview_efficiency);
        price_inversor = findViewById(R.id.textview_price_inversor);

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
                                dumpdataState();
                                dumpdataPanel();
                                dumpdataInversor();
                                break;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(FormularioInterconectadoRedActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinner_state:
                cities = states[i].getMunicipio();
                dumpdataCity();
                break;
            case R.id.spinner_city:
                cityObj = cities[i];
                peak_solar.setText(cityObj.getHora_solar_pico() + " h");
                break;
            case R.id.spinner_panel:
                Panel panel = panels[i];
                panelObj = panel;
                price_panel.setText(panel.getPrecio() + "");
                potencia_modulo.setText(panel.getPotencia() + "");
                vmpp.setText(panel.getVmpp() + "");
                impp.setText(panel.getImpp() + "");
                isc_panel.setText(panel.getIsc() + "");
                dimension.setText(panel.getDimension());
                break;
            case R.id.spinner_inversor:
                Inversor inversor = inversors[i];
                inversorObj = inversor;
                controllers.setText(inversor.getNumero_controladores() + "");
                involtage.setText(inversor.getVoltaje_entrada() + "");
                system_voltage.setText(inversor.getVoltaje_sistema() + "");
                idc.setText(inversor.getIdc() + "");
                isc_inversor.setText(inversor.getIsc() + "");
                efficiency.setText(inversor.getEficiencia() + "");
                price_inversor.setText(inversor.getPrecio() + "");
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void calculate(String energy) {
        pp = Double.valueOf(energy) / cityObj.getHora_solar_pico();
        modulos = pp / panelObj.getPotencia();
        String[] dim = panelObj.getDimension().split("x");
        area = modulos * Double.valueOf(dim[0]) * Double.valueOf(dim[1]);
        strings = modulos / inversorObj.getNumero_controladores();
        vstrings = inversorObj.getVoltaje_entrada() * strings;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_calculate:
                String energyObj = energy.getText().toString();
                energy.setError(null);
                if (energyObj != null && !energyObj.equalsIgnoreCase("")) {
                    calculate(energyObj);
                    Toast.makeText(this, "" + vstrings, Toast.LENGTH_SHORT).show();
                    showDialog();
                } else {
                    energy.setError(getString(R.string.fail_energy));
                }
                break;
        }
    }

    private void showDialog() {
        DialogFragment dialogFragment = new CalculateDialog();
        Bundle args = new Bundle();
        String panelString = Constant.GSON.toJson(panelObj);
        String inversorString = Constant.GSON.toJson(inversorObj);
        args.putString(Constant.PANEL, panelString);
        args.putString(Constant.INVERSOR, inversorString);
        args.putDouble(Constant.MODULOS, modulos);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "Calculate");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
