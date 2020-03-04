package com.diegoasencio.scfe.view.dialogs;

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
import com.diegoasencio.scfe.view.adapters.ArticleAdapter;
import com.diegoasencio.scfe.interfaces.Initials;
import com.diegoasencio.scfe.objects.Article;
import com.diegoasencio.scfe.objects.Autogenerador;
import com.diegoasencio.scfe.tools.Constant;

import java.util.ArrayList;
import java.util.List;

public class ArticleDialog extends DialogFragment implements Initials {

    private ArticleDialog.AlertDialogListener listener;

    private ListView listView;
    private ArticleAdapter adapter;
    private List<Article> list;
    private View view;
    private TextView total;

    private Autogenerador autogenerador;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        view = getActivity().getLayoutInflater().inflate(R.layout.dialog_article, null);
        setCancelable(false);
        builder.setView(view).setNegativeButton(getString(R.string.back), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                listener.onDialogNegativeClick(ArticleDialog.this);
            }
        });
        initObjects();
        initElements();
        return builder.create();
    }

    @Override
    public void initElements() {
        listView = view.findViewById(R.id.list_view);
        adapter = new ArticleAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        total = view.findViewById(R.id.textview_result);
        autogenerador = Constant.GSON.fromJson(getArguments().getString(Constant.ARTICLE), Autogenerador.class);
        double total_price = 0;
        for (Article article : autogenerador.getArticulos()) {
            list.add(article);
            total_price += article.getPrecio();
        }
        adapter.notifyDataSetChanged();
        total.setText(Constant.FORMAT_MONEY.format(total_price));
    }

    @Override
    public void initObjects() {
        list = new ArrayList<>();

    }

    public interface AlertDialogListener {
        void onDialogPositiveClick(DialogFragment dialog);

        void onDialogNegativeClick(DialogFragment dialog);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ArticleDialog.AlertDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }
}
