/**
 * 
 */
package weixin.server.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.dom4j.DocumentException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weixin.server.entity.base.WxBaseMsgEntity;
import weixin.server.entity.base.WxBaseRespEntity;
import weixin.server.exception.WxException;
import weixin.server.service.WxAuthService;
import weixin.server.service.WxMessageService;


/**
 * @author honey.zhao@aliyun.com
 * @version Jul 28, 2013
 * 
 */
@Controller
@RequestMapping("/wx")
public class WxController {
	private static final Logger log = Logger.getLogger(WxController.class);
	//http://www.202.106.199.34/wxdemo/rest/weixinmp/authGet.do?signature=1&timestamp=1&nonce=1&echostr=1
	@Resource
	private WxAuthService authService;
	@Resource
	private WxMessageService messageService;
	
	@RequestMapping(value = "/authGet.do", method = RequestMethod.GET)
	public @ResponseBody
	String authGet(@RequestParam("signature") String signature,
			@RequestParam("timestamp") String timestamp,
			@RequestParam("nonce") String nonce,
			@RequestParam("echostr") String echostr) throws WxException {
		if (authService.validateAuth(signature, timestamp, nonce, echostr)) {
			log.info("received authentication message from Weixin Server.");
			return echostr;
		}
		return null;
	}

	@RequestMapping(value = "/authGet.do", method = RequestMethod.POST)
	public void authPost(HttpServletRequest request, HttpServletResponse response) throws WxException, IOException {
		// 将请求、响应的编码均设置为UTF-8（防止中文乱码）  
        request.setCharacterEncoding("UTF-8");  
        response.setCharacterEncoding("UTF-8");  
  
        // 调用核心业务类接收消息、处理消息  
        String respMessage = WxAuthService.processRequest(request);  
          
        // 响应消息  
        PrintWriter out = response.getWriter();  
        out.print(respMessage);  
        out.close();  
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@ResponseBody
    public String post(@RequestBody String requestBody) throws DocumentException, WxException {
		WxBaseMsgEntity msg = messageService.parseXML(requestBody);
		log.info("received " + msg.getMsgType() + " message.");
		
		WxBaseRespEntity resp = messageService.handleMessage(msg);
		
		return messageService.parseRespXML(resp).asXML();
	}

}
