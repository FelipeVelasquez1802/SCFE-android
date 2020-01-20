package com.diegoasencio.scfe.activities;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
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
import com.diegoasencio.scfe.dialogs.ArticleDialog;
import com.diegoasencio.scfe.interfaces.Initials;
import com.diegoasencio.scfe.objects.Article;
import com.diegoasencio.scfe.objects.Autogenerador;
import com.diegoasencio.scfe.objects.Eolico;
import com.diegoasencio.scfe.objects.EolicoGeneral;
import com.diegoasencio.scfe.tools.Constant;

public class MenuEolicoActivity extends AppCompatActivity implements Initials, AdapterView.OnItemSelectedListener, View.OnClickListener, ArticleDialog.AlertDialogListener {

    private EditText energy;
    private TextView state;
    private Spinner city;
    private TextView speed;
    private TextView rosa;

    private EolicoGeneral eolicoGeneral;

    private Eolico eolicos[];
    private Eolico eolico;
    private Autogenerador autogeneradores[];

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
        energy = findViewById(R.id.energy);
        state = findViewById(R.id.textview_state);
        city = findViewById(R.id.spinner_city);
        speed = findViewById(R.id.textview_speed);
        rosa = findViewById(R.id.textView_rosa_vientos);
    }

    @Override
    public void initObjects() {
        eolicoGeneral = new EolicoGeneral();
        eolicos = new Eolico[0];
        autogeneradores = null;
        eolico = new Eolico();
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
                                autogeneradores = eolicoGeneral.getAutogenerador();
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
                eolico = eolicos[i];
                state.setText(eolico.getDepartamento());
                speed.setText(eolico.getVelocidadFormat());
                rosa.setText(eolico.getRosa_vientos());
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private Autogenerador _find_item(double energy) {
        Autogenerador autogenerador = null;
        if (this.autogeneradores != null) {
            for (Autogenerador a : this.autogeneradores) {
                if (a.getPotencia() >= energy) {
                    for (Article article : a.getArticulos()) {
                        if (article.getVelocidad() <= eolico.getVelocidad())
                            autogenerador = a;
                    }
                }
            }
        }
        return autogenerador;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_calculate:
                String energyString = this.energy.getText().toString();
                if (energyString != null && !energyString.equalsIgnoreCase("")) {
                    double energy = Double.parseDouble(energyString);
                    Autogenerador autogenerador = _find_item(energy);
                    if (autogenerador == null) {
                        AlertDialog.Builder alert_error = new AlertDialog.Builder(this);
                        alert_error.setTitle(R.string.error_inversor_title).setMessage(R.string.error_inversor_message);
                        alert_error.setNegativeButton(R.string.back, null);
                        AlertDialog alertDialog = alert_error.create();
                        alertDialog.show();
                        return;
                    }
                    DialogFragment dialogFragment = new ArticleDialog();
                    Bundle args = new Bundle();
                    args.putString(Constant.ARTICLE, Constant.GSON.toJson(autogenerador));
                    dialogFragment.setArguments(args);
                    dialogFragment.show(getSupportFragmentManager(), "Article");
                } else {
                    energy.setError(getString(R.string.fail_energy));
                }
                break;
        }
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
