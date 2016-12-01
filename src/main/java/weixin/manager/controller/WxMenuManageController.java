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

import weixin.manager.bean.WxMenu;
import weixin.manager.service.WxMenuServiceNew;
import core.controller.BaseController;

@Controller
@RequestMapping(value = "/pc")
public class WxMenuManageController extends BaseController {

	@Resource
	private WxMenuServiceNew wxMenuServiceNew;

	@RequestMapping(value = "/toGetWxMenuList.do")
	public Object toGetWxMenuList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/wxMenu/wxMenuList";
	}

	@RequestMapping(value = "/toGetAddWxMenu.do")
	public Object toGetAddWxMenu(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", "0");
		condition.put("parent_id", "0");
		condition.put("is_extend", "0");
		List<WxMenu> wxMenuList = wxMenuServiceNew.selectByMap(condition);
		if (wxMenuList != null) {
			request.setAttribute("wxMenuList", wxMenuList);
		}
		return "weixin/wxMenu/addWxMenu";
	}

	@RequestMapping(value = "/toGetUpdateWxMenu.do")
	public Object toGetUpdateWxMenu(HttpServletRequest request, String id,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		WxMenu menu = wxMenuServiceNew.selectByPrimaryKey(id);
		if (menu != null) {
			request.setAttribute("menu", menu);
		}

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", 0);
		condition.put("parent_id", "0");
		List<WxMenu> menuList = wxMenuServiceNew.selectByMap(condition);
		if (menuList != null) {
			request.setAttribute("menuList", menuList);
		}
		return "weixin/wxMenu/updateWxMenu";
	}

	@RequestMapping(value = "/getWxMenuList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getWxMenuList(HttpServletRequest request, Integer limit,
			Integer offset, Integer status) throws IllegalStateException,
			IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		List<WxMenu> menuList = wxMenuServiceNew.selectByMap(condition);

		Map<String, Object> rsMap = new HashMap<String, Object>();

		request.setAttribute("sessionId", "");
		rsMap.put("rows", menuList);
		rsMap.put("total", 30);
		return rsMap;

	}

	@RequestMapping(value = "/addWxMenu.do", method = RequestMethod.POST)
	@ResponseBody
	public Object addWxMenu(WxMenu menu) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		wxMenuServiceNew.insertSelective(menu);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateWxMenu.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWxMenu(WxMenu menu) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		wxMenuServiceNew.updateByPrimaryKeySelective(menu);

		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateWxMenuStatus.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateWxMenuStatus(@RequestParam String id,
			@RequestParam Integer status) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		WxMenu menu = new WxMenu();
		menu.setId(id);
		menu.setDelFlag(status);
		wxMenuServiceNew.updateByPrimaryKeySelective(menu);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败 rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/batchDeleteWxMenu.do", method = RequestMethod.POST)
	@ResponseBody
	public Object batchDeleteWxMenu(String ids) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		wxMenuServiceNew.batchDeleteWxMenuById(ids);

		rsMap.put("rtnCode", 1); // 1：成功 0：失败 rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/synchronizeWxMenu.do", method = RequestMethod.POST)
	@ResponseBody
	public Object synchronizeWxMenu() {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		System.out.println("===========执行同步操作=========");
		rsMap.put("rtnCode", 1); // 1：成功 0：失败 rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

}
