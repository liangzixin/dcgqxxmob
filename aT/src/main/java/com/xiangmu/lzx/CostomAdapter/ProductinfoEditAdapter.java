package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.widget.TextView;

import com.xiangmu.lzx.R;
import com.xiangmu.lzx.utils.XinWen_productinfo;

import java.util.List;

/**
 * Created by Administrator on 2017/7/19 0019.
 */
public class ProductinfoEditAdapter extends BaseEditResultAdapter<XinWen_productinfo.T18908805728Entity> {
    public ProductinfoEditAdapter(List<XinWen_productinfo.T18908805728Entity> datas, Context context) {
        super(datas, context);
    }

    @Override
    protected void bindData(BaseViewHolder holder, int position) {
        // 两个参数，具体的控件id ,  对应的参数数据
        ((TextView)  holder.getView(R.id.result_title)).setText(datas.get(position).getName());
    }

    @Override
    public int getLayoutId() {
        // 条目的布局id
        return R.layout.item_searcheditresult;
    }

}
