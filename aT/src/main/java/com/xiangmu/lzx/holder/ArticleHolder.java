package com.xiangmu.lzx.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.twiceyuan.commonadapter.library.LayoutId;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.holder.CommonHolder;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.Modle.Article;
import com.xiangmu.lzx.utils.Utils;

/**
 * Created by twiceYuan on 3/4/16.
 * Email: i@twiceyuan.com
 * Site: http://twiceyuan.com
 */
@LayoutId(R.layout.item_article)
public class ArticleHolder extends CommonHolder<Article> {

    @ViewId(R.id.textTitle) public   TextView textTitle;
    @ViewId(R.id.textContent) public TextView textContent;
    @ViewId(R.id.xinwen_duotu_date) public TextView xinwen_duotu_date;
    @ViewId(R.id.productinfo_gsmz) public TextView productinfo_gsmz;
    @ViewId(R.id.productinfo_gsdz) public TextView productinfo_gsdz;
    @ViewId(R.id.productinfo_nl) public TextView productinfo_nl;
    @ViewId(R.id.productinfo_sex) public TextView productinfo_sex;
    @ViewId(R.id.productinfo_ed) public TextView productinfo_ed;
    @ViewId(R.id.productinfo_xj) public TextView productinfo_xj;
    @ViewId(R.id.productinfo_nl2) public TextView productinfo_nl2;
    @ViewId(R.id.productinfo_sex2) public TextView productinfo_sex2;
    @ViewId(R.id.productinfo_ed2) public TextView productinfo_ed2;
    @ViewId(R.id.productinfo_xj2) public TextView productinfo_xj2;
    @ViewId(R.id.productinfo_sxcy2) public TextView productinfo_sxcy2;

    @ViewId(R.id.productinfo_fwcs_xqmz) public TextView productinfo_fwcs_xqmz;
    @ViewId(R.id.productinfo_fwcs_jzmj) public TextView productinfo_fwcs_jzmj;
    @ViewId(R.id.productinfo_fwcs_hx) public TextView productinfo_fwcs_hx;
    @ViewId(R.id.productinfo_fwcs_lz) public TextView productinfo_fwcs_lz;
    @ViewId(R.id.productinfo_fwcs_fwzj) public TextView productinfo_fwcs_fwzj;
    @ViewId(R.id.productinfo_fwcs_xqmz4) public TextView productinfo_fwcs_xqmz4;
    @ViewId(R.id.productinfo_fwcs_jzmj4) public TextView productinfo_fwcs_jzmj4;
    @ViewId(R.id.productinfo_fwcs_hx4) public TextView productinfo_fwcs_hx4;
    @ViewId(R.id.productinfo_fwcs_lz4) public TextView productinfo_fwcs_lz4;
    @ViewId(R.id.productinfo_fwcs_fwcj4) public TextView productinfo_fwcs_fwcj4;
    @ViewId(R.id.productinfo_fwcs_fwcjfx) public TextView productinfo_fwcs_fwcjfx;

    @ViewId(R.id.productinfo_gsmz50) public TextView productinfo_gqxx_gsmz50;
    @ViewId(R.id.productinfo_gqxx_gsmz5) public TextView productinfo_gqxx_gsmz5;
    @ViewId(R.id.productinfo_gsdz50) public TextView productinfo_gqxx_gsdz50;
    @ViewId(R.id.productinfo_gqxx_gsdz5) public TextView productinfo_gqxx_gsdz5;
    @ViewId(R.id.productinfo_gqxx_gqsl5) public TextView productinfo_gqxx_gqsl5;
    @ViewId(R.id.productinfo_gqxx_gqsl50) public TextView productinfo_gqxx_gqsl50;

    @ViewId(R.id.productinfo_lxr) public TextView lxr;
    @ViewId(R.id.productinfo_lxdh) public TextView lxdh;
    @ViewId(R.id.category1) public LinearLayout category1;
    @ViewId(R.id.category2) public LinearLayout category2;
    @ViewId(R.id.category3) public LinearLayout category3;
    @ViewId(R.id.category4) public LinearLayout category4;
    @ViewId(R.id.category5) public LinearLayout category5;
    @Override public void bindData(Article article) {
        textTitle.setText(article.title);
        textContent.setText(article.content);
        xinwen_duotu_date.setText(article.dat);
        productinfo_gsmz.setText(article.gsmz);
        productinfo_gsdz.setText(article.gsdz);


            switch (article.productCategory.getId()) {
                case 1:
                    category1.setVisibility(View.VISIBLE);
               break;
                case 2:
                    category2.setVisibility(View.VISIBLE);
                    break;
                case 3:
                    category3.setVisibility(View.VISIBLE);
                    break;
                case 4:
                    category4.setVisibility(View.VISIBLE);
                    break;
                default:
                    category5.setVisibility(View.VISIBLE);
                    break;
        }
        switch (article.productCategory.getId()) {
            case 1:
            case 2:
            if (article.zpxx != null) {
                   System.out.println(article.zpxx.getZpnlrequest().getName());
                    productinfo_nl.setText(article.zpxx.getZpnlrequest().getName());

                    productinfo_ed.setText(article.zpxx.getEdurequest().getName());
                    productinfo_ed2.setText(article.zpxx.getEdurequest().getName());


                    productinfo_xj.setText(article.zpxx.getGzdx().getName());
                    productinfo_xj2.setText(article.zpxx.getGzdx().getName());

                    productinfo_sex.setText(article.zpxx.getSexrequest().getName());
                    productinfo_sex2.setText(article.zpxx.getSexrequest().getName());


                    productinfo_nl2.setText(article.zpxx.getQjnl() + "");


                    productinfo_sxcy2.setText(article.zpxx.getSxcy());

            }
                break;
            case 3:
            case 4:
                if (article.fwcs!= null) {
                    productinfo_fwcs_xqmz.setText(article.gsmz);
                    productinfo_fwcs_jzmj.setText(Utils.FloatToChar(article.fwcs.getJzmj())+" 平米");
                    productinfo_fwcs_hx.setText(Utils.IntToChar(article.fwcs.getFws())+" 室 "+Utils.IntToChar(+article.fwcs.getFwt())+" 厅 "+Utils.IntToChar(article.fwcs.getFwzf())+" 厨 "+Utils.IntToChar(article.fwcs.getFww())+" 卫 ");
                    productinfo_fwcs_lz.setText(Utils.IntToChar(article.fwcs.getFwlj())+"  层，楼层总数： "+Utils.IntToChar(article.fwcs.getFwcj())+" 层 ");
                    productinfo_fwcs_fwzj.setText(Utils.FloatToChar(article.fwcs.getFwzj())+" 万元");

                    productinfo_fwcs_xqmz4.setText(article.gsmz);
                    productinfo_fwcs_jzmj4.setText(Utils.FloatToChar(article.fwcs.getJzmj())+" 平米");
                    productinfo_fwcs_hx4.setText(Utils.IntToChar(article.fwcs.getFws())+" 室 "+Utils.IntToChar(article.fwcs.getFwt())+" 厅 "+Utils.IntToChar(article.fwcs.getFwzf())+" 厨 "+Utils.IntToChar(article.fwcs.getFww())+" 卫 ");
                    productinfo_fwcs_lz4.setText(Utils.IntToChar(article.fwcs.getFwlj())+"  层，楼层总数： "+Utils.IntToChar(article.fwcs.getFwcj())+" 层 ");
                    productinfo_fwcs_fwcj4.setText(Utils.FloatToChar(article.fwcs.getFwzj())+" 元");
                    productinfo_fwcs_fwcjfx.setText(article.fwcs.getFzfsrequest().getName());

                }
                break;
            case 5:
                productinfo_gqxx_gsmz5.setText(article.gsmz);
                productinfo_gqxx_gsdz5.setText(article.gsdz);
                if (article.gqxx!= null) {
                    productinfo_gqxx_gqsl5.setText(Utils.IntToChar(article.gqxx.getGqsl()));
                }else{
                    productinfo_gqxx_gqsl5.setVisibility(View.GONE);
                }
                break;
            case 6:
                productinfo_gqxx_gsmz50.setText("成色：");
                productinfo_gqxx_gsmz5.setText(article.gsmz);
                productinfo_gqxx_gsdz50.setText("具体位置：");
                productinfo_gqxx_gsdz5.setText(article.gsdz);

                if (article.gqxx!= null) {
                    productinfo_gqxx_gqsl5.setText(Utils.IntToChar(article.gqxx.getGqsl()));
                }else{
                    productinfo_gqxx_gqsl5.setVisibility(View.GONE);
                }
                break;
            case 7:
                productinfo_gqxx_gsmz5.setText(article.gsmz);
                productinfo_gqxx_gsdz5.setText(article.gsdz);
                productinfo_gqxx_gqsl50.setVisibility(View.GONE);
                productinfo_gqxx_gqsl5.setVisibility(View.GONE);
                break;
            case 8:
                productinfo_gqxx_gsmz5.setVisibility(View.GONE);
                productinfo_gqxx_gsmz50.setVisibility(View.GONE);
                productinfo_gqxx_gsdz5.setText(article.gsdz);
                productinfo_gqxx_gsdz50.setText("铺面位置：");
                productinfo_gqxx_gqsl50.setVisibility(View.GONE);
                productinfo_gqxx_gqsl5.setVisibility(View.GONE);
                break;
            case 9:

                productinfo_gqxx_gsmz5.setText(article.gsmz);
                productinfo_gqxx_gsdz5.setText(article.gsdz);
                productinfo_gqxx_gsmz50.setText("家具建材店名称：");
                productinfo_gqxx_gsdz50.setText("家具建材店地址：");
                if (article.gqxx!= null) {
                    productinfo_gqxx_gqsl5.setText(Utils.IntToChar(article.gqxx.getGqsl()));
                }else{
                    productinfo_gqxx_gqsl5.setVisibility(View.GONE);
                }
                break;
        }

        lxr.setText(article.lxr);
        lxdh.setText(article.lxdh);
    }
}
