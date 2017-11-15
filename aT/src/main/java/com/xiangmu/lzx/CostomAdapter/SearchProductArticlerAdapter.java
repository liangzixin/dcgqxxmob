package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.listener.MyItemLongClickListener;
import com.xiangmu.lzx.utils.DateTime;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import java.util.List;

public class SearchProductArticlerAdapter extends BaseAdapter {


	private List<XinWen_productinfo.ProductArticlerEntity> productArticler_list;

	private MyItemLongClickListener mItemLongClickListener;
	private DateTime dateTime=new DateTime();
	private Context context;
	public List<XinWen_productinfo.ProductArticlerEntity> getToutiao_list() {
		return productArticler_list;
	}

	public void setToutiao_list(List<XinWen_productinfo.ProductArticlerEntity>productArticler_list) {
		this.productArticler_list =productArticler_list;
		notifyDataSetChanged();
	}
	public SearchProductArticlerAdapter(List<XinWen_productinfo.ProductArticlerEntity>productArticler_list , Context context){
		this.productArticler_list=productArticler_list;
		this.context = context;
	}


//	/**
//	 * 设置Item点击监听
//	 * @param listener
//	 */
	/**
	 * 设置Item点击监听
	 */
	public interface MyItemClickListener {
		public void onItemClick(View view, int postion);
	}
	private MyItemClickListener mItemClickListener;
	public void setOnItemClickListener(MyItemClickListener listener){
		this.mItemClickListener = listener;
	}

	public void setOnItemLongClickListener(MyItemLongClickListener listener){
		this.mItemLongClickListener = listener;
	}

	@Override
	public int getCount() {
		return productArticler_list.size();
	}

	@Override
	public Object getItem(int i) {
		return productArticler_list.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {

		ViewHoudle2 viewHoudle2 = null;
		if (view == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_searchcustomer, parent,false);
			viewHoudle2 = new ViewHoudle2();
			viewHoudle2.bt_id = (TextView)view.findViewById(R.id.result_customerid);
		//	viewHoudle2.tv = (TextView)view.findViewById(R.id.result_title);
			viewHoudle2.bt_name= (TextView)view.findViewById(R.id.result_customername);
			viewHoudle2.bt_replace= (TextView)view.findViewById(R.id.result_replace);
			viewHoudle2.bt_del = (TextView)view.findViewById(R.id.result_delete);

			viewHoudle2.bt_registerdate= (TextView)view.findViewById(R.id.result_registerdate);
			viewHoudle2.bt_enddate= (TextView)view.findViewById(R.id.result_endtime);

			view.setTag(viewHoudle2);
		} else {
			viewHoudle2 = (ViewHoudle2) view.getTag();
		}

		viewHoudle2.bt_id .setText(productArticler_list.get(position).getId().toString());
				if(productArticler_list.get(position).getArtreview_content().length()<=10) {
					viewHoudle2.bt_name.setText(productArticler_list.get(position).getArtreview_content());
		}else {
			//		System.out.println(productArticler_list.get(position).getUsername().substring(0,8));
					viewHoudle2.bt_name.setText(productArticler_list.get(position).getArtreview_content().substring(0,10));
		}
if(!productArticler_list.get(position).getArtreview_time().equals(""))		viewHoudle2.bt_registerdate.setText(dateTime.getmd(productArticler_list.get(position).getArtreview_time()));
//		if(!productArticler_list.get(position).getLogindate().equals(""))		viewHoudle2.bt_enddate.setText(dateTime.getmd(productArticler_list.get(position).getLogindate()));


//		viewHoudle2.bt_name.setOnClickListener(new View.OnClickListener(){
//			@Override
//			public void onClick(View arg0) {
//				mItemClickListener.onItemClick(arg0,position);
//			}
//
//		});
//
		viewHoudle2.bt_replace.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				mItemClickListener.onItemClick(arg0,position);
			}

		});
		viewHoudle2.bt_del.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				mItemClickListener.onItemClick(arg0,position);
			}

		});

		return view;
	}
	class ViewHoudle2 {
		TextView   bt_name,bt_registerdate,bt_enddate,bt_id,bt_del,bt_replace;
	}
}
