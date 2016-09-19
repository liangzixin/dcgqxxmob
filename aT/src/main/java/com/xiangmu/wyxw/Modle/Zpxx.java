package com.xiangmu.wyxw.Modle;

import java.io.Serializable;




/**
 
 * @author Li Yongqiang
 */
public class Zpxx implements Serializable{
	private static final long serialVersionUID = 1L;
	
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
