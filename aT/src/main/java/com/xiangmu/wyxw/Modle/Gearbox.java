package com.xiangmu.wyxw.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别枚举类
 * @author Li Yongqiang
 */
public enum Gearbox {
	GEAR1{
		@Override
		public String getName() {
			return "手动";
		}
	},
	GEAR2{
		@Override
		public String getName() {
			return "自动";
		}
	},
	GEAR3{
		@Override
		public String getName() {
			return "手自一体";
		}
	};
	/**
	 * 获取名称的抽象方法
	 * @return 名称
	 */
	public abstract String getName();
	public static List<String> getValues(){
		List<String> list = new ArrayList<String>();
		for (Gearbox gearbox: Gearbox.values()) {
			list.add(gearbox.getName());
		}
		return list;
	}
}
