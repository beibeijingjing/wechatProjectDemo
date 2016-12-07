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

import weixin.manager.bean.WxUserLabel;
import weixin.manager.service.WxUserLabelService;
import core.controller.BaseController;

@Controller
@RequestMapping(value = "/pc")
public class WxUserLabelManageController extends BaseController {

	@Resource
	private WxUserLabelService wxUserLabelService;

	@RequestMapping(value = "/toGetWxUserLabelList.do")
	public Object toGetWxUserLabelList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/wxUserLabel/wxUserLabelList";
	}

	@RequestMapping(value = "/toGetAddWxUserLabel.do")
	public Object toGetAddWxUserLabel(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/wxUserLabel/addWxUserLabel";
	}

	@RequestMapping(value = "/toGetUpdateWxUserLabel.do")
	public Object toGetUpdateWxMenu(HttpServletRequest request, String id,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		WxUserLabel label = wxUserLabelService.selectByPrimaryKey(id);
		if (label != null) {
			request.setAttribute("label", label);
		}

		return "weixin/wxUserLabel/updateWxUserLabel";
	}

	@RequestMapping(value = "/getWxUserLabelList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getWxUserLabelList(HttpServletRequest request, Integer limit,
			Integer offset, Integer status) throws IllegalStateException,
			IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		List<WxUserLabel> labelList = wxUserLabelService.selectByMap(condition);

		Map<String, Object> rsMap = new HashMap<String, Object>();

		request.setAttribute("sessionId", "");
		rsMap.put("rows", labelList);
		rsMap.put("total", 30);
		return rsMap;

	}

	@RequestMapping(value = "/addWxUserLabel.do", method = RequestMethod.POST)
	@ResponseBody
	public Object addWxUserLabel(WxUserLabel label) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		wxUserLabelService.addSynUserLabel(label);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateWxUserLabel.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWxUserLabel(WxUserLabel label) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		wxUserLabelService.updateSynUserLabel(label);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/batchDeleteWxUserLabel.do", method = RequestMethod.POST)
	@ResponseBody
	public Object batchDeleteWxUserLabel(String ids) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		wxUserLabelService.batchDeleteUserLabel(ids);

		rsMap.put("rtnCode", 1); // 1：成功 0：失败 rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/synchronizeWxUserLabel.do", method = RequestMethod.POST)
	@ResponseBody
	public Object synchronizeWxUserLabel() {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		System.out.println("===========执行同步操作=========");
		rsMap.put("rtnCode", 1); // 1：成功 0：失败 rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

}
