/** 
 * @Prject: ssm-master
 * @Package: weixin.manager.security 
 * @Title: MyUserDetailService.java 
 * Copyright © 2017Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2017年1月4日 上午11:26:48   
 */
package weixin.manager.security;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @ClassName: MyUserDetailService
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2017年1月4日 上午11:26:48
 */
public class MyUserDetailService implements UserDetailsService {

	// 登陆验证时，通过username获取用户的所有权限信息，
	// 并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
	@Override
	@SuppressWarnings("deprecation")
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

		GrantedAuthorityImpl auth2 = new GrantedAuthorityImpl("ROLE_ADMIN");
		GrantedAuthorityImpl auth1 = new GrantedAuthorityImpl("ROLE_USER");

		if (username.equals("cyw")) {
			auths = new ArrayList<GrantedAuthority>();
			auths.add(auth1);
			auths.add(auth2);
		}

		User user = new User(username, "cyw", true, true, true, true, auths);
		return user;
	}

}
