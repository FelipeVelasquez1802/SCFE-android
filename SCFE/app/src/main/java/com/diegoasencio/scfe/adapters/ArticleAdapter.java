package com.diegoasencio.scfe.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.objects.Article;
import com.diegoasencio.scfe.objects.Autogenerador;

import java.util.List;

public class ArticleAdapter extends ArrayAdapter<Article> {
    public ArticleAdapter(@NonNull Context context, List<Article> list) {
        super(context, 0, list);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_article, parent, false);
        }
        Article article = getItem(position);
        TextView intrument = convertView.findViewById(R.id.textView_instrument);
        intrument.setText(article.getInstrumento());

        TextView characteristic = convertView.findViewById(R.id.textView_characteristic);
        characteristic.setText(article.getCaracteristica());

        TextView count = convertView.findViewById(R.id.textView_count);
        count.setText(article.getCantidadFormat());

        TextView price = convertView.findViewById(R.id.textView_price);
        price.setText(article.getPrecioFormat());

        return convertView;
    }
}
