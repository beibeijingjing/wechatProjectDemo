package weixin.manager.bean;

import java.io.Serializable;

public class WxMenu extends BaseBean implements Serializable {

	private static final long serialVersionUID = -6606049246553750761L;

	private String parentId;
	private String wxMenuName;
	private Integer wxMenuLevel;
	private Integer wxMenuType;
	private Integer wxMenuNo;
	private String wxMenuKey;
	private String wxMenuUrl;
	private String wxMediaId;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getWxMenuName() {
		return wxMenuName;
	}

	public void setWxMenuName(String wxMenuName) {
		this.wxMenuName = wxMenuName;
	}

	public Integer getWxMenuLevel() {
		return wxMenuLevel;
	}

	public void setWxMenuLevel(Integer wxMenuLevel) {
		this.wxMenuLevel = wxMenuLevel;
	}

	public Integer getWxMenuType() {
		return wxMenuType;
	}

	public void setWxMenuType(Integer wxMenuType) {
		this.wxMenuType = wxMenuType;
	}

	public Integer getWxMenuNo() {
		return wxMenuNo;
	}

	public void setWxMenuNo(Integer wxMenuNo) {
		this.wxMenuNo = wxMenuNo;
	}

	public String getWxMenuKey() {
		return wxMenuKey;
	}

	public void setWxMenuKey(String wxMenuKey) {
		this.wxMenuKey = wxMenuKey;
	}

	public String getWxMenuUrl() {
		return wxMenuUrl;
	}

	public void setWxMenuUrl(String wxMenuUrl) {
		this.wxMenuUrl = wxMenuUrl;
	}

	public String getWxMediaId() {
		return wxMediaId;
	}

	public void setWxMediaId(String wxMediaId) {
		this.wxMediaId = wxMediaId;
	}

}
