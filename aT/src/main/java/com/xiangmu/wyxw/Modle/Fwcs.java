package com.xiangmu.wyxw.Modle;

import java.io.Serializable;


/**
 * ??????????
 * @author Li Yongqiang
 */
public class Fwcs implements Serializable{
	private static final long serialVersionUID = 1L;
	// ???
	
	private Integer id;

	private Float jzmj=0.0f;
	private Integer fws=0;
	private Integer fwt=0;
	private Integer fww=0;
	private Float fwzj=0.0f;
	private Integer fwlj=0;
	private Integer fwzs=0;
	private Integer fwcj=0;
	private Integer fwzf=0;
	
	private Fzfs fzfsrequest=Enum.valueOf(Fzfs.class, "FZFS5");
	// ???路??

	private ProductInfo productInfo;
	public Fwcs()
	      {
          }
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}

	public void setJzmj(Float jzmj) {
		this.jzmj = jzmj;
	}
	public Float getJzmj() {
		return jzmj;
	}
	public void setFws(Integer fws) {
		this.fws = fws;
	}
	public Integer getFws() {
		return fws;
	}
	public void setFwt(Integer fwt) {
		this.fwt = fwt;
	}
	public Integer getFwt() {
		return fwt;
	}
	public void setFww(Integer fww) {
		this.fww = fww;
	}
	public Integer getFww() {
		return fww;
	}
	public void setFwzj(Float fwzj) {
		this.fwzj = fwzj;
	}
	public Float getFwzj() {
		return fwzj;
	}
	public void setFwlj(Integer fwlj) {
		this.fwlj = fwlj;
	}
	public Integer getFwlj() {
		return fwlj;
	}
	public void setFwzs(Integer fwzs) {
		this.fwzs = fwzs;
	}
	public Integer getFwzs() {
		return fwzs;
	}
	public void setProductInfo(ProductInfo productInfo) {
		this.productInfo = productInfo;
	}
	public ProductInfo getProductInfo() {
		return productInfo;
	}
		
	public void setFwcj(Integer fwcj) {
		this.fwcj = fwcj;
	}
	public Integer getFwcj() {
		return fwcj;
	}
	public void setFzfsrequest(Fzfs fzfsrequest) {
		this.fzfsrequest = fzfsrequest;
	}
	public Fzfs getFzfsrequest() {
		return fzfsrequest;
	}
	public Integer getFwzf() {
		return fwzf;
	}
	public void setFwzf(Integer fwzf) {
		this.fwzf = fwzf;
	}
	

	
}
