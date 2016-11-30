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

import weixin.manager.bean.SysUser;
import weixin.manager.service.SysUserService;
import core.controller.BaseController;

@Controller
@RequestMapping(value = "/pc")
public class SysUserManageController extends BaseController {

	@Resource
	private SysUserService sysUserService;

	@RequestMapping(value = "/toGetSysUserList.do")
	public Object toGetSysUserList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/sysUser/sysUserList";
	}

	@RequestMapping(value = "/toGetAddSysUser.do")
	public Object toGetAddSysUser(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", "0");
		List<SysUser> userList = sysUserService.selectByMap(condition);
		if (userList != null) {
			request.setAttribute("userList", userList);
		}
		return "weixin/sysUser/addSysUser";
	}

	@RequestMapping(value = "/toGetUpdateSysUser.do")
	public Object toGetUpdateSysUser(HttpServletRequest request, String id,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		SysUser user = sysUserService.selectByPrimaryKey(id);
		if (user != null) {
			request.setAttribute("user", user);
		}

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", 0);
		List<SysUser> userList = sysUserService.selectByMap(condition);
		if (userList != null) {
			request.setAttribute("userList", userList);
		}
		return "weixin/sysUser/updateSysUser";
	}

	@RequestMapping(value = "/getSysUserList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getSysUserList(HttpServletRequest request, Integer limit,
			Integer offset, Integer status) throws IllegalStateException,
			IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		List<SysUser> userList = sysUserService.selectByMap(condition);

		Map<String, Object> rsMap = new HashMap<String, Object>();

		request.setAttribute("sessionId", "");
		rsMap.put("rows", userList);
		rsMap.put("total", 30);
		return rsMap;

	}

	@RequestMapping(value = "/addSysUser.do", method = RequestMethod.POST)
	@ResponseBody
	public Object addSysUser(SysUser user) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		sysUserService.insertSelective(user);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateSysUser.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSysUser(SysUser user) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		sysUserService.updateByPrimaryKeySelective(user);

		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateSysUserStatus.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSysUserStatus(@RequestParam String id,
			@RequestParam Integer status) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		SysUser user = new SysUser();
		user.setId(id);
		user.setDelFlag(status);
		sysUserService.updateByPrimaryKeySelective(user);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败 rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

}
