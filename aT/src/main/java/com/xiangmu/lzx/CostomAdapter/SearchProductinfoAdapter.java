package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.listener.MyItemClickListener;
import com.xiangmu.lzx.listener.MyItemLongClickListener;
import com.xiangmu.lzx.utils.DateTime;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import java.util.List;

//public class SearchProductinfoAdapter extends Adapter<SearchViewHolder> {
public class SearchProductinfoAdapter extends BaseAdapter {


	private List<XinWen_productinfo.T18908805728Entity> toutiao_list;
	private MyItemClickListener mItemClickListener;
	private MyItemLongClickListener mItemLongClickListener;
	private DateTime dateTime=new DateTime();
	private Context context;
	public List<XinWen_productinfo.T18908805728Entity> getToutiao_list() {
		return toutiao_list;
	}

	public void setToutiao_list(List<XinWen_productinfo.T18908805728Entity> toutiao_list) {
		this.toutiao_list = toutiao_list;
		notifyDataSetChanged();
	}
	public SearchProductinfoAdapter(List<XinWen_productinfo.T18908805728Entity> toutiao_list , Context context){
		this.toutiao_list= toutiao_list;
		this.context = context;
	}
//
//	@Override
//	public int getItemCount() {
//		return mData.size();
//	}

	
//	@Override
//	public void onBindViewHolder(SearchViewHolder holder, int position) {
//		XinWen_productinfo.T18908805728Entity bean = mData.get(position);
//		dateTime=new DateTime();
//		String title =bean.getName();//专家：<em>中国</em>需要股权分散的B类企业
//		String str = title.replace("<em>", "");
//		String replace = str.replace("</em>", "");
//		if(replace.length()<=10) {
//			holder.tv.setText(bean.getName());
//		}else {
//			holder.tv.setText(bean.getName().substring(0,10));
//		}
//		holder.bt_date.setText(dateTime.getmd(bean.getCreateTime()));
//		if(bean.getAgree().equals("1")) {
//			holder.bt_agree.setText("已审");
//		}else {
//			holder.bt_agree.setText("未审");
//		}
//	}

//	@Override
//	public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_searcheditresult, parent,false);
//		SearchViewHolder vh = new SearchViewHolder(itemView,mItemClickListener,mItemLongClickListener);
//		return vh;
//	}

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

	@Override
	public int getCount() {
		return toutiao_list.size();
	}

	@Override
	public Object getItem(int i) {
		return toutiao_list.get(i);
	}

	@Override
	public long getItemId(int i) {
		return i;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		//		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_searcheditresult, parent,false);
	//	SearchViewHolder vh = new SearchViewHolder(itemView,mItemClickListener,mItemLongClickListener);
		ViewHoudle2 viewHoudle2 = null;
		if (view == null) {
			view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_searcheditresult, parent,false);
			viewHoudle2 = new ViewHoudle2();
			viewHoudle2.bt_productinfoid = (TextView)view.findViewById(R.id.result_productinfoid);
			viewHoudle2.tv = (TextView)view.findViewById(R.id.result_title);
			viewHoudle2.bt_replace = (TextView)view.findViewById(R.id.result_replace);
			viewHoudle2.bt_del = (TextView)view.findViewById(R.id.result_delete);
			viewHoudle2.bt_date = (TextView)view.findViewById(R.id.result_date);
			viewHoudle2.bt_agree= (TextView)view.findViewById(R.id.result_agree);
//			this.mListener = listener;
//			this.mLongClickListener = longClickListener;
////		arg0.setOnClickListener(this);
////		arg0.setOnLongClickListener(this);
//			tv.setOnClickListener(this);
//			bt_replace.setOnClickListener(this);
//			bt_del.setOnClickListener(this);
//			bt_agree.setOnClickListener(this);
//			viewHoudle2.result_title = (TextView) view.findViewById(R.id.result_title);
//			viewHoudle2.result_digest= (TextView) view.findViewById(R.id.result_digest);
//			viewHoudle2.result_ptime = (TextView) view.findViewById(R.id.result_ptime);
//			viewHoudle2.result_count= (TextView) view.findViewById(R.id.result_count);
//			viewHoudle2.result_articler= (TextView) view.findViewById(R.id.result_articler);
			view.setTag(viewHoudle2);
		} else {
			viewHoudle2 = (ViewHoudle2) view.getTag();
		}
//
//		String title =toutiao_list.get(i).getName();//专家：<em>中国</em>需要股权分散的B类企业
//		String str = title.replace("<em>", "");
//		String replace = str.replace("</em>", "");
		viewHoudle2.bt_productinfoid .setText(toutiao_list.get(position).getId().toString());
				if(toutiao_list.get(position).getName().length()<=8) {
					viewHoudle2.tv.setText(toutiao_list.get(position).getName());
		}else {

					viewHoudle2.tv.setText(toutiao_list.get(position).getName().substring(0,8));
		}
		viewHoudle2.bt_date.setText(dateTime.getmd(toutiao_list.get(position).getCreateTime()));
		if(toutiao_list.get(position).getAgree().equals("1")) {
			viewHoudle2.bt_agree.setText("已审");
		}else {
			viewHoudle2.bt_agree.setText("未审");
		}
//		viewHoudle2.result_digest.setText(list.get(i).getDescription());
//		viewHoudle2.result_ptime.setText(list.get(i).getCreateTime().substring(0,11));
//		viewHoudle2.result_count.setText("人气:"+list.get(i).getClickcount());
//		viewHoudle2.result_articler.setText("留言:"+list.get(i).getArticlers().size());
		return view;
	}
	class ViewHoudle2 {
		TextView  tv, bt_replace,bt_del,bt_date,bt_agree,bt_productinfoid ;
	}
}
