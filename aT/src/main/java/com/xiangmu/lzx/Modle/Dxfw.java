package com.xiangmu.lzx.Modle;

import java.util.ArrayList;
import java.util.List;

/**
 * 底薪枚举类
 * @author Li Yongqiang
 */
public enum Dxfw {
	OTHER{
		@Override
		public String getName() {
			return "底薪";
		}
	},
	GZDX1{
		@Override
		public String getName() {
			return "4-5千";
		}
	},
	GZDX2{
		@Override
		public String getName() {
			return "3-4千";
		}
	},
	GZDX3{
		@Override
		public String getName() {
			return "3-2千";
		}
	},
	GZDX4{
		@Override
		public String getName() {
			return "2-1千";
		}
	},
	GZDX5{
		@Override
		public String getName() {
			return "其它";
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
		for (Dxfw dxfw : Dxfw.values()) {
			list.add(dxfw.getName());
		}
		return list;
	}
}
