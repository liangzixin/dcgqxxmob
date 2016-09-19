package com.xiangmu.wyxw.Modle;

import android.widget.ImageView;

import com.twiceyuan.commonadapter.library.adapter.ViewTypeItem;

import java.io.Serializable;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
public class PhotoImage implements Serializable {

    public int getPhotoId() {
        return photoId;
    }

    public void setPhotoId(int photoId) {
        this.photoId = photoId;
    }

    private int photoId;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;

    public ImageView getImagePicture() {
        return imagePicture;
    }

    public void setImagePicture(ImageView imagePicture) {
        this.imagePicture = imagePicture;
    }

    private ImageView imagePicture;
}
