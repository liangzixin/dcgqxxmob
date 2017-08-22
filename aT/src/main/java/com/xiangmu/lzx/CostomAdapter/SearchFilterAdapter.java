package com.xiangmu.lzx.CostomAdapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.holder.FilterViewHolder;
import com.xiangmu.lzx.listener.MyItemClickListener;
import com.xiangmu.lzx.listener.MyItemLongClickListener;
import com.xiangmu.lzx.utils.DateTime;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import java.util.List;

public class SearchFilterAdapter extends Adapter<FilterViewHolder> {

	private List<XinWen_productinfo.FilterEntity> mData;
	private MyItemClickListener mItemClickListener;
	private MyItemLongClickListener mItemLongClickListener;
	private DateTime dateTime;

	public SearchFilterAdapter(List<XinWen_productinfo.FilterEntity> data){
		this.mData = data;
	}
	
	@Override
	public int getItemCount() {
		return mData.size();
	}

	
	@Override
	public void onBindViewHolder(FilterViewHolder holder, int position) {
		XinWen_productinfo.FilterEntity bean = mData.get(position);
		dateTime=new DateTime();
		String title =bean.getFilters();//专家：<em>中国</em>需要股权分散的B类企业
		String str = title.replace("<em>", "");
		String replace = str.replace("</em>", "");
		if(replace.length()<=12) {
			holder.tv.setText(bean.getFilters());
		}else {
			holder.tv.setText(bean.getFilters().substring(0,12));
		}
		//holder.bt_date.setText(dateTime.getmd(bean.getCreateTime()));

	}

	@Override
	public FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_searchfilter, parent,false);
		FilterViewHolder vh = new FilterViewHolder(itemView,mItemClickListener,mItemLongClickListener);
		return vh;
	}

	/**
	 * 设置Item点击监听
	 * @param listener
	 */
	public void setOnItemClickListener(MyItemClickListener listener){
		this.mItemClickListener = listener;
	}
	
	public void setOnItemLongClickListener(MyItemLongClickListener listener){
		this.mItemLongClickListener = listener;
	}
}
