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
	private Long next_openid;
	private Map<String, List<Long>> data;

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

	public Long getNext_openid() {
		return next_openid;
	}

	public void setNext_openid(Long next_openid) {
		this.next_openid = next_openid;
	}

	public Map<String, List<Long>> getData() {
		return data;
	}

	public void setData(Map<String, List<Long>> data) {
		this.data = data;
	}

}
