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

public class SmsAdapter extends BaseAdapter {


	private List<XinWen_productinfo.ShortmessageEntity> sms_list;

	private MyItemLongClickListener mItemLongClickListener;
	private DateTime dateTime=new DateTime();
	private Context context;
	public List<XinWen_productinfo.ShortmessageEntity> getToutiao_list() {
		return sms_list;
	}

	public void setToutiao_list(List<XinWen_productinfo.ShortmessageEntity>sms_list) {
		this.sms_list =sms_list;
		notifyDataSetChanged();
	}
	public SmsAdapter(List<XinWen_productinfo.ShortmessageEntity>sms_list , Context context){
		this.sms_list=sms_list;
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
		return sms_list.size();
	}

	@Override
	public Object getItem(int i) {
		return sms_list.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(final int position, View view, ViewGroup parent) {

		ViewHoudle2 viewHoudle2 = null;
		if (view == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sms, parent,false);
			viewHoudle2 = new ViewHoudle2();
			viewHoudle2.bt_id = (TextView)view.findViewById(R.id.bt_id);
			viewHoudle2.bt_mobile= (TextView)view.findViewById(R.id.bt_mobile);
			viewHoudle2.bt_captcha= (TextView)view.findViewById(R.id.bt_captcha);
			viewHoudle2.bt_sendtime= (TextView)view.findViewById(R.id.bt_sendtime);
			viewHoudle2.bt_smstype= (TextView)view.findViewById(R.id.bt_smstype);
			viewHoudle2.bt_del = (TextView)view.findViewById(R.id.bt_delete);
			viewHoudle2.bt_replace= (TextView)view.findViewById(R.id.bt_replace);

			view.setTag(viewHoudle2);
		} else {
			viewHoudle2 = (ViewHoudle2) view.getTag();
		}

		viewHoudle2.bt_id .setText(sms_list.get(position).getId().toString());
		viewHoudle2.bt_mobile.setText(sms_list.get(position).getMobile());
		viewHoudle2.bt_captcha.setText(sms_list.get(position).getCaptcha());
		
if(!sms_list.get(position).getSendtime().equals(""))		viewHoudle2.bt_sendtime.setText(dateTime.getmd(sms_list.get(position).getSendtime()));

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
		TextView   bt_mobile,bt_captcha,bt_sendtime,bt_id,bt_del,bt_replace,bt_smstype;
	}
}
