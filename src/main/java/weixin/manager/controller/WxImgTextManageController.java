package weixin.manager.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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
	// private static final String priUrl =
	// "http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=";

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
		WxImgText imgText = wxImgTextService.selectByPrimaryKey(id);
		// if (imgText != null) { imgText.setThumb_media_url(priUrl +
		// imgText.getThumb_media_url()); }
		request.setAttribute("imgText", imgText);
		return "weixin/imgText/updateImgTextOne";
	}

	@RequestMapping(value = "/toGetImgTextMoreList.do")
	public Object toGetImgTextMoreList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/imgText/imgTextMoreList";
	}

	@RequestMapping(value = "/toGetAddImgTextMore.do")
	public Object toGetAddImgTextMore(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/imgText/addImgTextMore";
	}

	@RequestMapping(value = "/toGetUpdateImgTextMore.do")
	public Object toGetUpdateImgTextMore(HttpServletRequest request, String id,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		WxImgText imgText = wxImgTextService.selectByPrimaryKey(id);
		request.setAttribute("imgText", imgText);

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("delFlag", 0);
		condition.put("parent_id", imgText.getId());
		List<WxImgText> imgTextList = wxImgTextService.selectByMap(condition);
		WxImgText imgTextFirst = new WxImgText();
		if (imgTextList != null && imgTextList.size() > 0) {
			imgTextFirst = imgTextList.get(0);
		}
		request.setAttribute("imgTextFirst", imgTextFirst);
		request.setAttribute("imgTextList", imgTextList);
		return "weixin/imgText/updateImgTextMore";
	}

	@RequestMapping(value = "/getImgTextList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getImgTextList(HttpServletRequest request, Integer limit,
			Integer offset, Integer type, Integer status, String parentId)
			throws IllegalStateException, IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		condition.put("type", type);
		condition.put("parent_id", parentId);
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
		wxImgTextService.addSynImgTextOne(imgText);
		rsMap.put("rtnCode", 0);
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateImgTextOne.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateImgTextOne(WxImgText imgText) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		wxImgTextService.updateSynImgTextOne(imgText);
		rsMap.put("rtnCode", 0);
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/deleteImgText.do", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteImgText(String mediaId) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(mediaId)) {
			String articleIdArr[] = mediaId.split("@");
			if (articleIdArr != null && articleIdArr.length > 0) {
				wxImgTextService.deleteSynImgText(articleIdArr[0]);
			}
		}
		rsMap.put("rtnCode", 0);
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/deleteImgTextMore.do", method = RequestMethod.POST)
	@ResponseBody
	public Object deleteImgTextMore(String id) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(id)) {
			String articleIdArr[] = id.split("@");
			if (articleIdArr != null && articleIdArr.length > 0) {
				wxImgTextService.deleteImgTextMore(articleIdArr[0]);
			}
		}
		rsMap.put("rtnCode", 0);
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/synImgTextMore.do", method = RequestMethod.POST)
	@ResponseBody
	public Object synImgTextMore(String id) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if (StringUtils.isNotEmpty(id)) {
			String articleIdArr[] = id.split("@");
			if (articleIdArr != null && articleIdArr.length > 0) {
				wxImgTextService.updateSynImgTextMore(articleIdArr[0]);
			}
		}
		rsMap.put("rtnCode", 0);
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/addImgTextMore.do", method = RequestMethod.POST)
	@ResponseBody
	public Object addImgTextMore(WxImgText imgText) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		String id = wxImgTextService.addImgTextMore(imgText);
		rsMap.put("rtnCode", 0);
		rsMap.put("rtnMsg", "操作成功.");
		rsMap.put("id", id);
		return rsMap;
	}

	@RequestMapping(value = "/updateImgTextMore.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateImgTextMore(WxImgText imgText) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		wxImgTextService.updateImgTextMore(imgText);
		rsMap.put("rtnCode", 0);
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/getImgText.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getImgText(String id) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		WxImgText imgText = wxImgTextService.selectByPrimaryKey(id);

		rsMap.put("rtnCode", 0);
		rsMap.put("rtnMsg", "操作成功.");
		rsMap.put("data", imgText);
		return rsMap;
	}

}
