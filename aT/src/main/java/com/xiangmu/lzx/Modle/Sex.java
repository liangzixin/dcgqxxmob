package com.xiangmu.lzx.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别枚举类
 * @author Li Yongqiang
 */
public enum Sex {
	OTHER{
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
	},
	ALL{
		@Override
		public String getName() {
			return "不限";
		}
	}
	;

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
//    All("不限"),
//	BOY("男"),
//	GIRL("女");
//	private  String friendlyName;
//
//	 Sex(String friendlyName){
//		this.friendlyName = friendlyName;
//	}
//
//	@Override public String toString(){
//		return friendlyName;
//	}
//	public String shortCode() {
//		return friendlyName;
//	}
//

}
