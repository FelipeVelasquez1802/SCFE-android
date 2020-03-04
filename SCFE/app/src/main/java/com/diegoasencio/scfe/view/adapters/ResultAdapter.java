package com.diegoasencio.scfe.view.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.diegoasencio.scfe.R;
import com.diegoasencio.scfe.objects.Result;
import com.diegoasencio.scfe.tools.Constant;

import java.util.List;

public class ResultAdapter extends ArrayAdapter<Result> {

    public ResultAdapter(Context context, List<Result> list) {
        super(context, 0, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.adapter_result, parent, false);
        }
        Result result = getItem(position);
        TextView article = convertView.findViewById(R.id.textview_article);
        article.setText(result.getArticle());

        TextView count = convertView.findViewById(R.id.textview_count);
        count.setText(Constant.FORMAT_COUNT.format(result.getCount()));

        TextView price = convertView.findViewById(R.id.textview_price);
        price.setText(Constant.FORMAT_MONEY.format(result.getPrice()));

        TextView total = convertView.findViewById(R.id.textview_total);
        total.setText(Constant.FORMAT_MONEY.format(result.getTotal()));
        return convertView;
    }
}
