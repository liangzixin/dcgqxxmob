package com.xiangmu.lzx.utils;

import android.content.Context;
import android.widget.ImageView;

import com.xiangmu.lzx.Modle.UploadFile;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/13.
 */
public class XinWenXiImage {
    public static List<PhotosObj> getdata(List<UploadFile> potolist, Context context){
        XutilsGetData xutilsGetData=new XutilsGetData();
        LogUtils.e("xinwenactivitydata", potolist.size()+"");
        List<PhotosObj> list=new ArrayList<>();
        try {
//            JSONObject object=new JSONObject(data);
//            JSONArray photos=object.getJSONArray("uploadFile");
           // String gentieUrl=object.getString("commenturl");
            PhotosObj photosObj=new PhotosObj();
            photosObj.setGentieUrl("");
            list.add(photosObj);

            List<PhotosObj.Photos> photosList=new ArrayList<>();
            for (int i=0;i<potolist.size();i++){
                PhotosObj.Photos ps= new PhotosObj.Photos();
//
                ImageView imageView=new ImageView(context);
//                JSONObject photo= (JSONObject) photos.get(i);
             //  String imgurl=potolist.get(i).getPath();
               String imgurl="http://img3.cache.netease.com/3g/2015/11/11/20151111084918c6c18.jpg";
              xutilsGetData.xUtilsImageiv(imageView, imgurl, context,true);
                String text=null;

                ps.setImage(imageView);
                ps.setText(text);

                photosList.add(ps);
            }
            list.set(0,photosObj).setPhotosList(photosList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        xutilsGetData.XutilsClose();
        return list;
    }
    public static class PhotosObj{
        List<Photos> photosList;
        String gentieUrl;

        public List<Photos> getPhotosList() {
            return photosList;
        }

        public void setPhotosList(List<Photos> photosList) {
            this.photosList = photosList;
        }

        public static class Photos{
            public ImageView getImage() {
                return image;
            }

            public void setImage(ImageView image) {
                this.image = image;
            }

            ImageView image;
            String text;
//            public ImageView getImg() {
//                return image;
//            }
//
//            public void setImg(ImageView img) {
//                this.image = img;
//            }

            public void setText(String text) {
                this.text = text;
            }

            public String getText() {
                return text;
            }
        }

        public String getGentieUrl() {
            return gentieUrl;
        }

        public void setGentieUrl(String gentieUrl) {
            this.gentieUrl = gentieUrl;
        }
    }
}
