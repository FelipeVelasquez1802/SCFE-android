package com.diegoasencio.scfe.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.diegoasencio.scfe.R;

public class MenuFotovoltaicaActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_fotovoltaica);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_interconectado_red:
                Intent intent = new Intent(this, FormularioInterconectadoRedActivity.class);
                startActivity(intent);
                break;
            case R.id.image_button_help:
                Toast.makeText(this, "Aquí se despliega un menú de ayuda", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
