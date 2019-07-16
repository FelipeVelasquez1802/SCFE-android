package com.diegoasencio.scfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuFotovoltaica extends AppCompatActivity {

    Button button_interconectado_a_la_red;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_fotovoltaica);

        button_interconectado_a_la_red = (Button) findViewById(R.id.button_interconectado_a_la_red);

        button_interconectado_a_la_red.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MenuFotovoltaica.this, FormularioInterconectadoRed.class);
                MenuFotovoltaica.this.startActivity(mainIntent);
                // MenuPrincipal.this.finish();
            }
        });


    }
}
