package com.diegoasencio.scfe.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.dialogs.HelpDialog;
import com.diegoasencio.scfe.dialogs.SettingsDialog;
import com.diegoasencio.scfe.interfaces.Initials;
import com.diegoasencio.scfe.tools.Constant;

public class MenuPrincipalActivity extends AppCompatActivity implements Initials, View.OnClickListener, HelpDialog.AlertDialogListener, SettingsDialog.AlertDialogListener {

    private SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);
        initElements();
        initObjects();
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
                showDialog('0');
                break;
        }
    }

    private void showDialog(char c) {
        DialogFragment dialogFragment = null;
        switch (c) {
            case '0':
                dialogFragment = new HelpDialog();
                break;
            case '1':
                dialogFragment = new SettingsDialog();
                break;
        }
        dialogFragment.show(getSupportFragmentManager(), "Help");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {

    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_tools, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                showDialog('1');
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void initElements() {
        preferences = getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String domain_string = preferences.getString("domain", null);
        if (domain_string != null) {
            Constant.URL_DOMAIN = domain_string;
        }
    }

    @Override
    public void initObjects() {

    }
}
