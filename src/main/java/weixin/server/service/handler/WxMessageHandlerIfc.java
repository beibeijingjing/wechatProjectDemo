/**
 * 
 */
package weixin.server.service.handler;

import java.util.Map;

import weixin.server.constant.WxMsgTypeEnum;
import weixin.server.entity.base.WxBaseMsgEntity;
import weixin.server.entity.base.WxBaseRespEntity;

/**
 * @author honey.zhao@aliyun.com
 * @version Jan 5, 2014
 *
 */
public interface WxMessageHandlerIfc {
	
	WxMsgTypeEnum[] listIntetestedMessageType();
	
	WxBaseRespEntity handle(WxBaseMsgEntity msg, Map<String, Object> context);
	
	Integer priority();
}
