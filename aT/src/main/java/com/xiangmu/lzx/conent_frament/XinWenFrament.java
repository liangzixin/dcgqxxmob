package com.xiangmu.lzx.conent_frament;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.xiangmu.lzx.Bean.WeatherBean;
import com.xiangmu.lzx.R;
import com.xiangmu.lzx.activitys.SearchActivity;
import com.xiangmu.lzx.activitys.SecondCodeActivity;
import com.xiangmu.lzx.activitys.WeatherActivity;
import com.xiangmu.lzx.utils.ServerURL;
import com.xiangmu.lzx.utils.SharedPreferencesUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/9.
 */
public class XinWenFrament extends Fragment implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        initdata();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    private View contentview;
    private SearchView search_viewl;



    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (contentview == null) {
            contentview = initview(inflater);
        }
        return contentview;
    }

    private List<Fragment> xinwen_framentlist;

    //初始化数据
    private void initdata() {
        xinwen_framentlist = new ArrayList<>();
        TouTiaoFrament toutiao=new TouTiaoFrament();
        Bundle bundletoutiao=new Bundle();
        bundletoutiao.putString("xinwendaohang", "最新");
        toutiao.setArguments(bundletoutiao);
        xinwen_framentlist.add(toutiao);

        TouTiaoFrament yule=new TouTiaoFrament();
        Bundle bundleyule=new Bundle();
        bundleyule.putString("xinwendaohang", "招聘");
        yule.setArguments(bundleyule);
        xinwen_framentlist.add(yule);

        TouTiaoFrament tiyu=new TouTiaoFrament();
        Bundle bundletiyu=new Bundle();
        bundletiyu.putString("xinwendaohang", "求职");
        tiyu.setArguments(bundletiyu);
        xinwen_framentlist.add(tiyu);

        TouTiaoFrament caijing=new TouTiaoFrament();
        Bundle bundlecaijing=new Bundle();
        bundlecaijing.putString("xinwendaohang", "出售");
        caijing.setArguments(bundlecaijing);
        xinwen_framentlist.add(caijing);


        TouTiaoFrament keji=new TouTiaoFrament();
        Bundle bundlekeji=new Bundle();
        bundlekeji.putString("xinwendaohang","出租");
        keji.setArguments(bundlekeji);
        xinwen_framentlist.add(keji);

        TouTiaoFrament gongqiao=new TouTiaoFrament();
        Bundle bundleshishang=new Bundle();
        bundleshishang.putString("xinwendaohang","供求");
        gongqiao.setArguments(bundleshishang);
        xinwen_framentlist.add(gongqiao);

        TouTiaoFrament lishi=new TouTiaoFrament();
        Bundle bundlelishi=new Bundle();
        bundlelishi.putString("xinwendaohang", "二手");
        lishi.setArguments(bundlelishi);
        xinwen_framentlist.add(lishi);

        TouTiaoFrament caipiao=new TouTiaoFrament();
        Bundle bundlecaipiao=new Bundle();
        bundlecaipiao.putString("xinwendaohang", "其它");
        caipiao.setArguments(bundlecaipiao);
        xinwen_framentlist.add(caipiao);

        TouTiaoFrament junshi=new TouTiaoFrament();
        Bundle bundlejunshi=new Bundle();
        bundlejunshi.putString("xinwendaohang", "铺面");
        junshi.setArguments(bundlejunshi);
        xinwen_framentlist.add(junshi);

        TouTiaoFrament youxi=new TouTiaoFrament();
        Bundle bundleyouxi=new Bundle();
        bundleyouxi.putString("xinwendaohang","家具");
        youxi.setArguments(bundleyouxi);
        xinwen_framentlist.add(youxi);

    }

    private ImageButton btn_right;
    private View xuanfu_view;
    //初始化控件
    private View initview(LayoutInflater inflater) {
        View view = inflater.inflate(R.layout.xinwen_frament, null, false);

        //加载悬浮窗布局
        xuanfu_view = View.inflate(getActivity(), R.layout.popwindow_view, null);
        search_viewl = (SearchView) view.findViewById(R.id.search_viewl);
        //   search_viewl.setSubmitButtonEnabled(true);//是否显示确认搜索按钮
          search_viewl.setIconified(true);//设置搜索框默认展开
//        android:imeOptions="actionSearch" 设置点击输入法自动匹配是确认,下一条...
//        app:defaultQueryHint="请输入关键字..."  设置输入框展开默认显示文字
          search_viewl.onActionViewExpanded();//表示在内容为空时不显示取消的x按钮，内容不为空时显示.
        search_viewl.clearFocus();
        SearchView.SearchAutoComplete textView = ( SearchView.SearchAutoComplete) search_viewl.findViewById(R.id.search_src_text);
        textView .setTextSize(16);
        // 设置搜索文本监听
        textView .setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    Intent intent6 = new Intent(getActivity(), SearchActivity.class);
                    getActivity().startActivityForResult(intent6, 6);
                     getActivity().overridePendingTransition(R.anim.zcdh_alpha_in, R.anim.zcdh_set_out);
                    search_viewl.clearFocus();
                } else {
                    // 此处为失去焦点时的处理内容
                }
            }
        });
     //   textView.setClickable(true);
//        textView.setOnClickListener(new View.OnClickListener() {
//
//            @Override
//
//            public void onClick(View arg0) {
//                                                   Intent      intent = new Intent(getActivity(), SearchActivity.class);
//                                                   startActivity(intent);
//                                                   getActivity().overridePendingTransition(R.anim.zcdh_alpha_in, R.anim.zcdh_set_out);
//
//                                               }
//
//        });
                                               // inintHotWordsData();//加载热词推荐数据
        btn_right = (ImageButton) view.findViewById(R.id.btn_right);//点击该图标弹出悬浮窗
        btn_right.setOnClickListener(new View.OnClickListener() {//点击该图标弹出悬浮窗
            @Override
            public void onClick(View view) {
                inintPopWindowView(view);
            }
        });
        //新闻导航栏控件
        final RadioGroup xinwen_Rradio = (RadioGroup) view.findViewById(R.id.xinwen_radiogroup);
        final ViewPager xinwen_viewpage = (ViewPager) view.findViewById(R.id.xinwen_viewpager);
        final HorizontalScrollView xinwen_scrollView = (HorizontalScrollView) view.findViewById(R.id.xinwen_scroll);
        final TextView xinwen_indicator = (TextView) view.findViewById(R.id.xinwen_indicator);
        //新闻页面的adapter
        XinWenFragmentAdapter fragmentAdapter = new XinWenFragmentAdapter(getActivity().getSupportFragmentManager());
        xinwen_viewpage.setAdapter(fragmentAdapter);
        xinwen_viewpage.setOffscreenPageLimit(2);
        xinwen_Rradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                //选中的RadioButton播放动画
                ScaleAnimation sAnim = new ScaleAnimation(1, 1.1f, 1, 1.1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                sAnim.setDuration(500);
                sAnim.setFillAfter(true);
                //遍历所有的RadioButton
                for (int i = 0; i < group.getChildCount(); i++) {
                    RadioButton radioBtn = (RadioButton) group.getChildAt(i);
                    if (radioBtn.isChecked()) {
                        radioBtn.startAnimation(sAnim);
                    } else {
                        radioBtn.clearAnimation();
                    }
                }
                switch (checkedId) {
                    case R.id.xinwen_rb1:
                        xinwen_viewpage.setCurrentItem(0);
                        break;
                    case R.id.xinwen_rb2:
                        xinwen_viewpage.setCurrentItem(1);
                        break;
                    case R.id.xinwen_rb3:
                        xinwen_viewpage.setCurrentItem(2);
                        break;
                    case R.id.xinwen_rb4:
                        xinwen_viewpage.setCurrentItem(3);
                        break;
                    case R.id.xinwen_rb5:
                        xinwen_viewpage.setCurrentItem(4);
                        break;
                    case R.id.xinwen_rb6:
                        xinwen_viewpage.setCurrentItem(5);
                        break;
                    case R.id.xinwen_rb7:
                        xinwen_viewpage.setCurrentItem(6);
                        break;
                    case R.id.xinwen_rb8:
                        xinwen_viewpage.setCurrentItem(7);
                        break;
                    case R.id.xinwen_rb9:
                        xinwen_viewpage.setCurrentItem(8);
                        break;
                    case R.id.xinwen_rb10:
                        xinwen_viewpage.setCurrentItem(9);
                        break;
                }
            }
        });

        xinwen_viewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int arg0) {
                // 获取对应位置的RadioButton
                RadioButton radioBtn = (RadioButton) xinwen_Rradio.getChildAt(arg0);
                // 设置对应位置的RadioButton为选中的状态
                radioBtn.setChecked(true);
                /* 滚动HorizontalScrollView使选中的RadioButton处于屏幕中间位置 */
                //获取屏幕信息
                DisplayMetrics outMetrics = new DisplayMetrics();
                getActivity().getWindowManager().getDefaultDisplay().getMetrics(outMetrics);
                //获取每一个RadioButton的宽度
                int radioBtnPiexls = radioBtn.getWidth();
                //计算滚动距离
                int distance = (int) ((arg0 + 0.5) * radioBtnPiexls - outMetrics.widthPixels / 2);
                //滚动
                xinwen_scrollView.scrollTo(distance, 0);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
				/* 伴随着ViewPager的滑动，滚动指示条TextView */
                // 获取TextView在其父容器LinearLayout中的布局参数
                LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) xinwen_indicator
                        .getLayoutParams();
                params.leftMargin = (int) ((arg0 + arg1) * params.width);
                xinwen_indicator.setLayoutParams(params);
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {

            }
        });

        return view;
    }



    //新闻viewpager的填充类
    class XinWenFragmentAdapter extends FragmentPagerAdapter {
        public XinWenFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int arg0) {
            return xinwen_framentlist.get(arg0);
        }

        @Override
        public int getCount() {
            return xinwen_framentlist.size();
        }

    }



    private LinearLayout search;
    private LinearLayout shangtoutiao;
    private LinearLayout lixian;
    private LinearLayout yejian;
    private LinearLayout code;
    private LinearLayout friends;
    private LinearLayout weather_all;//天气
    private TextView tv_temperature;
    private TextView tv_date;
    private TextView tv_pm25;
    private ImageView iv_dayPictureUrl;
    private TextView tv_weather;
    private TextView tv_wind;
    private TextView tv_currentCity;
    private TextView tv_lixian;
    private TextView tv_yejian;

    WeatherBean weatherBean;
    PopupWindow popupWindow;
    private BitmapUtils bitmapUtils;
    //悬浮窗
    public void inintPopWindowView(View view){
        int width = getActivity().getWindowManager().getDefaultDisplay().getWidth();
        int height = getActivity().getWindowManager().getDefaultDisplay().getHeight();
        popupWindow = new PopupWindow(width, height-150);
        popupWindow.setAnimationStyle(R.style.AnimationFade);
        popupWindow.setContentView(xuanfu_view);
        //点击空白区关闭窗口
        popupWindow.setFocusable(true);
        popupWindow.setBackgroundDrawable(new ColorDrawable(0));
        popupWindow.showAsDropDown(view, 0, 0);
        //悬浮窗内部空间
        weather_all  = (LinearLayout) xuanfu_view.findViewById(R.id.weather_all);
        search = (LinearLayout) xuanfu_view.findViewById(R.id.search);
        shangtoutiao = (LinearLayout) xuanfu_view.findViewById(R.id.shangtoutiao);
        lixian = (LinearLayout) xuanfu_view.findViewById(R.id.lixian);
        yejian = (LinearLayout) xuanfu_view.findViewById(R.id.yejian);
        code = (LinearLayout) xuanfu_view.findViewById(R.id.code);
        friends = (LinearLayout) xuanfu_view.findViewById(R.id.friends);
        tv_lixian = (TextView) xuanfu_view.findViewById(R.id.tv_lixian);
        tv_yejian = (TextView) xuanfu_view.findViewById(R.id.tv_yejian);
        weather_all.setOnClickListener(this);
        search.setOnClickListener(this);
        shangtoutiao.setOnClickListener(this);
        lixian.setOnClickListener(this);
        yejian.setOnClickListener(this);
        code.setOnClickListener(this);
        friends.setOnClickListener(this);
        //悬浮窗天气
        tv_temperature = (TextView) xuanfu_view.findViewById(R.id.temperature);
        tv_date = (TextView) xuanfu_view.findViewById(R.id.date);
        tv_pm25 = (TextView) xuanfu_view.findViewById(R.id.pm25);
        iv_dayPictureUrl = (ImageView) xuanfu_view.findViewById(R.id.dayPictureUrl);
        tv_weather = (TextView) xuanfu_view.findViewById(R.id.weather);
        tv_wind = (TextView) xuanfu_view.findViewById(R.id.wind);
        tv_currentCity = (TextView) xuanfu_view.findViewById(R.id.currentCity);
        inintData();//加载悬浮窗中的天气数据
    }

    //悬浮窗内部控件的点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.weather_all://天气详情页面
                Intent intent = new Intent(getActivity(), WeatherActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("weatherBean", weatherBean);
                intent.putExtras(bundle);
                startActivity(intent);
                popupWindow.dismiss();
                break;
            case R.id.search:
                popupWindow.dismiss();
                intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zcdh_alpha_in, R.anim.zcdh_set_out);
                break;
            case R.id.shangtoutiao:
                popupWindow.dismiss();
          //      intent = new Intent(getActivity(), ProductinfoAddActivity.class);
            //    startActivityForResult(intent,1);
//                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.zcdh_alpha_in, R.anim.zcdh_set_out);
                break;
            case R.id.lixian:
                popupWindow.dismiss();

                if (tv_lixian.getText().equals("离线")){
                    tv_lixian.setText("取消离线");
                    Toast.makeText(getActivity(),"已开启新闻离线加载",Toast.LENGTH_SHORT).show();
                }else {
                    tv_lixian.setText("离线");
                    Toast.makeText(getActivity(),"已取消下载离线新闻",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.yejian:
                popupWindow.dismiss();

                if (tv_yejian.getText().equals("夜间")){
                    tv_yejian.setText("日间");
                    Toast.makeText(getActivity(),"已开启夜间模式",Toast.LENGTH_SHORT).show();
                }else {
                    tv_yejian.setText("夜间");
                    Toast.makeText(getActivity(),"已关闭夜间模式",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.code://扫一扫
                popupWindow.dismiss();
                startActivity(new Intent(getActivity(), SecondCodeActivity.class));
                break;
            case R.id.friends:
                popupWindow.dismiss();// TODO: 2015/11/20 加分享 标题:冒泡新闻,世界在你手中,快来加入 一起耍吧......
                //下载url:http://111.7.131.58/cache/file.ws.126.net/3g/client/netease_newsreader_android.apk?ich_args=41a669ccfae7d6b13b8232b12ec14f7b_1_0_0_3_b1a290dba9ce2d0e3b8de77017b2708b15b4d38e478ad92fd821f1c1174d1238_8a18af6a7bc5c9d6dfdd82240a39a902_1_0&ich_ip=
          //      ShareUtils.shareContent(getActivity(), "冒泡新闻,世界在你手中,快来加入 一起耍吧......","http://www.mobipop.cn/style002/images/logo.png");
                break;
        }
    }

    private void inintData() {
        String result = SharedPreferencesUtil.getData(getActivity(), ServerURL.weatherUrl, "");
        if (!TextUtils.isEmpty(result)) {
            paserData(result);
        }
        getData(ServerURL.weatherUrl);//如果无缓存再去请求网络
    }

    private void getData(final String url) {
        if (url!= null){
            HttpUtils httpUtils = new HttpUtils();
            httpUtils.send(HttpRequest.HttpMethod.GET, url, new RequestCallBack<String>() {
                @Override
                public void onSuccess(ResponseInfo<String> responseInfo) {
                    String result = responseInfo.result;
                    if (result != null) {
                        SharedPreferencesUtil.saveData(getActivity(), url,result);
                        paserData(responseInfo.result);
                    }
                }
                @Override
                public void onFailure(HttpException e, String s) {
                    Toast.makeText(getActivity(),"网络连接有误,数据请求失败...",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    //解析数据并添加
    private void paserData(String result) {
        weatherBean = new Gson().fromJson(result, WeatherBean.class);
        String currentCity = weatherBean.results.get(0).currentCity;
        String pm25 = weatherBean.results.get(0).pm25;
        String date = weatherBean.results.get(0).weather_data.get(0).date;
        String dayPictureUrl = weatherBean.results.get(0).weather_data.get(0).dayPictureUrl;
        String weather = weatherBean.results.get(0).weather_data.get(0).weather;
        String wind = weatherBean.results.get(0).weather_data.get(0).wind;
        String temperature = weatherBean.results.get(0).weather_data.get(0).temperature;
        tv_temperature.setText(temperature);
        tv_date.setText(date);
        tv_pm25.setText("PM2.5: "+pm25);
        tv_weather.setText(weather);
        tv_wind.setText(wind);
        tv_currentCity.setText(currentCity);
        bitmapUtils = new BitmapUtils(getActivity());
        bitmapUtils.display(iv_dayPictureUrl,dayPictureUrl);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (bitmapUtils != null){
            bitmapUtils.cancel();
        }
    }
}
