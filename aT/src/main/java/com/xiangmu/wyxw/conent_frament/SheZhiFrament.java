package com.xiangmu.wyxw.conent_frament;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.xiangmu.wyxw.Modle.Shezhi;
import com.xiangmu.wyxw.R;
import com.xiangmu.wyxw.Setting_Utils.SearchDB;
import com.xiangmu.wyxw.Setting_Utils.TouXiangCache;
import com.xiangmu.wyxw.activitys.GenTieActivity;
import com.xiangmu.wyxw.activitys.LoginActivity;
import com.xiangmu.wyxw.activitys.ReadChievement;
import com.xiangmu.wyxw.activitys.ReadHistoryActivity;
import com.xiangmu.wyxw.activitys.Setting_Collection;
import com.xiangmu.wyxw.activitys.Setting_GlodPage;
import com.xiangmu.wyxw.activitys.Setting_glodmall;
import com.xiangmu.wyxw.activitys.Setting_headpage;
import com.xiangmu.wyxw.activitys.Setting_my_Task;
import com.xiangmu.wyxw.activitys.Setting_set_page;
import com.xiangmu.wyxw.utils.XutilsGetData;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.json.JSONException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/9.
 */
public class SheZhiFrament extends Fragment implements View.OnClickListener {
    //判断是否登录,获得登录后,阅读的篇数;
    private int number;
    private String emailbox;
    //所有监听的控件
    static ImageView picture;
    static TextView userName,jinbiCount,readnumbe2,readercount2,readerCount;

    TextView setting, userlevel, read, messageText, goldMallText, myTaskText, myWalletText, mymailboxText;
    ImageView readNumber, collectNumber, gentieNumber;
    static ImageView goldNumber;
    RelativeLayout reader, collect, Thread, gold;
    LinearLayout myMessage, goldMall, myTask, myWallet, mymailbox;
    String emailAddress;
    View view;
    //判断登录的标记
    static boolean flag;
    private static String user_name;
    private String pic_path;
    private  static  String  jinbi;
    private String  shezhi;
    private  static int read0=0;
    private static  int conllect0=0;
    private  static int gentie0=0;
//    private  String result="";
    private static Gson gson = new Gson();
    List<Shezhi> listshezhi=new ArrayList<Shezhi>();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        try {
            if (view == null) {
                view = inFlater(inflater);
            }
            return view;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public View inFlater(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.hgz_activity_main_fragment, null, false);
        initView(view);
    shezhi= SearchDB.createDb(getActivity(), "shezhi");

        user_name = SearchDB.createDb(getActivity(), "userName");
        jinbi = SearchDB.createDb(getActivity(), "jinbi");
        shezhi = SearchDB.createDb(getActivity(), "shezhi");

        Log.e("aaa","--------user_name"+user_name);
        if (user_name != null&&!user_name.equals("")) {
            userName.setText(user_name);
            userlevel.setText("跟帖局科员");
            if (shezhi!= null&&!shezhi.equals("[]")) {
                Gson gson = new Gson();
                Type type = new TypeToken<List<Shezhi>>() {
                }.getType();
                // List<CoordinateAlterSample> alterSamples = new ArrayList<CoordinateAlterSample>();
                listshezhi = gson.fromJson(shezhi, type);

                for (int i = 0; i < listshezhi.size(); i++) {
                    switch (listshezhi.get(i).getShezhitype()) {
                        case 1:
                            read0 = read0 + 1;
                            break;
                        case 2:
                            conllect0 = conllect0 + 1;
                            break;
                        case 3:
                            gentie0 = gentie0 + 1;
                            break;

                    }
                    System.out.println("序号:" + listshezhi.get(i).getId());
                }
            }
            denglujinbi();
            flag = true;
            pic_path = SearchDB.TouXiangDb(getActivity(), "pic_path");
            if (pic_path != null) {
                Log.e("aaa","--------pic_path"+pic_path);
//                Bitmap bitmap = TouXiangCache.getphoto(pic_path);
//                Bitmap bitmap = TouXiangCache.getBitmapFromFile(pic_path,100,100);
//                picture.setImageBitmap(bitmap);
                XutilsGetData.xUtilsImageiv(picture,pic_path, getActivity(),false);
            }


        } else {
            jinbiCount.setVisibility(View.VISIBLE);
            goldNumber.setVisibility(View.VISIBLE);
            readnumbe2.setVisibility(View.VISIBLE);
        }
        return view;
    }

    private void initView(View view) {
        picture = (ImageView) view.findViewById(R.id.picture);
        setting = (TextView) view.findViewById(R.id.setting);
        jinbiCount = (TextView) view.findViewById(R.id.jinbiCount);
        readnumbe2= (TextView) view.findViewById(R.id.read_numbe2);
       readercount2 = (TextView) view.findViewById(R.id.readerCount2);
        readerCount = (TextView) view.findViewById(R.id.readerCount);
        userName = (TextView) view.findViewById(R.id.userName);
        userlevel = (TextView) view.findViewById(R.id.userlevel);
        read = (TextView) view.findViewById(R.id.read);
        reader = (RelativeLayout) view.findViewById(R.id.reader);
        collect = (RelativeLayout) view.findViewById(R.id.collect);
        Thread = (RelativeLayout) view.findViewById(R.id.Thread);
        gold = (RelativeLayout) view.findViewById(R.id.gold);
        mymailbox = (LinearLayout) view.findViewById(R.id.mymailbox);
        myWallet = (LinearLayout) view.findViewById(R.id.myWallet);
        myTask = (LinearLayout) view.findViewById(R.id.myTask);
        goldMall = (LinearLayout) view.findViewById(R.id.goldMall);
        myMessage = (LinearLayout) view.findViewById(R.id.myMessage);
        mymailboxText = (TextView) view.findViewById(R.id.mymailboxText);
        myWalletText = (TextView) view.findViewById(R.id.myWalletText);
        myTaskText = (TextView) view.findViewById(R.id.myTaskText);
        goldMallText = (TextView) view.findViewById(R.id.goldMallText);
        messageText = (TextView) view.findViewById(R.id.messageText);
        mymailboxText = (TextView) view.findViewById(R.id.mymailboxText);
        goldNumber = (ImageView) view.findViewById(R.id.gold_number);
        gentieNumber = (ImageView) view.findViewById(R.id.gentie_number);
        collectNumber = (ImageView) view.findViewById(R.id.collect_number);
        readNumber = (ImageView) view.findViewById(R.id.read_number);
        initOnClick();
    }

    private void initOnClick() {
        mymailboxText.setOnClickListener(this);
        myWalletText.setOnClickListener(this);
        myTaskText.setOnClickListener(this);
        jinbiCount.setOnClickListener(this);
        goldMallText.setOnClickListener(this);
        messageText.setOnClickListener(this);
        goldNumber.setOnClickListener(this);
        gentieNumber.setOnClickListener(this);
        collectNumber.setOnClickListener(this);
        readNumber.setOnClickListener(this);
        picture.setOnClickListener(this);
        setting.setOnClickListener(this);
        userName.setOnClickListener(this);
        userlevel.setOnClickListener(this);
        read.setOnClickListener(this);
        reader.setOnClickListener(this);
        collect.setOnClickListener(this);
        Thread.setOnClickListener(this);
        gold.setOnClickListener(this);
        mymailbox.setOnClickListener(this);
        myWallet.setOnClickListener(this);
        myTask.setOnClickListener(this);
        goldMall.setOnClickListener(this);
        myMessage.setOnClickListener(this);
    }

    public static void denglujinbi() {
        if (!flag) {
            goldNumber.setVisibility(View.VISIBLE);
//            goldNumber.setVisibility(View.GONE);
            readnumbe2.setVisibility(View.VISIBLE);
            readercount2.setVisibility(View.VISIBLE);
            readerCount.setVisibility(View.VISIBLE);
            jinbiCount.setVisibility(View.VISIBLE);
            userName.setText(user_name);
            jinbiCount.setText(jinbi);
            readnumbe2.setText(read0+"");
            readercount2.setText(conllect0+"");
           readerCount.setText(gentie0+"");
        }else{
            goldNumber.setVisibility(View.GONE);
            readnumbe2.setVisibility(View.GONE);
            jinbiCount.setVisibility(View.GONE);
            readercount2.setVisibility(View.GONE);
            readerCount.setVisibility(View.GONE);
        }
    }

    @TargetApi(Build.VERSION_CODES.ECLAIR)
	@SuppressLint("NewApi")
	@Override
    public void onClick(View v) {
        switch (v.getId()) {
            // TODO: 2015/11/18 头像
            case R.id.picture:
                Log.e("------------->", "点击登陆成功后" + flag);
                if (flag) {
                    Intent intent1 = new Intent(getActivity(), Setting_headpage.class);
                    startActivity(intent1);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                } else {
                    Log.e("------------->", "点击登陆失败后" + flag);
                    Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent2);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                break;
            // TODO: 2015/11/18  设置
            case R.id.setting:
                Intent intent3 = new Intent(getActivity(), Setting_set_page.class);
                startActivity(intent3);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            // TODO: 2015/11/18  登录名称(立即登录)
            case R.id.userName:
                if (flag) {
                    Intent intent4 = new Intent(getActivity(), Setting_headpage.class);
                    startActivity(intent4);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                } else {
                    Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent2);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                break;
            // TODO: 2015/11/18 用户等级名称
            case R.id.userlevel:

                break;
            // TODO: 2015/11/18 阅读量
            case R.id.read:
                Intent intent5 = new Intent(getActivity(), ReadChievement.class);
                intent5.putExtra("number", number);
                startActivity(intent5);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                getActivity().finish();
                FragmentTransaction manager = getFragmentManager().beginTransaction();
                manager.addToBackStack(null);
                manager.commit();
                break;
            // TODO: 2015/11/18 阅读量
            case R.id.read_number:

                break;
            // TODO: 2015/11/18 收藏条数
            case R.id.collect_number:

                break;
            // TODO: 2015/11/18 跟帖次数
            case R.id.gentie_number:

                break;
            // TODO: 2015/11/18 金币数量
//            case R.id.gold_number:
////                if (!flag) {
////                    goldNumber.setEnabled(true);
////                    jinbiCount.setText("5");
////                }
//                break;
            // TODO: 2015/11/18 阅读  加动画
            case R.id.reader:
                startActivity(new Intent(getActivity(), ReadHistoryActivity.class));

                break;
            // TODO: 2015/11/18 收藏
            case R.id.collect:
                if (flag) {
                    Intent intent6 = new Intent(getActivity(), Setting_Collection.class);
                    startActivity(intent6);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                } else {
                    Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent2);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                break;
            // TODO: 2015/11/18 跟帖  完成
            case R.id.Thread:
                startActivity(new Intent(getActivity(),GenTieActivity.class));
                break;
            // TODO: 2015/11/18 金币
            case R.id.gold:
                if (flag) {
                    Intent intent4 = new Intent(getActivity(), Setting_GlodPage.class);
                    startActivity(intent4);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                } else {
                    Intent intent2 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent2);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                break;
            // TODO: 2015/11/18 我的消息
            case R.id.myMessage:

                break;
            case R.id.messageText:

                break;
            // TODO: 2015/11/18 金币商城
            case R.id.goldMall:
                Intent intent17 = new Intent(getActivity(), Setting_glodmall.class);
                startActivity(intent17);
                getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                break;
            case R.id.goldMallText:

                break;
            case R.id.myTask:
                //获取数据库对应的数据
                if (flag) {
                    Intent intent8 = new Intent(getActivity(), Setting_my_Task.class);
                    startActivity(intent8);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                } else {
                    Intent intent9 = new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent9);
                    getActivity().overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
                }
                break;
            case R.id.myTaskText:

                break;
            // TODO: 2015/11/18 我的钱包
            case R.id.myWallet:

                break;
            case R.id.myWalletText:

                break;
            // TODO: 2015/11/18 我的邮箱
            case R.id.mymailbox:

                break;
            case R.id.mymailboxText:

                break;

        }
    }
    public static Handler handle=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what){
                case 1:
                    userName.setText("立即登录");
                    goldNumber.setVisibility(View.VISIBLE);
                    jinbiCount.setVisibility(View.GONE);

                    readnumbe2.setVisibility(View.GONE);

                    readercount2.setVisibility(View.GONE);
                    readerCount.setVisibility(View.GONE);
                    picture.setImageResource(R.mipmap.biz_tie_user_avater_default_common);
                    flag=false;
                    break;
                case 2:
                      String aa=(String)msg.obj;
                    System.out.println("序号:" + aa);
//                    Bitmap bp= (Bitmap) msg.obj;
//                    if (bp!=null){
//                        picture.setImageBitmap(bp);
//                    }
//                    shezhi= SearchDB.createDb(getActivity(), "shezhi");
//
//                    user_name = SearchDB.createDb(, "userName");
//                    jinbi = SearchDB.createDb(getActivity(), "jinbi");
//                    shezhi = SearchDB.createDb(getActivity(), "shezhi");
//
//                    Log.e("aaa","--------user_name"+user_name);
//                    if (user_name != null&&!user_name.equals("")) {
//                        userName.setText(user_name);
//                        userlevel.setText("跟帖局科员");
//                        if (shezhi!= null&&!shezhi.equals("[]")) {
//                            Gson gson = new Gson();
//                            Type type = new TypeToken<List<Shezhi>>() {
//                            }.getType();
//                            // List<CoordinateAlterSample> alterSamples = new ArrayList<CoordinateAlterSample>();
//                            listshezhi = gson.fromJson(shezhi, type);
//
//                            for (int i = 0; i < listshezhi.size(); i++) {
//                                switch (listshezhi.get(i).getShezhitype()) {
//                                    case 1:
//                                        read0 = read0 + 1;
//                                        break;
//                                    case 2:
//                                        conllect0 = conllect0 + 1;
//                                        break;
//                                    case 3:
//                                        gentie0 = gentie0 + 1;
//                                        break;
//
//                                }
//                                System.out.println("序号:" + listshezhi.get(i).getId());
//                            }
//                        }
//                        denglujinbi();
//                        flag = true;
//                        pic_path = SearchDB.TouXiangDb(getActivity(), "pic_path");
//                        if (pic_path != null) {
//                            Log.e("aaa","--------pic_path"+pic_path);
////                Bitmap bitmap = TouXiangCache.getphoto(pic_path);
////                Bitmap bitmap = TouXiangCache.getBitmapFromFile(pic_path,100,100);
////                picture.setImageBitmap(bitmap);
//                            XutilsGetData.xUtilsImageiv(picture,pic_path, getActivity(),false);
//                        }
//
//
//                    } else {
//                        jinbiCount.setVisibility(View.VISIBLE);
//                        goldNumber.setVisibility(View.VISIBLE);
//                        readnumbe2.setVisibility(View.VISIBLE);
//                    }
//                  onResume();
                    denglujinbi();
                    flag =false;
                    break;
            }

        }
  };
    public Gson getGson() {
        return gson;
    }


}
