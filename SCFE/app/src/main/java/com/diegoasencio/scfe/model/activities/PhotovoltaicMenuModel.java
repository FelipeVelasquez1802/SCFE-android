package com.diegoasencio.scfe.model.activities;

import com.diegoasencio.scfe.interfaces.activities.PhotovoltaicMenuInterface;

public class PhotovoltaicMenuModel implements PhotovoltaicMenuInterface.Model {

    private PhotovoltaicMenuInterface.Presenter presenter;

    public PhotovoltaicMenuModel(PhotovoltaicMenuInterface.Presenter presenter) {
        this.presenter = presenter;
    }
}
