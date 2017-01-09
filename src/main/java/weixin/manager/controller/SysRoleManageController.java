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

import weixin.manager.bean.SysRole;
import weixin.manager.service.SysRoleService;
import core.controller.BaseController;

@Controller
@RequestMapping(value = "/pc")
public class SysRoleManageController extends BaseController {

	@Resource
	private SysRoleService sysRoleService;

	@RequestMapping(value = "/toGetSysRoleList.do")
	public Object toGetSysRoleList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/sysRole/sysRoleList";
	}

	@RequestMapping(value = "/toGetAddSysRole.do")
	public Object toGetAddSysRole(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);

		return "weixin/sysRole/addSysRole";
	}

	@RequestMapping(value = "/toGetUpdateSysRole.do")
	public Object toGetUpdateSysRole(HttpServletRequest request, String id,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		SysRole role = sysRoleService.selectByPrimaryKey(id);
		if (role != null) {
			request.setAttribute("role", role);
		}

		return "weixin/sysRole/updateSysRole";
	}

	@RequestMapping(value = "/getSysRoleList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getSysRoleList(HttpServletRequest request, Integer limit,
			Integer offset, Integer status) throws IllegalStateException,
			IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		List<SysRole> roleList = sysRoleService.selectByMap(condition);

		Map<String, Object> rsMap = new HashMap<String, Object>();

		request.setAttribute("sessionId", "");
		rsMap.put("rows", roleList);
		rsMap.put("total", 30);
		return rsMap;

	}

	@RequestMapping(value = "/addSysRole.do", method = RequestMethod.POST)
	@ResponseBody
	public Object addSysRole(SysRole role) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		sysRoleService.insertSelective(role);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateSysRole.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSysRole(SysRole role) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		sysRoleService.updateByPrimaryKeySelective(role);

		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateSysRoleStatus.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSysRoleStatus(@RequestParam String id,
			@RequestParam Integer status) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		SysRole role = new SysRole();
		role.setId(id);
		role.setDelFlag(status);
		sysRoleService.updateByPrimaryKeySelective(role);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

}
