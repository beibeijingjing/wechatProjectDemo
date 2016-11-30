/**
 * 
 */
package weixin.server.entity.base;

import javax.persistence.Column;

import com.google.gson.annotations.SerializedName;

import weixin.manager.bean.BaseBean;

/**
 * @author honey.zhao@aliyun.com
 * @version Dec 29, 2013
 * 
 */

public class WxMenuEntity extends BaseBean {

	private String type;// 菜单类别 //click view scancode_push scancode_waitmsg
						// pic_sysphoto pic_photo_or_album pic_weixin
						// location_select media_idview_limited
	private String name;// 菜单名称
	@SerializedName("key")
	private String menu_key;// 关键字
	private String url;// 链接地址s
	private String media_id;// 素材id
	private Integer level;// 菜单级别
	private String parentId;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenu_key() {
		return menu_key;
	}

	public void setMenu_key(String menu_key) {
		this.menu_key = menu_key;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String media_id) {
		this.media_id = media_id;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	@Override
	public String toString() {
		return "WxMenuEntity [type=" + type + ", name=" + name + ", menu_key="
				+ menu_key + ", url=" + url + ", media_id=" + media_id
				+ ", level=" + level + ", parentId=" + parentId + "]";
	}

}
