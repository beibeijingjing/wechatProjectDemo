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
public class WxItemVideoEntity extends WxBaseRespEntity {
	private String mediaId;
	private String title;
	private String description;
	WxItemThumbEntity thumb;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public WxItemThumbEntity getThumb() {
		return thumb;
	}
	public void setThumb(WxItemThumbEntity thumb) {
		this.thumb = thumb;
	}
	public WxItemVideoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WxItemVideoEntity(String mediaId, String title, String description,
			WxItemThumbEntity thumb) {
		super();
		this.mediaId = mediaId;
		this.title = title;
		this.description = description;
		this.thumb = thumb;
	}
	@Override
	public String toString() {
		return "WxItemVideoEntity [mediaId=" + mediaId + ", title=" + title
				+ ", description=" + description + ", thumb=" + thumb + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((description == null) ? 0 : description.hashCode());
		result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
		result = prime * result + ((thumb == null) ? 0 : thumb.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WxItemVideoEntity other = (WxItemVideoEntity) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (mediaId == null) {
			if (other.mediaId != null)
				return false;
		} else if (!mediaId.equals(other.mediaId))
			return false;
		if (thumb == null) {
			if (other.thumb != null)
				return false;
		} else if (!thumb.equals(other.thumb))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
}
