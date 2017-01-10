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
	private String roleId;
	private String resourceUrl;
	private String resourceDesc;
	private String resourceName;
	private String id;// 资源id

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

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getResourceDesc() {
		return resourceDesc;
	}

	public void setResourceDesc(String resourceDesc) {
		this.resourceDesc = resourceDesc;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
