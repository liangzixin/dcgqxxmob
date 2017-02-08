package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiangmu.lzx.R;

import java.util.List;

/**
 * Created by mintcode on 2015/12/18.
 */
public class SaveAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater m;
    private List<String> list;

    public SaveAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
        m = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            holder = new Holder();
            convertView = m.inflate(R.layout.item_productarticler, null);
            holder.textView = (TextView) convertView.findViewById(R.id.articler_date);
            holder.imageView= (TextView) convertView.findViewById(R.id.articler_content);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }
        holder.textView.setText(list.get(position));
        return convertView;
    }

    class Holder {
        public TextView imageView;
        public TextView textView;
    }
}
