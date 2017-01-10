/** 
 * @Prject: ssm-master
 * @Package: weixin.manager.security 
 * @Title: MyInvocationSecurityMetadataSource.java 
 * Copyright © 2017Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2017年1月4日 上午11:42:23   
 */
package weixin.manager.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import weixin.manager.mapper.SysResourcesMapper;
import weixin.manager.vo.SysRoleResourcesVo;

/**
 * @ClassName: MyInvocationSecurityMetadataSource
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2017年1月4日 上午11:42:23
 */
public class MyInvocationSecurityMetadataSource implements
		FilterInvocationSecurityMetadataSource, InitializingBean {
	private boolean expire = false;

	private final static List<ConfigAttribute> NULL_CONFIG_ATTRIBUTE = Collections
			.emptyList();

	private Map<String, Collection<ConfigAttribute>> requestMap;

	private final AntPathMatcher urlMatcher = new AntPathMatcher();

	@Autowired
	private SysResourcesMapper sysResourceService;

	/**
	 * 获取所有权限集合
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		Set<ConfigAttribute> set = new HashSet<ConfigAttribute>();
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : requestMap
				.entrySet()) {
			set.addAll(entry.getValue());
		}
		return set;
	}

	/**
	 * 根据request请求获取访问资源所需权限
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object obj)
			throws IllegalArgumentException {
		// 刷新资源
		if (isExpire()) {
			this.requestMap.clear();
			expire = false;
		}
		// 若map为空，则重新加载
		if (this.requestMap == null || this.requestMap.isEmpty()) {
			this.requestMap = bindRequestMap();
		}

		String URL = ((FilterInvocation) obj).getRequestUrl();
		if (URL.contains("?")) {
			URL = URL.substring(0, URL.indexOf("?"));
		}
		Collection<ConfigAttribute> attrs = NULL_CONFIG_ATTRIBUTE;
		for (Map.Entry<String, Collection<ConfigAttribute>> entry : requestMap
				.entrySet()) {
			if (urlMatcher.match(URL, entry.getKey())) {
				attrs = entry.getValue();
				break;
			}
		}

		// 防止数据库中没有数据，不能进行权限拦截
		if (attrs.size() < 1) {
			attrs = new ArrayList<ConfigAttribute>();
			ConfigAttribute configAttribute = new SecurityConfig("ROLE_NO_USER");
			attrs.add(configAttribute);
		}
		return attrs;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return FilterInvocation.class.isAssignableFrom(arg0);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		this.requestMap = this.bindRequestMap();

	}

	protected Map<String, Collection<ConfigAttribute>> bindRequestMap() {
		Map<String, Collection<ConfigAttribute>> map = new LinkedHashMap<String, Collection<ConfigAttribute>>();

		Map<String, String> sourceMap = this.loadResouece();

		for (Map.Entry<String, String> entry : sourceMap.entrySet()) {
			String key = entry.getKey();
			Collection<ConfigAttribute> attr = new ArrayList<ConfigAttribute>();
			attr = SecurityConfig.createListFromCommaDelimitedString(entry
					.getValue());
			map.put(key, attr);
		}

		return map;
	}

	/**
	 * 从数据库中加载权限和资源的对应列表
	 * 
	 * @return
	 */
	private Map<String, String> loadResouece() {
		Map<String, String> map = new LinkedHashMap<String, String>();
		List<SysRoleResourcesVo> list = sysResourceService.getAllResRole();
		if (list != null && list.size() > 0) {
			for (SysRoleResourcesVo r : list) {
				String path = r.getResourceUrl();
				String code = r.getRoleCode();
				if (map.containsKey(path)) {
					String existCode = map.get(path);
					map.put(path, existCode + "," + code);
				} else {
					map.put(path, code);
				}
			}
		}
		return map;
	}

	public boolean isExpire() {
		return expire;
	}

	public void expireNow() {
		this.expire = true;
	}

}
