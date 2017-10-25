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

public class SearchCustomerAdapter extends BaseAdapter {


	private List<XinWen_productinfo.CustomerEntity> customer_list;

	private MyItemLongClickListener mItemLongClickListener;
	private DateTime dateTime=new DateTime();
	private Context context;
	public List<XinWen_productinfo.CustomerEntity> getToutiao_list() {
		return customer_list;
	}

	public void setToutiao_list(List<XinWen_productinfo.CustomerEntity>customer_list) {
		this.customer_list =customer_list;
		notifyDataSetChanged();
	}
	public SearchCustomerAdapter(List<XinWen_productinfo.CustomerEntity>customer_list , Context context){
		this.customer_list=customer_list;
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
		return customer_list.size();
	}

	@Override
	public Object getItem(int i) {
		return customer_list.get(i);
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
			viewHoudle2.bt_del = (TextView)view.findViewById(R.id.result_delete);
			viewHoudle2.bt_registerdate= (TextView)view.findViewById(R.id.result_registerdate);
			viewHoudle2.bt_enddate= (TextView)view.findViewById(R.id.result_endtime);

			view.setTag(viewHoudle2);
		} else {
			viewHoudle2 = (ViewHoudle2) view.getTag();
		}

		viewHoudle2.bt_id .setText(customer_list.get(position).getId().toString());
				if(customer_list.get(position).getUsername().length()<=8) {
					viewHoudle2.bt_name.setText(customer_list.get(position).getUsername());
		}else {
			//		System.out.println(customer_list.get(position).getUsername().substring(0,8));
					viewHoudle2.bt_name.setText(customer_list.get(position).getUsername().substring(0,8));
		}
		viewHoudle2.bt_registerdate.setText(dateTime.getmd(customer_list.get(position).getRegisterdate()));
		viewHoudle2.bt_enddate.setText(dateTime.getmd(customer_list.get(position).getEndtime()));


//		viewHoudle2.bt_name.setOnClickListener(new View.OnClickListener(){
//			@Override
//			public void onClick(View arg0) {
//				mItemClickListener.onItemClick(arg0,position);
//			}
//
//		});
//
//		viewHoudle2.bt_replace.setOnClickListener(new View.OnClickListener(){
//			@Override
//			public void onClick(View arg0) {
//				mItemClickListener.onItemClick(arg0,position);
//			}
//
//		});
//		viewHoudle2.bt_del.setOnClickListener(new View.OnClickListener(){
//			@Override
//			public void onClick(View arg0) {
//				mItemClickListener.onItemClick(arg0,position);
//			}
//
//		});

		return view;
	}
	class ViewHoudle2 {
		TextView   bt_name,bt_registerdate,bt_enddate,bt_id,bt_del,bt_replace;
	}
}
