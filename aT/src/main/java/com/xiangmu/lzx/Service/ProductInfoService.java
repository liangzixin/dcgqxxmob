package com.xiangmu.lzx.Service;



import com.google.gson.reflect.TypeToken;
import com.xiangmu.lzx.Modle.ProductInfo;
import com.xiangmu.lzx.utils.HttpUtil;
import com.xiangmu.lzx.utils.ToolsHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

public class ProductInfoService extends BaseService {
    private ProductInfo productInfo=null;
    private String json="";
    private List<ProductInfo> productInfos=null;
    private int count=0;

//    /**
//     * 用户登陆
//     * @param userName 用户名
//     * @param userPwd 密码
//     * @return productInfo Txxx对象
//     * */
//    public ProductInfo login(String userName,String userPwd) throws Exception
//    {
//        String json="";
//        ProductInfo productInfo=null;
//        String path= HttpUtil.BASE_URL+"productInfo!login.action?userName="+userName+"&userPwd="+userPwd+"";
//        URL url = new URL(path);
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setReadTimeout(5000);
//        conn.setRequestMethod("POST");
//        if (200 == conn.getResponseCode())
//        {
//            //获取输入流
//            InputStream is = conn.getInputStream();
//            ToolsHandler toolsHandler=new ToolsHandler();
//            byte[] data=toolsHandler.InputStreamToByte(is);
//            json=new String(data);
//            System.out.println(json);
//            if(json!="0")
//            {
//                productInfo =getGson().fromJson(json, new TypeToken<ProductInfo>() {}.getType());
//            }
//        }
//        return productInfo;
//    }
//    /**
//     * 查询所有菜单
//     * @return List<Tfoods> foods
//     */
//    public List<ProductInfo> QueryAllTxxx(int intFirst,int recPerPage) throws Exception
//    {
//        String path = HttpUtil.BASE_URL+"productInfo!queryTxxxAll.action?intFirst="+intFirst+"&recPerPage="+recPerPage+"";
//        URL url;
//        try {
//            url = new URL(path);
//            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//            conn.setReadTimeout(5000);
//            conn.setRequestMethod("POST");
//            if (200 == conn.getResponseCode())
//            {
//                //获取输入流
//                InputStream is = conn.getInputStream();
//                ToolsHandler toolsHandler=new ToolsHandler();
//                byte[] data=toolsHandler.InputStreamToByte(is);
//                json=new String(data);
//                System.out.println(json);
//                txxxs =getGson().fromJson(json, new TypeToken<List<ProductInfo>>() {}.getType());
//            }
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//        return txxxs;
//    }
    /**
     * 根据最新信息
     * @return List<Tfoods> foods
     */
    public List<ProductInfo> QueryAllProductInfo(int intFirst,int recPerPage) throws Exception
    {
        String path = HttpUtil.BASE_URL+"product!QueryAllProductInfo.action?intFirst="+intFirst+"&recPerPage="+recPerPage+"";
        URL url;
        try {
            url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(5000);
            conn.setRequestMethod("POST");
            if (200 == conn.getResponseCode())
            {
                //获取输入流
                InputStream is = conn.getInputStream();
                ToolsHandler toolsHandler=new ToolsHandler();
                byte[] data=toolsHandler.InputStreamToByte(is);
                json=new String(data);
                System.out.println(json);
                productInfos =getGson().fromJson(json, new TypeToken<List<ProductInfo>>() {}.getType());
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return productInfos;
   }
    public  Boolean loginPostData(String path, Map<String, String> map) {
        String json0;
        Boolean json=false;
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL url = new URL(path);
            HttpURLConnection connection = (HttpURLConnection) url
                    .openConnection();
            connection.setDoInput(true);
            connection.setDoOutput(true);
//            connection.setReadTimeout(50000);
            connection.setRequestMethod("POST");
            StringBuffer buffer = new StringBuffer();
            if (map != null && !map.isEmpty()) {
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    buffer.append(entry.getKey())
                            .append("=")
                            .append(URLEncoder.encode(entry.getValue(), "utf-8"))
                            .append("&");
                }
                buffer.deleteCharAt(buffer.length() - 1);
            }
            byte[] data = buffer.toString().getBytes();
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            connection.setRequestProperty("Content-Length",
                    String.valueOf(data.length));
            outputStream = connection.getOutputStream();
            outputStream.write(data);
            if (connection.getResponseCode() == 200) {
                inputStream = connection.getInputStream();
                ToolsHandler toolsHandler=new ToolsHandler();
                byte[] data1=toolsHandler.InputStreamToByte(inputStream);
                json0=new String(data1);
               // json=new Boolean(data1);
                if(json0!=null)
                {

                    try {
                        JSONObject jsonObject=new JSONObject(json0);
                        json=jsonObject.getBoolean("productinfoadd");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return json;
    }
    /**
     * 添加请假　
     * @param
     * @return Ttables
     */
    public boolean ProductinfoAdd(Map map) throws Exception
    {
        boolean str=false;
        String path=HttpUtil.BASE_URL+"product!saveproductinfomob.action";
        str=loginPostData(path, map);
        return str;
    }
}
