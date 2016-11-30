/**
 * 
 */
package weixin.server.entity.item;

import weixin.server.entity.base.WxBaseRespEntity;


/**
 * @author honey.zhao@aliyun.com
 * @version Dec 29, 2013
 *
 */
public class WxItemImageEntity extends WxBaseRespEntity {
	private String mediaId;
	private String picUrl;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public WxItemImageEntity(String mediaId, String picUrl) {
		super();
		this.mediaId = mediaId;
		this.picUrl = picUrl;
	}
	public WxItemImageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "WxItemImageEntity [mediaId=" + mediaId + ", picUrl=" + picUrl
				+ "]";
	}
	
	
}