package com.xiangmu.wyxw.Modle;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by admin on 2016-08-17.
 */
public class ProductInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;// 商品编号
    private String name;// 商品名称
    private String description;// 商品说明
    private Date createTime = new Date();// 上架时间
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
//	private Float youhei;

//	private Integer categoryId;

    //private ProductArticler articlers;// 子产品类别
    private Set<ProductArticler> articlers = new TreeSet<ProductArticler>();// 包含商品
    //	private Set uploadFile=new HashSet();;
    private Set<UploadFile> uploadFile = new HashSet<UploadFile>();
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
    private Date endTime;


    private String artRContent;
    private Integer rootId;

    private Integer authorid;
    public ProductInfo()
    {}
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
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
    public Set<ProductArticler> getArticlers() {
        return articlers;
    }
    public void setArticlers(Set<ProductArticler> articlers) {
        this.articlers = articlers;
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



    public void setUploadFile(Set<UploadFile> uploadFile) {
        this.uploadFile = uploadFile;
    }
    public Set<UploadFile> getUploadFile() {
        return uploadFile;
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
    public Date getEndTime() {
        return endTime;
    }
    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
    public String getIp() {
        return ip;
    }
    public void setIp(String ip) {
        this.ip = ip;
    }

}
