package com.diegoasencio.scfe.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.adapters.ResultAdapter;
import com.diegoasencio.scfe.interfaces.Initials;
import com.diegoasencio.scfe.objects.Inversor;
import com.diegoasencio.scfe.objects.Panel;
import com.diegoasencio.scfe.objects.Result;
import com.diegoasencio.scfe.tools.Constant;

import java.util.ArrayList;
import java.util.List;

public class CalculateDialog extends DialogFragment implements Initials {

    private ListView listView;
    private ResultAdapter resultAdapter;
    private List<Result> list;
    private Inversor inversor;
    private Panel panel;
    private double modulos;

    @Override
    public void initElements() {
        listView.setAdapter(resultAdapter);
        View vHeader = getActivity().getLayoutInflater().inflate(R.layout.adapter_result, null);
        listView.addHeaderView(vHeader);
        View vFooter = getActivity().getLayoutInflater().inflate(R.layout.footer_result, null);
        listView.addFooterView(vFooter);
        TextView result = vFooter.findViewById(R.id.textview_result);
        double total = 0;
        for (int i = 0; i < list.size(); i++) {
            total += list.get(i).getTotal();
        }
        result.setText(total + "");
    }

    @Override
    public void initObjects() {
        Result result1 = new Result();
        result1.setArticle(Constant.INVERSOR);
        result1.setCount(inversor.getNumero_controladores());
        result1.setPrice(inversor.getPrecio());
        Result result2 = new Result();
        result2.setArticle("Modulo (" + panel.getPotencia() + ")");
        result2.setCount(Math.round(modulos));
        result2.setPrice(panel.getPrecio());
        list = new ArrayList();
        list.add(result1);
        list.add(result2);
        resultAdapter = new ResultAdapter(getActivity(), list);
    }

    public interface AlertDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }

    private AlertDialogListener listener;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View view = getActivity().getLayoutInflater().inflate(R.layout.dialog_calculate, null);
        builder.setView(view).setNegativeButton(getString(R.string.back), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onDialogNegativeClick(CalculateDialog.this);
            }
        });
        setCancelable(false);
        listView = view.findViewById(R.id.list_view);
        inversor = Constant.GSON.fromJson(getArguments().getString(Constant.INVERSOR), Inversor.class);
        panel = Constant.GSON.fromJson(getArguments().getString(Constant.PANEL), Panel.class);
        modulos = getArguments().getDouble(Constant.MODULOS);
        initObjects();
        initElements();
        return builder.create();
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (CalculateDialog.AlertDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
}
