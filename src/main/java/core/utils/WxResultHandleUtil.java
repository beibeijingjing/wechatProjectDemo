/** 
 * @Prject: ssm-master
 * @Package: core.utils 
 * @Title: WxResultHandleUtil.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月9日 上午11:54:52   
 */
package core.utils;

import weixin.manager.wxentity.ResponBaseEntity;
import weixin.server.exception.WxException;
import weixin.server.model.WxRespCode;

import com.google.gson.Gson;

import core.exception.WxBaseException;

/**
 * @ClassName: WxResultHandleUtil
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月9日 上午11:54:52
 */
public class WxResultHandleUtil {

	@SuppressWarnings("unchecked")
	public static final <T> T getWxResponResult(String respBody,
			Class<T> resultClass) throws WxBaseException {
		Gson gson = new Gson();

		if (respBody.indexOf("{\"errcode\"") == 0
				|| respBody.indexOf("{\"errmsg\"") == 0) {
			ResponBaseEntity exJson = gson.fromJson(respBody,
					ResponBaseEntity.class);

			if (ResponBaseEntity.class.getName().equals(resultClass.getName())
					&& exJson.getErrcode() == 0) {
				return (T) exJson;
			} else {
				throw new WxBaseException(exJson);
			}
		}

		T result = gson.fromJson(respBody, resultClass);

		return result;
	}

}
