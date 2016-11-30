/**
 * 
 */
package weixin.server.entity.msg;

import weixin.server.entity.base.WxBaseMsgEntity;
import weixin.server.entity.item.WxItemVideoEntity;

/**
 * @author honey.zhao@aliyun.com
 * @version Dec 24, 2013
 *
 */
public class WxMsgVideoEntity extends WxBaseMsgEntity {
	private WxItemVideoEntity video;

	public WxItemVideoEntity getVideo() {
		return video;
	}

	public void setVideo(WxItemVideoEntity video) {
		this.video = video;
	}

	public WxMsgVideoEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WxMsgVideoEntity(String toUserName, String fromUserName,
			Long createTime, String msgType, Long msgId) {
		// TODO Auto-generated constructor stub
	}

	public WxMsgVideoEntity(String toUserName, String fromUserName,
			Long createTime, String msgType, Long msgId, WxItemVideoEntity video) {
		this.video = video;
	}

	@Override
	public String toString() {
		return "WxMsgVideoEntity [video=" + video + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((video == null) ? 0 : video.hashCode());
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
		WxMsgVideoEntity other = (WxMsgVideoEntity) obj;
		if (video == null) {
			if (other.video != null)
				return false;
		} else if (!video.equals(other.video))
			return false;
		return true;
	}
	
	
}
