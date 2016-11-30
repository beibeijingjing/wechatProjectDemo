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

import weixin.manager.bean.SysMenu;
import weixin.manager.service.SysMenuService;
import core.controller.BaseController;

@Controller
@RequestMapping(value = "/pc")
public class SysMenuManageController extends BaseController {

	@Resource
	private SysMenuService sysMenuService;

	@RequestMapping(value = "/toGetSysMenuList.do")
	public Object toGetSysMenuList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/sysMenu/sysMenuList";
	}

	@RequestMapping(value = "/toGetAddSysMenu.do")
	public Object toGetAddSysMenu(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		Map<String, Object> condition = new HashMap<String, Object>();
		List<SysMenu> menuList = sysMenuService.selectByMap(condition);
		if (menuList != null) {
			request.setAttribute("menuList", menuList);
		}
		return "weixin/sysMenu/addSysMenu";
	}

	@RequestMapping(value = "/toGetUpdateSysMenu.do")
	public Object toGetUpdateSysMenu(HttpServletRequest request, String id,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		SysMenu menu = sysMenuService.selectByPrimaryKey(id);
		if (menu != null) {
			request.setAttribute("menu", menu);
		}

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", 0);
		List<SysMenu> menuList = sysMenuService.selectByMap(condition);
		if (menuList != null) {
			request.setAttribute("menuList", menuList);
		}
		return "weixin/sysMenu/updateSysMenu";
	}

	@RequestMapping(value = "/getSysMenuList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getSysMenuList(HttpServletRequest request, Integer limit,
			Integer offset, Integer status) throws IllegalStateException,
			IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		List<SysMenu> menuList = sysMenuService.selectByMap(condition);
		for (SysMenu menu : menuList) {
			System.out.println("==========" + menu.getMenuName());
		}

		Map<String, Object> rsMap = new HashMap<String, Object>();

		request.setAttribute("sessionId", "");
		rsMap.put("rows", menuList);
		rsMap.put("total", 30);
		return rsMap;

	}

	@RequestMapping(value = "/addSysMenu.do", method = RequestMethod.POST)
	@ResponseBody
	public Object addSysMenu(SysMenu menu) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if (menu != null && StringUtils.isNotEmpty(menu.getParentId())
				&& "0".equals(menu.getParentId())) {
			menu.setMenuLevel(0);
		} else {
			menu.setMenuLevel(1);
		}
		sysMenuService.insert(menu);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateSysMenu.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSysMenu(SysMenu menu) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		if (menu != null && StringUtils.isNotEmpty(menu.getParentId())
				&& "0".equals(menu.getParentId())) {
			menu.setMenuLevel(0);
		} else {
			menu.setMenuLevel(1);
		}

		sysMenuService.updateByPrimaryKeySelective(menu);

		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateSysMenuStatus.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSysMenuStatus(@RequestParam String id,
			@RequestParam Integer status) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		SysMenu menu = new SysMenu();
		menu.setId(id);
		menu.setDelFlag(status);
		sysMenuService.updateByPrimaryKeySelective(menu);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败 rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

}
