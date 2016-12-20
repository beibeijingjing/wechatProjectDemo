package weixin.manager.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import weixin.manager.bean.WxImgText;
import weixin.manager.service.WxImgTextService;
import core.controller.BaseController;
import core.exception.WxBaseException;

@Controller
@RequestMapping(value = "/pc")
public class WxImgTextManageController extends BaseController {

	@Resource
	private WxImgTextService wxImgTextService;

	@RequestMapping(value = "/toGetImgTextOneList.do")
	public Object toGetImgTextOneList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/imgText/imgTextOneList";
	}

	@RequestMapping(value = "/toGetAddImgTextOne.do")
	public Object toGetAddImgTextOne(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/imgText/addImgTextOne";
	}

	@RequestMapping(value = "/toGetUpdateImgTextOne.do")
	public Object toGetUpdateImgTextOne(HttpServletRequest request, String id,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);

		return "weixin/imgText/updateImgTextOne";
	}

	@RequestMapping(value = "/getImgTextList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getImgTextList(HttpServletRequest request, Integer limit,
			Integer offset, Integer type, Integer status)
			throws IllegalStateException, IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		condition.put("type", type);
		List<WxImgText> imgTextList = wxImgTextService.selectByMap(condition);

		Map<String, Object> rsMap = new HashMap<String, Object>();

		request.setAttribute("sessionId", "");
		rsMap.put("rows", imgTextList);
		rsMap.put("total", 30);
		return rsMap;

	}

	@RequestMapping(value = "/addImgTextOne.do", method = RequestMethod.POST)
	@ResponseBody
	public Object addImgTextOne(WxImgText imgText) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		rsMap.put("rtnCode", 0); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateImgTextOne.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateImgTextOne(WxImgText imgText) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		rsMap.put("rtnCode", 0); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

}
