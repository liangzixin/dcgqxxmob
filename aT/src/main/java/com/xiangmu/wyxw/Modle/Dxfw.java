package com.xiangmu.wyxw.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 底薪枚举类
 * @author Li Yongqiang
 */
public enum Dxfw {
	GZDX5{
		@Override
		public String getName() {
			return "底薪";
		}
	},
	GZDX1{
		@Override
		public String getName() {
			return "4000-5000元";
		}
	},
	GZDX2{
		@Override
		public String getName() {
			return "3000-4000元";
		}
	},
	GZDX3{
		@Override
		public String getName() {
			return "3000-2000元";
		}
	},
	GZDX4{
		@Override
		public String getName() {
			return "2000-1000元";
		}
	};


	
	/**
	 * 获取名称的抽象方法
	 * @return 名称
	 */
	public abstract String getName();
	public static List<String> getValues(){
		List<String> list = new ArrayList<String>();
		for (Dxfw dxfw : Dxfw.values()) {
			list.add(dxfw.getName());
		}
		return list;
	}
}
