package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import java.util.List;

/**
 * Created by Administrator on 2015/11/16.
 */
//public class SearchEditResultAdapter extends BaseAdapter {
    public class SearchEditResultAdapter extends RecyclerView.Adapter<SearchEditResultAdapter.ViewHolder2> {
    private List<XinWen_productinfo.T18908805728Entity> list;
    private Context context;
    private LayoutInflater mInflater;

    private OnItmeClickListener mlistener;
    public  interface OnItmeClickListener{
        void onItemClicked(int position);

    }
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
    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder2 holder, int position) {

    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public int getItemCount() {
        return 0;
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
  //  class ViewHoudle2 {
  public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView result_title;
      private TextView result_replace;
      private TextView result_delete;
      private int position;
      public ViewHolder2(View itemView, int position) {
          super(itemView);
          this.position = position;
          result_title = (TextView) itemView.findViewById(R.id.result_title);
          result_replace = (TextView) itemView.findViewById(R.id.result_replace);
          result_delete= (TextView) itemView.findViewById(R.id.result_delete);
          result_title.setOnClickListener(this);
          result_replace.setOnClickListener(this);
          result_delete.setOnClickListener(this);
      }

      @Override
      public void onClick(View view) {

          mlistener.onItemClicked(position);
      }

    }
}
