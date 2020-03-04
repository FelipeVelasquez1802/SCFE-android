package com.diegoasencio.scfe.presenter.activities;

import com.diegoasencio.scfe.interfaces.activities.PhotovoltaicMenuInterface;
import com.diegoasencio.scfe.model.activities.PhotovoltaicMenuModel;
import com.diegoasencio.scfe.view.activities.BatteryFormActivity;
import com.diegoasencio.scfe.view.activities.NetworkedFormActivity;

public class PhotovoltaicMenuPresenter implements PhotovoltaicMenuInterface.Presenter {

    private PhotovoltaicMenuInterface.View view;
    private PhotovoltaicMenuInterface.Model model;

    public PhotovoltaicMenuPresenter(PhotovoltaicMenuInterface.View view) {
        this.view = view;
        model = new PhotovoltaicMenuModel(this);
    }

    @Override
    public void goNetworkedActivity() {
        view.goOtherActivity(NetworkedFormActivity.class);
    }

    @Override
    public void goBatteryActivity() {
        view.goOtherActivity(BatteryFormActivity.class);
    }
}
