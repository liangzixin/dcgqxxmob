package com.xiangmu.lzx.CostomAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.xiangmu.lzx.Modle.Dxfw;
import com.xiangmu.lzx.Modle.Edu;
import com.xiangmu.lzx.Modle.Fzfs;
import com.xiangmu.lzx.Modle.Sex;
import com.xiangmu.lzx.Modle.Zpnl;
import com.xiangmu.lzx.R;

import java.util.List;


/**
 * Created by Administrator on 2015/11/9.
 */
public class ProductinfoAddAdapter extends BaseAdapter {
//    private List<YueDuBean.推荐Entity> list;
//   private Spinner spinner_sex = null;
//    private Spinner spinner_dxfw= null;
//    private Spinner spinner_nl= null;
//    private Spinner spinner_xl= null;
//private static final String[] msex={"请选择类别","招聘信息","求职信息","房屋出售","房屋出租","供求信息","二手市场","其它信息","铺面信息","家居装饰"};

   List  msex= Sex.getValues();
    List  dxfw= Dxfw.getValues();
    List  nl= Zpnl.getValues();
    List  xl= Edu.getValues();
    List  listcjfs= Fzfs.getValues();
    private int mytype;
    private Context context;
    final int VIEW_TYPE =5;
    final int TYPE_1 = 1;
    final int TYPE_2 = 2;
    final int TYPE_3 = 3;
    final int TYPE_4 = 4;
    boolean isSpinnerFirst = true ;
//
//    public List<YueDuBean.推荐Entity> getList() {
//        return list;
//    }
//
//    public void setList(List<YueDuBean.推荐Entity> list) {
//        this.list = list;
//        notifyDataSetChanged();
//    }

    public int getMytype() {
        return mytype;
    }

    public void setMytype(int mytype) {
        this.mytype = mytype;
        notifyDataSetChanged();
    }

    public ProductinfoAddAdapter(Context context,int mytype) {
        this.context = context;
        this.mytype =mytype;
    }

    @Override
    public int getCount() {
    //    return list.size();
        return 1;
    }

    @Override
    public Object getItem(int i) {
      //  return list.get(i);
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
//
//    @Override
//    public int getItemViewType(int i) {
//            String template = list.get(i).template;
//            if (template.equals("pic1")){
//                return TYPE_2;
//            }else if (template.equals("pic31")){
//                return TYPE_3;
//            }else if (template.equals("pic32")){
//                return TYPE_4;
//            }else if (template.equals("normal")){
//                return TYPE_1;
//            }
//        return TYPE_1;
//    }
//
//    @Override
//    public int getViewTypeCount() {
//        return 4;
//    }
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHoudle_zaoping viewHoudle_zaoping= null;
            ViewHoudle_qiuzhi viewHoudle_qiuzhi= null;
//            ViewHoudle_pic31 viewHoudle_pic31 = null;
//            ViewHoudle_pic32 viewHoudle_pic32 = null;
//            int type = getItemViewType(i);
        if(mytype==0) mytype=1;
            //无convertView，需要new出各个控件
            if (view == null) {
                //按当前所需的样式，确定new的布局
                switch (mytype) {
                    case TYPE_1:
                        viewHoudle_zaoping = new ViewHoudle_zaoping();
                        view = LayoutInflater.from(context).inflate(R.layout.productinfo_item_zaoping, null);
                   //     viewHoudle_zaoping.name = (EditText) view.findViewById(R.id.name);
//                        viewHoudle_zaoping.productinfo_gsmz= (EditText) view.findViewById(R.id.productinfo_gsmz);
//                        viewHoudle_zaoping.productinfo_gsdz= (EditText) view.findViewById(R.id.productinfo_gsdz);
                        viewHoudle_zaoping.spinner_sex= (Spinner) view.findViewById(R.id.spin_sex);

                        viewHoudle_zaoping.spinner_sex.setAdapter(new ArrayAdapter<Sex>(context, android.R.layout.simple_spinner_item,msex));


                        viewHoudle_zaoping.spinner_dxfw = (Spinner) view.findViewById(R.id.spin_dxfw);
                        viewHoudle_zaoping.spinner_nl= (Spinner) view.findViewById(R.id.spin_nl);
                        viewHoudle_zaoping.spinner_xl= (Spinner) view.findViewById(R.id.spin_xl);
                        viewHoudle_zaoping.spinner_dxfw.setAdapter(new ArrayAdapter<String>(context,
                                android.R.layout.simple_spinner_item,dxfw));

                        viewHoudle_zaoping.spinner_nl.setAdapter(new ArrayAdapter<String>(context,
                                android.R.layout.simple_spinner_item,nl));


//                        viewHoudle_zaoping.spinner_nl.setAdapter(new ArrayAdapter<String>(context,
//                                android.R.layout.simple_spinner_item,nl));
                        viewHoudle_zaoping.spinner_xl.setAdapter(new ArrayAdapter<String>(context,
                                android.R.layout.simple_spinner_item,xl));

                        view.setTag(viewHoudle_zaoping);
                    //    viewHoudle_zaoping.name.setFocusable(true);
                        break;
                    case TYPE_2:
                        viewHoudle_qiuzhi = new ViewHoudle_qiuzhi();
                        view = LayoutInflater.from(context).inflate(R.layout.productinfo_item_qiuzhi, null);
                     //   viewHoudle_qiuzhi.name = (EditText) view.findViewById(R.id.name);
//                        viewHoudle_qiuzhi.productinfo_gsmz= (EditText) view.findViewById(R.id.productinfo_gsmz);
//                        viewHoudle_qiuzhi.productinfo_gsdz= (EditText) view.findViewById(R.id.productinfo_gsdz);
                        view.setTag(viewHoudle_zaoping);
                       // viewHoudle_qiuzhi.name.setFocusable(true);
                        break;
                    case TYPE_3:

                        break;
                    case TYPE_4:

                        break;
                }
            } else {
//                //有convertView，按样式，取得不用的布局
                switch (mytype) {
                    case TYPE_1:
                        viewHoudle_zaoping= (ViewHoudle_zaoping) view.getTag();
                        break;
                    case TYPE_2:
                        viewHoudle_qiuzhi = (ViewHoudle_qiuzhi) view.getTag();
                        break;
//                    case TYPE_3:
//                        viewHoudle_pic31 = (ViewHoudle_pic31) view.getTag();
//                        break;
//                    case TYPE_4:
//                        viewHoudle_pic32 = (ViewHoudle_pic32) view.getTag();
//                        break;
                }
            }
//            YueDuBean.推荐Entity entity = list.get(i);
//            BitmapUtils bitmapUtils = new BitmapUtils(context);
//            //设置资源
//            switch (type) {
//                case TYPE_1:
//                    if ("".equals(entity.imgsrc)){
//                        viewHoudle_normal.normal_iv.setVisibility(View.GONE);
//                    }else {
//                        bitmapUtils.display(viewHoudle_normal.normal_iv, entity.imgsrc);
//                    }
//                    viewHoudle_normal.normal_title.setText(entity.title);
//                    viewHoudle_normal.normal_source.setText(entity.source);
//                    break;
//                case TYPE_2:
//                    bitmapUtils.display(viewHoudle_pic1.pic1_iv, entity.imgsrc);
//                    viewHoudle_pic1.pic1_tv.setText(entity.title);
//                    viewHoudle_pic1.pic1_source.setText(entity.source);
//                    break;
//                case TYPE_3:
//                    bitmapUtils.display(viewHoudle_pic31.pic31_iv1, entity.imgsrc);
//                    bitmapUtils.display(viewHoudle_pic31.pic31_iv2, entity.imgnewextra.get(0).imgsrc);
//                    bitmapUtils.display(viewHoudle_pic31.pic31_iv3, entity.imgnewextra.get(0).imgsrc);
//                    viewHoudle_pic31.pic31_tv.setText(entity.title);
//                    viewHoudle_pic31.pic31_source.setText(entity.source);
//                    break;
//                case TYPE_4:
//                    bitmapUtils.display(viewHoudle_pic32.pic32_iv1, entity.imgsrc);
//                    bitmapUtils.display(viewHoudle_pic32.pic32_iv2, entity.imgnewextra.get(0).imgsrc);
//                    bitmapUtils.display(viewHoudle_pic32.pic32_iv3, entity.imgnewextra.get(0).imgsrc);
//                    viewHoudle_pic32.pic32_tv.setText(entity.title);
//                    viewHoudle_pic32.pic32_source.setText(entity.source);
//                    break;
//            }

        return view;
    }

    //item样式(四种)
    class ViewHoudle_zaoping {

        EditText name, productinfo_gsdz, productinfo_gsmz;
        Spinner spinner_sex, spinner_dxfw, spinner_nl, spinner_xl;
    }

    class ViewHoudle_qiuzhi {
        EditText name, productinfo_gsdz,productinfo_gsmz;
    }

    class ViewHoudle_pic31 {
        ImageView pic31_iv1, pic31_iv2, pic31_iv3;
        TextView pic31_tv, pic31_source;
    }

    class ViewHoudle_pic32 {
        ImageView pic32_iv1, pic32_iv2, pic32_iv3;
        TextView pic32_tv, pic32_source;
    }
}
