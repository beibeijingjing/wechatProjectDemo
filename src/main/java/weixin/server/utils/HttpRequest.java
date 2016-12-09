/** 
 * @Prject: ssm-master
 * @Package: weixin.server.utils 
 * @Title: HttpRequest.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月6日 下午3:29:51   
 */
package weixin.server.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import core.exception.WxBaseException;
import weixin.server.config.WxConfig;
import weixin.server.entity.auth.WxAuth;
import weixin.server.service.WxAuthService;

/**
 * @ClassName: HttpRequest
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月6日 下午3:29:51
 */
public class HttpRequest {

	public static String postJson(String uri, String jsonParm) {
		String result = "";

		try {
			WxAuthService authService = new WxAuthService();
			WxAuth wxAuth = authService.getAccessToken(WxConfig.APP_ID,
					WxConfig.APP_SECRET);
			if (wxAuth == null) {
				return null;
			}
			String accessToken = wxAuth.getAccessToken();
			String action = uri + "?access_token=" + accessToken;

			URL url = new URL(action);
			HttpURLConnection http = (HttpURLConnection) url.openConnection();

			http.setRequestMethod("POST");
			http.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			http.setDoOutput(true);
			http.setDoInput(true);
			System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时3ss0秒
			System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
			http.connect();
			// 发送传递参数
			OutputStream os = http.getOutputStream();
			os.write(jsonParm.getBytes("UTF-8"));// 传入参数
			os.flush();
			os.close();

			// 读取返回数据
			InputStream is = http.getInputStream();
			int size = is.available();
			byte[] jsonBytes = new byte[size];
			is.read(jsonBytes);
			result = new String(jsonBytes, "UTF-8");

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (WxBaseException e) {
			e.printStackTrace();
		}

		return result;
	}

}
