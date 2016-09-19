package com.xiangmu.wyxw.Modle;

import java.io.Serializable;


/**
 * �ϴ��ļ�����
 * @author Li Yongqiang
 */
public class Gqxx implements Serializable{
	private static final long serialVersionUID = 1L;
	// ���
	private Integer gqxxid;
	// �ļ�·��

	private Integer gqsl=0;
	private Sex sexrequest=Enum.valueOf(Sex.class, "ALL"); 
	private Married marry=Enum.valueOf(Married.class, "ALL");
	private ProductInfo productInfo;
	private Float youhei=0.0f;
	private String spsj="";
	private Gearbox gearbox=Enum.valueOf(Gearbox.class, "GEAR1");
	
	
	
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
	public void setGqsl(Integer gqsl) {
		this.gqsl = gqsl;
	}
	public Integer getGqsl() {
		return gqsl;
	}
	public Sex getSexrequest() {
		return sexrequest;
	}
	public void setSexrequest(Sex sexrequest) {
		this.sexrequest = sexrequest;
	}
	public Married getMarry() {
		return marry;
	}
	public void setMarry(Married marry) {
		this.marry = marry;
	}
	public Float getYouhei() {
		return youhei;
	}
	public void setYouhei(Float youhei) {
		this.youhei = youhei;
	}
	public String getSpsj() {
		return spsj;
	}
	public void setSpsj(String spsj) {
		this.spsj = spsj;
	}
	public Gearbox getGearbox() {
		return gearbox;
	}
	public void setGearbox(Gearbox gearbox) {
		this.gearbox = gearbox;
	}
	public Integer getGqxxid() {
		return gqxxid;
	}
	public void setGqxxid(Integer gqxxid) {
		this.gqxxid = gqxxid;
	}

	
}
