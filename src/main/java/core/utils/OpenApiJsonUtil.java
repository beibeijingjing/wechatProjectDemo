/** 
 * @Prject: ssm-master
 * @Package: core.utils 
 * @Title: WetherJsonUtil.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月14日 上午11:59:17   
 */
package core.utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName: WetherJsonUtil
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月14日 上午11:59:17
 */
public class OpenApiJsonUtil {

	public static String getWetherBasicInfo(String jsonStr)
			throws JSONException {
		String result = "";

		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray dataArray = (JSONArray) jsonObj
				.get("HeWeather data service 3.0");
		JSONObject dataInfo = (JSONObject) dataArray.get(0);
		// 获取基础信息
		JSONObject basecData = (JSONObject) dataInfo.get("basic");
		// 实况信息
		JSONObject nowData = (JSONObject) dataInfo.get("now");
		// 天气状况
		JSONObject condData = (JSONObject) nowData.get("cond");
		// 风力风向
		JSONObject windData = (JSONObject) nowData.get("wind");

		// 建议
		JSONObject suggestionData = (JSONObject) dataInfo.get("suggestion");
		// 穿衣指数
		JSONObject drsgData = (JSONObject) suggestionData.get("drsg");
		// 感冒指数
		JSONObject fluData = (JSONObject) suggestionData.get("flu");

		String city = "城市：" + basecData.get("city");
		String nowInfo = "天气：  " + condData.get("txt") + " 温度：  "
				+ nowData.get("tmp") + " 降水量： " + nowData.get("pcpn") + " 风向： "
				+ windData.get("dir") + " 风力：  " + windData.get("sc");
		String suggestInfo = "穿衣指数：" + drsgData.get("txt") + "感冒指数："
				+ fluData.get("txt");
		result = city + nowInfo + suggestInfo;

		return result;
	}

	public static String getDreamInfo(String jsonStr) throws JSONException {
		String result = "";

		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray dataArray = (JSONArray) jsonObj.get("newslist");
		JSONObject dataInfo = (JSONObject) dataArray.get(0);
		// 获取基础信息
		result += dataInfo.get("result");

		return result;
	}

	public static String getHoroscopeInfo(String jsonStr) throws JSONException {
		String result = "";

		JSONObject jsonObj = new JSONObject(jsonStr);

		result += "星座名称：" + jsonObj.getString("name") + " 速配星座："
				+ jsonObj.getString("QFriend") + " 综合指数："
				+ jsonObj.getString("all") + " 幸运色："
				+ jsonObj.getString("color") + " 健康指数："
				+ jsonObj.getString("health") + "爱情指数："
				+ jsonObj.getString("love") + " 财富指数："
				+ jsonObj.getString("money") + " 幸运数："
				+ jsonObj.getString("number") + " 工作指数："
				+ jsonObj.getString("number") + " 运势："
				+ jsonObj.getString("summary");

		return result;
	}

}
