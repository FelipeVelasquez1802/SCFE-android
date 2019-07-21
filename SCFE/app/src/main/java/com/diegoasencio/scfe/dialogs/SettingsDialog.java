package com.diegoasencio.scfe.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.EditText;

import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.interfaces.Initials;
import com.diegoasencio.scfe.tools.Constant;

public class SettingsDialog extends DialogFragment implements Initials {


    @Override
    public void initElements() {
        preferences = getActivity().getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE);
        String domain_string = preferences.getString("domain", null);
        if (domain_string != null) {
            Constant.URL_DOMAIN = domain_string;
        }
    }

    @Override
    public void initObjects() {
    }

    public interface AlertDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    private SharedPreferences preferences;

    private AlertDialogListener listener;


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        initObjects();
        initElements();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_settings, null);
        final EditText domain = view.findViewById(R.id.edittext_domain);
        domain.setText(Constant.URL_DOMAIN);
        builder.setView(view)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("domain", domain.getText().toString());
                        editor.commit();
                        listener.onDialogPositiveClick(SettingsDialog.this);
                    }
                })
                .setNegativeButton(R.string.back, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listener.onDialogNegativeClick(SettingsDialog.this);
                    }
                });
        setCancelable(false);
        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (AlertDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

}
