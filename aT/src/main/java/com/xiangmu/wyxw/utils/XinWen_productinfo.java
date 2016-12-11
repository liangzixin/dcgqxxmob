package com.xiangmu.wyxw.utils;

import com.xiangmu.wyxw.Modle.Customer;
import com.xiangmu.wyxw.Modle.Dxfw;
import com.xiangmu.wyxw.Modle.Edu;
import com.xiangmu.wyxw.Modle.Fwcs;
import com.xiangmu.wyxw.Modle.Gqxx;
import com.xiangmu.wyxw.Modle.Hotel;
//import com.xiangmu.wyxw.Modle.ProductArticler;
import com.xiangmu.wyxw.Modle.ProductCategory;
//import com.xiangmu.wyxw.Modle.ProductInfo;
import com.xiangmu.wyxw.Modle.ProductInfo;
import com.xiangmu.wyxw.Modle.Sex;
import com.xiangmu.wyxw.Modle.Upcomment;
import com.xiangmu.wyxw.Modle.UploadFile;
import com.xiangmu.wyxw.Modle.Zpnl;
import com.xiangmu.wyxw.Modle.Zpxx;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by Administrator on 2015/11/11.
 */
public class XinWen_productinfo  implements Serializable{

    /**
     * T18908805728 : [{"template":"manual","imgextra":[{"imgsrc":"http://img4.cache.netease.com/3g/2015/11/11/2015111108492975082.jpg"},{"imgsrc":"http://img6.cache.netease.com/3g/2015/11/11/201511110849475d44e.jpg"}],"hasCover":false,"docid":"9IG74V5H00963VRO_B84KL7EVwuyuxuupdateDoc","skipID":"54GI0096|81609","tname":"头条","lmodify":"2015-11-11 08:49:01","title":"全美快餐店员罢工 要求时薪涨至15美元","priority":250,"hasImg":1,"ads":[{"subtitle":"","tag":"doc","title":"沃4G狂欢11天 最低1折起购","imgsrc":"http://img4.cache.netease.com/3g/2015/11/10/20151110093044878f2.jpg","url":"B826MFVD00963VRO"},{"subtitle":"","tag":"photoset","title":"南非天空现罕见怪云 如飞碟舰队","imgsrc":"http://img5.cache.netease.com/3g/2015/11/11/2015111108341682e34.jpg","url":"54GI0096|81599"},{"subtitle":"","tag":"photoset","title":"深圳公园征婚广告墙 8成为女性","imgsrc":"http://img2.cache.netease.com/3g/2015/11/11/2015111109123945083.jpg","url":"54GI0096|81615"},{"subtitle":"","tag":"photoset","title":"巴勒斯坦男孩持刀袭击以士兵被捕","imgsrc":"http://img6.cache.netease.com/3g/2015/11/10/201511102156334a3a7.jpg","url":"54GI0096|81587"},{"subtitle":"","tag":"photoset","title":"实拍平壤街头 中国汽车随处见","imgsrc":"http://img6.cache.netease.com/3g/2015/11/10/2015111020485152e5c.jpg","url":"54GI0096|81583"}],"replyCount":7488,"ename":"iosnews","hasIcon":true,"digest":"","skipType":"photoset","alias":"Top News","photosetID":"54GI0096|81609","hasAD":1,"imgsrc":"http://img3.cache.netease.com/3g/2015/11/11/20151111084918c6c18.jpg","ptime":"2015-11-11 08:49:01","hasHead":1,"cid":"C1348646712614","order":1},{"votecount":1547,"docid":"B834AF6200963VRO","lmodify":"2015-11-10 20:14:55","url_3w":"http://help.3g.163.com/15/1110/18/B834AF6200963VRO.html","source":"新华网$","title":"习近平主持中央财经小组会议","priority":220,"url":"http://3g.163.com/ntes/15/1110/18/B834AF6200963VRO.html","replyCount":2414,"subtitle":"","digest":"要加快形成融资功能完备、保护投资者权益的股票市场。","boardid":"3g_bbs","TAG":"视频","imgsrc":"http://img4.cache.netease.com/3g/2015/11/10/20151110184636aaa0d.jpg","ptime":"2015-11-10 18:44:17","TAGS":"视频"},{"votecount":4210,"docid":"B84GIU3D00963VRO","lmodify":"2015-11-11 09:17:50","url_3w":"http://help.3g.163.com/15/1111/07/B84GIU3D00963VRO.html","source":"澎湃新闻网","title":"第四套纪念钞将发行:航天主题","priority":210,"url":"http://3g.163.com/ntes/15/1111/07/B84GIU3D00963VRO.html","replyCount":5257,"subtitle":"","digest":"11月26日发行，纪念币纪念钞面值分别为10元和100元。","boardid":"3g_bbs","imgsrc":"http://img4.cache.netease.com/3g/2015/11/11/20151111074035aaf9f.jpg","ptime":"2015-11-11 07:37:52"},{"votecount":5812,"docid":"B84H97CC00963VRO","lmodify":"2015-11-11 09:35:15","url_3w":"http://help.3g.163.com/15/1111/07/B84H97CC00963VRO.html","source":"中国青年报","title":"7月胎儿被引产续:父放弃起诉","priority":203,"url":"http://3g.163.com/ntes/15/1111/07/B84H97CC00963VRO.html","replyCount":6652,"subtitle":"","digest":"因不符合政策被强制引产，本欲起诉政府:\"这就是犯罪\"。","boardid":"3g_bbs","imgsrc":"http://img4.cache.netease.com/3g/2015/11/11/20151111075257a77fc.jpg","ptime":"2015-11-11 07:50:02"},{"docid":"9IG74V5H00963VRO_B837ANTQzhangyahuiupdateDoc","skipID":"70203","digest":"带你揭秘2015维密20周年大秀台前幕后。","skipType":"live","lmodify":"2015-11-10 19:36:52","TAG":"正在直播","title":"网易携手奚梦瑶直击维密大秀","priority":202,"imgsrc":"http://img4.cache.netease.com/lady/2015/11/10/201511102026194b0c1.jpg","ptime":"2015-11-10 19:36:52","TAGS":"正在直播","live_info":{"start_time":"2015-11-11 06:00:00.0","user_count":136775,"end_time":"2015-11-11 11:00:00.0","video":false,"type":0,"roomId":70203}},{"imgextra":[{"imgsrc":"http://img6.cache.netease.com/3g/2015/11/11/201511110857474b5f0.jpg"},{"imgsrc":"http://img3.cache.netease.com/3g/2015/11/11/20151111085755eeaf0.jpg"}],"replyCount":291,"docid":"9IG74V5H00963VRO_B84L4JM3daililiupdateDoc","skipID":"54GI0096|81612","digest":"","skipType":"photoset","photosetID":"54GI0096|81612","lmodify":"2015-11-11 08:57:25","title":"四川现巨型恐龙足迹：长60cm","priority":95,"imgsrc":"http://img4.cache.netease.com/3g/2015/11/11/20151111085740486f7.jpg","ptime":"2015-11-11 08:57:25"},{"votecount":1190,"docid":"B83JK92F00264OD0","lmodify":"2015-11-11 08:38:43","url_3w":"http://lady.163.com/15/1110/23/B83JK92F00264OD0.html","source":"网易女人","title":"奚梦瑶成第1个有翅膀中国维密","priority":92,"url":"http://3g.163.com/lady/15/1110/23/B83JK92F00264OD0.html","replyCount":1463,"subtitle":"","digest":"中国超模奚梦瑶小明童鞋远征维密秀场，终于有翅膀了。","boardid":"lady_bbs","imgsrc":"http://img6.cache.netease.com/3g/2015/11/11/20151111001032aacac.jpg","ptime":"2015-11-10 23:11:47"},{"votecount":14235,"docid":"B84G24PU00963VRO","lmodify":"2015-11-11 07:32:49","url_3w":"http://help.3g.163.com/15/1111/07/B84G24PU00963VRO.html","source":"中国新闻网","title":"俄方:中俄之间不存在领土争议","priority":91,"url":"http://3g.163.com/ntes/15/1111/07/B84G24PU00963VRO.html","replyCount":16209,"subtitle":"","digest":"再次声明\"向中国转交土地\"不实，中方曾称该消息不严谨。","boardid":"3g_bbs","imgsrc":"http://img5.cache.netease.com/3g/2015/11/11/20151111073112fad67.jpg","ptime":"2015-11-11 07:28:42"},{"votecount":8313,"docid":"B84DVFI1000915BF","lmodify":"2015-11-11 07:51:15","url_3w":"http://tech.163.com/15/1111/06/B84DVFI1000915BF.html","source":"网易科技报道","title":"外媒泼冷水:双11是最大假货节","priority":90,"url":"http://3g.163.com/tech/15/1111/06/B84DVFI1000915BF.html","replyCount":9565,"subtitle":"","digest":"双11到来，消费维权律师接到的案子指数式增长。","boardid":"tech_bbs","imgsrc":"http://img2.cache.netease.com/3g/2015/11/11/201511110752557c126.jpg","ptime":"2015-11-11 06:52:17"},{"votecount":5091,"docid":"B83Q2D8K00011229","lmodify":"2015-11-11 01:15:21","url_3w":"http://news.163.com/15/1111/01/B83Q2D8K00011229.html","source":"华商网-新文化报","title":"\"双11\"跟踪:真正降价不足6成","priority":90,"url":"http://3g.163.com/news/15/1111/01/B83Q2D8K00011229.html","replyCount":5819,"subtitle":"","digest":"天猫销售额12分28秒破百亿，有产品价格不降反升。","boardid":"news_shehui7_bbs","imgsrc":"http://img5.cache.netease.com/3g/2015/11/11/2015111101164309284.jpg","ptime":"2015-11-11 01:04:21"},{"imgextra":[{"imgsrc":"http://img2.cache.netease.com/3g/2015/11/11/201511110853477bc7f.jpg"},{"imgsrc":"http://img3.cache.netease.com/3g/2015/11/11/20151111085359bab03.jpg"}],"replyCount":272,"docid":"9IG74V5H00963VRO_B84KSVV6wuyuxuupdateDoc","skipID":"54GI0096|81611","digest":"","skipType":"photoset","photosetID":"54GI0096|81611","lmodify":"2015-11-11 08:53:16","title":"男子改装老年车成迷你\"路虎\"","priority":88,"imgsrc":"http://img3.cache.netease.com/3g/2015/11/11/20151111085334a3825.jpg","ptime":"2015-11-11 08:53:16"},{"votecount":141,"docid":"B84L4EAL000146BE","lmodify":"2015-11-11 09:08:36","url_3w":"http://news.163.com/15/1111/08/B84L4EAL000146BE.html","source":"中国新闻网","title":"网曝长春完颜娄室墓地遭破坏","priority":87,"url":"http://3g.163.com/news/15/1111/08/B84L4EAL000146BE.html","replyCount":181,"subtitle":"","digest":"千年石龟像被用来烤肉喝酒，防火员:石龟像是被雷劈开的。","boardid":"news3_bbs","imgsrc":"http://img4.cache.netease.com/3g/2015/11/11/201511110908218493c.jpg","ptime":"2015-11-11 08:59:00"},{"votecount":529,"docid":"B84KGJ0U00963VRO","lmodify":"2015-11-11 08:48:32","url_3w":"http://help.3g.163.com/15/1111/08/B84KGJ0U00963VRO.html","source":"网易","title":"\"性福\"比拼:哪个省多大胸妹？","priority":87,"url":"http://3g.163.com/ntes/15/1111/08/B84KGJ0U00963VRO.html","replyCount":673,"subtitle":"","digest":"光棍节不只是买买买，来看看哪国人民最\u201c性福\u201d。","boardid":"3g_bbs","TAG":"画报","imgsrc":"http://img3.cache.netease.com/cnews/2015/11/10/2015111018453459374.jpg","ptime":"2015-11-11 08:46:29","TAGS":"画报"},{"votecount":37,"docid":"B84JGUAS00014JB6","lmodify":"2015-11-11 08:49:14","url_3w":"http://news.163.com/15/1111/08/B84JGUAS00014JB6.html","source":"人民网","title":"环保部:将调查沈阳局官网瘫痪","priority":87,"url":"http://3g.163.com/news/15/1111/08/B84JGUAS00014JB6.html","replyCount":86,"subtitle":"","digest":"此前沈阳遭遇六级重度雾霾，环保局官网瘫痪近2小时。","boardid":"news3_bbs","imgsrc":"http://img3.cache.netease.com/3g/2015/11/11/20151111084832953ef.jpg","ptime":"2015-11-11 08:14:00"},{"votecount":35,"docid":"B84M8G2600963VRO","lmodify":"2015-11-11 09:17:26","url_3w":"http://help.3g.163.com/15/1111/09/B84M8G2600963VRO.html","source":"中国青年报","title":"镇政府逼拆古宅:停水电堵家门","priority":86,"url":"http://3g.163.com/ntes/15/1111/09/B84M8G2600963VRO.html","replyCount":73,"subtitle":"","digest":"事发浙江，宅子数百年历史，政府曾题\"保留\"然而无用。","boardid":"3g_bbs","imgsrc":"http://img6.cache.netease.com/3g/2015/11/11/2015111109211190f15.jpg","ptime":"2015-11-11 09:17:01"},{"imgextra":[{"imgsrc":"http://img1.cache.netease.com/3g/2015/11/11/20151111090351372c0.jpg"},{"imgsrc":"http://img2.cache.netease.com/3g/2015/11/11/20151111090402dba18.jpg"}],"replyCount":24,"docid":"9IG74V5H00963VRO_B84LF0IHfengleiupdateDoc","skipID":"54GI0096|81613","digest":"","skipType":"photoset","photosetID":"54GI0096|81613","lmodify":"2015-11-11 09:03:06","title":"歼-11战机超低空飞行引围观","priority":86,"imgsrc":"http://img6.cache.netease.com/3g/2015/11/11/20151111090336cce91.jpg","ptime":"2015-11-11 09:03:06"},{"votecount":2126,"docid":"B828B9A0051780D4","lmodify":"2015-11-10 15:55:27","url_3w":"","source":"代军哥哥的私媒体","title":"她曾比琼瑶还红 48岁自杀去世","priority":84,"url":"null","replyCount":2446,"subtitle":"","digest":"三毛最终没和荷西葬一起，却一直留在属于各自的地方。","boardid":"dy_wemedia_bbs","TAG":"订阅","imgsrc":"http://img2.cache.netease.com/3g/2015/11/10/201511101555205f604.jpg","ptime":"2015-11-10 10:35:24","TAGS":"订阅"},{"votecount":34,"docid":"B84L77LH00963VRO","lmodify":"2015-11-11 09:01:01","url_3w":"http://help.3g.163.com/15/1111/08/B84L77LH00963VRO.html","source":"澎湃新闻网","title":"虐童案男童生父:他心理负担重","priority":86,"url":"http://3g.163.com/ntes/15/1111/08/B84L77LH00963VRO.html","replyCount":52,"subtitle":"","digest":"已半个月未上学；曾到法院门口举牌:我要妈妈李征琴。","boardid":"3g_bbs","imgsrc":"http://img6.cache.netease.com/3g/2015/11/11/20151111090049c19e7.jpg","ptime":"2015-11-11 08:58:51"},{"votecount":16,"docid":"B84K22QA00963VRO","lmodify":"2015-11-11 08:38:42","url_3w":"http://help.3g.163.com/15/1111/08/B84K22QA00963VRO.html","source":"四川在线-华西都市报","title":"男子扇女友一耳光获刑9个月","priority":86,"url":"http://3g.163.com/ntes/15/1111/08/B84K22QA00963VRO.html","replyCount":25,"subtitle":"","digest":"导致女方右耳鼓膜穿孔，男子还被判赔偿对方4.3万。","boardid":"3g_bbs","imgsrc":"http://img6.cache.netease.com/3g/2015/11/11/20151111084058a96f3.jpg","ptime":"2015-11-11 08:38:34"},{"votecount":19,"docid":"B8310G8900084TUO","lmodify":"2015-11-11 09:24:14","url_3w":"http://auto.163.com/15/1110/17/B8310G8900084TUO.html","source":"网易汽车","title":"全面升级 smart四门版更实用","priority":70,"url":"http://3g.163.com/auto/15/1110/17/B8310G8900084TUO.html","replyCount":43,"subtitle":"","digest":"新smart forfour、国产GLC和S500 eL亮相广州车展。","boardid":"auto_bbs","imgsrc":"http://img5.cache.netease.com/3g/2015/11/11/20151111080245dcfaa.jpg","ptime":"2015-11-11 07:46:25"},{"docid":"9IG74V5H00963VRO_B84JS4GKwangchenxiupdateDoc","skipID":"VB76PSNGR","videosource":"新媒体","videoID":"VB76PSNGR","lmodify":"2015-11-11 08:35:19","title":"男子穿喷气背包飞越自由女神","priority":86,"replyCount":73,"digest":"美国公司总裁首次穿自家背包飞行，时速可达160km。","skipType":"video","imgsrc":"http://img3.cache.netease.com/video/2015/11/11/20151111083525848df.jpg","ptime":"2015-11-11 08:35:19","TAGS":"视频"},{"votecount":1487,"docid":"B83JO98200014MTN","lmodify":"2015-11-11 07:41:52","url_3w":"http://news.163.com/15/1110/23/B83JO98200014MTN.html","source":"网易","title":"美国和俄罗斯打击IS花多少钱?","priority":86,"url":"http://3g.163.com/news/15/1110/23/B83JO98200014MTN.html","replyCount":1682,"subtitle":"","digest":"俄罗斯打击IS日均花费236万美元，仅为美国的五分之一。","boardid":"news3_bbs","TAG":"独家","imgsrc":"http://img2.cache.netease.com/3g/2015/6/15/201506150808373b97a.jpg","ptime":"2015-11-10 23:13:58","TAGS":"独家"}]
     */
    private List<T18908805728Entity> T18908805728;

    public void setT18908805728(List<T18908805728Entity> T18908805728) {
        this.T18908805728 = T18908805728;
    }

    public List<T18908805728Entity> getT18908805728() {
        return T18908805728;
    }

    public static class T18908805728Entity {
        private static final long serialVersionUID = 1L;
        private Integer id;// 商品编号
        private String name;// 商品名称
        private String description;// 商品说明

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        private String createTime;// 上架时间
        private Float baseprice;// 商品采购价格
        private Float marketprice;// 现在市场价格
        private Float sellprice;// 商城销售价格
        //	private Sex sexrequest;// 所属性别
        private Boolean commend = false;// 是否是推荐商品（默认值为false）
        private Integer clickcount = 1;// 访问量（统计受欢迎的程度）
        private Integer sellCount = 0;// 销售数量（统计热销商品）
        private Integer customerid;
        private String lxr;
        private String lxdh;
        private String jbPhone;
        private String agree ="1";// 是否显示
        private String ip;
        private  ProductCategory productcategory;

        public ProductCategory getProductcategory() {
            return productcategory;
        }

        public void setProductcategory(ProductCategory productcategory) {
            this.productcategory = productcategory;
        }


//	private Float youhei;

//	private Integer categoryId;

        public List<ProductArticlerEntity> getArticlers() {
            return articlers;
        }

        public void setArticlers(List<ProductArticlerEntity> articlers) {
            this.articlers = articlers;
        }

        //private ProductArticler articlers;// 子产品类别
        private List<ProductArticlerEntity> articlers;// 包含商品


        public List<UploadFileEntity> getUploadFile() {
            return uploadFile;
        }

        public void setUploadFile(List<UploadFileEntity> uploadFile) {
            this.uploadFile = uploadFile;
        }

        //	private Set uploadFile=new HashSet();;
    private List<UploadFileEntity> uploadFile;

        public List<ProductArticlerEntity> getProductArticler() {
            return productArticler;
        }

        public void setProductArticler(List<ProductArticlerEntity> productArticler) {
            this.productArticler = productArticler;
        }

        private List<ProductArticlerEntity> productArticler;
        private Set<Hotel> hotel= new HashSet<Hotel>();
        private Upcomment upcomment;
        private ProductCategory category;// 所属类别
        //	private ProductArticler articler;
        //private UploadFile uploadFile;// 上传文件
        private Zpxx zpxx;
        private Fwcs fwcs;
        private Gqxx gqxx;
        //	private Hotel Hotel;
        private Customer customer;

        private String gsmz;
        private String gsdz;

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        private String endTime;


        private String artRContent;
        private Integer rootId;

        private Integer authorid;

        public Integer getLunbo() {
            return lunbo;
        }

        public void setLunbo(Integer lunbo) {
            this.lunbo = lunbo;
        }

        private Integer lunbo;

        public List<AdsEntity> getAds() {
            return ads;
        }

        public void setAds(List<AdsEntity> ads) {
            this.ads = ads;
        }

        private List<AdsEntity> ads;

        public String getDigest() {
            return digest;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        private String digest;

        public Integer getId() {
            return id;
        }
        public void setId(Integer id) {
            this.id = id;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
        public String getDescription() {
            return description;
        }
        public void setDescription(String description) {
            this.description = description;
        }

        public Float getBaseprice() {
            return baseprice;
        }
        public void setBaseprice(Float baseprice) {
            this.baseprice = baseprice;
        }
        public Float getMarketprice() {
            return marketprice;
        }
        public void setMarketprice(Float marketprice) {
            this.marketprice = marketprice;
        }
        public Float getSellprice() {
            return sellprice;
        }
        public void setSellprice(Float sellprice) {
            this.sellprice = sellprice;
        }

        public Boolean getCommend() {
            return commend;
        }
        public void setCommend(Boolean commend) {
            this.commend = commend;
        }
        public Integer getClickcount() {
            return clickcount;
        }
        public void setClickcount(Integer clickcount) {
            this.clickcount = clickcount;
        }
        public Integer getSellCount() {
            return sellCount;
        }
        public void setSellCount(Integer sellCount) {
            this.sellCount = sellCount;
        }
        public ProductCategory getCategory() {
            return category;
        }
        public void setCategory(ProductCategory category) {
            this.category = category;
        }



        public void setCustomer(Customer customer) {
            this.customer = customer;
        }
        public Customer getCustomer() {
            return customer;
        }
        public void setCustomerid(Integer customerid) {
            this.customerid = customerid;
        }
        public Integer getCustomerid() {
            return customerid;
        }


        public String getArtRContent() {
            return artRContent;
        }
        public void setArtRContent(String artRContent) {
            this.artRContent = artRContent;
            //System.out.println("原始值："+this.artRContent);
        }

        public void setRootId(Integer rootId) {
            this.rootId = rootId;
        }
        public Integer getRootId() {
            return rootId;
        }

        public void setAuthorid(Integer authorid) {
            this.authorid = authorid;
        }
        public Integer getAuthorid() {
            return authorid;
        }
        /*
            public String getSubname(int len){
                int  length  =   0 ;
                 for ( int  i  =   0 ; i  <  this.name.length(); i ++ )
                    {
                         int  ascii  =  Character.codePointAt(this.name, i);
                         if (ascii  >=   0   &&  ascii  <= 255 )
                            length ++ ;
                         else
                            length  +=   2 ;

                    }

                if(len<length)
                    {
                    try {
                        this.name=new String(this.name.getBytes("GBK"),"ISO8859_1");
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    this.name=this.name.substring(0,len);
                    try {
                        this.name=new String(this.name.getBytes("ISO8859_1"),"GBK");
                    } catch (UnsupportedEncodingException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }


                    }

                else{


            //		System.out.println("旧this.name的字节长度="+length);

                    int j=length;
                    int k=len-j;

                    for(int i=1;i<=k;i++)
                    {
                        this.name=this.name+" ";

                    }
                //	System.out.println("新this.name="+this.name);
                //	System.out.println("新this.name的字节长度="+this.name.length());

                }
                return this.name;
            }
            */
        public String getSubname(int len){
            if(this.name.length()>13)
            {
                len=13;
            }else{
                len=this.name.length();
            }
            return this.name.substring(0,len);
        }
        public String getSubdescription(int len){
            if(this.description.length()>18)
            {
                len=18;
            }else{
                len=this.description.length();
            }
            return this.description.substring(0,len);
        }
        public String getSubname1(int len){
            if(this.name.length()>22)
            {
                len=22;
            }else{
                len=this.name.length();
            }
            return this.name.substring(0,len);
        }
        public String getAfterReplace(){
            return this.description.replaceAll("\r\n","<br/>").replaceAll("\r","</br>").replaceAll("\n","<br/>");
        }
        public void setLxr(String lxr) {
            this.lxr = lxr;
        }
        public String getLxr() {
            return lxr;
        }
        public void setLxdh(String lxdh) {
            this.lxdh = lxdh;
        }
        public String getLxdh() {
            return lxdh;
        }




        public Upcomment getUpcomment() {
            return upcomment;
        }
        public void setUpcomment(Upcomment upcomment) {
            this.upcomment = upcomment;
        }
        public String getGsmz() {
            return gsmz;
        }
        public void setGsmz(String gsmz) {
            this.gsmz = gsmz;
        }
        public String getGsdz() {
            return gsdz;
        }
        public void setGsdz(String gsdz) {
            this.gsdz = gsdz;
        }



        public Set<Hotel> getHotel() {
            return hotel;
        }
        public void setHotel(Set<Hotel> hotel) {
            this.hotel = hotel;
        }
        public String getJbPhone() {
            return jbPhone;
        }
        public void setJbPhone(String jbPhone) {
            this.jbPhone = jbPhone;
        }

        public Zpxx getZpxx() {
            return zpxx;
        }
        public void setZpxx(Zpxx zpxx) {
            this.zpxx = zpxx;
        }
        public Fwcs getFwcs() {
            return fwcs;
        }
        public void setFwcs(Fwcs fwcs) {
            this.fwcs = fwcs;
        }
        public Gqxx getGqxx() {
            return gqxx;
        }
        public void setGqxx(Gqxx gqxx) {
            this.gqxx = gqxx;
        }
        public String getAgree() {
            return agree;
        }
        public void setAgree(String agree) {
            this.agree = agree;
        }

        public String getIp() {
            return ip;
        }
        public void setIp(String ip) {
            this.ip = ip;
        }

        public static class ProductArticlerEntity{
            private static final long serialVersionUID = 1L;
            private Integer id;// 类别编号
            private Integer artreview_rootid;// 类别名称
            private String artreview_content;

            public String getArtreview_authorid() {
                return artreview_authorid;
            }

            public void setArtreview_authorid(String artreview_authorid) {
                this.artreview_authorid = artreview_authorid;
            }

            private String artreview_authorid;

            public String getArtreview_time() {
                return artreview_time;
            }

            public void setArtreview_time(String artreview_time) {
                this.artreview_time = artreview_time;
            }

            private String  artreview_time;
            //private Set<ProductCategory> children;// 子产品类别
            //private ProductCategory parent;// 父类别
            //private Set<ProductInfo> products = new TreeSet<ProductInfo>();// 包含商品
           // private ProductInfo product;
            private Customer customer;
            public Integer getId() {
                return id;
            }
            public void setId(Integer id) {
                this.id = id;
            }
            /*
            public Set<ProductInfo> getProducts() {
                return products;
            }
            public void setProducts(Set<ProductInfo> products) {
                this.products = products;
            }
            */
            public void setArtreview_rootid(Integer artreview_rootid) {
                this.artreview_rootid = artreview_rootid;
            }
            public Integer getArtreview_rootid() {
                return artreview_rootid;
            }
            public void setArtreview_content(String artreview_content) {
                this.artreview_content = artreview_content;
            }
            public String getArtreview_content() {
                return artreview_content;
            }

            public void setCustomer(Customer customer) {
                this.customer = customer;
            }
            public Customer getCustomer() {
                return customer;
            }

        }

        /**
         * template : manual
         * imgextra : [{"imgsrc":"http://img4.cache.netease.com/3g/2015/11/11/2015111108492975082.jpg"},{"imgsrc":"http://img6.cache.netease.com/3g/2015/11/11/201511110849475d44e.jpg"}]
         * hasCover : false
         * docid : 9IG74V5H00963VRO_B84KL7EVwuyuxuupdateDoc
         * skipID : 54GI0096|81609
         * tname : 头条
         * lmodify : 2015-11-11 08:49:01
         * title : 全美快餐店员罢工 要求时薪涨至15美元
         * priority : 250
         * hasImg : 1
         * ads : [{"subtitle":"","tag":"doc","title":"沃4G狂欢11天 最低1折起购","imgsrc":"http://img4.cache.netease.com/3g/2015/11/10/20151110093044878f2.jpg","url":"B826MFVD00963VRO"},{"subtitle":"","tag":"photoset","title":"南非天空现罕见怪云 如飞碟舰队","imgsrc":"http://img5.cache.netease.com/3g/2015/11/11/2015111108341682e34.jpg","url":"54GI0096|81599"},{"subtitle":"","tag":"photoset","title":"深圳公园征婚广告墙 8成为女性","imgsrc":"http://img2.cache.netease.com/3g/2015/11/11/2015111109123945083.jpg","url":"54GI0096|81615"},{"subtitle":"","tag":"photoset","title":"巴勒斯坦男孩持刀袭击以士兵被捕","imgsrc":"http://img6.cache.netease.com/3g/2015/11/10/201511102156334a3a7.jpg","url":"54GI0096|81587"},{"subtitle":"","tag":"photoset","title":"实拍平壤街头 中国汽车随处见","imgsrc":"http://img6.cache.netease.com/3g/2015/11/10/2015111020485152e5c.jpg","url":"54GI0096|81583"}]
         * replyCount : 7488
         * ename : iosnews
         * hasIcon : true
         * digest :
         * skipType : photoset
         * alias : Top News
         * photosetID : 54GI0096|81609
         * hasAD : 1
         * imgsrc : http://img3.cache.netease.com/3g/2015/11/11/20151111084918c6c18.jpg
         * ptime : 2015-11-11 08:49:01
         * hasHead : 1
         * cid : C1348646712614
         * order : 1

        private List<ImgextraEntity> imgextra;
        private String photosetID;
        private String skipID;
        private String specialID;
        private String skipType;
        private String template;
        private String lmodify;
        private String source;
        private String title;
        private int hasImg;
        private String digest;
        private String boardid;
        private String alias;
        private int hasAD;
        private String imgsrc;
        private String ptime;
        private int hasHead;
        private int order;
        private int votecount;
        private boolean hasCover;
        private String docid;
        private String tname;
        private String url_3w;
        private int priority;
        private String url;
        private List<AdsEntity> ads;
        private int replyCount;
        private String ename;
        private boolean hasIcon;
        private String subtitle;
        private String cid;

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getBoardid() {
            return boardid;
        }

        public void setBoardid(String boardid) {
            this.boardid = boardid;
        }

        public int getVotecount() {
            return votecount;
        }

        public void setVotecount(int votecount) {
            this.votecount = votecount;
        }

        public String getUrl_3w() {
            return url_3w;
        }

        public void setUrl_3w(String url_3w) {
            this.url_3w = url_3w;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getSpecialID() {
            return specialID;
        }

        public void setSpecialID(String specialID) {
            this.specialID = specialID;
        }

        public String getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(String subtitle) {
            this.subtitle = subtitle;
        }

        public void setTemplate(String template) {
            this.template = template;
        }

        public void setImgextra(List<ImgextraEntity> imgextra) {
            this.imgextra = imgextra;
        }

        public void setHasCover(boolean hasCover) {
            this.hasCover = hasCover;
        }

        public void setDocid(String docid) {
            this.docid = docid;
        }

        public void setSkipID(String skipID) {
            this.skipID = skipID;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public void setLmodify(String lmodify) {
            this.lmodify = lmodify;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public void setHasImg(int hasImg) {
            this.hasImg = hasImg;
        }

        public void setAds(List<AdsEntity> ads) {
            this.ads = ads;
        }

        public void setReplyCount(int replyCount) {
            this.replyCount = replyCount;
        }

        public void setEname(String ename) {
            this.ename = ename;
        }

        public void setHasIcon(boolean hasIcon) {
            this.hasIcon = hasIcon;
        }

        public void setDigest(String digest) {
            this.digest = digest;
        }

        public void setSkipType(String skipType) {
            this.skipType = skipType;
        }

        public void setAlias(String alias) {
            this.alias = alias;
        }

        public void setPhotosetID(String photosetID) {
            this.photosetID = photosetID;
        }

        public void setHasAD(int hasAD) {
            this.hasAD = hasAD;
        }

        public void setImgsrc(String imgsrc) {
            this.imgsrc = imgsrc;
        }

        public void setPtime(String ptime) {
            this.ptime = ptime;
        }

        public void setHasHead(int hasHead) {
            this.hasHead = hasHead;
        }

        public void setCid(String cid) {
            this.cid = cid;
        }

        public void setOrder(int order) {
            this.order = order;
        }

        public String getTemplate() {
            return template;
        }

        public List<ImgextraEntity> getImgextra() {
            return imgextra;
        }

        public boolean isHasCover() {
            return hasCover;
        }

        public String getDocid() {
            return docid;
        }

        public String getSkipID() {
            return skipID;
        }

        public String getTname() {
            return tname;
        }

        public String getLmodify() {
            return lmodify;
        }

        public String getTitle() {
            return title;
        }

        public int getPriority() {
            return priority;
        }

        public int getHasImg() {
            return hasImg;
        }

        public List<AdsEntity> getAds() {
            return ads;
        }

        public int getReplyCount() {
            return replyCount;
        }

        public String getEname() {
            return ename;
        }

        public boolean isHasIcon() {
            return hasIcon;
        }

        public String getDigest() {
            return digest;
        }

        public String getSkipType() {
            return skipType;
        }

        public String getAlias() {
            return alias;
        }

        public String getPhotosetID() {
            return photosetID;
        }

        public int getHasAD() {
            return hasAD;
        }

        public String getImgsrc() {
            return imgsrc;
        }

        public String getPtime() {
            return ptime;
        }

        public int getHasHead() {
            return hasHead;
        }

        public String getCid() {
            return cid;
        }

        public int getOrder() {
            return order;
        }

        public static class ImgextraEntity {

             * imgsrc : http://img4.cache.netease.com/3g/2015/11/11/2015111108492975082.jpg

            private String imgsrc;

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public String getImgsrc() {
                return imgsrc;
            }
        }
*/
        public static class UploadFileEntity {

            //  * imgsrc : http://img4.cache.netease.com/3g/2015/11/11/2015111108492975082.jpg
       //   private int id;
          private String path;

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            private String title;
            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

        }
        public static class ZpxxEntity {

            private Integer zpxxid;

            private Dxfw gzdx=Enum.valueOf(Dxfw.class, "GZDX5");
            private Edu edurequest=Enum.valueOf(Edu.class, "EDU4");
            private String sxcy="";
            private Integer qjnl;
            private Sex sexrequest=Enum.valueOf(Sex.class, "ALL");
            private Zpnl zpnlrequest=Enum.valueOf(Zpnl.class, "ZPNL4");
            private ProductInfo productInfo;


            public void setSxcy(String sxcy) {
                this.sxcy = sxcy;
            }
            public String getSxcy() {
                return sxcy;
            }

            public void setSexrequest(Sex sexrequest) {
                this.sexrequest = sexrequest;
            }
            public Sex getSexrequest() {
                return sexrequest;
            }
            public void setGzdx(Dxfw gzdx) {
                this.gzdx = gzdx;
            }
            public Dxfw getGzdx() {
                return gzdx;
            }
            public void setEdurequest(Edu edurequest) {
                this.edurequest = edurequest;
            }
            public Edu getEdurequest() {
                return edurequest;
            }
            public void setQjnl(Integer qjnl) {
                this.qjnl = qjnl;
            }
            public Integer getQjnl() {
                return qjnl;
            }

            public void setZpnlrequest(Zpnl zpnlrequest) {
                this.zpnlrequest = zpnlrequest;
            }
            public Zpnl getZpnlrequest() {
                return zpnlrequest;
            }
            public Integer getZpxxid() {
                return zpxxid;
            }
            public void setZpxxid(Integer zpxxid) {
                this.zpxxid = zpxxid;
            }
            public ProductInfo getProductInfo() {
                return productInfo;
            }
            public void setProductInfo(ProductInfo productInfo) {
                this.productInfo = productInfo;
            }

        }
        public static class AdsEntity {
          /*
             * subtitle :
             * tag : doc
             * title : 沃4G狂欢11天 最低1折起购
             * imgsrc : http://img4.cache.netease.com/3g/2015/11/10/20151110093044878f2.jpg
             * url : B826MFVD00963VRO
*/
            private String subtitle;
            private String tag;
            private String title;
            private String imgsrc;
            private String url;

            public void setSubtitle(String subtitle) {
                this.subtitle = subtitle;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public void setImgsrc(String imgsrc) {
                this.imgsrc = imgsrc;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getSubtitle() {
                return subtitle;
            }

            public String getTag() {
                return tag;
            }

            public String getTitle() {
                return title;
            }

            public String getImgsrc() {
                return imgsrc;
            }

            public String getUrl() {
                return url;
            }
        }
        public static class Category {


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            private String id;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            private String name;


        }




    }

}
