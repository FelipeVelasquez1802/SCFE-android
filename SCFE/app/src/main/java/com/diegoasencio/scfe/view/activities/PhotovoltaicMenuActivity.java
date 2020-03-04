package com.diegoasencio.scfe.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.interfaces.activities.PhotovoltaicMenuInterface;
import com.diegoasencio.scfe.presenter.activities.PhotovoltaicMenuPresenter;
import com.diegoasencio.scfe.view.dialogs.HelpDialog;

public class PhotovoltaicMenuActivity extends BaseActivity implements PhotovoltaicMenuInterface.View,
        View.OnClickListener, HelpDialog.AlertDialogListener {

    private PhotovoltaicMenuInterface.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new PhotovoltaicMenuPresenter(this);
    }

    @Override
    public int getLayout() {
        return R.layout.activity_menu_fotovoltaica;
    }

    @Override
    public void onClick(View view) {
        Intent intent = null;
        switch (view.getId()) {
            case R.id.button_networked:
                presenter.goNetworkedActivity();
                break;
            case R.id.button_battery:
                presenter.goBatteryActivity();
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

    @Override
    public void goOtherActivity(Class<?> cls) {
        Intent intent = new Intent(this, cls);
        startActivity(intent);
    }
}
