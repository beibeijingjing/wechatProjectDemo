/** 
 * @Prject: ssm-master
 * @Package: weixin.server.service 
 * @Title: WxAuthServiceTest.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年7月28日 下午4:52:31   
 */
package weixin.server.service;

import weixin.server.entity.auth.WxAuth;

import com.google.gson.Gson;

/** 
 * @ClassName: WxAuthServiceTest 
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年7月28日 下午4:52:31  
 */
public class WxAuthServiceTest {
	public static void main(String []args){
		String str="{\"access_token\":\"TlGT7mGccxMbaDrBLzK0MFX_kk9Jp5ifwVnxRzpnQhRyCDaIYfApT0wxxck6ohNkN9d6lEASSlHC1K6ZEE101hHA-P4lhz3inbio_3KvGjMVDX7diuA0tbZ4di7MAJrWORXjAFAJMS\",\"expires_in\":7200}";
		Gson gson=new Gson();
		WxAuth auth=gson.fromJson(str, WxAuth.class);
		System.out.println(auth.toString());
	}
}
