package com.xiangmu.wyxw.Modle;

import java.io.Serializable;
import java.util.Date;
/**
 * 管理员
 * @author Li Yongqiang
 */
public class Shezhi implements Serializable{
	private static final long serialVersionUID = 1L;
	// 编号
	private Integer id;
	// 用户名
	private Integer customerid ;


	// 密码
	private String createdate;
	private Integer productinfoid;
	private Integer shezhitype;
//	private Customer customer;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCustomerid() {
		return customerid;
	}
	public void setCustomerid(Integer customerid) {
		this.customerid = customerid;
	}
//
	public Integer getProductinfoid() {
		return productinfoid;
	}
	public void setProductinfoid(Integer productinfoid) {
		this.productinfoid = productinfoid;
	}
	public Integer getShezhitype() {
		return shezhitype;
	}
	public void setShezhitype(Integer shezhitype) {
		this.shezhitype = shezhitype;
	}


	public String getCreatedate() {
		return createdate;
	}

	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}

}
