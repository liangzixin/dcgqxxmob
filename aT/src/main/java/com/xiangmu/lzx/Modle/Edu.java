package com.xiangmu.lzx.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别枚举类
 * @author Li Yongqiang
 */
public enum Edu {
	OTHER{
		@Override
		public String getName() {
			return "学历";
		}
	},
	EDU1{
		@Override
		public String getName() {
			return "硕士";
		}
	},
	EDU2{
		@Override
		public String getName() {
			return "本科";
		}
	},
	EDU3{
		@Override
		public String getName() {
			return "专科";
		}

	},
	EDU4 {
		@Override
		public String getName() {
			return "不限";
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
