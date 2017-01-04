/** 
 * @Prject: ssm-master
 * @Package: weixin.manager.security 
 * @Title: AntUrlPathMatcher.java 
 * Copyright © 2017Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2017年1月4日 上午11:43:56   
 */
package weixin.manager.security;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @ClassName: AntUrlPathMatcher
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2017年1月4日 上午11:43:56
 */
public class AntUrlPathMatcher implements UrlMatcher {
	private boolean requiresLowerCaseUrl;
	private final PathMatcher pathMatcher;

	public AntUrlPathMatcher() {
		this(true);

	}

	public AntUrlPathMatcher(boolean requiresLowerCaseUrl) {
		this.requiresLowerCaseUrl = true;
		this.pathMatcher = new AntPathMatcher();
		this.requiresLowerCaseUrl = requiresLowerCaseUrl;
	}

	@Override
	public Object compile(String path) {
		if (this.requiresLowerCaseUrl) {
			return path.toLowerCase();
		}
		return path;
	}

	public void setRequiresLowerCaseUrl(boolean requiresLowerCaseUrl) {

		this.requiresLowerCaseUrl = requiresLowerCaseUrl;
	}

	@Override
	public boolean pathMatchesUrl(Object path, String url) {
		if (("/**".equals(path)) || ("**".equals(path))) {
			return true;
		}

		return this.pathMatcher.match((String) path, url);
	}

	@Override
	public String getUniversalMatchPattern() {
		return "/**";
	}

	@Override
	public boolean requiresLowerCaseUrl() {
		return this.requiresLowerCaseUrl;
	}

	@Override
	public String toString() {
		return super.getClass().getName() + "[requiresLowerCase='"
				+ this.requiresLowerCaseUrl + "']";
	}
}
