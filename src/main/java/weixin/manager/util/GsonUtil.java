/** 
 * @Prject: ssm-master
 * @Package: weixin.manager.util 
 * @Title: GsonUtil.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月8日 下午12:59:26   
 */
package weixin.manager.util;

import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @ClassName: GsonUtil
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月8日 下午12:59:26
 */
public class GsonUtil {
	private static Gson gson = null;
	static {
		if (gson == null) {
			gson = new Gson();
		}
	}

	private GsonUtil() {
	}

	/*
	 * // 将Json数据解析成相应的映射对象 public static <T> T parseJsonWithGson(String
	 * jsonData, Class<T> type) { Gson gson = new Gson(); T result =
	 * gson.fromJson(jsonData, type); return result; }
	 * 
	 * // 将Json数组解析成相应的映射对象列表 public static <T> List<T>
	 * parseJsonArrayWithGson(String jsonData, Class<T> type) { Gson gson = new
	 * Gson(); List<T> result = gson.fromJson(jsonData, new TypeToken<List<T>>()
	 * { }.getType()); return result; }
	 */

	/**
	 * 转成json
	 * 
	 * @param object
	 * @return
	 */
	public static String GsonString(Object object) {
		String gsonString = null;
		if (gson != null) {
			gsonString = gson.toJson(object);
		}
		return gsonString;
	}

	/**
	 * 转成bean
	 * 
	 * @param gsonString
	 * @param cls
	 * @return
	 */
	public static <T> T GsonToBean(String gsonString, Class<T> cls) {
		T t = null;
		if (gson != null) {
			t = gson.fromJson(gsonString, cls);
		}
		return t;
	}

	/**
	 * 转成list
	 * 
	 * @param gsonString
	 * @param cls
	 * @return
	 */
	public static <T> List<T> GsonToList(String gsonString, Class<T> cls) {
		List<T> list = null;
		if (gson != null) {
			list = gson.fromJson(gsonString, new TypeToken<List<T>>() {
			}.getType());
		}
		return list;
	}

	/**
	 * 转成list中有map的
	 * 
	 * @param gsonString
	 * @return
	 */
	public static <T> List<Map<String, T>> GsonToListMaps(String gsonString) {
		List<Map<String, T>> list = null;
		if (gson != null) {
			list = gson.fromJson(gsonString,
					new TypeToken<List<Map<String, T>>>() {
					}.getType());
		}
		return list;
	}

	/**
	 * 转成map的
	 * 
	 * @param gsonString
	 * @return
	 */
	public static <T> Map<String, T> GsonToMaps(String gsonString) {
		Map<String, T> map = null;
		if (gson != null) {
			map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
			}.getType());
		}
		return map;
	}
}
