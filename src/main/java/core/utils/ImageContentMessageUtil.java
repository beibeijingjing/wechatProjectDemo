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
public class ImageContentMessageUtil {
	private static final Logger log = Logger
			.getLogger(ImageContentMessageUtil.class);
	private static final String RESPON_EXCEPTION_MESSAGE = "小主请更换关键字试试";

	public static String getServerResponImageText(String fromUserName,
			String toUserName, String content) {
		String result = "";
		if (StringUtils.isNotEmpty(content)) {
			if (content.indexOf("@") > 0) {
				String contentArr[] = content.split("@");
				if (contentArr != null && contentArr.length == 2) {
					try {
						if ("新闻".equals(contentArr[0])) {
							result = getWxNewsHot(fromUserName, toUserName,
									contentArr[1]);
						}

					} catch (JSONException e) {
						result = RESPON_EXCEPTION_MESSAGE;
						log.error("第三方接口报错 ：" + e.getMessage());
					}
				}
			}
		}
		return result;
	}

	private static String getWxNewsHot(String fromUserName, String toUserName,
			String key) throws JSONException {
		String keyEncode = "";
		try {
			keyEncode = URLEncoder.encode(key, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		if ("menu".equals(keyEncode)) {
			keyEncode = "";
		}
		String httpArg = "num=5&rand=1&word=" + keyEncode;
		String jsonResult = BaiduApiRequest.request(
				ResourceUtils.getResource("api_weixin_news_url"), httpArg);

		log.info("-------------查询新闻：" + key + "结果--------------");
		log.info(OpenApiJsonUtil.getWxNewsHot(fromUserName, toUserName,
				jsonResult));

		return OpenApiJsonUtil.getWxNewsHot(fromUserName, toUserName,
				jsonResult);
	}

	public static void main(String args[]) throws JSONException {
		getServerResponImageText("from123", "to456", "新闻@林丹");
	}
}
