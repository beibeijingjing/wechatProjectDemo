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
public class ResponBaseEntity implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */

	private static final long serialVersionUID = 7390637883977204455L;
	private Integer errcode;
	private String errmsg;

	public Integer getErrcode() {
		return errcode;
	}

	public void setErrcode(Integer errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
