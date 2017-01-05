package weixin.manager.bean;

import java.io.Serializable;

public class SysRole extends BaseBean implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -6223412471869527156L;

	private String role_name;
	private String role_code;
	private Integer role_type;

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public String getRole_code() {
		return role_code;
	}

	public void setRole_code(String role_code) {
		this.role_code = role_code;
	}

	public Integer getRole_type() {
		return role_type;
	}

	public void setRole_type(Integer role_type) {
		this.role_type = role_type;
	}

}
