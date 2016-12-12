/** 
 * @Prject: ssm-master
 * @Package: weixin.manager.wxentity 
 * @Title: WxUserOpenIdList.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月12日 上午11:13:12   
 */
package weixin.manager.wxentity;

import java.util.List;
import java.util.Map;

/**
 * @ClassName: WxUserOpenIdList
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月12日 上午11:13:12
 */
public class WxUserOpenIdList {

	private Integer total;
	private Integer count;
	private Map<String, List<String>> data;
	private String next_openid;

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Map<String, List<String>> getData() {
		return data;
	}

	public void setData(Map<String, List<String>> data) {
		this.data = data;
	}

	public String getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(String next_openid) {
		this.next_openid = next_openid;
	}

}
