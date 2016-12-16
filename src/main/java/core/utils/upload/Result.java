/** 
 * @Prject: ssm-master
 * @Package: core.utils.upload 
 * @Title: Result.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月16日 下午4:17:04   
 */
package core.utils.upload;

/**
 * @ClassName: Result
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月16日 下午4:17:04
 */
public class Result<T> {
	private T obj;
	private String errcode;
	private String errmsg;

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

}
