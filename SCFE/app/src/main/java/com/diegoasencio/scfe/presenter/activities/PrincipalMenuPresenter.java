package com.diegoasencio.scfe.presenter.activities;

import com.diegoasencio.scfe.interfaces.activities.PrincipalMenuInterface;
import com.diegoasencio.scfe.model.activities.PrincipalMenuModel;
import com.diegoasencio.scfe.view.activities.MenuEolicoActivity;
import com.diegoasencio.scfe.view.activities.MenuFotovoltaicaActivity;

public class PrincipalMenuPresenter implements PrincipalMenuInterface.Presenter {

    private PrincipalMenuInterface.View view;
    private PrincipalMenuInterface.Model model;

    public PrincipalMenuPresenter(PrincipalMenuInterface.View view) {
        this.view = view;
        model = new PrincipalMenuModel(this);
    }

    @Override
    public void goPhotoVoltaicActivity() {
        view.goOtherActivity(MenuFotovoltaicaActivity.class);
    }

    @Override
    public void goEolianActivity() {
        view.goOtherActivity(MenuEolicoActivity.class);
    }

    @Override
    public void showDialogHelp() {
        view.showDialog('0');
    }

    @Override
    public void showDialogSetting() {
        view.showDialog('1');
    }
}
