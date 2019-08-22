package com.diegoasencio.scfe.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.adapters.ResultAdapter;
import com.diegoasencio.scfe.interfaces.Initials;
import com.diegoasencio.scfe.objects.Calculate;
import com.diegoasencio.scfe.objects.Result;
import com.diegoasencio.scfe.tools.Constant;

import java.util.ArrayList;
import java.util.List;

public class CalculateDialog extends DialogFragment implements Initials {

    private ListView listView;
    private ResultAdapter resultAdapter;
    private List<Result> list;
    private Calculate calculate;

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
        TextView number_module = vFooter.findViewById(R.id.textview_number_modules);
        number_module.setText(String.format(getString(R.string.number_string) + "", calculate.getStrings(), calculate.getInversor().getNumero_controladores()));
        TextView area_min = vFooter.findViewById(R.id.textview_area);
        area_min.setText(String.format(getString(R.string.min_area) + "", calculate.getArea()));
        TextView monthly_saving = vFooter.findViewById(R.id.textview_monthly_saving);
        monthly_saving.setText(String.format(getString(R.string.monthly_savings) + "", Math.round(calculate.getAhorroMensual())));
        TextView note = vFooter.findViewById(R.id.textview_note);
        note.setText(getText(R.string.note_normal));
    }

    @Override
    public void initObjects() {
        Result result1 = new Result();
        result1.setArticle(Constant.INVERSOR);
        result1.setCount(calculate.getInversor().getNumero_controladores());
        result1.setPrice(calculate.getInversor().getPrecio());
        Result result2 = new Result();
        result2.setArticle(String.format("Modulo (%d)", (int) calculate.getPanel().getPotencia()));
        result2.setCount(Math.round(calculate.getModulos()));
        result2.setPrice(calculate.getPanel().getPrecio());
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
        calculate = Constant.GSON.fromJson(getArguments().getString(Constant.CALCULATE), Calculate.class);
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
