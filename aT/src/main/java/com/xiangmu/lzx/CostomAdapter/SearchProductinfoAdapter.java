package com.xiangmu.lzx.CostomAdapter;

import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.holder.SearchViewHolder;
import com.xiangmu.lzx.listener.MyItemClickListener;
import com.xiangmu.lzx.listener.MyItemLongClickListener;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import java.util.List;

public class SearchProductinfoAdapter extends Adapter<SearchViewHolder> {

	private List<XinWen_productinfo.T18908805728Entity> mData;
	private MyItemClickListener mItemClickListener;
	private MyItemLongClickListener mItemLongClickListener;
	
	public SearchProductinfoAdapter(List<XinWen_productinfo.T18908805728Entity> data){
		this.mData = data;
	}
	
	@Override
	public int getItemCount() {
		return mData.size();
	}

	
	@Override
	public void onBindViewHolder(SearchViewHolder holder, int position) {
		XinWen_productinfo.T18908805728Entity bean = mData.get(position);
		String title =bean.getName();//专家：<em>中国</em>需要股权分散的B类企业
		String str = title.replace("<em>", "");
		String replace = str.replace("</em>", "");
		if(replace.length()<=14) {
			holder.tv.setText(bean.getName());
		}else {
			holder.tv.setText(bean.getName().substring(0,14));
		}
	//	holder.bt_replace.setText("修改");
	//	holder.bt_del.setText("删除");
	}

	@Override
	public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_searcheditresult, parent,false);
		SearchViewHolder vh = new SearchViewHolder(itemView,mItemClickListener,mItemLongClickListener);
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
