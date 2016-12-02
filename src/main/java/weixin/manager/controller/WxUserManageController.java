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

import weixin.manager.bean.WxUser;
import weixin.manager.service.WxUserService;
import core.controller.BaseController;

@Controller
@RequestMapping(value = "/pc")
public class WxUserManageController extends BaseController {

	@Resource
	private WxUserService wxUserService;

	@RequestMapping(value = "/toGetWxUserList.do")
	public Object toGetWxUserLabelList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/wxUser/wxUserList";
	}

	@RequestMapping(value = "/getWxUserList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getWxUserList(HttpServletRequest request, Integer limit,
			Integer offset, Integer status) throws IllegalStateException,
			IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		List<WxUser> userList = wxUserService.selectByMap(condition);

		Map<String, Object> rsMap = new HashMap<String, Object>();

		request.setAttribute("sessionId", "");
		rsMap.put("rows", userList);
		rsMap.put("total", 30);
		return rsMap;

	}

	@RequestMapping(value = "/batchAddUserLabel.do", method = RequestMethod.POST)
	@ResponseBody
	public Object batchAddUserLabel(String labelIds, String opendIds) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		System.out.println("===========执行批量修改标签操作=========");
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/batchPullBlackUser.do", method = RequestMethod.POST)
	@ResponseBody
	public Object batchPullBlackUser(String opendIds) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		System.out.println("===========执行批量拉黑操作=========");
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/synchronizeWxServerUser.do", method = RequestMethod.POST)
	@ResponseBody
	public Object synchronizeWxServerUser() {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		System.out.println("===========执行同步服务器用户操作=========");
		rsMap.put("rtnCode", 1); // 1：成功 0：失败 rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

}
