package com.xiangmu.lzx.holder;

import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.xiangmu.lzx.Modle.DateCount;
import com.xiangmu.lzx.R;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
@LayoutId(R.layout.item_datecount)
public class DateCountHolder extends CommonHolder<DateCount> {

    @ViewId(R.id.textTitle) public   TextView textTitle;
    @ViewId(R.id.textContent) public TextView textContent;
    @ViewId(R.id.xinwen_duotu_date) public TextView xinwen_duotu_date;
    @ViewId(R.id.count_countnume0) public TextView count_countnume0;
    @ViewId(R.id.count_current0) public TextView count_current0;
    @ViewId(R.id.count_start0) public TextView count_start0;
    @ViewId(R.id.count_today0) public TextView count_today0;
    @ViewId(R.id.count_average0) public TextView count_average0;
    @ViewId(R.id.count_max0) public TextView count_max0;
    @ViewId(R.id.count_maxdate0) public TextView count_maxdate0;
    @Override public void bindData(DateCount dateCount) {
        textTitle.setText("网站访问次数");
        textContent.setText("");
        xinwen_duotu_date.setText(dateCount.todaydate.substring(0,10));
        count_countnume0.setText(dateCount.allcount);
        count_current0.setText("");
        count_start0.setText(dateCount.createdate.substring(0,10));
        count_today0.setText(dateCount.todaycount);
        count_average0.setText(dateCount.avgcount);
        count_max0.setText(dateCount.maxcount);
        count_maxdate0.setText(dateCount.maxdate.substring(0,10));

    }
}
