package com.xiangmu.lzx.pullrefreshview;

/**
 * Created by Administrator on 2017/12/4 0004.
 */

    import android.content.Context;
    import android.util.AttributeSet;
    import android.util.Log;
    import android.view.View;
  // import android.widget.BaseAdapter;
    import android.widget.LinearLayout;

    import com.xiangmu.lzx.CostomAdapter.ProductinfoAddAdapter;

/**
     * 取代ListView的LinearLayout，使之能够成功嵌套在ScrollView中
     * @author terry_龙
     */
    public class LinearLayoutForListView extends LinearLayout {
        private ProductinfoAddAdapter adapter;
        private OnClickListener onClickListener = null;

        /**
         * 绑定布局
         */
        public void bindLinearLayout() {
            int count = adapter.getCount();
            this.removeAllViews();
            for (int i = 0; i < count; i++) {
                View v = adapter.getView(i, null, null);
                v.setOnClickListener(this.onClickListener);
                addView(v, i);
            }
            Log.v("countTAG", "" + count);
        }

        public LinearLayoutForListView(Context context) {
            super(context);

        }
    }