package com.xiangmu.lzx.Service;

import com.google.gson.Gson;

public class BaseService {

	private static Gson gson = new Gson();

	public static Gson getGson() {
		return gson;
	}
}
