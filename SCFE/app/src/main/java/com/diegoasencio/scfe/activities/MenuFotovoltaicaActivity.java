package com.diegoasencio.scfe.activities;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.dialogs.HelpDialog;

public class MenuFotovoltaicaActivity extends AppCompatActivity implements View.OnClickListener, HelpDialog.AlertDialogListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_fotovoltaica);
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.button_interconectado_red:
                intent = new Intent(this, FormularioInterconectadoRedActivity.class);
                startActivity(intent);
                break;
            case R.id.button_baterias:
                intent = new Intent(this, FormularioBateriaActivity.class);
                startActivity(intent);
                break;
            case R.id.image_button_help:
                showDialog();
                break;
        }
    }

    private void showDialog() {
        DialogFragment dialogFragment = new HelpDialog();
        Bundle args = new Bundle();
        args.putBoolean("state", true);
        dialogFragment.setArguments(args);
        dialogFragment.show(getSupportFragmentManager(), "Help");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }
}
