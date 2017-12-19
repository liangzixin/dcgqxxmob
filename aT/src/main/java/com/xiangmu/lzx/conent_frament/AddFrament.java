package com.xiangmu.lzx.conent_frament;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.MaterialEditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.baoyz.actionsheet.ActionSheet;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.litao.android.lib.Utils.GridSpacingItemDecoration;
import com.xiangmu.lzx.CostomAdapter.ChooseFramentAdapter;
import com.xiangmu.lzx.CostomAdapter.ProductinfoAddAdapter;
import com.xiangmu.lzx.CostomProgressDialog.CustomProgressDialog;
import com.xiangmu.lzx.Modle.Article;
import com.xiangmu.lzx.Modle.Dxfw;
import com.xiangmu.lzx.Modle.Edu;
import com.xiangmu.lzx.Modle.Fzfs;
import com.xiangmu.lzx.Modle.Liuyuan;
import com.xiangmu.lzx.Modle.Photo;
import com.xiangmu.lzx.Modle.ProductArticler;
import com.xiangmu.lzx.Modle.ProductCategory;
import com.xiangmu.lzx.Modle.Sex;
import com.xiangmu.lzx.Modle.UploadFile;
import com.xiangmu.lzx.Modle.Zpnl;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.activitys.MainActivity;
import com.xiangmu.lzx.activitys.PictureActivity;
import com.xiangmu.lzx.jieping.ScreenShot;
import com.xiangmu.lzx.utils.DateTime;
import com.xiangmu.lzx.utils.LogUtils;
import com.xiangmu.lzx.utils.MySqlOpenHelper;
import com.xiangmu.lzx.utils.PictureUtil;
import com.xiangmu.lzx.utils.Upload;
import com.xiangmu.lzx.utils.XinWenURL;
import com.xiangmu.lzx.utils.XinWenXiData;
import com.xiangmu.lzx.utils.XutilsGetData;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.finalteam.galleryfinal.GalleryFinal;
import cn.finalteam.galleryfinal.model.PhotoInfo;

//import com.litao.android.lib.entity.PhotoEntry;

/**
 * Created by Administrator on 2015/11/9.
 */
public class AddFrament extends Fragment implements ChooseFramentAdapter.OnClickPhotoListener{
    private XinWenXiData xinWenXiData;
    private XinWenURL xinWenURL=new XinWenURL();
    private XutilsGetData xutilsGetData = new XutilsGetData();
    private List<UploadFile> potolist;
    private List<ProductArticler> liuyuanlist;
    private HttpUtils httpUtils;
    private HttpHandler<String> handler;
   // private PullToRefreshListView mRecyclerView;
    private ListView mRecyclerView;

    private List<PhotoInfo> mSelectedPhotos=new ArrayList<PhotoInfo>();
    private List<String> listfile = new ArrayList<String>();
    private List<File> list=new ArrayList<>();
    private List<String> imgstmppath=new ArrayList<String>();
    private ProductinfoAddAdapter mAdapter;
    private ChooseFramentAdapter lAdapter;
    private final int REQUEST_CODE_CAMERA = 1000;
    private final int REQUEST_CODE_GALLERY = 1001;

    private static final String[] m={"请选择类别","招聘信息","求职信息","房屋出售","房屋出租","供求信息","二手市场","其它信息","铺面信息","家居装饰"};
    // private static final List msex=new List() { };
//    ImageButton fenxiang;
    //
    private Spinner articlerSpinner = null;

    private RecyclerView recyclerView;
    private EditText name,gsmz,gsdz,sex,dxfwl,nll,xll;
    private TextView name0,gsmz0,gsdz0,hxzj0,hxzh0;
    private Spinner spinner_sex,spinner_dxfw,spinner_nl,spinner_xl,cjfs;
    private LinearLayout qznlLayout,sxcyLayout,gsmzLayout,gsdzLayout,fwhx0Layout,fwhx1Layout,fwhx2Layout,fwhx3Layout;
//    private MaterialEditText productinfo_gsdz;
//    private MaterialEditText productinfo_gsmz;
    private EditText productinfo_lxr;
    private EditText productinfo_lxdh;
    private EditText productinfo_sxcy;
    private EditText productinfo_content;
    private MaterialEditText productinfo_qjnl;


    private EditText jzmj;
    private EditText fwzj;
    //    private EditText fwzs_fwcj;
    private EditText hxs;
    private EditText hxt;
    private EditText hxw;
    private EditText hxc;
    private EditText fwlz;
    private EditText fwzc;
    private Spinner zjfs;


    private EditText gqxx_gqsl;
    private EditText gqxx_spsj;
    LinearLayout category2;
    LinearLayout category3;
    private String filepath;
    private String filepath1;
    private TextView fpxx;
    public static  final int RESULT_CODE=101;
    View view;
    List  msex=Sex.getValues();
    List  dxfw= Dxfw.getValues();
    List  nl= Zpnl.getValues();
    List  xl= Edu.getValues();
    List  listcjfs= Fzfs.getValues();

    @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
     //   protected void onCreate(Bundle savedInstanceState) {
//        initdata();
     //  super.onCreate(savedInstanceState);

    View view = inflater.inflate(R.layout.productinfoadd_content, null);
        fpxx=(TextView)view.findViewById(R.id.bt_fpxx);
      // setContentView(R.layout.activity_productinfo_add);
             articlerSpinner = (Spinner)  view.findViewById(R.id.spin_articler);
                ArrayAdapter adapter= new ArrayAdapter<String>( getActivity(),
                android.R.layout.simple_spinner_item,m);
        articlerSpinner.setAdapter(adapter);
        articlerSpinner.setSelection(1);
        name = (EditText) view.findViewById(R.id.name);
        name0 = (TextView) view.findViewById(R.id.name0);
        gsdz0 = (TextView) view.findViewById(R.id.gsdz0);
        gsmz0 = (TextView) view.findViewById(R.id.gsmz0);
        hxzj0 = (TextView) view.findViewById(R.id.hxzj0);
        hxzh0 = (TextView) view.findViewById(R.id.hxzh0);
        gsmz=(EditText) view.findViewById(R.id.productinfo_gsmz);
        gsdz= (EditText) view.findViewById(R.id.productinfo_gsdz);
        qznlLayout= (LinearLayout) view.findViewById(R.id.qznlLayout);
        sxcyLayout= (LinearLayout) view.findViewById(R.id.sxcyLayout);
       gsmzLayout= (LinearLayout) view.findViewById(R.id.gsmzLayout);
        gsdzLayout= (LinearLayout) view.findViewById(R.id.gsdzLayout);
        fwhx0Layout= (LinearLayout) view.findViewById(R.id.fwhx0);
        fwhx1Layout= (LinearLayout) view.findViewById(R.id.fwhx1);
        fwhx2Layout= (LinearLayout) view.findViewById(R.id.fwhx2);
        fwhx3Layout= (LinearLayout) view.findViewById(R.id.fwhx3);
        spinner_sex= (Spinner) view.findViewById(R.id.spin_sex);
        spinner_dxfw = (Spinner) view.findViewById(R.id.spin_dxfw);
        spinner_nl = (Spinner) view.findViewById(R.id.spin_nl);
        spinner_xl= (Spinner) view.findViewById(R.id.spin_xl);
        cjfs= (Spinner) view.findViewById(R.id.cjfs);

       jzmj= (EditText) view.findViewById(R.id.jzmj);
       fwzj= (EditText) view.findViewById(R.id.fwzj);

       hxs= (EditText) view.findViewById(R.id.hxs);
       hxt= (EditText) view.findViewById(R.id.hxt);
       hxs= (EditText) view.findViewById(R.id.hxs);
       hxw= (EditText) view.findViewById(R.id.hxw);
       hxc= (EditText) view.findViewById(R.id.hxc);
       fwlz= (EditText) view.findViewById(R.id.fwlz);
       fwzc= (EditText) view.findViewById(R.id.fwzc);


     //  fwzs_zjfs= (MaterialSpinner) view.findViewById(R.id.spin_cjfs);

        spinner_sex.setAdapter(new ArrayAdapter<Sex>(getActivity(), android.R.layout.simple_spinner_item,msex));
        spinner_dxfw.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,dxfw));
        spinner_nl.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,nl));
        spinner_xl.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,xl));
       cjfs.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,listcjfs));
        productinfo_content=(EditText)  view.findViewById(R.id.productinfo_content);
        //改变默认的单行模式
        productinfo_content.setSingleLine(false);
        //水平滚动设置为False
        productinfo_content.setHorizontallyScrolling(false);
//        mRecyclerView =   (ListView) view.findViewById(R.id.refresh);
        articlerSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //当选中某一个数据项时触发该方法
            /*
             * parent接收的是被选择的数据项所属的 Spinner对象，
             * view参数接收的是显示被选择的数据项的TextView对象
             * position接收的是被选择的数据项在适配器中的位置
             * id被选择的数据项的行号
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                    case 1:
                        name0.setText("招聘标题");
                        gsdz0.setText("公司地址");
                       gsdz.setVisibility(View.VISIBLE);
                       gsmz.setVisibility(View.VISIBLE);
                        gsmzLayout.setVisibility(View.VISIBLE);
                        gsdzLayout.setVisibility(View.VISIBLE);
                        spinner_nl.setVisibility(View.VISIBLE);
                        spinner_sex.setVisibility(View.VISIBLE);
                        spinner_dxfw.setVisibility(View.VISIBLE);
                        spinner_xl.setVisibility(View.VISIBLE);

                        qznlLayout.setVisibility(View.GONE);
                        sxcyLayout.setVisibility(View.GONE);
                        gsmzLayout.setVisibility(View.VISIBLE);
                        gsdzLayout.setVisibility(View.VISIBLE);
                        fwhx0Layout.setVisibility(View.GONE);
                        fwhx1Layout.setVisibility(View.GONE);
                        fwhx2Layout.setVisibility(View.GONE);
                        fwhx3Layout.setVisibility(View.GONE);
                        break;
                    case 2:
                        name0.setText("求职标题");
                        spinner_sex.setVisibility(View.VISIBLE);
                        spinner_dxfw.setVisibility(View.VISIBLE);
                        spinner_xl.setVisibility(View.VISIBLE);
                       qznlLayout.setVisibility(View.VISIBLE);
                        sxcyLayout.setVisibility(View.VISIBLE);
                        productinfo_sxcy.setVisibility(View.VISIBLE);
                        gsmzLayout.setVisibility(View.GONE);
                        gsdzLayout.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                        fwhx0Layout.setVisibility(View.GONE);
                        fwhx1Layout.setVisibility(View.GONE);
                        fwhx2Layout.setVisibility(View.GONE);
                        fwhx3Layout.setVisibility(View.GONE);
                        break;
                    case 3:
                        name0.setText("房屋出售标题");
                        hxzj0.setText("㎡，总价");
                        hxzh0.setText("万元 ");
                        gsdzLayout.setVisibility(View.VISIBLE);
                       fwhx0Layout.setVisibility(View.VISIBLE);
                        fwhx1Layout.setVisibility(View.VISIBLE);
                        fwhx2Layout.setVisibility(View.VISIBLE);
                        cjfs.setVisibility(View.GONE);
                        gsmzLayout.setVisibility(View.GONE);
                        gsdz0.setText("小区名称");
                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        qznlLayout.setVisibility(View.GONE);
                        sxcyLayout.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                     ; fwhx3Layout.setVisibility(View.GONE);
                        break;
                    case 4:
                        name0.setText("房屋出租标题");
                        hxzj0.setText("㎡，租金");
                        hxzh0.setText("元");
                        gsdzLayout.setVisibility(View.VISIBLE);
                        fwhx0Layout.setVisibility(View.VISIBLE);
                        fwhx1Layout.setVisibility(View.VISIBLE);
                        fwhx2Layout.setVisibility(View.VISIBLE);
                        cjfs.setVisibility(View.VISIBLE);
                        gsmzLayout.setVisibility(View.GONE);
                        gsdz0.setText("小区名称");
                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        qznlLayout.setVisibility(View.GONE);
                        sxcyLayout.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                        fwhx3Layout.setVisibility(View.GONE);
                        break;
                    case 5:
                        name0.setText("供求信息标题");
                        gsdz0.setText("公司地址");
                        gsdzLayout.setVisibility(View.VISIBLE);
                        fwhx0Layout.setVisibility(View.GONE);
                        fwhx1Layout.setVisibility(View.GONE);
                        fwhx2Layout.setVisibility(View.GONE);
                        cjfs.setVisibility(View.GONE);
                        gsmzLayout.setVisibility(View.GONE);

                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        qznlLayout.setVisibility(View.GONE);
                        sxcyLayout.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                        fwhx3Layout.setVisibility(View.VISIBLE);
                        break;
                    case 6:
                        name0.setText("二手信息标题");
                        gsdz0.setText("具体地址");
                        gsmz0.setText("成    色");
                        gsdzLayout.setVisibility(View.VISIBLE);
                        gsmzLayout.setVisibility(View.VISIBLE);
                        fwhx0Layout.setVisibility(View.GONE);
                        fwhx1Layout.setVisibility(View.GONE);
                        fwhx2Layout.setVisibility(View.GONE);
                        cjfs.setVisibility(View.GONE);
                        gsmzLayout.setVisibility(View.GONE);

                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        qznlLayout.setVisibility(View.GONE);
                        sxcyLayout.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                        fwhx3Layout.setVisibility(View.VISIBLE);

                        break;
                    case 7:
                        name0.setText("其它信息标题");
                        gsdz0.setText("公司地址");
                        gsmz0.setText("公司名称");
                        gsdzLayout.setVisibility(View.VISIBLE);
                        gsmzLayout.setVisibility(View.VISIBLE);
                        fwhx0Layout.setVisibility(View.GONE);
                        fwhx1Layout.setVisibility(View.GONE);
                        fwhx2Layout.setVisibility(View.GONE);
                        cjfs.setVisibility(View.GONE);
                        gsmzLayout.setVisibility(View.GONE);

                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        qznlLayout.setVisibility(View.GONE);
                        sxcyLayout.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                        fwhx3Layout.setVisibility(View.GONE);

                        break;
                    case 8:
                        name0.setText("铺面信息标题");
                        gsdz0.setText("铺面地址");

                        gsdzLayout.setVisibility(View.VISIBLE);
                        gsmzLayout.setVisibility(View.GONE);
                        fwhx0Layout.setVisibility(View.GONE);
                        fwhx1Layout.setVisibility(View.GONE);
                        fwhx2Layout.setVisibility(View.GONE);
                        cjfs.setVisibility(View.GONE);
                        gsmzLayout.setVisibility(View.GONE);

                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        qznlLayout.setVisibility(View.GONE);
                        sxcyLayout.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                        fwhx3Layout.setVisibility(View.GONE);

                        break;
                    case 9:
                        name0.setText("家具建材信息标题");
                        gsdz0.setText("铺面地址");
                        gsmz0.setText("家具建材商店名称");
                        gsdzLayout.setVisibility(View.VISIBLE);
                        gsmzLayout.setVisibility(View.VISIBLE);
                        fwhx0Layout.setVisibility(View.GONE);
                        fwhx1Layout.setVisibility(View.GONE);
                        fwhx2Layout.setVisibility(View.GONE);
                        cjfs.setVisibility(View.GONE);
                        gsmzLayout.setVisibility(View.GONE);

                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        qznlLayout.setVisibility(View.GONE);
                        sxcyLayout.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                        fwhx3Layout.setVisibility(View.VISIBLE);

                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
//        mAdapter = new ProductinfoAddAdapter(this,1);
//        mRecyclerView.getRefreshableView().setAdapter(mAdapter);

    // setContentView(R.layout.activity_productinfo_add);
//        EventBus.getDefault().register(this);
//
        recyclerView = (RecyclerView)  view.findViewById(R.id.recycler_view1);
        mSelectedPhotos.add(new PhotoInfo());
        lAdapter = new ChooseFramentAdapter( getActivity(),mSelectedPhotos,this);
        recyclerView.setLayoutManager(new GridLayoutManager( getActivity(), 5));
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(5, 2, true));
        recyclerView.setAdapter(lAdapter);


//
//        name= (MaterialEditText)  findViewById(R.id.productinfo_name);
//        productinfo_gsdz= (MaterialEditText)  findViewById(R.id.productinfo_gsdz);
//        productinfo_gsmz= (MaterialEditText)  findViewById(R.id.productinfo_gsmz);
        productinfo_sxcy= (EditText)  view.findViewById(R.id.productinfo_sxcy);
        productinfo_lxr= (EditText)  view.findViewById(R.id.productinfo_lxr);
        productinfo_lxdh= (EditText)  view.findViewById(R.id.productinfo_lxdh);


        fpxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  ShareUtils.shareContent(this, xinwentitle, url);
//               System.out.println("articlerSpinnerarti="+articlerSpinner.getSelectedItemPosition()+"");
//                System.out.println("name="+name.getText()+"");
                if(articlerSpinner.getSelectedItemPosition()==0)  {

                    //   Toast.makeText(this, "请选择发布类型！！", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder( getActivity()).setMessage("请选择类型！！").setPositiveButton("确定", null).show();
                    return;
                }


                if(mAdapter.getName().equals(""))  {
                    //   Toast.makeText( getActivity(), "请选择发布类型！！", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder( getActivity()).setMessage("请输入标题！！").setPositiveButton("确定", null).show();
                    return;
                }
                if(productinfo_content.getText().toString().trim().equals(""))  {
                    //   Toast.makeText( getActivity(), "请选择发布类型！！", Toast.　).show();
                    new AlertDialog.Builder( getActivity()).setMessage("请输入详情！！").setPositiveButton("确定", null).show();
                    return;
                }
                if(!isMobileNO(productinfo_lxdh.getText().toString()))  {


                    new AlertDialog.Builder( getActivity()).setMessage("手机号输入错误！！").setPositiveButton("确定", null).show();
                    return;
                }


                String saveproduct=xinWenURL.getSaveproductinfo();
                Toast.makeText( getActivity(), "发布中.....", Toast.LENGTH_LONG).show();
                SaveData(saveproduct);
//                int size =mSelectedPhotos.size();
//                for (int i = 0; i < size; i++) {
//                 listfile.add(mSelectedPhotos.get(i).getPath().toString());
//
//                }
//
//                new Thread(runnable).start();
            }
        });

//        mAdapter..setFocusable(true);
//        editText.setFocusableInTouchMode(true);
//        editText.requestFocus();
//

//        productinfo_content= (MaterialEditText) view.findViewById(R.id.productinfo_content);
//
//
//

//
//
//
//        category2=(LinearLayout) view.findViewById(R.id.category2);
//        category3=(LinearLayout) view.findViewById(R.id.category3);
//
//
//
//
//
//        productinfo_sxcy= (MaterialEditText) view.findViewById(R.id. productinfo_sxcy);
//        productinfo_qjnl= (MaterialEditText) view.findViewById(R.id. productinfo_qjnl);
//
//

//
//        gqxx_gqsl= (MaterialEditText) view.findViewById(R.id.gqxx_gqsl);
//        gqxx_spsj= (MaterialEditText) view.findViewById(R.id.gqxx_spsj);
//        List  msex= Sex.getValues();
//        List  dxfw= Dxfw.getValues();
//        List  nl= Zpnl.getValues();
//        List  xl= Edu.getValues();
//        List  listcjfs= Fzfs.getValues();

//
//
//

//
//        spinner_xl.setAdapter(adapter);
//
//        fwzs_zjfs.setAdapter(new ArrayAdapter<Sex>(this, android.R.layout.simple_spinner_item,listcjfs));
//        articlerSpinner.setSelection(0, true);
//
//
//        //给Spinner添加事件监听

//        fwzs_jzmj.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if ( fwzs_jzmj.getText().toString().indexOf(".") >= 0) {
//                    if ( fwzs_jzmj.getText().toString().indexOf(".",  fwzs_jzmj.getText().toString().indexOf(".") + 1) > 0) {
//                        //  tv_numOfChar.setText("已经输入\".\"不能重复输入");
//                        fwzs_jzmj.setText( fwzs_jzmj.getText().toString().substring(0, fwzs_jzmj.getText().toString().length() - 1));
//                        fwzs_jzmj.setSelection( fwzs_jzmj.getText().toString().length());
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//        fwzs_fwzj.addTextChangedListener(new TextWatcher() {
//
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                if ( fwzs_fwzj.getText().toString().indexOf(".") >= 0) {
//                    if ( fwzs_fwzj.getText().toString().indexOf(".",fwzs_fwzj.getText().toString().indexOf(".") + 1) > 0) {
//                        //  tv_numOfChar.setText("已经输入\".\"不能重复输入");
//                        fwzs_fwzj.setText( fwzs_fwzj.getText().toString().substring(0,fwzs_fwzj.getText().toString().length() - 1));
//                        fwzs_fwzj.setSelection( fwzs_fwzj.getText().toString().length());
//                    }
//
//                }
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        });
//
//        try {
//            initview();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return view;
    }
//    @Override
//    protected void onDestroy() {
//        EventBus.getDefault().unregister(this);
//        super.onDestroy();
//    }
    Runnable runnable = new Runnable() {

        @Override
        public void run() {

            Upload load = new Upload();
            load.postMethod(listfile);

        }
    };
    private void initview() throws   IOException{
//        final String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
//        final String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用

        ImageButton imageback = null;
        imageback = (ImageButton) view.findViewById(R.id.xinwen_xi_back);//返回


//        TextView duotu_gentie = null;
//        duotu_gentie = (TextView) view.findViewById(R.id.xinwen_duotu_gentie);//跟帖
        ImageButton caidan = null;
        caidan = (ImageButton) view.findViewById(R.id.xinwen_xi_kuanzhan_caidan);//菜单
//        webView = null;
//        webView = (WebView) view.findViewById(R.id.xinwen_xi_text_webview);
//        duotu_gentie.setText(xinWenXiData.getReplaycount() + "跟帖");
//        fenxiang = (ImageButton) view.findViewById(R.id.xinwen_xi_fenxiang);
        // getdata(url);//获得数据
        //点击finish
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
           //     finish();
            }
        });
        //点击进入跟帖 详细页面
        //// TODO: 2015/11/14 点击进入跟帖 详细页面 完成
//        duotu_gentie.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(this,GenTieActivity.class));
//            }
//        });
        //点击打开扩展 详细页面
        //// TODO: 2015/11/14
        caidan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getpopuwindow(view);
            }
        });


        fpxx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //  ShareUtils.shareContent(this, xinwentitle, url);
//               System.out.println("articlerSpinnerarti="+articlerSpinner.getSelectedItemPosition()+"");
//                System.out.println("name="+name.getText()+"");
                if(articlerSpinner.getSelectedItemPosition()==0)  {

                    //   Toast.makeText(this, "请选择发布类型！！", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder( getActivity()).setMessage("请选择类型！！").setPositiveButton("确定", null).show();
                    return;
                }
       //  mAdapter.getClass().
                if(mAdapter.getName().trim().equals(""))  {
                    //   Toast.makeText( getActivity(), "请选择发布类型！！", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder( getActivity()).setMessage("请输入标题！！").setPositiveButton("确定", null).show();
                    return;
                }
                if(productinfo_content.getText().toString().trim().equals(""))  {
                    //   Toast.makeText( getActivity(), "请选择发布类型！！", Toast.　).show();
                    new AlertDialog.Builder( getActivity()).setMessage("请输入详情！！").setPositiveButton("确定", null).show();
                    return;
                }
                if(!isMobileNO(productinfo_lxdh.getText().toString()))  {


                    new AlertDialog.Builder( getActivity()).setMessage("手机号输入错误！！").setPositiveButton("确定", null).show();
                    return;
                }


                String saveproduct=xinWenURL.getSaveproductinfo();
                Toast.makeText( getActivity(), "发布中.....", Toast.LENGTH_LONG).show();
                SaveData(saveproduct);
//                int size =mSelectedPhotos.size();
//                for (int i = 0; i < size; i++) {
//                 listfile.add(mSelectedPhotos.get(i).getPath().toString());
//
//                }
//
//                new Thread(runnable).start();
            }
        });
    }

    public void getdata(String url) {
        LogUtils.e("putongframenturl", "==" + url);

//        settings = webView.getSettings();
//        settings.setDomStorageEnabled(true);
//        settings.setJavaScriptEnabled(true);
//        settings.setBlockNetworkImage(true);
//        settings.setSupportZoom(true);  //支持缩放
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);//支持通过JS打开新窗口
//        settings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//        settings.setLoadsImagesAutomatically(true); //支持自动加载图片
//        settings.setNeedInitialFocus(true);//当webview调用requestFocus时为webview设置节点
//        settings.setBuiltInZoomControls(true);
//        settings.setUseWideViewPort(true);
//        settings.setAppCacheEnabled(true);//是否使用缓存
//        settings.setTextSize(WebSettings.TextSize.NORMAL);
//        webView.setWebChromeClient(new WebChromeClient());// 支持运行特殊的javascript(例如：alert())
        final CustomProgressDialog progress=new CustomProgressDialog( getActivity(),"正在加载中.....",R.drawable.donghua_frame);
        progress.show();
//        webView.loadUrl(url);
        //设置打开页面的客户端WebViewClient,如果不设置,则调用系统默认浏览器打开地址
        // 当点击超链地址后不会新打开浏览器来访问，而是始终在本app中浏览页面
//        webView.setWebViewClient(new WebViewClient() {
//            public boolean shouldOverrideUrlLoading(WebView view, String url) {
////                LogUtils.e("putongframenturl", "==" + url);
//                view.loadUrl(url);
//                return true;
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                progress.dismiss();
//            }
//        });
//        webView.setWebChromeClient(new WebChromeClient(){
//            public void onRequestFocus(WebView view) {
//                super.onRequestFocus(view);
//                view.requestFocus();
//
//            }
//        });


//        String data = XutilsGetData.getData(this, url, null);
//        //判断本地数据是否存在  如果没有网络请求
//        if (data != null) {
//            getshowdata(data);
//        } else {
//            XutilsGetData.xUtilsHttp(this, url, url, new XutilsGetData.CallBackHttp() {
//                @Override
//                public void handleData(String data) {
//                    getshowdata(data);
//                }
//            });
//        }
    }
    /**
     * 提交的监听器
     */
//    View.OnClickListener c = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            String msg = edit.getText().toString();
//            if (msg.equals("") || msg == null) {
//                new AlertDialog.Builder(ProductinfoAddActivity.this).setMessage("不能为空").setPositiveButton("确定", null).show();
//                return;
//            }else{
//
//                ProductArticler productArticler=new ProductArticler();
//
//
//                productArticler.setArtreview_rootid(xinWenXiData.getId());
//                productArticler.setArtreview_content(msg);
//                DateTime dateTime =new DateTime();
//                productArticler.setArtreview_time(dateTime.getDateFormatter());
//                productArticler.setArtreview_authorid(1+"");
//                ((List<ProductArticler>)liuyuanlist.get(0)).add(0,productArticler);
//                NotifyFunction();
//            }
//            //  MultiTypeAdapter adapter = new MultiTypeAdapter(ProductinfoAddActivity.this);
//            // adapter.add(mockLiuyuan( liuyuanlist.size()));
//            new AlertDialog.Builder(ProductinfoAddActivity.this).setMessage("留言成功！").setPositiveButton("确定", null).show();
//            edit.setText("");
//        }
//    };
    /**
     * 通知数据发生改变
     */
    public void NotifyFunction() {
        //recyclerView.deferNotifyDataSetChanged();
        ///  recyclerView.notifyDataSetChanged();
        //  recyclerView.getChildItemId(item_p)
        // recyclerView.setAdapter(new SaveAdapter(this,liuyuanlist));
        //  recyclerView.setAdapter(new SaveAdapter(this,liuyuanlist));
        //  MultiTypeAdapter adapter = new MultiTypeAdapter(this);
        // adapter = new MultiTypeAdapter(ProductinfoAddActivity.this);
        //  adapter.registerViewType(Liuyuan.class, ProductArticleHolder.class);
        // for (int i = 0;i <((List<ProductArticler>)liuyuanlist.get(0)).size(); i++) {
//        adapter.add(mockLiuyuan(0));
//        String url=xinWenURL.getSavearticler()+xinWenXiData.getId()+"&msg="+edit.getText().toString();
//        UpData(url);
        // adapter.add(mockLiuyuan(i));
        // }
    }
    //popuwindow设置
    private void getpopuwindow(View v) {
      //  final PopupWindow popu = new PopupWindow((int) (getWindowManager().getDefaultDisplay().getWidth() / 2.5), getWindowManager().getDefaultDisplay().getHeight() / 2);
        final PopupWindow popu;
                View view = View.inflate(getActivity(), R.layout.popwindow_detial, null);
        Button shouchang = (Button) view.findViewById(R.id.bt_save);
        Button jieping = (Button) view.findViewById(R.id.jieping);
        Button ziti = (Button) view.findViewById(R.id.ziti);
        Button yejian = (Button) view.findViewById(R.id.bt_yejian);
        //收藏按钮
        shouchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /// popu.dismiss();
                // TODO: 2015/11/17

            }
        });
        //截屏按钮
        jieping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //    popu.dismiss();
                // TODO: 2015/11/17

                Toast.makeText( getActivity(), "截屏...", Toast.LENGTH_SHORT).show();
                String date_time = DateTime.getDate_Time();
                File file = new File("sdcard/Photo/Screenshots/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                Bitmap bitmap = ScreenShot.takeScreenShot( getActivity());
                String s = "sdcard/Photo/Screenshots/" + date_time;
                String path = s + ".png";
                ScreenShot.savePic(bitmap, path);
                Intent intent = new Intent( getActivity(), PictureActivity.class);
                intent.putExtra("path", s);
                startActivity(intent);

            }
        });
        //改变字体按钮
        ziti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  popu.dismiss();
                // TODO: 2015/11/17
                // ZiTiScale.zitiStyle2(this, view);
            }
        });
        //夜间模式按钮
        yejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              //  popu.dismiss();
                // TODO: 2015/11/17
            }
        });
//        popu.setContentView(view);
//        popu.setFocusable(true);
//        popu.setBackgroundDrawable(new ColorDrawable(0));
//        popu.showAsDropDown(v, 0, 0);
    }


    public void initDate() {
        String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
        String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用
        int replaycount = xinWenXiData.getReplaycount();//获得跟帖数目  //收藏用
        String clickcount=xinWenURL.getClickcount()+xinWenXiData.getId();
        String clickcount0=xinWenURL.getCount();
        //String data = xutilsGetData.getData(this, clickcount, null);
        // String data = SharedPreferencesUtil.getData(this, clickcount, "");
        //    UpData(clickcount);
        // UpData(clickcount0);
        //   UpCount(clickcount0);
        System.out.println("clickcount="+clickcount );
        Log.e("aa", "******xinwentitle*******" + xinwentitle);
        //拿到当前日期
        String date = DateTime.getDate();
        MySqlOpenHelper mySqlOpenHelper = new MySqlOpenHelper(getActivity());
        SQLiteDatabase writableDatabase = mySqlOpenHelper.getWritableDatabase();
        //查询数据库  当前日期 有无存储过 本页的标题
        Cursor cursor = writableDatabase.query("read_date", null, "date =?",
                new String[]{date}, null, null, null, null);
        //有没有当天的数据
        if (cursor.getCount() > 0) {
            ArrayList<String> biaoti = new ArrayList<>();//声明一个集合,用来存放遍历出来的标题
            while (cursor.moveToNext()) {   //遍历  拿到当天的 所有存储的标题
                String cursorString = cursor.getString(cursor.getColumnIndex("title"));
                biaoti.add(cursorString);
            }
            //当天数据中没有 本页的标题
            if (!biaoti.contains(xinwentitle)) {
                ContentValues values = new ContentValues();
                values.put("date", date + "");
                values.put("url", url + "");
                values.put("title", xinwentitle + "");
                values.put("num", 2);
                values.put("replaycount", replaycount + "");
//            values.put("url",url);存储详情页的地址  在 阅读记录里取出来
                writableDatabase.insert("read_date", null, values);
            }
        } else {
            ContentValues values = new ContentValues();
            values.put("date", date + "");
            values.put("url", url + "");
            values.put("title", xinwentitle + "");
            values.put("num", 2 + "");
            values.put("replaycount", replaycount + "");
            writableDatabase.insert("read_date", null, values);
        }
        //关闭
        cursor.close();
        writableDatabase.close();
    }
    public Article mockArticle(int seed) {
        Article article = new Article();
        XinWenXiData xinWenXiData=new XinWenXiData();
//        article.title = getResources().getStringArray(R.array.mock_title)[seed % 4];
//        article.content = getResources().getStringArray(R.array.mock_content)[seed % 4];
        article.title =xinWenXiData.getTitle();
        article.content =xinWenXiData.getXinwentext();
//        article.dat =xinWenXiData.getCreateDate().substring(0,10);
        article.dat ="aaaa";

        article.gsmz=xinWenXiData.getGsmz();
        article.gsdz=xinWenXiData.getGsdz();
        article.lxr=xinWenXiData.getLxr();
        article.lxdh=xinWenXiData.getLxdh();

        if(xinWenXiData.getZpxx()!=null) article.zpxx=xinWenXiData.getZpxx();
        if(xinWenXiData.getFwcs()!=null) article.fwcs=xinWenXiData.getFwcs();
//        article.productCategory=xinWenXiData.getProductCategory();
        ProductCategory productCategory0=new ProductCategory();
        productCategory0.setId(1);

        article.productCategory=productCategory0;
        return article;

    }
    public Liuyuan mockLiuyuan(int seed) {
        Liuyuan liuyuan= new Liuyuan();

        liuyuan.liuyuan_content=((List<ProductArticler>)liuyuanlist.get(0)).get(seed).getArtreview_content();
        liuyuan.liuyuan_id=((List<ProductArticler>)liuyuanlist.get(0)).get(seed).getArtreview_authorid();
        liuyuan.liuyuan_date=((List<ProductArticler>)liuyuanlist.get(0)).get(seed).getArtreview_time();

        return liuyuan;
    }
    public Photo mockPhoto(int seed) {
        Photo photo = new Photo();
        photo.path=((List<UploadFile>)potolist.get(0)).get(seed).getPath();
//        photo.photoId = new int[]{
//                R.drawable.img_sample1,
//                R.drawable.img_sample2,
//                R.drawable.img_sample3,
//                R.drawable.img_sample4
//        }[seed % 4];
        //  photo.description = getResources().getStringArray(R.array.mock_img_desc)[seed % 4];
        photo.photoId = 0;

        //  XutilsGetData.xUtilsImageiv(photo.imagePicture, "http://www.dcgqxx.com/upload/"+potolist.get(0).getPath(),View().getContext(),false);
        // XutilsGetData.xUtilsImageiv(photo.imagePicture, "http://img3.cache.netease.com/3g/2015/11/11/20151111084918c6c18.jpg",this,true);
//        BitmapUtils bitmapUtils = new BitmapUtils(this);
//        bitmapUtils.display(photo.imagePicture,"http://img3.cache.netease.com/3g/2015/11/11/20151111084918c6c18.jpg");
        photo.description =xinWenXiData.getTitle();
        return photo;
    }

    private void SaveData(final String url){
//        filepath= Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator;
//        filepath1= Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"abc.txt";
//        FileInputStream fis = null;//文件输入流
//        try {
//            fis = new FileInputStream(new File(filepath));
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }

        if (!url.equals("")) {
            httpUtils = new HttpUtils();
//       name=;
//                gsmz=;
//                gsdz=;
//                sex=;
//        dxfwl=;
//                nll=;
//                xll=;
//          mSelectedPhotos=Entries.photos;
            RequestParams params = new RequestParams();
            params.addQueryStringParameter("name",mAdapter.getName());
            params.addQueryStringParameter("gsdz",mAdapter.getGsdz());
            params.addQueryStringParameter("gsmz",mAdapter.getGsmz());
            params.addQueryStringParameter("lxr",productinfo_lxr.getText().toString());
            params.addQueryStringParameter("lxdh",productinfo_lxdh.getText().toString());
            params.addQueryStringParameter("categoryId",articlerSpinner.getSelectedItemPosition()+"");
            params.addQueryStringParameter("description",productinfo_content.getText().toString());
            params.addQueryStringParameter("zpxx.sexrequest",mAdapter.getSex());

            params.addQueryStringParameter("zpxx.zpnlrequest",mAdapter.getSpringnl());
            params.addQueryStringParameter("zpxx.gzdx",mAdapter.getSpring_dxfw());
            params.addQueryStringParameter("zpxx.edurequest",mAdapter.getSpring_xl());


//            params.addQueryStringParameter("zpxx.sxcy",productinfo_sxcy.getText().toString());
//            params.addQueryStringParameter("zpxx.qjnl",productinfo_qjnl.getText().toString());
//            params.addQueryStringParameter("fwcs.jzmj",fwzs_jzmj.getText().toString());
//            params.addQueryStringParameter("fwcs.fwzj",fwzs_fwzj.getText().toString());
//            params.addQueryStringParameter("fwcs.fws",fwzs_hxs.getText().toString());
//            params.addQueryStringParameter("fwcs.fwt",fwzs_hxt.getText().toString());
//            params.addQueryStringParameter("fwcs.fww",fwzs_hxw.getText().toString());
//            params.addQueryStringParameter("fwcs.fwzf",fwzs_hxc.getText().toString());
//            params.addQueryStringParameter("fwcs.fwlj",fwzs_fwlz.getText().toString());
//            params.addQueryStringParameter("fwcs.fwcj",fwzs_fwzc.getText().toString());
//
//            params.addQueryStringParameter("fwcs.fzfsrequest", fwzs_zjfs.getSelectedItem().toString());
//
//            params.addQueryStringParameter("gqxx.gqsl",gqxx_gqsl.getText().toString());




            if(mSelectedPhotos.size()>0) {
                for (int i = 0; i < mSelectedPhotos.size()-1; i++) {
                    Log.i("F", filepath + "a0" + i + "jpg");

                    String tmepName = null;
                    try {
                        tmepName = PictureUtil.bitmapToPath(mSelectedPhotos.get(i).getPhotoPath());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

//                   File file = new File(tmepName);
//                   FileBody fileBody = new FileBody(file);

                    //存储临时文件名
                    imgstmppath.add(tmepName);
                    list.add(new File(tmepName));
                }
//            list.add(new File(filepath1));

                for (int j = 0;j< mSelectedPhotos.size()-1; j++) {
                    params.addBodyParameter("upload[" + j + "]", list.get(j));
                }

            }



            // params.addQueryStringParameter("product.gsdz","东川");
            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, params,new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {

                    if (responseInfo.result != null) {
                        Toast.makeText( getActivity(), "发布信息成功！", Toast.LENGTH_SHORT).show();
                        //    SharedPreferencesUtil.saveData(this, url, responseInfo.result);
                        PictureUtil.deleteImgTmp(imgstmppath);
                        Intent intent = new Intent();
                        intent.setClass( getActivity(), MainActivity.class);

                        startActivity(intent);
//                        setResult(RESULT_CODE, intent);
                       // finish();

                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText( getActivity(), "发布信息失败！", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

   // @Override
//    public void onItemClicked(int position) {
//        if (position == mAdapter.getItemCount()-1) {
//            startActivity(new Intent(this, PhotosActivity.class));
//            EventBus.getDefault().postSticky(new EventEntry(mAdapter.getData(),EventEntry.SELECTED_PHOTOS_ID));
//        }
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void photosMessageEvent(EventEntry entries){
//        if (entries.id == EventEntry.RECEIVED_PHOTOS_ID) {
//            mAdapter.reloadList(entries.photos);
//            mSelectedPhotos=entries.photos;
//        }
//    }
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void photoMessageEvent(PhotoInfo entry){
//        mAdapter.appendPhoto(entry);
//    }
//    private void UpCount(final String url) {
//        if (!url.equals("")) {
//            httpUtils = new HttpUtils();
//
//            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//                @Override
//                public void onSuccess(ResponseInfo<String> responseInfo) {
//                    if (responseInfo.result != null) {
//                        SharedPreferencesUtil.saveData(this, url, responseInfo.result);
//
//
//                    }
//                }
//
//                @Override
//                public void onFailure(HttpException e, String s) {
//                    Toast.makeText(this, "数据请求失败", Toast.LENGTH_SHORT).show();
//                }
//            });
//
//        }
//    }

//    @Override      public View getView(int position, View convertView, ViewGroup arg2) {
//        if (convertView == null) {              convertView = inflater.inflate(R.layout.test_layout, null);          }
//        /**           * 状态为1、2时需要显示Item，其他情况不显示Item           */
//        int itemState = 0;          switch(itemState){
//            case 1:
//            convertView.setVisibility(View.VISIBLE);
//                break;
//            case 2:
//                convertView.setVisibility(View.VISIBLE);
//                break;
//            default://下面这段代码就是让GridView中的指定的item不显示并且不占用界面空间的方法
//                              convertView.setVisibility(View.GONE);
//                             AbsListView.LayoutParams param = new AbsListView.LayoutParams(0, 0);
//                // 将设置好的布局属性应用到GridView的Item上
//                            convertView.setLayoutParams(param);
//                                 break;
//                       }
//                  return convertView;
//                    }


    public static boolean isMobileNO(String mobiles) {
        boolean flag = false;
        try {
            //13********* ,15********,18*********
            Pattern p = Pattern
                    .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");
            Matcher m = p.matcher(mobiles);
            flag = m.matches();
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }
    boolean first = true; // 是否是第一次点击选择图片
    private GalleryFinal.OnHanlderResultCallback mOnHanlderResultCallback = new GalleryFinal.OnHanlderResultCallback() {
        @Override
        public void onHanlderSuccess(int reqeustCode, List<PhotoInfo> resultList) {
            if (resultList != null) {
                if (resultList.size() > 0) {  // 这里是清除刚开始默认的图片
                    if (first) {
                        first = false;
                        mSelectedPhotos.clear();
                    }
                }

                Iterator<PhotoInfo> sListIterator =mSelectedPhotos.iterator();  // 删除集合中特定的元素
                while(sListIterator.hasNext()){
                    PhotoInfo e =sListIterator.next();
                    if(e.getPhotoPath().equals("fly")){
                        sListIterator.remove();
                    }
                }

                mSelectedPhotos.addAll(resultList);

                PhotoInfo photoInfo = new PhotoInfo();
                photoInfo.setPhotoPath("fly");
                mSelectedPhotos.add(photoInfo);

                lAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onHanlderFailure(int requestCode, String errorMsg) {
            Toast.makeText( getActivity(), errorMsg, Toast.LENGTH_SHORT).show();
        }
    };
    @Override
    public void onClickPhotoListener() {
          FragmentManager frament=getActivity().getSupportFragmentManager();
        ActionSheet.createBuilder(getActivity(),frament)
                .setCancelButtonTitle("取消")
                .setOtherButtonTitles("打开相册", "拍照")
                .setCancelableOnTouchOutside(true)
                .setListener(new ActionSheet.ActionSheetListener() {
                    @Override
                    public void onDismiss(ActionSheet actionSheet, boolean isCancel) {}

                    @Override
                    public void onOtherButtonClick(ActionSheet actionSheet, int index) {
                        switch (index) {
                            case 0:
                                GalleryFinal.openGalleryMuti(REQUEST_CODE_GALLERY, 9, mOnHanlderResultCallback); // 多选
//                                    GalleryFinal.openGallerySingle(REQUEST_CODE_GALLERY, mOnHanlderResultCallback); // 单选
                                break;
                            case 1:
                                GalleryFinal.openCamera(REQUEST_CODE_CAMERA, mOnHanlderResultCallback); // 打开相机
                                break;
                        }
                    }
                })
                .show();
    }

//    @Override
//    public void onItemClicked(int position) {
//        if (position ==lAdapter.getItemCount()-1) {
//            startActivity(new Intent(this, PhotosActivity.class));
//            EventBus.getDefault().postSticky(new EventEntry(lAdapter.getData(),EventEntry.SELECTED_PHOTOS_ID));
//        }
//    }
//
//    @Override
//    public void onDetach() {
//        super.onDetach();
//        if (httpUtils != null) {
//       //     httpHandler.cancel();
//        }
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//
//    }
}
