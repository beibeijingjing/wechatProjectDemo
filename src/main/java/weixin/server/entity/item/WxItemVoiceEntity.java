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
public class WxItemVoiceEntity extends WxBaseRespEntity {
	private String mediaId;
	private String format;
	private String recognition;
	public String getMediaId() {
		return mediaId;
	}
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}
	public String getFormat() {
		return format;
	}
	public void setFormat(String format) {
		this.format = format;
	}
	public String getRecognition() {
		return recognition;
	}
	public void setRecognition(String recognition) {
		this.recognition = recognition;
	}
	public WxItemVoiceEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public WxItemVoiceEntity(String mediaId, String format, String recognition) {
		super();
		this.mediaId = mediaId;
		this.format = format;
		this.recognition = recognition;
	}
	@Override
	public String toString() {
		return "WxItemVoiceEntity [mediaId=" + mediaId + ", format=" + format
				+ ", recognition=" + recognition + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((format == null) ? 0 : format.hashCode());
		result = prime * result + ((mediaId == null) ? 0 : mediaId.hashCode());
		result = prime * result
				+ ((recognition == null) ? 0 : recognition.hashCode());
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
		WxItemVoiceEntity other = (WxItemVoiceEntity) obj;
		if (format == null) {
			if (other.format != null)
				return false;
		} else if (!format.equals(other.format))
			return false;
		if (mediaId == null) {
			if (other.mediaId != null)
				return false;
		} else if (!mediaId.equals(other.mediaId))
			return false;
		if (recognition == null) {
			if (other.recognition != null)
				return false;
		} else if (!recognition.equals(other.recognition))
			return false;
		return true;
	}
	
	
}
