package com.xiangmu.wyxw.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别枚举类
 * @author Li Yongqiang
 */
public enum Edu {
	EDU1{
		@Override
		public String getName() {
			return "硕士生";
		}
	},
	EDU2{
		@Override
		public String getName() {
			return "本科生";
		}
	},
	EDU3{
		@Override
		public String getName() {
			return "专科生";
		}
	},
	EDU4{
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
		for (Edu edu : Edu.values()) {
			list.add(edu.getName());
		}
		return list;
	}
}
