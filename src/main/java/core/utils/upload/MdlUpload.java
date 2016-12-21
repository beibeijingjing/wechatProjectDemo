/** 
 * @Prject: ssm-master
 * @Package: core.utils.upload 
 * @Title: MdlUpload.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月16日 下午4:18:09   
 */
package core.utils.upload;

/**
 * @ClassName: MdlUpload
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月16日 下午4:18:09
 */
public class MdlUpload {
	private String type;
	private String media_id;
	private String created_at;
	private String url;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getMedia_id() {
		return media_id;
	}

	public void setMedia_id(String mediaId) {
		media_id = mediaId;
	}

	public String getCreated_at() {
		return created_at;
	}

	public void setCreated_at(String createdAt) {
		created_at = createdAt;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public MdlUpload() {
		super();
	}

	@Override
	public String toString() {
		return "MdlUpload [created_at=" + created_at + ", media_id=" + media_id
				+ ", type=" + type + "]";
	}
}
