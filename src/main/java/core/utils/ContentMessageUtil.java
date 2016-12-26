/** 
 * @Prject: ssm-master
 * @Package: core.utils 
 * @Title: ContentMessageUtil.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月14日 下午2:22:36   
 */
package core.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.apache.log4j.Logger;
import org.json.JSONException;

/**
 * @ClassName: ContentMessageUtil
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月14日 下午2:22:36
 */
public class ContentMessageUtil {
	private static final Logger log = Logger
			.getLogger(ContentMessageUtil.class);
	private static final String RESPON_EXCEPTION_MESSAGE = "小主请更换关键字试试";

	public static String getServerResponText(String content) {
		String result = "";
		if (StringUtils.isNotEmpty(content)) {
			if (content.indexOf("@") > 0) {
				String contentArr[] = content.split("@");
				if (contentArr != null && contentArr.length == 2) {
					try {
						if ("星座".equals(contentArr[0])) {
							result = getHoroscopeInfo(contentArr[1]);
						}
						if ("天气".equals(contentArr[0])) {
							result = getWetherInfo(contentArr[1]);
						}
						if ("解梦".equals(contentArr[0])) {
							result = getDreamInfo(contentArr[1]);
						}
						if ("history".equals(contentArr[0])) {
							result = getHistoryOfToday(contentArr[1]);
						}
					} catch (JSONException e) {
						result = RESPON_EXCEPTION_MESSAGE;
						log.error("第三方接口报错 ：" + e.getMessage());

					}
				}
			} else {
				// 调用机器人
				try {
					result = getTulingRobotInfo(content);
				} catch (JSONException e) {
					result = RESPON_EXCEPTION_MESSAGE;
					log.error("第三方接口报错 ：" + e.getMessage());
				}

			}
		}
		return result;
	}

	private static String getWetherInfo(String key) throws JSONException {
		String keyEncode = "";
		try {
			keyEncode = URLEncoder.encode(key, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String httpArg = "city=" + keyEncode;
		String jsonResult = BaiduApiRequest.request(
				ResourceUtils.getResource("api_wether_url"), httpArg);
		log.info("-------------查询天气：" + key + "  结果："
				+ OpenApiJsonUtil.getWetherBasicInfo(jsonResult)
				+ "--------------------");
		return OpenApiJsonUtil.getWetherBasicInfo(jsonResult);
	}

	private static String getDreamInfo(String key) throws JSONException {
		String keyEncode = "";
		try {
			keyEncode = URLEncoder.encode(key, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String httpArg = "word=" + keyEncode;
		String jsonResult = BaiduApiRequest.request(
				ResourceUtils.getResource("api_dream_ur"), httpArg);
		log.info("-------------查询解梦：" + key + "  结果："
				+ OpenApiJsonUtil.getDreamInfo(jsonResult)
				+ "--------------------");
		return OpenApiJsonUtil.getDreamInfo(jsonResult);
	}

	private static String getHoroscopeInfo(String key) throws JSONException {
		String keyEncode = "";
		try {
			keyEncode = URLEncoder.encode(key, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		String httpArg = "consName=" + keyEncode + "&type=today";
		String jsonResult = BaiduApiRequest.request(
				ResourceUtils.getResource("api_horoscope_url"), httpArg);
		log.info("-------------查询星座：" + key + "  结果："
				+ OpenApiJsonUtil.getHoroscopeInfo(jsonResult)
				+ "--------------------");
		return OpenApiJsonUtil.getHoroscopeInfo(jsonResult);
	}

	private static String getHistoryOfToday(String key) throws JSONException {
		String dateNow = DateUtils.getCurrentDate();
		String yue = "01";
		String ri = "01";
		if (StringUtils.isNotEmpty(dateNow)) {
			yue = dateNow.substring(5, 7);
			ri = dateNow.substring(8, 10);
		}
		String type = "2";
		if ("international".equals(key)) {
			type = "1";
		}
		String httpArg = "yue=" + yue + "&ri=" + ri + "&type=" + type
				+ "&page=1&rows=20&dtype=JOSN&format=false";
		String jsonResult = BaiduApiRequest.request(
				ResourceUtils.getResource("api_history_today_url"), httpArg);

		log.info("-------------查询历史上的今天：" + "  结果："
				+ OpenApiJsonUtil.getHistoryOfToday(jsonResult)
				+ "--------------------");
		return OpenApiJsonUtil.getHistoryOfToday(jsonResult);
	}

	private static String getTulingRobotInfo(String key) throws JSONException {
		String keyEncode = "";
		try {
			keyEncode = URLEncoder.encode(key, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}

		String httpArg = "key="
				+ ResourceUtils.getResource("tuling_robot_apikey") + "&info="
				+ keyEncode;
		String jsonResult = BaiduApiRequest.request(
				ResourceUtils.getResource("api_tuling_robot_url"), httpArg);

		log.info("-------------查询机器人：" + key + "  结果："
				+ OpenApiJsonUtil.getTulingRobotInfo(jsonResult)
				+ "--------------------");
		return OpenApiJsonUtil.getTulingRobotInfo(jsonResult);
	}

	public static void main(String args[]) throws JSONException {
		System.out.println(getServerResponText(""));
	}
}
