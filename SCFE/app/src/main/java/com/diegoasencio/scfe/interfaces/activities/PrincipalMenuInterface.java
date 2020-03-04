package com.diegoasencio.scfe.interfaces.activities;

public interface PrincipalMenuInterface {
    interface View {
        void goOtherActivity(Class<?> cls);

        void showDialog(char c);
    }

    interface Presenter {
        void goPhotoVoltaicActivity();

        void goEolianActivity();

        void showDialogHelp();

        void showDialogSetting();
    }

    interface Model {

    }
}
