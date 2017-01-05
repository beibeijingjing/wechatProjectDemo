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
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import weixin.manager.bean.SysRole;
import weixin.manager.bean.SysUser;
import weixin.manager.mapper.SysRoleMapper;
import weixin.manager.mapper.SysUserMapper;

/**
 * @ClassName: MyUserDetailService
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2017年1月4日 上午11:26:48
 */
public class MyUserDetailService implements UserDetailsService {
	@Autowired
	private SysUserMapper userMapper;
	@Autowired
	private SysRoleMapper roleMapper;

	// 登陆验证时，通过username获取用户的所有权限信息，
	// 并返回User放到spring的全局缓存SecurityContextHolder中，以供授权器使用
	@Override
	@SuppressWarnings("deprecation")
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		String psd = "";
		// 读取用户
		SysUser user = userMapper.getSysUserByLoginName(username);
		// 读取用户角色
		List<SysRole> roleList = null;
		if (user != null) {
			roleList = roleMapper.getSysRoleListByUserId(user.getId());
			psd = user.getPassword();
		}
		// 用户权限集合
		Collection<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();
		if (roleList != null) {
			for (SysRole role : roleList) {
				auths.add(new GrantedAuthorityImpl(role.getRole_code()));
			}
		}

		User userAuth = new User(username, psd, true, true, true, true, auths);
		return userAuth;
	}

}
