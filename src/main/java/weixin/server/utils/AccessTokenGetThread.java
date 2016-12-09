/** 
 * @Prject: ssm-master
 * @Package: weixin.server.utils 
 * @Title: AccessTokenGetThread.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年7月28日 下午3:46:57   
 */
package weixin.server.utils;

import weixin.server.entity.auth.WxAuth;

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
		/*
		 * while (true) { try { WxAuthService authService = new WxAuthService();
		 * wxAuth = authService.getAccessToken(appId, appSecret); if (wxAuth !=
		 * null && wxAuth.getAccessToken() != null &&
		 * !"".equals(wxAuth.getAccessToken())) { accessToken =
		 * wxAuth.getAccessToken(); WxConfig.accessToken = accessToken;
		 * System.out.println("=========================accessToken：" +
		 * AccessTokenGetThread.accessToken +
		 * "===============================");
		 * System.out.println("=========================accessToken：" +
		 * AccessTokenGetThread.accessToken +
		 * "===============================");
		 * System.out.println("=========================accessToken：" +
		 * AccessTokenGetThread.accessToken +
		 * "===============================");
		 * System.out.println("=========================accessToken：" +
		 * AccessTokenGetThread.accessToken +
		 * "===============================");
		 * 
		 * try{ System.out.println(
		 * "=========================Thread.sleep==============================="
		 * ); Thread.sleep(1000*60); System.out.println(
		 * "=========================Thread.up==============================="
		 * ); }catch (Exception e){ e.printStackTrace(); }
		 * 
		 * } else { try { // 发生异常休眠1秒 Thread.sleep(1000 * 3); } catch (Exception
		 * e) { e.printStackTrace(); } }
		 * 
		 * } catch (WxException e) { e.printStackTrace(); try { // 发生异常休眠1秒
		 * Thread.sleep(1000 * 10); } catch (Exception e1) {
		 * e1.printStackTrace(); } } }
		 */
	}
}
