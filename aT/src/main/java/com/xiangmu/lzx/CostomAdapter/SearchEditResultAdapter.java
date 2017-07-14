package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
public class SearchEditResultAdapter extends BaseAdapter {
    private List<XinWen_productinfo.T18908805728Entity> list;
    private Context context;

    public List<XinWen_productinfo.T18908805728Entity> getList() {
        return list;
    }

    public SearchEditResultAdapter(List<XinWen_productinfo.T18908805728Entity> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoudle2 viewHoudle2 = null;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_searcheditresult, null);
            viewHoudle2 = new ViewHoudle2();
            viewHoudle2.result_title = (TextView) view.findViewById(R.id.result_title);

            view.setTag(viewHoudle2);
        } else {
            viewHoudle2 = (ViewHoudle2) view.getTag();
        }
        String title ="";
        if(list.get(i).getName().length()>14) {
           title = list.get(i).getName().substring(0, 14);//专家：<em>中国</em>需要股权分散的B类企业
        }else{
            title = list.get(i).getName();
        }
        String str = title.replace("<em>", "");
        String replace = str.replace("</em>", "");
        viewHoudle2.result_title.setText(replace);

        return view;
    }
    class ViewHoudle2 {
        TextView result_title;
    }
}
