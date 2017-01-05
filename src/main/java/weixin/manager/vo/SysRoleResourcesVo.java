/** 
 * @Prject: ssm-master
 * @Package: weixin.manager.vo 
 * @Title: SysRoleResourcesVo.java 
 * Copyright © 2017Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2017年1月5日 下午4:26:00   
 */
package weixin.manager.vo;

/**
 * @ClassName: SysRoleResourcesVo
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2017年1月5日 下午4:26:00
 */
public class SysRoleResourcesVo {
	private String roleName;
	private String roleCode;
	private String resourceUrl;

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getRoleCode() {
		return roleCode;
	}

	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

}
