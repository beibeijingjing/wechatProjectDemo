package weixin.manager.bean;

import java.io.Serializable;

public class SysResources extends BaseBean implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -4513363144675084335L;

	private String resource_name;
	private String resource_desc;
	private String resource_url;
	private Integer resource_type;

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public String getResource_desc() {
		return resource_desc;
	}

	public void setResource_desc(String resource_desc) {
		this.resource_desc = resource_desc;
	}

	public String getResource_url() {
		return resource_url;
	}

	public void setResource_url(String resource_url) {
		this.resource_url = resource_url;
	}

	public Integer getResource_type() {
		return resource_type;
	}

	public void setResource_type(Integer resource_type) {
		this.resource_type = resource_type;
	}

}
