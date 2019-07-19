package com.diegoasencio.scfe.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.interfaces.initials;
import com.diegoasencio.scfe.objects.City;
import com.diegoasencio.scfe.objects.State;
import com.diegoasencio.scfe.tools.Constant;
import com.diegoasencio.scfe.tools.VolleyRequest;
import com.google.gson.Gson;

import java.util.ArrayList;

public class FormularioInterconectadoRedActivity extends AppCompatActivity implements initials {

    private VolleyRequest request;
    private Spinner state;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_interconectado_red);
        initElement();
        //request = new VolleyRequest();
        //request.request(this, Constant.URL_STATE);

    }

    @Override
    public void initElement() {
        state = findViewById(R.id.spinner_state);
        request(Constant.URL_STATE);
    }

    private void request(final String path) {
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = Constant.URL_DOMAIN + path;
        //Toast.makeText(context, url, Toast.LENGTH_SHORT).show();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        switch (path) {
                            case Constant.URL_STATE:
                                State[] states = gson.fromJson(response, State[].class);
                                dumpdataState(states);
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

    private void dumpdataState(State[] states) {
        ArrayAdapter<State> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, states);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        state.setAdapter(arrayAdapter);
    }
}
