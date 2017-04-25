package com.xiangmu.lzx.holder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.xiangmu.lzx.Modle.Liuyuan;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.Setting_Utils.TouXiangCache;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
@LayoutId(R.layout.item_productarticler)
public class ProductArticleHolder extends CommonHolder<Liuyuan> {

    @ViewId(R.id.articler_authorid) public   TextView articler_authorid;
    @ViewId(R.id.articler_date) public TextView articler_date;
    @ViewId(R.id.articler_content) public TextView articler_content;
    @ViewId(R.id.articler_imgurl) public ImageView articler_imgurl;

    @Override public void bindData(Liuyuan liuyuan) {
       // articler_authorid.setText(liuyuan.liuyuan_id);
        Context context = getItemView().getContext();
        articler_authorid.setText(liuyuan.liuyuan_name);
        articler_content.setText(liuyuan.liuyuan_content);
        articler_date.setText(liuyuan.liuyuan_date.substring(0,10));
      //  XutilsGetData.xUtilsImageiv(articler_imgurl,liuyuan.liuyuan_imag,context,false);
        if (liuyuan.liuyuan_imag != null) {
            Log.e("aaa","--------pic_path"+liuyuan.liuyuan_imag);
            try {
                byte[] data = TouXiangCache.getImage( liuyuan.liuyuan_imag);
                String d = new String(data);
                // File file = new File("1.jpg");
                //OutputStream out = new FileOutputStream(file);
                //out.write(data);
                //out.close();
                int length = data.length;
                Bitmap bitMap = BitmapFactory.decodeByteArray(data, 0, length);
                articler_imgurl.setImageBitmap(bitMap);
                //imageView.seti
            } catch (Exception e) {
//                    Log.i(TAG, e.toString());
                Log.e("读取网络图片错误","--------e"+e);
//                    Toast.makeText(DataActivity.this, "获取图片失败", 1).show();
            }
        }

    }
}
