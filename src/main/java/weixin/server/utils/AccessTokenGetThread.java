/** 
 * @Prject: ssm-master
 * @Package: weixin.server.utils 
 * @Title: AccessTokenGetThread.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年7月28日 下午3:46:57   
 */
package weixin.server.utils;

import weixin.server.config.WxConfig;
import weixin.server.entity.auth.WxAuth;
import weixin.server.service.WxAuthService;
import core.exception.WxBaseException;

/**
 * @ClassName: AccessTokenGetThread
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年7月28日 下午3:46:57
 */
public class AccessTokenGetThread implements Runnable {

	public static String appId = "";

	public static String appSecret = "";

	public static WxAuth wxAuth = null;

	public static String accessToken = "";

	@Override
	public void run() {

		while (true) {
			try {
				WxAuthService authService = new WxAuthService();
				wxAuth = authService.getAccessToken(appId, appSecret);
				if (wxAuth != null && wxAuth.getAccessToken() != null
						&& !"".equals(wxAuth.getAccessToken())) {
					accessToken = wxAuth.getAccessToken();
					WxConfig.accessToken = accessToken;
					System.out.println("=========================accessToken："
							+ AccessTokenGetThread.accessToken
							+ "===============================");
					Thread.sleep(1000 * 60 * 30);
				} else {
					Thread.sleep(1000 * 60);
				}
			} catch (WxBaseException e) {
				e.printStackTrace();
				try { // 发生异常休眠1秒
					Thread.sleep(1000 * 10);
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}
}
