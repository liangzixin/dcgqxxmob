package com.xiangmu.wyxw.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别枚举类
 * @author Li Yongqiang
 */
public enum Married {
	NOTT{
		@Override
		public String getName() {
			return "未婚";
		}
	},
	YESS{
		@Override
		public String getName() {
			return "离异";
		}
	},
	ALL{
		@Override
		public String getName() {
			return "其它";
		}
	};
	/**
	 * 获取名称的抽象方法
	 * @return 名称
	 */
	public abstract String getName();
	public static List<String> getValues(){
		List<String> list = new ArrayList<String>();
		for (Married married : Married.values()) {
			list.add(married.getName());
		}
		return list;
	}
}
