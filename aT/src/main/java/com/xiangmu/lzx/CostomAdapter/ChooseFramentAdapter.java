package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.xiangmu.lzx.R;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import cn.finalteam.galleryfinal.model.PhotoInfo;
//import com.litao.android.lib.entity.PhotoInfo;

public class ChooseFramentAdapter extends RecyclerView.Adapter<ChooseFramentAdapter.ViewHolder> {

    private List<PhotoInfo> list = new ArrayList<PhotoInfo>();

    private Context mContext;

    private LayoutInflater mInflater;

    //  private OnItmeClickListener mlistener;
    private OnClickPhotoListener mOnClickPhotoListener;

//    public  interface OnItemClickListener{
//        void onItemClicked(int position);
//
//    }
//    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
//        this.onItemClickListener = onItemClickListener;
//    }

    public ChooseFramentAdapter(Context mContext , List<PhotoInfo> list,OnClickPhotoListener mOnClickPhotoListener) {
        //  public ChooseFramentAdapter() {
        this.list=list;
        this.mContext = mContext;
   // mOnClickPhotoListener= (OnClickPhotoListener) mContext;
        this.mOnClickPhotoListener=mOnClickPhotoListener;
        //   this.mOnClickPhotoListener = mOnClickPhotoListener;
        mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // list.add(createAddEntry());
    }

    public void reloadList(List<PhotoInfo> data) {
        if (data != null) {
            list.clear();
            list.addAll(data);
            list.add(createAddEntry());
        } else {
            list.clear();
        }
        notifyDataSetChanged();

    }

    public void appendList(List<PhotoInfo> data) {
        if (data != null) {
            list.addAll(list.size()-1,data);
        } else {
            list.clear();
        }
        notifyDataSetChanged();

    }


    public void appendPhoto(PhotoInfo entry) {
        if (entry != null) {
            list.add(list.size()-1,entry);
        }
        notifyDataSetChanged();
    }

    public List<PhotoInfo> getData(){
        return list.subList(0,list.size()-1);
    }
    public PhotoInfo getEntry(int position) {
        return list.get(position);
    }

    private PhotoInfo createAddEntry(){
        return new PhotoInfo();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
  public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        //   public ViewHolder onCreateViewHolder(int position, ViewHolder convertView, ViewGroup parent) {
  ViewHolder vh = new ViewHolder(mInflater.inflate(R.layout.item_selected_photo, viewGroup, false), i);
       // ViewHolder vh =(ViewHolder) LayoutInflater.from(mContext).inflate(R.layout.productinfo_item_zaoping,viewGroup, false),i);
  //   ViewHolder   convertView0 = new ViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_selected_photo, parent,false));
        //    ViewHolder vh = new ViewHolder(mInflater.inflate(R.layout.adapter_photo_list_item, viewGroup, false), i);
      //  convertView = LayoutInflater.from(activity).inflate(R.layout.adapter_photo_list_item, parent,false);
        return vh;
    }


    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        if (list != null && list.size() >0) {
            if (position == list.size()-1) {   // 集合的最后一张显示默认图片，可点击添加图片
                viewHolder.delete.setVisibility(View.GONE);
                Glide.with(mContext)
                        .load(R.drawable.ic_gf_default_photo)
                        .placeholder(R.drawable.ic_gf_default_photo)
                        .error(R.drawable.ic_gf_default_photo)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(viewHolder.mImageView);
                viewHolder.mImageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnClickPhotoListener.onClickPhotoListener();
                    }
                });
            }else{
                viewHolder.delete.setVisibility(View.VISIBLE);
                PhotoInfo photoEntry = list.get(position);
                viewHolder.delete.setOnClickListener(new DeleteClick(position));

                Glide.with(mContext)
                        .load(new File(photoEntry.getPhotoPath()))
                        .placeholder(R.drawable.ic_gf_default_photo)
                        .centerCrop()
                        .error(R.drawable.ic_gf_default_photo)
                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
                        .into(viewHolder.mImageView);
            }
        }
//        if (list != null && list.size() > 0) {
//            if (position == list.size() - 1) {   // 集合的最后一张显示默认图片，可点击添加图片
//                viewHolder.mImageView.setImageResource(R.drawable.ic_gf_default_photo);
//            } else {
//                PhotoInfo entry = list.get(position);
//                Glide.with(mContext)
//                        .load(new File(entry.getPhotoPath()))
//                        .centerCrop()
//                        .placeholder(R.drawable.ic_gf_default_photo)
//                        .into(viewHolder.mImageView);
//            }
//        }else{
//                            Glide.with(mContext)
//                        .load(R.drawable.ic_gf_default_photo)
//                        .placeholder(R.drawable.ic_gf_default_photo)
//                        .error(R.drawable.ic_gf_default_photo)
//                        .diskCacheStrategy(DiskCacheStrategy.RESULT)
//                        .into(viewHolder.mImageView);
//        }
    }
    @Override
    public int getItemCount() {
        if (list != null && list.size() > 0) {
            if (list.size() < 10) {
                return list.size();
            }else{
                return 9;
            }
        }else{
            return 0;
        }
        //  return list.size();
    }

   public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
       //         public class ViewHolder extends RecyclerView.ViewHolder  {
        private ImageView mImageView;
        private ImageView delete;
        private int position;

        public ViewHolder(View itemView, int position) {
            super(itemView);
            this.position = position;
            mImageView = (ImageView) itemView.findViewById(R.id.iv_photo);
            delete = (ImageView) itemView.findViewById(R.id.iv_photo_delete);
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnClickPhotoListener.onClickPhotoListener();
                }
            });
            delete.setOnClickListener(new DeleteClick(position));
        }

        @Override
        public void onClick(View view) {
            //     mOnClickPhotoListener.onItemClicked(position);
        }
    }
    class DeleteClick implements View.OnClickListener {
        private int position;

        DeleteClick(int position) {
            this.position = position;
        }

        public void onClick(View v) {
            list.remove(list.get(position));
            notifyDataSetChanged();
        }
    }
    public interface OnClickPhotoListener{
        void onClickPhotoListener();

    }
}
