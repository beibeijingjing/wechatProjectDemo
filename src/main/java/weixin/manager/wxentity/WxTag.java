/** 
 * @Prject: ssm-master
 * @Package: weixin.manager.wxentity 
 * @Title: WxTag.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月7日 上午11:56:49   
 */
package weixin.manager.wxentity;

import java.io.Serializable;

/**
 * @ClassName: WxTag
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月7日 上午11:56:49
 */
public class WxTag implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 4935779938773516705L;

	private String id;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
