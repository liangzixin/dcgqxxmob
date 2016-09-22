package com.xiangmu.wyxw.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.xiangmu.wyxw.Modle.Article;
import com.xiangmu.wyxw.Modle.Liuyuan;
import com.xiangmu.wyxw.Modle.ProductArticler;
import com.xiangmu.wyxw.R;
import com.xiangmu.wyxw.utils.Utils;

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

    @Override public void bindData(Liuyuan liuyuan) {
        articler_authorid.setText(liuyuan.liuyuan_id);
        articler_content.setText(liuyuan.liuyuan_content);
        articler_date.setText(liuyuan.liuyuan_date);

    }
}
