package com.xiangmu.lzx.conent_frament;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
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

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.litao.android.lib.Utils.GridSpacingItemDecoration;
import com.litao.android.lib.entity.PhotoEntry;
import com.xiangmu.lzx.Bean.YueDuBean;
import com.xiangmu.lzx.CostomAdapter.ChooseAdapter;
import com.xiangmu.lzx.CostomAdapter.ProductinfoAddAdapter;
import com.xiangmu.lzx.CostomAdapter.YueDuAdapter;
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
import com.xiangmu.lzx.activitys.EventEntry;
import com.xiangmu.lzx.activitys.MainActivity;
import com.xiangmu.lzx.activitys.PhotosActivity;
import com.xiangmu.lzx.activitys.PictureActivity;
import com.xiangmu.lzx.activitys.YueDuDetialActivity;
import com.xiangmu.lzx.jieping.ScreenShot;
import com.xiangmu.lzx.pullrefreshview.PullToRefreshBase;
import com.xiangmu.lzx.pullrefreshview.PullToRefreshListView;
import com.xiangmu.lzx.utils.CommonUtil;
import com.xiangmu.lzx.utils.DateTime;
import com.xiangmu.lzx.utils.LogUtils;
import com.xiangmu.lzx.utils.MySqlOpenHelper;
import com.xiangmu.lzx.utils.PictureUtil;
import com.xiangmu.lzx.utils.ServerURL;
import com.xiangmu.lzx.utils.SharedPreferencesUtil;
import com.xiangmu.lzx.utils.Upload;
import com.xiangmu.lzx.utils.XinWenURL;
import com.xiangmu.lzx.utils.XinWenXiData;
import com.xiangmu.lzx.utils.XutilsGetData;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.ganfra.materialspinner.MaterialSpinner;

/**
 * Created by Administrator on 2015/11/9.
 */
public class AddFrament extends Fragment {
    private XinWenXiData xinWenXiData;
    private XinWenURL xinWenURL=new XinWenURL();
    private XutilsGetData xutilsGetData = new XutilsGetData();
    private List<UploadFile> potolist;
    private List<ProductArticler> liuyuanlist;
    private HttpUtils httpUtils;
    private HttpHandler<String> handler;
    private PullToRefreshListView mRecyclerView;
    private List<PhotoEntry> mSelectedPhotos=new ArrayList<PhotoEntry>();
    private List<String> listfile = new ArrayList<String>();
    private List<File> list=new ArrayList<>();
    private List<String> imgstmppath=new ArrayList<String>();
    private ProductinfoAddAdapter mAdapter;
    private ChooseAdapter lAdapter;

    private static final String[] m={"请选择类别","招聘信息","求职信息","房屋出售","房屋出租","供求信息","二手市场","其它信息","铺面信息","家居装饰"};
    // private static final List msex=new List() { };
//    ImageButton fenxiang;
    //
    private Spinner articlerSpinner = null;

    private RecyclerView recyclerView;
    private MaterialEditText name;
    private MaterialEditText productinfo_gsdz;
    private MaterialEditText productinfo_gsmz;
    private EditText productinfo_lxr;
    private EditText productinfo_lxdh;
    private MaterialEditText productinfo_sxcy;
    private EditText productinfo_content;
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
    public static  final int RESULT_CODE=101;
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        initdata();
        super.onCreate(savedInstanceState);

       View view = inflater.inflate(R.layout.productinfoadd_content, null);
             articlerSpinner = (Spinner) view.findViewById(R.id.spin_articler);
                ArrayAdapter adapter= new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_spinner_item,m);
        articlerSpinner.setAdapter(adapter);
        articlerSpinner.setSelection(1);
        productinfo_content=(EditText) view.findViewById(R.id.productinfo_content);
        //改变默认的单行模式
        productinfo_content.setSingleLine(false);
        //水平滚动设置为False
        productinfo_content.setHorizontallyScrolling(false);
        mRecyclerView =   (PullToRefreshListView)view.findViewById(R.id.refresh);
//        mAdapter = new ProductinfoAddAdapter(getActivity(),1);
//        mRecyclerView.getRefreshableView().setAdapter(mAdapter);

    // setContentView(R.layout.activity_productinfo_add);
//        EventBus.getDefault().register(getActivity());
//
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view1);


        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        recyclerView.setAdapter(lAdapter);
        recyclerView.addItemDecoration(new GridSpacingItemDecoration(5, 2, true));
//
//        name= (MaterialEditText) view.findViewById(R.id.productinfo_name);
//        productinfo_gsdz= (MaterialEditText) view.findViewById(R.id.productinfo_gsdz);
//        productinfo_gsmz= (MaterialEditText) view.findViewById(R.id.productinfo_gsmz);
        productinfo_lxr= (EditText) view.findViewById(R.id.productinfo_lxr);
        productinfo_lxdh= (EditText) view.findViewById(R.id.productinfo_lxdh);
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
//        fwzs_jzmj= (MaterialEditText) view.findViewById(R.id. productinfo_jzmj);
//        fwzs_fwzj= (MaterialEditText) view.findViewById(R.id. productinfo_fwzj);
//
//        fwzs_hxs= (MaterialEditText) view.findViewById(R.id. productinfo_hxs);
//        fwzs_hxt= (MaterialEditText) view.findViewById(R.id. productinfo_hxt);
//        fwzs_hxs= (MaterialEditText) view.findViewById(R.id. productinfo_hxs);
//        fwzs_hxw= (MaterialEditText) view.findViewById(R.id. productinfo_hxw);
//        fwzs_hxc= (MaterialEditText) view.findViewById(R.id. productinfo_hxc);
//        fwzs_fwlz= (MaterialEditText) view.findViewById(R.id. productinfo_fwlz);
//        fwzs_fwzc= (MaterialEditText) view.findViewById(R.id. productinfo_fwzc);
//
//
//        fwzs_zjfs= (MaterialSpinner) view.findViewById(R.id.spin_cjfs);
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
//        fwzs_zjfs.setAdapter(new ArrayAdapter<Sex>(getActivity(), android.R.layout.simple_spinner_item,listcjfs));
//        articlerSpinner.setSelection(0, true);
//
//
//        //给Spinner添加事件监听
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
                mAdapter = new ProductinfoAddAdapter(getActivity(),position);
                mRecyclerView.getRefreshableView().setAdapter(mAdapter);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
            }
        });
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
//        EventBus.getDefault().unregister(getActivity());
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

        fpxx=(TextView)view.findViewById(R.id.bt_fpxx);
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
//                startActivity(new Intent(getActivity(),GenTieActivity.class));
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
                //  ShareUtils.shareContent(getActivity(), xinwentitle, url);
//               System.out.println("articlerSpinnerarti="+articlerSpinner.getSelectedItemPosition()+"");
//                System.out.println("name="+name.getText()+"");
                if(articlerSpinner.getSelectedItemPosition()==0)  {

                    //   Toast.makeText(getActivity(), "请选择发布类型！！", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(getActivity()).setMessage("请选择类型！！").setPositiveButton("确定", null).show();
                    return;
                }

                if(name.getText().toString().trim().equals(""))  {
                    //   Toast.makeText(getActivity(), "请选择发布类型！！", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(getActivity()).setMessage("请输入标题！！").setPositiveButton("确定", null).show();
                    return;
                }
                if(productinfo_content.getText().toString().trim().equals(""))  {
                    //   Toast.makeText(getActivity(), "请选择发布类型！！", Toast.　).show();
                    new AlertDialog.Builder(getActivity()).setMessage("请输入详情！！").setPositiveButton("确定", null).show();
                    return;
                }
                if(!isMobileNO(productinfo_lxdh.getText().toString()))  {


                    new AlertDialog.Builder(getActivity()).setMessage("手机号输入错误！！").setPositiveButton("确定", null).show();
                    return;
                }


                String saveproduct=xinWenURL.getSaveproductinfo();
                Toast.makeText(getActivity(), "发布中.....", Toast.LENGTH_LONG).show();
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
        final CustomProgressDialog progress=new CustomProgressDialog(getActivity(),"正在加载中.....",R.drawable.donghua_frame);
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
//                new AlertDialog.Builder(ProductinfoAddActivity.getActivity()).setMessage("不能为空").setPositiveButton("确定", null).show();
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
//            //  MultiTypeAdapter adapter = new MultiTypeAdapter(ProductinfoAddActivity.getActivity());
//            // adapter.add(mockLiuyuan( liuyuanlist.size()));
//            new AlertDialog.Builder(ProductinfoAddActivity.getActivity()).setMessage("留言成功！").setPositiveButton("确定", null).show();
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
        // recyclerView.setAdapter(new SaveAdapter(getActivity(),liuyuanlist));
        //  recyclerView.setAdapter(new SaveAdapter(getActivity(),liuyuanlist));
        //  MultiTypeAdapter adapter = new MultiTypeAdapter(getActivity());
        // adapter = new MultiTypeAdapter(ProductinfoAddActivity.getActivity());
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

                Toast.makeText(getActivity(), "截屏...", Toast.LENGTH_SHORT).show();
                String date_time = DateTime.getDate_Time();
                File file = new File("sdcard/Photo/Screenshots/");
                if (!file.exists()) {
                    file.mkdirs();
                }
                Bitmap bitmap = ScreenShot.takeScreenShot(getActivity());
                String s = "sdcard/Photo/Screenshots/" + date_time;
                String path = s + ".png";
                ScreenShot.savePic(bitmap, path);
                Intent intent = new Intent(getActivity(), PictureActivity.class);
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
                // ZiTiScale.zitiStyle2(getActivity(), view);
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
        //String data = xutilsGetData.getData(getActivity(), clickcount, null);
        // String data = SharedPreferencesUtil.getData(getActivity(), clickcount, "");
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
        // XutilsGetData.xUtilsImageiv(photo.imagePicture, "http://img3.cache.netease.com/3g/2015/11/11/20151111084918c6c18.jpg",getActivity(),true);
//        BitmapUtils bitmapUtils = new BitmapUtils(getActivity());
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

//          mSelectedPhotos=Entries.photos;
            RequestParams params = new RequestParams();

            params.addQueryStringParameter("zpxx.sxcy",productinfo_sxcy.getText().toString());

//
//            params.addQueryStringParameter("zpxx.sexrequest",spinner_sex.getSelectedItem().toString());
//
//            params.addQueryStringParameter("zpxx.zpnlrequest",spinner_nl.getSelectedItem().toString());
//            params.addQueryStringParameter("zpxx.gzdx",spinner_dxfw.getSelectedItem().toString());
//            params.addQueryStringParameter("zpxx.edurequest",spinner_xl.getSelectedItem().toString());
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

                for (int j = 0;j< mSelectedPhotos.size(); j++) {
                    params.addBodyParameter("upload[" + j + "]", list.get(j));
                }

            }



            // params.addQueryStringParameter("product.gsdz","东川");
            handler = httpUtils.send(HttpRequest.HttpMethod.GET, url, params,new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {

                    if (responseInfo.result != null) {
                        Toast.makeText(getActivity(), "发布信息成功！", Toast.LENGTH_SHORT).show();
                        //    SharedPreferencesUtil.saveData(getActivity(), url, responseInfo.result);
                        PictureUtil.deleteImgTmp(imgstmppath);
                        Intent intent = new Intent();
                        intent.setClass(getActivity(), MainActivity.class);

                        startActivity(intent);
//                        setResult(RESULT_CODE, intent);
                       // finish();

                    }
                }

                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(getActivity(), "发布信息失败！", Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

   // @Override
//    public void onItemClicked(int position) {
//        if (position == mAdapter.getItemCount()-1) {
//            startActivity(new Intent(getActivity(), PhotosActivity.class));
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
//    public void photoMessageEvent(PhotoEntry entry){
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
//                        SharedPreferencesUtil.saveData(getActivity(), url, responseInfo.result);
//
//
//                    }
//                }
//
//                @Override
//                public void onFailure(HttpException e, String s) {
//                    Toast.makeText(getActivity(), "数据请求失败", Toast.LENGTH_SHORT).show();
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
