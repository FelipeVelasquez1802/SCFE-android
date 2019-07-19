package com.diegoasencio.scfe.activities;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.dialogs.HelpDialog;

public class MenuPrincipalActivity extends AppCompatActivity implements View.OnClickListener, HelpDialog.AlertDialogListener {

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
            case R.id.image_button_help:
                //Toast.makeText(this, "Aquí se despliega un menú de ayuda", Toast.LENGTH_SHORT).show();
                showDialog();
                break;
        }
    }

    private void showDialog() {
        DialogFragment dialogFragment = new HelpDialog();
        dialogFragment.show(getSupportFragmentManager(), "Help");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
