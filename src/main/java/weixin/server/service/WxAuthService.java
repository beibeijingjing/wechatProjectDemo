/**
 * 
 */
package weixin.server.service;



import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;

import weixin.server.constant.WxMsgEventType;
import weixin.server.constant.WxMsgType;
import weixin.server.entity.auth.WxAuth;
import weixin.server.entity.auth.WxAuthReq;
import weixin.server.entity.msg.WxMsgTextEntity;
import weixin.server.exception.WxException;
import weixin.server.utils.MessageUtil;
import weixin.server.utils.ResourceUtils;
import weixin.server.utils.WxUtil;

/**
 * @author honey.zhao@aliyun.com
 * @version Jan 1, 2014
 * 
 */
@Repository
public class WxAuthService {
	
	private static final Logger log = LoggerFactory.getLogger(WxAuthService.class);
	
	public WxAuth getAccessToken(String appid, String appsecret)
			throws WxException {
		Map<String, String> paramsJson = new HashMap<String, String>();
		paramsJson.put("grant_type", "client_credential");
		paramsJson.put("appid", appid);
		paramsJson.put("secret", appsecret);

		WxAuth result = WxUtil.sendRequest(ResourceUtils.getResource("wx_access_token_create_url"),
				HttpMethod.GET, paramsJson, null, WxAuth.class);
		result.setGrantType("client_credential");
		result.setAppid(appid);
		result.setSecret(appsecret);
		return result;
	}

	public boolean validateAuth(String signature, String timestamp,
			String nonce, String echostr) throws WxException {
		WxAuthReq authReq = new WxAuthReq();
		authReq.setCreatedDate(new Date());
		authReq.setSignature(signature);
		authReq.setTimestamp(timestamp);
		authReq.setNonce(nonce);
		authReq.setEchostr(echostr);

		String excepted = hash(getStringToHash(timestamp, nonce,
				ResourceUtils.getResource("wx_token")));

		if (signature == null || !signature.equals(excepted)) {
			log.error("Authentication failed! excepted echostr ->" + excepted);
			log.error("                                 actual ->" + signature);
			return false;
		}

		return true;
	}

	protected static String getStringToHash(String timestamp, String nonce,
			String token) {
		List<String> list = new ArrayList<String>();
		list.add(timestamp);
		list.add(nonce);
		list.add(token);

		String result = "";
		Collections.sort(list);
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
			result += list.get(i);
		}
		return result;
	}

	protected static String hash(String str) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] b = md.digest(str.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < b.length; i++) {
				sb.append(Integer.toString((b[i] & 0xff) + 0x100, 16)
						.substring(1));
			}
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			// never happens
		}
		return null;
	}
	
	
	
	 /** 
     * 处理微信发来的请求 
     *  
     * @param request 
     * @return 
     */  
    public static String processRequest(HttpServletRequest request) {  
        String respMessage = null;  
        try {  
            // 默认返回的文本消息内容  
            String respContent = "请求处理异常，请稍候尝试！";  
  
            // xml请求解析  
            Map<String, String> requestMap =MessageUtil.parseXml(request);  
  
            // 发送方帐号（open_id）  
            String fromUserName = requestMap.get("FromUserName");  
            // 公众帐号  
            String toUserName = requestMap.get("ToUserName");  
            // 消息类型  
            String msgType = requestMap.get("MsgType");  
            log.info("----------------------接收微信服务器fromUserName，toUserName，msgType：   "+fromUserName+", "+toUserName+", "+msgType+"------------");
            
            // 回复文本消息  
            WxMsgTextEntity textMessage = new WxMsgTextEntity();  
            textMessage.setToUserName(fromUserName);  
            textMessage.setFromUserName(toUserName);  
            textMessage.setCreateTime(new Date().getTime());  
            textMessage.setMsgType(WxMsgType.TEXT);  
           // textMessage.setFuncFlag(0);  
  
            // 文本消息  
            if (msgType.equals(WxMsgType.TEXT)) {  
                respContent = "您发送的是文本消息！";  
            }  
            // 图片消息  
            else if (msgType.equals(WxMsgType.IMAGE)) {  
                respContent = "您发送的是图片消息！";  
            }  
            // 地理位置消息  
            else if (msgType.equals(WxMsgType.LOCATION)) {  
                respContent = "您发送的是地理位置消息！";  
            }  
            // 链接消息  
            else if (msgType.equals(WxMsgType.LINK)) {  
                respContent = "您发送的是链接消息！";  
            }  
            // 音频消息  
            else if (msgType.equals(WxMsgType.VOICE)) {  
                respContent = "您发送的是音频消息！";  
            }  
            // 事件推送  
            else if (msgType.equals(WxMsgType.EVENT)) {  
                // 事件类型  
                String eventType = requestMap.get("Event");  
                // 订阅  
                if (eventType.equals(WxMsgEventType.SUBSCRIBE)) {  
                    respContent = "谢谢您的关注！";  
                }  
                // 取消订阅  
                else if (eventType.equals(WxMsgEventType.UNSUBSCRIBE)) {  
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息  
                }  
                // 自定义菜单点击事件  
                else if (eventType.equals(WxMsgEventType.CLICK)) {  
                    // TODO 自定义菜单权没有开放，暂不处理该类消息  
                }  
            }  
  
            textMessage.setContent(respContent);  
            respMessage = MessageUtil.textMessageToXml(textMessage);
            log.info("----------------------反馈微信服务器respMessage："+respMessage+"------------------------");
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return respMessage;  
    }  

}
