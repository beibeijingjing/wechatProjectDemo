/**
 * 
 */
package weixin.server.entity.resp;

import weixin.server.entity.base.WxBaseRespEntity;
import weixin.server.entity.item.WxItemVoiceEntity;

/**
 * @author honey.zhao@aliyun.com
 * @version Dec 29, 2013
 * 
 */
public class WxRespVoiceEntity extends WxBaseRespEntity {

	private WxItemVoiceEntity voice;

	public WxItemVoiceEntity getVoice() {
		return voice;
	}

	public void setVoice(WxItemVoiceEntity voice) {
		this.voice = voice;
	}

	public WxRespVoiceEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WxRespVoiceEntity(WxItemVoiceEntity voice) {
		super();
		this.voice = voice;
	}

	@Override
	public String toString() {
		return "WxRespVoiceEntity [voice=" + voice + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((voice == null) ? 0 : voice.hashCode());
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
		WxRespVoiceEntity other = (WxRespVoiceEntity) obj;
		if (voice == null) {
			if (other.voice != null)
				return false;
		} else if (!voice.equals(other.voice))
			return false;
		return true;
	}
	
	
}
