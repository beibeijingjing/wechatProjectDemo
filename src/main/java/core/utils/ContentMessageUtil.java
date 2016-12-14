/** 
 * @Prject: ssm-master
 * @Package: core.utils 
 * @Title: ContentMessageUtil.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月14日 下午2:22:36   
 */
package core.utils;

import org.json.JSONException;

/**
 * @ClassName: ContentMessageUtil
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月14日 下午2:22:36
 */
public class ContentMessageUtil {

	public static String getServerResponText(String content)
			throws JSONException {
		String result = "";
		if (StringUtils.isNotEmpty(content)) {
			if (content.indexOf("@") > 0) {
				String contentArr[] = content.split("@");
				if (contentArr != null && contentArr.length == 2) {
					if ("星座".equals(contentArr[0])) {
						result = getHoroscopeInfo(contentArr[1]);
					}
					if ("天气".equals(contentArr[0])) {
						result = getWetherInfo(contentArr[1]);
					}
					if ("解梦".equals(contentArr[0])) {
						result = getDreamInfo(contentArr[1]);
					}
				}
			} else {
				// 调用机器人
			}
		}
		return result;
	}

	private static String getWetherInfo(String key) throws JSONException {
		String httpArg = "city=" + key;
		String jsonResult = BaiduApiRequest.request(
				ResourceUtils.getResource("api_wether_url"), httpArg);

		return OpenApiJsonUtil.getWetherBasicInfo(jsonResult);
	}

	private static String getDreamInfo(String key) throws JSONException {
		String httpArg = "word=" + key;
		String jsonResult = BaiduApiRequest.request(
				ResourceUtils.getResource("api_dream_ur"), httpArg);

		return OpenApiJsonUtil.getDreamInfo(jsonResult);
	}

	private static String getHoroscopeInfo(String key) throws JSONException {
		String httpArg = "consName=" + key + "&type=today";
		String jsonResult = BaiduApiRequest.request(
				ResourceUtils.getResource("api_horoscope_url"), httpArg);

		return OpenApiJsonUtil.getHoroscopeInfo(jsonResult);
	}

	public static void main(String args[]) throws JSONException {
		System.out.println(getServerResponText("天气@长治"));
	}
}
