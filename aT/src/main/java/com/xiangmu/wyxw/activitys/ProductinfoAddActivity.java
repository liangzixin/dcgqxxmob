package com.xiangmu.wyxw.activitys;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.MaterialEditText;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import android.widget.AdapterView.OnItemSelectedListener;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.litao.android.lib.Utils.GridSpacingItemDecoration;
import com.litao.android.lib.entity.PhotoEntry;
import com.twiceyuan.commonadapter.library.ViewId;
import com.twiceyuan.commonadapter.library.adapter.MultiTypeAdapter;
import com.xiangmu.wyxw.CostomAdapter.ChooseAdapter;
import com.xiangmu.wyxw.CostomAdapter.SaveAdapter;
import com.xiangmu.wyxw.CostomProgressDialog.CustomProgressDialog;
import com.xiangmu.wyxw.Modle.Dxfw;
import com.xiangmu.wyxw.Modle.Edu;
import com.xiangmu.wyxw.Modle.Fzfs;
import com.xiangmu.wyxw.Modle.Liuyuan;
import com.xiangmu.wyxw.Modle.ProductArticler;
import com.xiangmu.wyxw.Modle.ProductCategory;
import com.xiangmu.wyxw.Modle.Sex;
import com.xiangmu.wyxw.Modle.UploadFile;
import com.xiangmu.wyxw.Modle.Zpnl;
import com.xiangmu.wyxw.Modle.Zpxx;
import com.xiangmu.wyxw.R;
import com.xiangmu.wyxw.Setting_Utils.ShareUtils;
import com.xiangmu.wyxw.Setting_Utils.ZiTiScale;
import com.xiangmu.wyxw.holder.ArticleHolder;
import com.xiangmu.wyxw.holder.PhotoHolder;
import  com.xiangmu.wyxw.Modle.Article;
import com.xiangmu.wyxw.Modle.Photo;
import com.xiangmu.wyxw.holder.ProductArticleHolder;
import com.xiangmu.wyxw.holder.ProductinfoAddHolder;
import com.xiangmu.wyxw.jieping.ScreenShot;
import com.xiangmu.wyxw.utils.DateTime;
import com.xiangmu.wyxw.utils.LogUtils;
import com.xiangmu.wyxw.utils.MySqlOpenHelper;
import com.xiangmu.wyxw.utils.PictureUtil;
import com.xiangmu.wyxw.utils.ServerURL;
import com.xiangmu.wyxw.utils.SharedPreferencesUtil;
import com.xiangmu.wyxw.utils.Upload;
import com.xiangmu.wyxw.utils.XinWenURL;
import com.xiangmu.wyxw.utils.XinWenXiData;
import com.xiangmu.wyxw.utils.XutilsGetData;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.entity.mime.content.FileBody;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.ganfra.materialspinner.MaterialSpinner;

public class ProductinfoAddActivity extends AppCompatActivity implements ChooseAdapter.OnItmeClickListener {
    private XinWenXiData xinWenXiData;
    private XinWenURL xinWenURL=new XinWenURL();
    private XutilsGetData xutilsGetData = new XutilsGetData();
    private List<UploadFile> potolist;
    private List<ProductArticler> liuyuanlist;
    private HttpUtils httpUtils;
    private HttpHandler<String> handler;
    private RecyclerView mRecyclerView;
    private List<PhotoEntry> mSelectedPhotos;
    private List<String> listfile = new ArrayList<String>();
    private List<File> list=new ArrayList<>();
    private List<String> imgstmppath=new ArrayList<String>();
    private ChooseAdapter mAdapter;
    private static final String[] m={"请选择类别","招聘信息","求职信息","房屋出售","房屋出租","供求信息","二手市场","其它信息","铺面信息","家居装饰"};
    // private static final List msex=new List() { };
//    ImageButton fenxiang;
    //
    private Spinner articlerSpinner = null;
    private Spinner spinner_sex = null;

    private Spinner spinner_dxfw= null;

    private Spinner spinner_nl= null;
    private Spinner spinner_xl= null;

    private RecyclerView recyclerView;
    private MaterialEditText name;
    private MaterialEditText productinfo_gsdz;
    private MaterialEditText productinfo_gsmz;
    private MaterialEditText productinfo_lxr;
    private MaterialEditText productinfo_lxdh;
    private MaterialEditText productinfo_sxcy;
    private MaterialEditText productinfo_content;
    private MaterialEditText productinfo_qjnl;


    private MaterialEditText fwzs_jzmj;
    private MaterialEditText fwzs_fwzj;
//    private MaterialEditText fwzs_fwcj;
    private MaterialEditText fwzs_hxs;
    private MaterialEditText fwzs_hxt;
    private MaterialEditText fwzs_hxw;
    private MaterialEditText fwzs_hxc;
    private MaterialEditText fwzs_fwlz;
    private MaterialEditText fwzs_fwzc;
    private Spinner fwzs_zjfs;


    private MaterialEditText gqxx_gqsl;
    private MaterialEditText gqxx_spsj;
    LinearLayout category2;
    LinearLayout category3;
    private String filepath;
    private String filepath1;
    private TextView fpxx;

    //    @ViewId(R.id.productinfo_content)  MaterialEditText productinfo_content;
 //@ViewId(R.id.productinfo_sxcy) public MaterialEditText productinfo_sxcy;
//    @ViewId(R.id.productinfo_qjnl)  MaterialEditText productinfo_qjnl;
   // @ViewId(R.id.spin_sex2) public MaterialSpinner spin_sex2;
//    @ViewId(R.id.spin_dxfw2) public MaterialSpinner spin_dxfw2;
//    @ViewId(R.id.spin_xl2) public MaterialSpinner spin_xl2;
//        @ViewId(R.id.category3) public LinearLayout category3;
//        @ViewId(R.id.category4) public LinearLayout category4;
    //   MultiTypeAdapter adapter;
    // MultiTypeAdapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productinfo_add);
        EventBus.getDefault().register(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view1);
        mAdapter = new ChooseAdapter(this);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 5));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.addItemDecoration(new GridSpacingItemDecoration(5, 2, true));

//        @ViewId(R.id.category1) public LinearLayout category1;
//        @ViewId(R.id.category2) public LinearLayout category2;
//        @ViewId(R.id.category3) public LinearLayout category3;
//        @ViewId(R.id.category4) public LinearLayout category4;


        name= (MaterialEditText) findViewById(R.id.productinfo_name);
        productinfo_gsdz= (MaterialEditText) findViewById(R.id.productinfo_gsdz);
        productinfo_gsmz= (MaterialEditText) findViewById(R.id.productinfo_gsmz);
        productinfo_lxr= (MaterialEditText) findViewById(R.id.productinfo_lxr);
        productinfo_lxdh= (MaterialEditText) findViewById(R.id.productinfo_lxdh);

        articlerSpinner = (MaterialSpinner) findViewById(R.id.spin_articler);
        productinfo_content= (MaterialEditText) findViewById(R.id.productinfo_content);


        spinner_sex = (MaterialSpinner) findViewById(R.id.spin_sex);
        spinner_dxfw= (MaterialSpinner) findViewById(R.id.spin_dxfw);
        spinner_nl= (MaterialSpinner) findViewById(R.id.spin_nl);
        spinner_xl= (MaterialSpinner) findViewById(R.id.spin_xl);



        category2=(LinearLayout) findViewById(R.id.category2);
        category3=(LinearLayout) findViewById(R.id.category3);





        productinfo_sxcy= (MaterialEditText) findViewById(R.id. productinfo_sxcy);
        productinfo_qjnl= (MaterialEditText) findViewById(R.id. productinfo_qjnl);


       fwzs_jzmj= (MaterialEditText) findViewById(R.id. productinfo_jzmj);
       fwzs_fwzj= (MaterialEditText) findViewById(R.id. productinfo_fwzj);

        fwzs_hxs= (MaterialEditText) findViewById(R.id. productinfo_hxs);
        fwzs_hxt= (MaterialEditText) findViewById(R.id. productinfo_hxt);
        fwzs_hxs= (MaterialEditText) findViewById(R.id. productinfo_hxs);
        fwzs_hxw= (MaterialEditText) findViewById(R.id. productinfo_hxw);
        fwzs_hxc= (MaterialEditText) findViewById(R.id. productinfo_hxc);
        fwzs_fwlz= (MaterialEditText) findViewById(R.id. productinfo_fwlz);
        fwzs_fwzc= (MaterialEditText) findViewById(R.id. productinfo_fwzc);


        fwzs_zjfs= (MaterialSpinner) findViewById(R.id.spin_cjfs);

        gqxx_gqsl= (MaterialEditText) findViewById(R.id.gqxx_gqsl);
        gqxx_spsj= (MaterialEditText) findViewById(R.id.gqxx_spsj);
      List  msex=Sex.getValues();
        List  dxfw= Dxfw.getValues();
        List  nl= Zpnl.getValues();
        List  xl= Edu.getValues();
        List  listcjfs= Fzfs.getValues();
        ArrayAdapter adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,m);
        articlerSpinner.setAdapter(adapter);
//        adapter= new ArrayAdapter<String>(this,
//                android.R.layout.simple_spinner_item,Sex.values());
//        spinner_sex.setAdapter(adapter);
        spinner_sex.setAdapter(new ArrayAdapter<Sex>(this, android.R.layout.simple_spinner_item,msex));


        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,dxfw);
        spinner_dxfw.setAdapter(adapter);

        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,nl);
        spinner_nl.setAdapter(adapter);
        adapter= new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item,xl);

        spinner_xl.setAdapter(adapter);

        fwzs_zjfs.setAdapter(new ArrayAdapter<Sex>(this, android.R.layout.simple_spinner_item,listcjfs));
        articlerSpinner.setSelection(0, true);


        //给Spinner添加事件监听
        articlerSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            //当选中某一个数据项时触发该方法
            /*
             * parent接收的是被选择的数据项所属的 Spinner对象，
             * view参数接收的是显示被选择的数据项的TextView对象
             * position接收的是被选择的数据项在适配器中的位置
             * id被选择的数据项的行号
             */
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //System.out.println(spinner==parent);//true
                //System.out.println(view);
                //String data = adapter.getItem(position);//从适配器中获取被选择的数据项
                //String data = list.get(position);//从集合中获取被选择的数据项
              //  int data =(int)articlerSpinner.getItemAtPosition(position);//从spinner中获取被选择的数据
             //   int data =1;
                switch (position) {
                    case 0:
                    case 1:
                        name.setHint("招聘标题");
                        spinner_nl.setVisibility(View.VISIBLE);
                        productinfo_gsdz.setVisibility(View.VISIBLE);
                        productinfo_gsmz.setVisibility(View.VISIBLE);

                        productinfo_sxcy.setVisibility(View.GONE);
                       spinner_nl.setVisibility(View.VISIBLE);
                       productinfo_qjnl.setVisibility(View.GONE);
                        spinner_sex.setVisibility(View.VISIBLE);
                        spinner_dxfw.setVisibility(View.VISIBLE);
                        spinner_xl.setVisibility(View.VISIBLE);
                        category2.setVisibility(View.VISIBLE);
                        gqxx_gqsl.setVisibility(View.GONE);
                        gqxx_spsj.setVisibility(View.GONE);

                        category3.setVisibility(View.GONE);
                        break;
                    case 2:
                     name.setHint("求职标题");


                        productinfo_sxcy.setVisibility(View.VISIBLE);
                        productinfo_gsdz.setVisibility(View.GONE);
                        productinfo_gsmz.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                        LinearLayout.LayoutParams sp_params = new LinearLayout.LayoutParams(

                                GridLayoutManager.LayoutParams.WRAP_CONTENT, GridLayoutManager.LayoutParams.WRAP_CONTENT);

                        sp_params.width =1;

                        spinner_nl.setLayoutParams(sp_params);
                        productinfo_qjnl.setVisibility(View.VISIBLE);
                        spinner_sex.setVisibility(View.VISIBLE);
                        spinner_dxfw.setVisibility(View.VISIBLE);
                        spinner_xl.setVisibility(View.VISIBLE);
                        category2.setVisibility(View.VISIBLE);
                        gqxx_gqsl.setVisibility(View.GONE);
                        gqxx_spsj.setVisibility(View.GONE);
                        category3.setVisibility(View.GONE);
                        break;
                    case 3:
                        name.setHint("房屋出售标题");
                        productinfo_gsmz.setHint("小区名称");
                        productinfo_gsmz.setVisibility(View.VISIBLE);
                        productinfo_gsdz.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                        productinfo_qjnl.setVisibility(View.GONE);
                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        category2.setVisibility(View.GONE);
                        category3.setVisibility(View.VISIBLE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        fwzs_zjfs.setVisibility(View.GONE);
                        fwzs_fwzj.setVisibility(View.VISIBLE);

//                        LinearLayout.LayoutParams sp_params1 = new LinearLayout.LayoutParams(
//
//                                GridLayoutManager.LayoutParams.WRAP_CONTENT, GridLayoutManager.LayoutParams.WRAP_CONTENT);
//
//                        sp_params1.width = 10;
//                        fwzs_fwcj.setLayoutParams(sp_params1);
                        fwzs_fwzj.setHint("总价(万元)");
                        gqxx_gqsl.setVisibility(View.GONE);
                        gqxx_spsj.setVisibility(View.GONE);
                        category2.setVisibility(View.GONE);
                        break;
                    case 4:
                        name.setHint("房屋出租标题");
                     fwzs_fwzj.setHint("租金(元/月)");
                        productinfo_gsmz.setHint("小区名称");
                        productinfo_gsmz.setVisibility(View.VISIBLE);
                        productinfo_gsdz.setVisibility(View.GONE);
                        spinner_nl.setVisibility(View.GONE);
                        productinfo_qjnl.setVisibility(View.GONE);
                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        category2.setVisibility(View.GONE);
                        category3.setVisibility(View.VISIBLE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        fwzs_zjfs.setVisibility(View.VISIBLE);
                        fwzs_fwzj.setVisibility(View.VISIBLE);

                        fwzs_fwzc.setVisibility(View.VISIBLE);
                        gqxx_gqsl.setVisibility(View.GONE);
                        gqxx_spsj.setVisibility(View.GONE);
                        category2.setVisibility(View.GONE);
                        break;
                    case 5:
                        name.setHint("供求信息标题");
                        productinfo_gsmz.setHint("公司名称");
                        productinfo_gsmz.setVisibility(View.VISIBLE);
                        productinfo_gsdz.setVisibility(View.VISIBLE);
                        spinner_nl.setVisibility(View.GONE);
                        productinfo_qjnl.setVisibility(View.GONE);
                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        category2.setVisibility(View.GONE);
                        category3.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        fwzs_zjfs.setVisibility(View.VISIBLE);
                        fwzs_fwzj.setVisibility(View.GONE);
                        gqxx_gqsl.setVisibility(View.VISIBLE);
                        gqxx_spsj.setVisibility(View.GONE);
                        break;
                    case 6:
                        name.setHint("二手信息标题");
                        productinfo_gsmz.setHint("成色");
                        productinfo_gsdz.setHint("具体位置");
                        productinfo_gsmz.setVisibility(View.VISIBLE);
                        productinfo_gsdz.setVisibility(View.VISIBLE);
                        spinner_nl.setVisibility(View.GONE);
                        productinfo_qjnl.setVisibility(View.GONE);
                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        category2.setVisibility(View.GONE);
                        category3.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        fwzs_zjfs.setVisibility(View.VISIBLE);
                        fwzs_fwzj.setVisibility(View.GONE);
                        gqxx_gqsl.setHint("供应数量");
                        gqxx_gqsl.setVisibility(View.VISIBLE);
                        gqxx_spsj.setVisibility(View.GONE);
                        break;
                    case 7:
                        name.setHint("其它信息标题");
                        productinfo_gsmz.setHint("公司名称");
                        productinfo_gsdz.setHint("公司地址");
                        productinfo_gsmz.setVisibility(View.VISIBLE);
                        productinfo_gsdz.setVisibility(View.VISIBLE);
                        spinner_nl.setVisibility(View.GONE);
                        productinfo_qjnl.setVisibility(View.GONE);
                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        category2.setVisibility(View.GONE);
                        category3.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        fwzs_zjfs.setVisibility(View.VISIBLE);
                        fwzs_fwzj.setVisibility(View.GONE);

                        gqxx_gqsl.setVisibility(View.GONE);
                        gqxx_spsj.setVisibility(View.GONE);
                        break;
                    case 8:
                        name.setHint("铺面信息标题");

                        productinfo_gsdz.setHint("铺面地址");
                        productinfo_gsmz.setVisibility(View.GONE);
                        productinfo_gsdz.setVisibility(View.VISIBLE);
                        spinner_nl.setVisibility(View.GONE);
                        productinfo_qjnl.setVisibility(View.GONE);
                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        category2.setVisibility(View.GONE);
                        category3.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        fwzs_zjfs.setVisibility(View.VISIBLE);
                        fwzs_fwzj.setVisibility(View.GONE);

                        gqxx_gqsl.setVisibility(View.GONE);
                        gqxx_spsj.setVisibility(View.GONE);
                        break;
                    case 9:
                        name.setHint("家具建材信息标题");
                        productinfo_gsmz.setHint("家居建材商店名称");
                        productinfo_gsdz.setHint("家居建材地址");
                        productinfo_gsmz.setVisibility(View.VISIBLE);
                        productinfo_gsdz.setVisibility(View.VISIBLE);
                        spinner_nl.setVisibility(View.GONE);
                        productinfo_qjnl.setVisibility(View.GONE);
                        spinner_sex.setVisibility(View.GONE);
                        spinner_dxfw.setVisibility(View.GONE);
                        spinner_xl.setVisibility(View.GONE);
                        category2.setVisibility(View.GONE);
                        category3.setVisibility(View.GONE);
                        productinfo_sxcy.setVisibility(View.GONE);
                        fwzs_zjfs.setVisibility(View.VISIBLE);
                        fwzs_fwzj.setVisibility(View.GONE);

                        gqxx_gqsl.setVisibility(View.VISIBLE);
                        gqxx_spsj.setVisibility(View.VISIBLE);
                        break;
//                    case 3:
//                        category3.setVisibility(View.VISIBLE);
//                        break;
//                    case 4:
//                        category4.setVisibility(View.VISIBLE);
//                        break;
                }
//                Toast.makeText(MainActivity.this, data, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
        fwzs_jzmj.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ( fwzs_jzmj.getText().toString().indexOf(".") >= 0) {
                    if ( fwzs_jzmj.getText().toString().indexOf(".",  fwzs_jzmj.getText().toString().indexOf(".") + 1) > 0) {
                      //  tv_numOfChar.setText("已经输入\".\"不能重复输入");
                        fwzs_jzmj.setText( fwzs_jzmj.getText().toString().substring(0, fwzs_jzmj.getText().toString().length() - 1));
                        fwzs_jzmj.setSelection( fwzs_jzmj.getText().toString().length());
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        fwzs_fwzj.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ( fwzs_fwzj.getText().toString().indexOf(".") >= 0) {
                    if ( fwzs_fwzj.getText().toString().indexOf(".",fwzs_fwzj.getText().toString().indexOf(".") + 1) > 0) {
                        //  tv_numOfChar.setText("已经输入\".\"不能重复输入");
                        fwzs_fwzj.setText( fwzs_fwzj.getText().toString().substring(0,fwzs_fwzj.getText().toString().length() - 1));
                        fwzs_fwzj.setSelection( fwzs_fwzj.getText().toString().length());
                    }

                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //      Intent intent = getIntent();
//        xinWenXiData = (XinWenXiData) intent.getSerializableExtra("xinwendata");
//        potolist=(List<UploadFile>)getIntent().getSerializableExtra("potolist");
//        liuyuanlist=(List<ProductArticler>)getIntent().getSerializableExtra("liuyuanlist");
//          recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        initDate();
        try {
            initview();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //    assert recyclerView != null;

//          recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));


//       MultiTypeAdapter adapter1 = new MultiTypeAdapter(this);
//        adapter = new MultiTypeAdapter(this);
//        //  adapter1 = new MultiTypeAdapter(this);
//        // 注册两种 ViewType，对应两种数据类型（必须在设置到 RecyclerView 上之前注册！）
//        adapter.registerViewType(Photo.class, PhotoHolder.class);
//        adapter1.registerViewType(Article.class, ProductinfoAddHolder.class);
//        adapter.registerViewType(Liuyuan.class, ProductArticleHolder.class);
//
//        recyclerView.setAdapter(adapter1);
//        recyclerView.setAdapter(adapter1);
//      adapter1.add(mockArticle(0));
//
//        for (int i = 0; i <((List<UploadFile>)potolist.get(0)).size(); i++) {
//
//            adapter.add(mockPhoto(i));
//        }
//        for (int i = 0; i <((List<ProductArticler>)liuyuanlist.get(0)).size(); i++) {
//
//            adapter.add(mockLiuyuan(i));
//        }
        //   button.setOnClickListener(c);
    }
    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
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
        imageback = (ImageButton) findViewById(R.id.xinwen_xi_back);//返回

        fpxx=(TextView)findViewById(R.id.bt_fpxx);
//        TextView duotu_gentie = null;
//        duotu_gentie = (TextView) findViewById(R.id.xinwen_duotu_gentie);//跟帖
        ImageButton caidan = null;
        caidan = (ImageButton) findViewById(R.id.xinwen_xi_kuanzhan_caidan);//菜单
//        webView = null;
//        webView = (WebView) findViewById(R.id.xinwen_xi_text_webview);
//        duotu_gentie.setText(xinWenXiData.getReplaycount() + "跟帖");
//        fenxiang = (ImageButton) findViewById(R.id.xinwen_xi_fenxiang);
        // getdata(url);//获得数据
        //点击finish
        imageback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //点击进入跟帖 详细页面
        //// TODO: 2015/11/14 点击进入跟帖 详细页面 完成
//        duotu_gentie.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(ProductinfoAddActivity.this,GenTieActivity.class));
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
              //  ShareUtils.shareContent(ProductinfoAddActivity.this, xinwentitle, url);
//               System.out.println("articlerSpinnerarti="+articlerSpinner.getSelectedItemPosition()+"");
//                System.out.println("name="+name.getText()+"");
              if(articlerSpinner.getSelectedItemPosition()==0)  {

               //   Toast.makeText(ProductinfoAddActivity.this, "请选择发布类型！！", Toast.LENGTH_SHORT).show();
                  new AlertDialog.Builder(ProductinfoAddActivity.this).setMessage("请选择类型！！").setPositiveButton("确定", null).show();
              return;
              }

                if(name.getText().toString().trim().equals(""))  {
                    //   Toast.makeText(ProductinfoAddActivity.this, "请选择发布类型！！", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(ProductinfoAddActivity.this).setMessage("请输入标题！！").setPositiveButton("确定", null).show();
                    return;
                }
                if(productinfo_content.getText().toString().trim().equals(""))  {
                    //   Toast.makeText(ProductinfoAddActivity.this, "请选择发布类型！！", Toast.　).show();
                    new AlertDialog.Builder(ProductinfoAddActivity.this).setMessage("请输入详情！！").setPositiveButton("确定", null).show();
                    return;
                }
                if(productinfo_lxdh.getText().toString().trim().equals(""))  {

                    new AlertDialog.Builder(ProductinfoAddActivity.this).setMessage("请输入联系电话！！").setPositiveButton("确定", null).show();
                    return;
                }

               String saveproduct=xinWenURL.getSaveproductinfo();
                Toast.makeText(ProductinfoAddActivity.this, "发布中.....", Toast.LENGTH_LONG).show();
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
        final CustomProgressDialog progress=new CustomProgressDialog(this,"正在加载中.....",R.anim.donghua_frame);
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


//        String data = XutilsGetData.getData(getActivity(), url, null);
//        //判断本地数据是否存在  如果没有网络请求
//        if (data != null) {
//            getshowdata(data);
//        } else {
//            XutilsGetData.xUtilsHttp(getActivity(), url, url, new XutilsGetData.CallBackHttp() {
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
        // recyclerView.setAdapter(new SaveAdapter(ProductinfoAddActivity.this,liuyuanlist));
        //  recyclerView.setAdapter(new SaveAdapter(ProductinfoAddActivity.this,liuyuanlist));
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
        final PopupWindow popu = new PopupWindow((int) (getWindowManager().getDefaultDisplay().getWidth() / 2.5), getWindowManager().getDefaultDisplay().getHeight() / 2);
        View view = View.inflate(this, R.layout.popwindow_detial, null);
        Button shouchang = (Button) view.findViewById(R.id.bt_save);
        Button jieping = (Button) view.findViewById(R.id.jieping);
        Button ziti = (Button) view.findViewById(R.id.ziti);
        Button yejian = (Button) view.findViewById(R.id.bt_yejian);
        //收藏按钮
        shouchang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popu.dismiss();
                // TODO: 2015/11/17

            }
        });
        //截屏按钮
        jieping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popu.dismiss();
                // TODO: 2015/11/17

                Toast.makeText(ProductinfoAddActivity.this, "截屏...", Toast.LENGTH_SHORT).show();
                String date_time = DateTime.getDate_Time();
                File file = new File("sdcard/Photo/Screenshots/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                Bitmap bitmap = ScreenShot.takeScreenShot(ProductinfoAddActivity.this);
                String s = "sdcard/Photo/Screenshots/" + date_time;
                String path = s + ".png";
                ScreenShot.savePic(bitmap, path);
                Intent intent = new Intent(ProductinfoAddActivity.this, PictureActivity.class);
                intent.putExtra("path", s);
                startActivity(intent);

            }
        });
        //改变字体按钮
        ziti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popu.dismiss();
                // TODO: 2015/11/17
                // ZiTiScale.zitiStyle2(ProductinfoAddActivity.this, view);
            }
        });
        //夜间模式按钮
        yejian.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popu.dismiss();
                // TODO: 2015/11/17
            }
        });
        popu.setContentView(view);
        popu.setFocusable(true);
        popu.setBackgroundDrawable(new ColorDrawable(0));
        popu.showAsDropDown(v, 0, 0);
    }


    public void initDate() {
        String url = xinWenXiData.getUrl();//获得详细页面的url      //分享用
        String xinwentitle = xinWenXiData.getTitle();//获得新闻标题     //分享用
        int replaycount = xinWenXiData.getReplaycount();//获得跟帖数目  //收藏用
        String clickcount=xinWenURL.getClickcount()+xinWenXiData.getId();
        String clickcount0=xinWenURL.getCount();
        //String data = xutilsGetData.getData(ProductinfoAddActivity.this, clickcount, null);
        // String data = SharedPreferencesUtil.getData(this, clickcount, "");
    //    UpData(clickcount);
       // UpData(clickcount0);
        //   UpCount(clickcount0);
        System.out.println("clickcount="+clickcount );
        Log.e("aa", "******xinwentitle*******" + xinwentitle);
        //拿到当前日期
        String date = DateTime.getDate();
        MySqlOpenHelper mySqlOpenHelper = new MySqlOpenHelper(this);
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
        filepath= Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator;
        filepath1= Environment.getExternalStorageDirectory().getAbsolutePath()+File.separator+"abc.txt";
        FileInputStream fis = null;//文件输入流
        try {
            fis = new FileInputStream(new File(filepath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        if (!url.equals("")) {
            httpUtils = new HttpUtils();

//          mSelectedPhotos=Entries.photos;
            RequestParams params = new RequestParams();

            params.addQueryStringParameter("zpxx.sxcy",productinfo_sxcy.getText().toString());


          params.addQueryStringParameter("zpxx.sexrequest",spinner_sex.getSelectedItem().toString());

            params.addQueryStringParameter("zpxx.zpnlrequest",spinner_nl.getSelectedItem().toString());
            params.addQueryStringParameter("zpxx.gzdx",spinner_dxfw.getSelectedItem().toString());
            params.addQueryStringParameter("zpxx.edurequest",spinner_xl.getSelectedItem().toString());
            params.addQueryStringParameter("zpxx.qjnl",productinfo_qjnl.getText().toString());

          params.addQueryStringParameter("name",name.getText().toString());
            params.addQueryStringParameter("gsdz",productinfo_gsdz.getText().toString());
            params.addQueryStringParameter("gsmz",productinfo_gsmz.getText().toString());
            params.addQueryStringParameter("lxr",productinfo_lxr.getText().toString());
            params.addQueryStringParameter("lxdh",productinfo_lxdh.getText().toString());
            params.addQueryStringParameter("categoryId",articlerSpinner.getSelectedItemPosition()+"");
            params.addQueryStringParameter("description",productinfo_content.getText().toString());


            params.addQueryStringParameter("fwcs.jzmj",fwzs_jzmj.getText().toString());
            params.addQueryStringParameter("fwcs.fwzj",fwzs_fwzj.getText().toString());
            params.addQueryStringParameter("fwcs.fws",fwzs_hxs.getText().toString());
            params.addQueryStringParameter("fwcs.fwt",fwzs_hxt.getText().toString());
            params.addQueryStringParameter("fwcs.fww",fwzs_hxw.getText().toString());
            params.addQueryStringParameter("fwcs.fwzf",fwzs_hxc.getText().toString());
            params.addQueryStringParameter("fwcs.fwlj",fwzs_fwlz.getText().toString());
            params.addQueryStringParameter("fwcs.fwcj",fwzs_fwzc.getText().toString());

               params.addQueryStringParameter("fwcs.fzfsrequest", fwzs_zjfs.getSelectedItem().toString());

            params.addQueryStringParameter("gqxx.gqsl",gqxx_gqsl.getText().toString());


           if(mSelectedPhotos.size()>0) {
               for (int i = 0; i < mSelectedPhotos.size(); i++) {
                   Log.i("F", filepath + "a0" + i + "jpg");

                   String tmepName = null;
                   try {
                       tmepName = PictureUtil.bitmapToPath(mSelectedPhotos.get(i).getPath());
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

               for (int i = 0; i < mSelectedPhotos.size(); i++) {
                   params.addBodyParameter("upload[" + i + "]", list.get(i));
               }
           }



            // params.addQueryStringParameter("product.gsdz","东川");
            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, params,new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    if (responseInfo.result != null) {
                        Toast.makeText(ProductinfoAddActivity.this, "发布信息成功！", Toast.LENGTH_SHORT).show();
                    //    SharedPreferencesUtil.saveData(ProductinfoAddActivity.this, url, responseInfo.result);
                        PictureUtil.deleteImgTmp(imgstmppath);
                        Intent intent = new Intent();
                        intent.setClass(ProductinfoAddActivity.this, MainActivity.class);

                        startActivity(intent);
                        finish();

                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(ProductinfoAddActivity.this, "发布信息失败！", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    @Override
    public void onItemClicked(int position) {
        if (position == mAdapter.getItemCount()-1) {
            startActivity(new Intent(ProductinfoAddActivity.this, PhotosActivity.class));
            EventBus.getDefault().postSticky(new EventEntry(mAdapter.getData(),EventEntry.SELECTED_PHOTOS_ID));
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photosMessageEvent(EventEntry entries){
        if (entries.id == EventEntry.RECEIVED_PHOTOS_ID) {
            mAdapter.reloadList(entries.photos);
            mSelectedPhotos=entries.photos;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void photoMessageEvent(PhotoEntry entry){
        mAdapter.appendPhoto(entry);
    }
//    private void UpCount(final String url) {
//        if (!url.equals("")) {
//            httpUtils = new HttpUtils();
//
//            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
//                @Override
//                public void onSuccess(ResponseInfo<String> responseInfo) {
//                    if (responseInfo.result != null) {
//                        SharedPreferencesUtil.saveData(ProductinfoAddActivity.this, url, responseInfo.result);
//
//
//                    }
//                }
//
//                @Override
//                public void onFailure(HttpException e, String s) {
//                    Toast.makeText(ProductinfoAddActivity.this, "数据请求失败", Toast.LENGTH_SHORT).show();
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

}

