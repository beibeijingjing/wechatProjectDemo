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

import weixin.manager.bean.WxKeyword;
import weixin.manager.service.WxKeywordService;
import core.controller.BaseController;
import core.exception.WxBaseException;

@Controller
@RequestMapping(value = "/pc")
public class WxKeywordManageController extends BaseController {

	@Resource
	private WxKeywordService wxKeywordService;

	@RequestMapping(value = "/toGetWxKeywordList.do")
	public Object toGetWxKeywordList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/keyword/keywordList";
	}

	@RequestMapping(value = "/toGetAddWxKeyword.do")
	public Object toGetAddWxKeyword(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/keyword/addKeyword";
	}

	@RequestMapping(value = "/toGetUpdateWxKeyword.do")
	public Object toGetUpdateWxKeyword(HttpServletRequest request, String id,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);

		WxKeyword keyword = wxKeywordService.selectByPrimaryKey(id);
		if (keyword != null) {
			request.setAttribute("keyword", keyword);
		}

		return "weixin/keyword/updateKeyword";
	}

	@RequestMapping(value = "/getKeywordList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getKeywordList(HttpServletRequest request, Integer limit,
			Integer offset, Integer status) throws IllegalStateException,
			IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		List<WxKeyword> keywordList = wxKeywordService.selectByMap(condition);

		Map<String, Object> rsMap = new HashMap<String, Object>();

		request.setAttribute("sessionId", "");
		rsMap.put("rows", keywordList);
		rsMap.put("total", 30);
		return rsMap;

	}

	@RequestMapping(value = "/addKeyword.do", method = RequestMethod.POST)
	@ResponseBody
	public Object addKeyword(WxKeyword keyword) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		wxKeywordService.insertSelective(keyword);
		rsMap.put("rtnCode", 0); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateKeyword.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateKeyword(WxKeyword keyword) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		wxKeywordService.updateByPrimaryKeySelective(keyword);

		rsMap.put("rtnCode", 0); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/batchDeleteKeyword.do", method = RequestMethod.POST)
	@ResponseBody
	public Object batchDeleteKeyword(String ids) throws WxBaseException {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		wxKeywordService.batchDeleteKeyword(ids);

		rsMap.put("rtnCode", 0); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

}
