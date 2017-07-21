package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseEditResultAdapter<T> extends RecyclerView.Adapter<BaseEditResultAdapter.BaseViewHolder> {

    private static OnItemClickListener onItemClickListener;

    protected List<T> datas;
    private Context context;
    public BaseEditResultAdapter(List<T> datas) {
        this.datas = datas;
    }
        public BaseEditResultAdapter(List<T> datas, Context context) {
        this.datas = datas;
        this.context = context;
    }
    public static interface OnItemClickListener<T> {
        void onItemClick(View view, int bean);

    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(getLayoutId(),parent,false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder( final BaseEditResultAdapter.BaseViewHolder holder, final int position) {
      final T bean = datas.get(position);
        bindData(holder,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(holder.itemView,position);
                }
            }
        });

    }


    /**
     * 刷新数据
     * @param datas
     */
    public void refresh(List<T> datas){
        this.datas.clear();
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }


    /**
     * 添加数据
     * @param datas
     */
    public void addData(List<T> datas){
        this.datas.addAll(datas);
        notifyDataSetChanged();
    }

    /**
     *  绑定数据
     * @param holder  具体的viewHolder
     * @param position  对应的索引
     */
    protected abstract void bindData(BaseViewHolder holder, int position);



    @Override
    public int getItemCount() {

        return datas==null?0:datas.size();
    }




    /**
     * 封装ViewHolder ,子类可以直接使用
     */
    public class BaseViewHolder extends  RecyclerView.ViewHolder{


        private Map<Integer, View> mViewMap;

        public BaseViewHolder(View itemView) {
            super(itemView);
            mViewMap = new HashMap<>();
        }

        /**
         * 获取设置的view
         * @param id
         * @return
         */
        public View getView(int id) {
            View view = mViewMap.get(id);
            if (view == null) {
                view = itemView.findViewById(id);
                mViewMap.put(id, view);
            }
            return view;
        }
    }

    /**
     * 获取子item
     * @return
     */
    public abstract int getLayoutId();



    /**
     * 设置文本属性
     * @param view
     * @param text
     */
    public void setItemText(View view,String text){
        if(view instanceof TextView){
            ((TextView) view).setText(text);
        }
    }
}
///**
// * Created by Administrator on 2015/11/16.
// */
////public class SearchEditResultAdapter extends BaseAdapter {
//    public class SearchEditResultAdapter extends RecyclerView.Adapter<SearchEditResultAdapter.ViewHolder2> {
//    private List<XinWen_productinfo.T18908805728Entity> list;
//    private Context context;
//    private LayoutInflater mInflater;
//
//    private OnItmeClickListener mlistener;
//    public  interface OnItmeClickListener{
//        void onItemClicked(int position);
//
//    }
//    public List<XinWen_productinfo.T18908805728Entity> getList() {
//        return list;
//    }
//
//    public SearchEditResultAdapter(List<XinWen_productinfo.T18908805728Entity> list, Context context) {
//        this.list = list;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//    @Override
//    public Object getItem(int i) {
//        return list.get(i);
//    }
//
//    @Override
//    public ViewHolder2 onCreateViewHolder(ViewGroup parent, int viewType) {
//        return null;
//    }
//
//    @Override
//    public void onBindViewHolder(ViewHolder2 holder, int position) {
//
//    }
//
//    @Override
//    public long getItemId(int i) {
//        return i;
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//    @Override
//    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHoudle2 viewHoudle2 = null;
//        if (view == null) {
//            view = LayoutInflater.from(context).inflate(R.layout.item_searcheditresult, null);
//            viewHoudle2 = new ViewHoudle2();
//            viewHoudle2.result_title = (TextView) view.findViewById(R.id.result_title);
//
//            view.setTag(viewHoudle2);
//        } else {
//            viewHoudle2 = (ViewHoudle2) view.getTag();
//        }
//        String title ="";
//        if(list.get(i).getName().length()>14) {
//           title = list.get(i).getName().substring(0, 14);//专家：<em>中国</em>需要股权分散的B类企业
//        }else{
//            title = list.get(i).getName();
//        }
//        String str = title.replace("<em>", "");
//        String replace = str.replace("</em>", "");
//        viewHoudle2.result_title.setText(replace);
//
//        return view;
//    }
//  //  class ViewHoudle2 {
//  public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
//        private TextView result_title;
//      private TextView result_replace;
//      private TextView result_delete;
//      private int position;
//      public ViewHolder2(View itemView, int position) {
//          super(itemView);
//          this.position = position;
//          result_title = (TextView) itemView.findViewById(R.id.result_title);
//          result_replace = (TextView) itemView.findViewById(R.id.result_replace);
//          result_delete= (TextView) itemView.findViewById(R.id.result_delete);
//          result_title.setOnClickListener(this);
//          result_replace.setOnClickListener(this);
//          result_delete.setOnClickListener(this);
//      }
//
//      @Override
//      public void onClick(View view) {
//
//          mlistener.onItemClicked(position);
//      }
//
//    }
//}
