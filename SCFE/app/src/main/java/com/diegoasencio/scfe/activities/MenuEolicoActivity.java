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
import com.diegoasencio.scfe.objects.Eolico;
import com.diegoasencio.scfe.objects.EolicoGeneral;
import com.diegoasencio.scfe.tools.Constant;

public class MenuEolicoActivity extends AppCompatActivity implements Initials, AdapterView.OnItemSelectedListener, View.OnClickListener {

    private TextView state;
    private Spinner city;
    private TextView speed;
    private TextView rosa;

    private EolicoGeneral eolicoGeneral;

    private Eolico eolicos[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_eolico);
        initObjects();
        initElements();
        request(Constant.URL_EOLICO);
    }

    @Override
    public void initElements() {
        state = findViewById(R.id.textview_state);
        city = findViewById(R.id.spinner_city);
        speed = findViewById(R.id.textview_speed);
        rosa = findViewById(R.id.textView_rosa_vientos);
    }

    @Override
    public void initObjects() {
        eolicoGeneral = new EolicoGeneral();
        eolicos = new Eolico[0];
    }


    private void request(final String path) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constant.URL_DOMAIN + path;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        switch (path) {
                            case Constant.URL_EOLICO:
                                eolicoGeneral = Constant.GSON.fromJson(response, EolicoGeneral.class);
                                eolicos = eolicoGeneral.getVelocidad();
                                dumpdataCity();
                                break;
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MenuEolicoActivity.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(stringRequest);
    }

    private void dumpdataCity() {
        ArrayAdapter<Eolico> arrayAdapter = new ArrayAdapter<Eolico>(this, android.R.layout.simple_spinner_item, eolicos);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        city.setAdapter(arrayAdapter);
        city.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        switch (adapterView.getId()) {
            case R.id.spinner_city:
                Eolico eolico = eolicos[i];
                state.setText(eolico.getDepartamento());
                speed.setText(eolico.getVelocidadFormat());
                rosa.setText(eolico.getRosa_vientos());
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

                break;
        }
    }
}
