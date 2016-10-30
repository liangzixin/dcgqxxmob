package com.xiangmu.wyxw.holder;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.xiangmu.wyxw.R;
import com.xiangmu.wyxw.Modle.Photo;
import com.xiangmu.wyxw.utils.XutilsGetData;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
@LayoutId(R.layout.item_photolzx)
public class PhotoHolder extends CommonHolder<Photo> {

    @ViewId(R.id.imagePicture) ImageView imagePicture;
    @ViewId(R.id.textDesc)     TextView  textDesc;

    @Override public void bindData(Photo photo) {
        Context context = getItemView().getContext();
     //   imagePicture.setImageDrawable(ContextCompat.getDrawable(context, photo.photoId));
        XutilsGetData.xUtilsImageiv( imagePicture, "http://www.dcgqxx.com/upload/"+photo.path,context,false);
      //  imagePicture=photo.imagePicture;
        textDesc.setText(photo.description);
    }
}
