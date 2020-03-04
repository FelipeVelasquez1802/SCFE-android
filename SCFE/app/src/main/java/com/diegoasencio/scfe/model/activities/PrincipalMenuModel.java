package com.diegoasencio.scfe.model.activities;

import com.diegoasencio.scfe.interfaces.activities.PrincipalMenuInterface;

public class PrincipalMenuModel implements PrincipalMenuInterface.Model {

    private PrincipalMenuInterface.Presenter presenter;

    public PrincipalMenuModel(PrincipalMenuInterface.Presenter presenter) {
        this.presenter = presenter;
    }
}
