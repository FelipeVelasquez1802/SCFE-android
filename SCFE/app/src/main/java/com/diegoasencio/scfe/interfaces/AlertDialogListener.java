package com.diegoasencio.scfe.interfaces;

import android.support.v4.app.DialogFragment;

public interface AlertDialogListener {
    void onDialogPositiveClick(DialogFragment dialog);

    void onDialogNegativeClick(DialogFragment dialog);
}
