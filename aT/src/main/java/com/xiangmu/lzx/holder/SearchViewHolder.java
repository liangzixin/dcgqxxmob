package com.xiangmu.lzx.holder;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.TextView;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.listener.MyItemClickListener;
import com.xiangmu.lzx.listener.MyItemLongClickListener;

public class SearchViewHolder extends ViewHolder implements OnClickListener,OnLongClickListener{

	//public ImageView iv;
	public TextView tv;
	public TextView bt_replace;
	public TextView bt_del;
	public TextView bt_date;
	public TextView bt_agree;
	private MyItemClickListener mListener;
	private MyItemLongClickListener mLongClickListener;
	
	public SearchViewHolder(View arg0,MyItemClickListener listener,MyItemLongClickListener longClickListener) {
		super(arg0);
	//	iv = (ImageView)arg0.findViewById(R.id.item_iv);
		tv = (TextView)arg0.findViewById(R.id.result_title);
		bt_replace = (TextView)arg0.findViewById(R.id.result_replace);
		bt_del = (TextView)arg0.findViewById(R.id.result_delete);
		bt_date = (TextView)arg0.findViewById(R.id.result_date);
		bt_agree= (TextView)arg0.findViewById(R.id.result_agree);
		this.mListener = listener;
		this.mLongClickListener = longClickListener;
//		arg0.setOnClickListener(this);
//		arg0.setOnLongClickListener(this);
		tv.setOnClickListener(this);
		bt_replace.setOnClickListener(this);
		bt_del.setOnClickListener(this);
		bt_agree.setOnClickListener(this);
	}

	/**
	 * 点击监听
	 */
	@Override
	public void onClick(View v) {

		if(mListener != null) {

			mListener.onItemClick(v, getPosition());
		}
	}

	/**
	 * 长按监听
	 */
	@Override
	public boolean onLongClick(View arg0) {
		if(mLongClickListener != null){
			mLongClickListener.onItemLongClick(arg0, getPosition());
		}
		return true;
	}

}
