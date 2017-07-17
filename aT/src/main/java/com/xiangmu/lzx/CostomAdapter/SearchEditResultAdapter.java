package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiangmu.lzx.R;

import java.util.List;

import github.nisrulz.recyclerviewhelper.RVHAdapter;

public abstract class SearchEditResultAdapter<T> extends RecyclerView.Adapter<SearchEditResultAdapter.BaseRecycleHolder> implements RVHAdapter {

    protected Context mContext;
    protected List<T> mList;
    protected int[] layoutIds;
    protected LayoutInflater mLInflater;

    private SparseArray<View> mConvertViews = new SparseArray<>();

    public SearchEditResultAdapter(Context context, List<T> list, int... layoutIds) {
        this.mContext = context;
        this.mList = list;
        this.layoutIds = layoutIds;
        this.mLInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BaseRecycleHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType < 0 || viewType > layoutIds.length) {
            throw new ArrayIndexOutOfBoundsException("layoutIndex");
        }
        if (layoutIds.length == 0) {
            throw new IllegalArgumentException("not layoutId");
        }
        int layoutId = layoutIds[viewType];
        View view = mConvertViews.get(layoutId);
        if (view == null) {
            view = mLInflater.inflate(layoutId, parent, false);
        }
        BaseRecycleHolder viewHolder = (BaseRecycleHolder) view.getTag();
        if (viewHolder == null || viewHolder.getLayoutId() != layoutId) {
            viewHolder = new BaseRecycleHolder(mContext, layoutId, view);
            return viewHolder;
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BaseRecycleHolder holder, int position) {
        final T item = mList.get(position);
        onBindData(holder, position, item);
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public int getItemViewType(int position) {
        return getLayoutIndex(position, mList.get(position));
    }

    /**
     * 指定item布局样式在layoutIds的索引。默认为第一个
     */
    public int getLayoutIndex(int position, T item) {
        return 0;
    }

    protected abstract void onBindData(BaseRecycleHolder viewHolder, int position, T item);

  // @Override
    public boolean addAll(List<T> list) {
        boolean result = mList.addAll(list);
        notifyDataSetChanged();
        return result;
    }

    //@Override
    public boolean addAll(int position, List list) {
        boolean result = mList.addAll(position, list);
        notifyDataSetChanged();
        return result;
    }

  //  @Override
    public void add(T data) {
        mList.add(data);
        notifyDataSetChanged();
    }

   // @Override
    public void add(int position, T data) {
        mList.add(position, data);
        notifyDataSetChanged();
    }

   // @Override
    public void clear() {
        mList.clear();
        notifyDataSetChanged();
    }

  //  @Override
    public boolean contains(T data) {
        return mList.contains(data);
    }

   // @Override
    public T getData(int index) {
        return mList.get(index);
    }

  //  @Override
    public void modify(T oldData, T newData) {
        modify(mList.indexOf(oldData), newData);
    }

   // @Override
    public void modify(int index, T newData) {
        mList.set(index, newData);
        notifyDataSetChanged();
    }

  //  @Override
    public boolean remove(T data) {
        boolean result = mList.remove(data);
        notifyDataSetChanged();
        return result;
    }

  //  @Override
    public void remove(int index) {
        mList.remove(index);
        notifyDataSetChanged();
    }

    class BaseRecycleHolder extends RecyclerView.ViewHolder{
        //  public class ViewHolder2 extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView result_title;
        private TextView result_replace;
        private TextView result_delete;
        private int position;

        public BaseRecycleHolder(Context context, int position,View itemView) {
            super(itemView);
            this.position = position;
            result_title = (TextView) itemView.findViewById(R.id.result_title);
            result_replace = (TextView) itemView.findViewById(R.id.result_replace);
            result_delete = (TextView) itemView.findViewById(R.id.result_delete);

        }
        public int getLayoutId() {
            return this.position;
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
