/** 
 * @Prject: ssm-master
 * @Package: core.utils 
 * @Title: BaiduApiRequest.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月14日 上午11:23:16   
 */
package core.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ClassName: BaiduApiRequest
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月14日 上午11:23:16
 */
public class BaiduApiRequest {

	/**
	 * @param urlAll
	 *            :请求接口
	 * @param httpArg
	 *            :参数
	 * @return 返回结果
	 */
	public static String request(String httpUrl, String httpArg) {
		BufferedReader reader = null;
		String result = null;
		StringBuffer sbf = new StringBuffer();
		httpUrl = httpUrl + "?" + httpArg;

		try {
			URL url = new URL(httpUrl);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setRequestMethod("GET");
			// 填入apikey到HTTP header
			connection.setRequestProperty("apikey",
					ResourceUtils.getResource("api_key"));
			connection.connect();
			InputStream is = connection.getInputStream();
			reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
			String strRead = null;
			while ((strRead = reader.readLine()) != null) {
				sbf.append(strRead);
				sbf.append("\r\n");
			}
			reader.close();
			result = sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public static void main(String args[]) throws JSONException {

		String httpUrl = "http://apis.baidu.com/heweather/weather/free";
		String httpArg = "city=beijing";
		String jsonResult = request(httpUrl, httpArg);
		System.out.println(jsonResult);

		WetherJsonUtil.getWetherBasicInfo(jsonResult);

	}
}
