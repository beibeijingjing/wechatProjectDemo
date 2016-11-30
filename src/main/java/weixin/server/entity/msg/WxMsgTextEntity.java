/**
 * 
 */
package weixin.server.entity.msg;

import weixin.server.entity.base.WxBaseMsgEntity;


/**
 * @author honey.zhao@aliyun.com
 * @version Jul 28, 2013
 *
 *
 */
public class WxMsgTextEntity extends WxBaseMsgEntity {
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	
	
	
}
