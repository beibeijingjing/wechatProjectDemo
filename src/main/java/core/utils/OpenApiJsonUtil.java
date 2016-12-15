/** 
 * @Prject: ssm-master
 * @Package: core.utils 
 * @Title: WetherJsonUtil.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月14日 上午11:59:17   
 */
package core.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import weixin.server.constant.WxMsgType;
import weixin.server.entity.item.WxItemPicDescEntity;
import weixin.server.entity.resp.WxRespPicDescEntity;

/**
 * @ClassName: WetherJsonUtil
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月14日 上午11:59:17
 */
public class OpenApiJsonUtil {

	public static String getWetherBasicInfo(String jsonStr)
			throws JSONException {

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

		StringBuffer result = new StringBuffer();
		result.append("城市：" + basecData.get("city")).append("\n");
		result.append("天气：  " + condData.get("txt")).append("\n");
		result.append("温度：  " + nowData.get("tmp") + " ℃").append("\n");
		result.append("降水量： " + nowData.get("pcpn")).append("\n");
		result.append("风向： " + windData.get("dir")).append("\n");
		result.append("风力：" + windData.get("sc")).append("\n");
		result.append("穿衣指数：" + drsgData.get("txt")).append("\n");
		result.append("感冒指数：" + fluData.get("txt")).append("\n");

		return result.toString();
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

		JSONObject jsonObj = new JSONObject(jsonStr);
		StringBuffer result = new StringBuffer();
		result.append("星座名称：" + jsonObj.getString("name")).append("\n");
		result.append("速配星座：" + jsonObj.getString("QFriend")).append("\n");
		result.append("综合指数：" + jsonObj.getString("all")).append("\n");
		result.append("幸运色：" + jsonObj.getString("color")).append("\n");
		result.append("健康指数：" + jsonObj.getString("health")).append("\n");
		result.append("爱情指数：" + jsonObj.getString("love")).append("\n");
		result.append("财富指数：" + jsonObj.getString("money")).append("\n");
		result.append("幸运数：" + jsonObj.getString("number")).append("\n");
		result.append("工作指数：" + jsonObj.getString("work")).append("\n");
		result.append("运势总结：" + jsonObj.getString("summary")).append("\n");
		return result.toString();
	}

	public static String getHistoryOfToday(String jsonStr) throws JSONException {
		StringBuffer result = new StringBuffer();
		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray historyArry = jsonObj.getJSONArray("result");
		if (historyArry != null) {
			JSONObject history = null;
			for (int i = 0; i < historyArry.length(); i++) {
				history = historyArry.getJSONObject(i);
				if (history != null) {
					result.append(history.getString("year") + "-"
							+ history.getString("month") + "-"
							+ history.getString("day"));
					result.append(":" + history.getString("title"))
							.append("\n");
				}
			}
		}

		return result.toString();
	}

	public static String getWxNewsHot(String fromUserName, String toUserName,
			String jsonStr) throws JSONException {
		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray newsArry = jsonObj.getJSONArray("newslist");
		// 回复新闻xml实体
		WxRespPicDescEntity newsEntity = new WxRespPicDescEntity();
		newsEntity.setToUserName(fromUserName);
		newsEntity.setFromUserName(toUserName);
		newsEntity.setCreateTime(new Date().getTime());
		newsEntity.setMsgType(WxMsgType.NEWS);
		List<WxItemPicDescEntity> articleList = new ArrayList<WxItemPicDescEntity>();
		if (newsArry != null) {
			// 设置图文总数
			newsEntity.setArticleCount(newsArry.length());
			JSONObject news = null;
			WxItemPicDescEntity article = null;
			// 遍历新闻列表
			for (int i = 0; i < newsArry.length(); i++) {
				news = newsArry.getJSONObject(i);
				if (news != null) {
					article = new WxItemPicDescEntity();
					article.setTitle(news.getString("title"));
					article.setDescription(news.getString("description"));
					article.setPicUrl(news.getString("picUrl"));
					article.setUrl(news.getString("url"));
					articleList.add(article);
				}
			}
			newsEntity.setArticles(articleList);
		}

		// 返回xml
		String result = MessageUtil.newsMessageToXml(newsEntity);
		return result;
	}

}
