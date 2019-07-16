package com.diegoasencio.scfe;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuPrincipal extends AppCompatActivity {

    Button button_fotovoltaica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        button_fotovoltaica = (Button) findViewById(R.id.button_fotovoltaica);

        button_fotovoltaica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mainIntent = new Intent(MenuPrincipal.this, MenuFotovoltaica.class);
                MenuPrincipal.this.startActivity(mainIntent);
                // MenuPrincipal.this.finish();
            }
        });

    }


}
