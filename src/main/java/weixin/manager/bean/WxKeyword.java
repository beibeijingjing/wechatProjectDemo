package weixin.manager.bean;

import java.io.Serializable;

public class WxKeyword extends BaseBean implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 4817782801291084357L;

	private String wxKey;
	private String wxMessage;
	private Integer wxKeyType;
	private String mediaId;

	public String getWxKey() {
		return wxKey;
	}

	public void setWxKey(String wxKey) {
		this.wxKey = wxKey;
	}

	public String getWxMessage() {
		return wxMessage;
	}

	public void setWxMessage(String wxMessage) {
		this.wxMessage = wxMessage;
	}

	public Integer getWxKeyType() {
		return wxKeyType;
	}

	public void setWxKeyType(Integer wxKeyType) {
		this.wxKeyType = wxKeyType;
	}

	public String getMediaId() {
		return mediaId;
	}

	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

}
