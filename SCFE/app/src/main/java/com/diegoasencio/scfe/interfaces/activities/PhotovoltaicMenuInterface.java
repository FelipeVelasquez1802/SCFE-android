package com.diegoasencio.scfe.interfaces.activities;

public interface PhotovoltaicMenuInterface {
    interface View {
        void goOtherActivity(Class<?> cls);
    }

    interface Presenter {
        void goNetworkedActivity();

        void goBatteryActivity();
    }

    interface Model {

    }
}
