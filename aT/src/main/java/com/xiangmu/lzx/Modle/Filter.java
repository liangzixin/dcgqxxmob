package com.xiangmu.lzx.Modle;

import java.io.Serializable;

/**
 * Created by admin on 2016-08-17.
 */
public class Filter implements Serializable {
    private static final long serialVersionUID = 1L;
    private Integer id;// 类别编号
    private String filters;// 类别名称
    private String note;// 类别名称
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getFilters() {
        return filters;
    }
    public void setFilters(String filters) {
        this.filters = filters;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
