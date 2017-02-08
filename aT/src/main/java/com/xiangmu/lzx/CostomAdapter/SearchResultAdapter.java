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
public class SearchResultAdapter extends BaseAdapter {
    private List<XinWen_productinfo.T18908805728Entity> list;
    private Context context;

    public List<XinWen_productinfo.T18908805728Entity> getList() {
        return list;
    }

    public SearchResultAdapter(List<XinWen_productinfo.T18908805728Entity> list, Context context) {
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
            view = LayoutInflater.from(context).inflate(R.layout.item_searchresult, null);
            viewHoudle2 = new ViewHoudle2();
            viewHoudle2.result_title = (TextView) view.findViewById(R.id.result_title);
            viewHoudle2.result_digest= (TextView) view.findViewById(R.id.result_digest);
            viewHoudle2.result_ptime = (TextView) view.findViewById(R.id.result_ptime);
            viewHoudle2.result_count= (TextView) view.findViewById(R.id.result_count);
            viewHoudle2.result_articler= (TextView) view.findViewById(R.id.result_articler);
            view.setTag(viewHoudle2);
        } else {
            viewHoudle2 = (ViewHoudle2) view.getTag();
        }
        String title = list.get(i).getName();//专家：<em>中国</em>需要股权分散的B类企业
        String str = title.replace("<em>", "");
        String replace = str.replace("</em>", "");
        viewHoudle2.result_title.setText(replace);
        viewHoudle2.result_digest.setText(list.get(i).getDescription());
        viewHoudle2.result_ptime.setText(list.get(i).getCreateTime().substring(0,11));
        viewHoudle2.result_count.setText("人气:"+list.get(i).getClickcount());
        viewHoudle2.result_articler.setText("留言:"+list.get(i).getArticlers().size());
        return view;
    }
    class ViewHoudle2 {
        TextView result_title,result_digest,result_ptime,result_count,result_articler;
    }
}
