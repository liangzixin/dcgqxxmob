package com.xiangmu.wyxw.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别枚举类
 * @author Li Yongqiang
 */
public enum Sex {
	ALL{
		@Override
		public String getName() {
			return "性别";
		}
	},
	BOY{
		@Override
		public String getName() {
			return "男";
		}
	},
	GIRL{
		@Override
		public String getName() {
			return "女";
		}
	};

	/**
	 * 获取名称的抽象方法
	 * @return 名称
	 */
	public abstract String getName();
	public static List<String> getValues(){
		List<String> list = new ArrayList<String>();
		for (Sex sex : Sex.values()) {
			list.add(sex.getName());
		}
		return list;
	}
}
