package com.diegoasencio.scfe.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.interfaces.Initials;
import com.diegoasencio.scfe.interfaces.activities.PrincipalMenuInterface;
import com.diegoasencio.scfe.presenter.activities.PrincipalMenuPresenter;
import com.diegoasencio.scfe.tools.Constant;
import com.diegoasencio.scfe.view.dialogs.HelpDialog;
import com.diegoasencio.scfe.view.dialogs.SettingsDialog;

public class PrincipalMenuActivity extends BaseActivity implements PrincipalMenuInterface.View,
        Initials, View.OnClickListener, HelpDialog.AlertDialogListener,
        SettingsDialog.AlertDialogListener {

    private PrincipalMenuInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initElements();
        initObjects();
        presenter = new PrincipalMenuPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_menu_principal;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_fotovoltaica:
                presenter.goPhotoVoltaicActivity();
                break;
            case R.id.button_eolico:
                presenter.goEolianActivity();
                break;
            case R.id.image_button_help:
                presenter.showDialogHelp();
                break;
        }
    }

    @Override
    public void showDialog(char c) {
        DialogFragment dialogFragment = new SettingsDialog();
        Bundle args = new Bundle();
        if (c == '0') {
            dialogFragment = new HelpDialog();
            args.putBoolean("state", false);
        }
        dialogFragment.setArguments(args);
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
        if (item.getItemId() == R.id.menu_settings) {
            presenter.showDialogSetting();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initElements() {
        String domain_string = preferences.getString("domain", null);
        if (domain_string != null) {
            Constant.URL_DOMAIN = domain_string;
        }
    }

    @Override
    public void initObjects() {

    }

    @Override
    public void goOtherActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
