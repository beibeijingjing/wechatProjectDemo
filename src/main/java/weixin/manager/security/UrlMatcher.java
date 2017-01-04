/** 
 * @Prject: ssm-master
 * @Package: weixin.manager.security 
 * @Title: UrlMatcher.java 
 * Copyright © 2017Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2017年1月4日 上午11:43:29   
 */
package weixin.manager.security;

/**
 * @ClassName: UrlMatcher
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2017年1月4日 上午11:43:29
 */
public interface UrlMatcher {
	Object compile(String paramString);

	boolean pathMatchesUrl(Object paramObject, String paramString);

	String getUniversalMatchPattern();

	boolean requiresLowerCaseUrl();
}
