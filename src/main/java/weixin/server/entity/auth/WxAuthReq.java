/**
 * 
 */
package weixin.server.entity.auth;

import weixin.server.entity.base.WxBaseEntity;


/**
 * @author honey.zhao@aliyun.com
 * @version Jul 27, 2013
 */
public class WxAuthReq extends WxBaseEntity {
	private String signature;
	private String timestamp;
	private String nonce;
	private String echostr;
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getNonce() {
		return nonce;
	}
	public void setNonce(String nonce) {
		this.nonce = nonce;
	}
	public String getEchostr() {
		return echostr;
	}
	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}
	public WxAuthReq() {
		super();
	}
	@Override
	public String toString() {
		return "WxAuthReq [signature=" + signature + ", timestamp=" + timestamp
				+ ", nonce=" + nonce + ", echostr=" + echostr + "]";
	}
}
