package com.xiangmu.wyxw.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 性别枚举类
 * @author Li Yongqiang
 */
public enum Fzfs {
	FZFS1{
		@Override
		public String getName() {
			return "按月交费";
		}
	},
	FZFS2{
		@Override
		public String getName() {
			return "按季交费";
		}
	},
	FZFS3{
		@Override
		public String getName() {
			return "半年一交";
		}
	},
	FZFS4{
		@Override
		public String getName() {
			return "一年一交";
		}
	},
	FZFS5{
		@Override
		public String getName() {
			return "其它方式";
		}
	};
	
	/**
	 * 获取名称的抽象方法
	 * @return 名称
	 */
	public abstract String getName();
	public static List<String> getValues(){
		List<String> list = new ArrayList<String>();
		for (Fzfs fzfs : Fzfs.values()) {
			list.add(fzfs.getName());
		}
		return list;
	}
}
