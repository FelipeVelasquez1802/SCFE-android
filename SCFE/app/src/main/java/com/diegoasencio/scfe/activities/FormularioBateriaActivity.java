package com.diegoasencio.scfe.activities;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
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
import com.diegoasencio.scfe.objects.Battery;
import com.diegoasencio.scfe.objects.Calculate;
import com.diegoasencio.scfe.objects.City;
import com.diegoasencio.scfe.objects.General;
import com.diegoasencio.scfe.objects.Inversor;
import com.diegoasencio.scfe.objects.Panel;
import com.diegoasencio.scfe.objects.State;
import com.diegoasencio.scfe.tools.Constant;

public class FormularioBateriaActivity extends AppCompatActivity implements Initials, AdapterView.OnItemSelectedListener, View.OnClickListener, CalculateDialog.AlertDialogListener {

    private Calculate calculate;

    private General general;
    private State[] states;
    private City[] cities;
    private City cityObj;
    private Panel[] panels;
    private Panel panelObj;
    private Inversor[] inversors;
    private Inversor inversorObj;
    private Battery[] batteries;
    private Battery batteryObj;

    private Spinner state;
    private Spinner city;
    private Spinner panel;
    private Spinner inversor;
    private Spinner battery;

    private EditText energy;
    private EditText days;
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
        cityObj = new City();
        panel = findViewById(R.id.spinner_panel);
        panelObj = new Panel();
        inversor = findViewById(R.id.spinner_inversor);
        inversorObj = new Inversor();
        battery = findViewById(R.id.spinner_battery);
        batteryObj = new Battery();

        energy = findViewById(R.id.edittext_energy);
        days = findViewById(R.id.editText_days);
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
                cityObj = cities[i];
                peak_solar.setText(cityObj.getHora_solar_pico_format());
                break;
            case R.id.spinner_panel:
                Panel panel = panelObj = panels[i];
                price_panel.setText(panel.getPrecio_format());
                potencia_modulo.setText(panel.getPotencia_format());
                vmpp.setText(panel.getVmpp_format());
                impp.setText(panel.getImpp_format());
                isc_panel.setText(panel.getIsc_format());
                dimension.setText(panel.getDimension());
                break;
            case R.id.spinner_inversor:
                Inversor inversor = inversorObj = inversors[i];
                controllers.setText(inversor.getNumero_controladores_format());
                involtage.setText(inversor.getVoltaje_entrada_format());
                system_voltage.setText(inversor.getVoltaje_sistema_format());
                idc.setText(inversor.getIdc_format());
                isc_inversor.setText(inversor.getIsc_format());
                efficiency.setText(inversor.getEficiencia_format());
                price_inversor.setText(inversor.getPrecio_format());
                break;
            case R.id.spinner_battery:
                Battery battery = batteryObj = batteries[i];
                capacity_battery.setText(battery.getCapacidad_format());
                voltage.setText(battery.getVoltaje_format());
                profundity_discharge.setText(battery.getProfundidad_descarga_format());
                price_battery.setText(battery.getPrecio_format());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_calculate:
                String energyObj = energy.getText().toString();
                energy.setError(null);
                if (energyObj != null && !energyObj.equalsIgnoreCase("")) {
                    String daysString = days.getText().toString();
                    if (daysString != null && !daysString.equalsIgnoreCase("")) {
                        calculate = new Calculate(Double.valueOf(energyObj), cityObj, panelObj, inversorObj, batteryObj, Double.valueOf(daysString));
                        if (calculate.isCorrectInversor()) {
                            showDialog();
                        } else {
                            AlertDialog.Builder alert_error = new AlertDialog.Builder(this);
                            alert_error.setTitle(R.string.error_inversor_title).setMessage(R.string.error_inversor_message);
                            alert_error.setNegativeButton(R.string.back, null);
                            AlertDialog alertDialog = alert_error.create();
                            alertDialog.show();
                        }
                        ((TextView) inversor.getSelectedView()).setError((calculate.isCorrectInversor()) ? null : "");
                    } else {
                        days.setError(getString(R.string.fail_days));
                    }
                } else {
                    energy.setError(getString(R.string.fail_energy));
                }
                break;
        }
    }

    private void showDialog() {
        DialogFragment dialogFragment = new CalculateDialog();
        Bundle args = new Bundle();
        String calculateObj = Constant.GSON.toJson(calculate);
        args.putString(Constant.CALCULATE, calculateObj);
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
