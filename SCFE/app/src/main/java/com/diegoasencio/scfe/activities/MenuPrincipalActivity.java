package com.diegoasencio.scfe.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.diegoasencio.scfe.R;

public class MenuPrincipalActivity extends AppCompatActivity implements View.OnClickListener {

    Button button_fotovoltaica;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_fotovoltaica:
                Intent intent = new Intent(this, MenuFotovoltaicaActivity.class);
                startActivity(intent);
                break;
            case R.id.button_eolico:
                break;
        }
    }
}
