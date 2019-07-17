package com.diegoasencio.scfe.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.diegoasencio.scfe.FormularioInterconectadoRed;
import com.diegoasencio.scfe.R;

public class MenuFotovoltaicaActivity extends AppCompatActivity {

    Button button_interconectado_a_la_red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_fotovoltaica);

        button_interconectado_a_la_red = (Button) findViewById(R.id.button_interconectado_a_la_red);

        button_interconectado_a_la_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MenuFotovoltaicaActivity.this, FormularioInterconectadoRed.class);
                MenuFotovoltaicaActivity.this.startActivity(mainIntent);
                // MenuPrincipalActivity.this.finish();
            }
        });


    }
}
