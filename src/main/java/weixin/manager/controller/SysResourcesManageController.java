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

import weixin.manager.bean.SysResources;
import weixin.manager.service.SysResourcesService;
import weixin.manager.vo.SysRoleResourcesVo;
import core.controller.BaseController;

@Controller
@RequestMapping(value = "/pc")
public class SysResourcesManageController extends BaseController {

	@Resource
	private SysResourcesService sysResourcesService;

	@RequestMapping(value = "/toGetSysResourcesList.do")
	public Object toGetSysResourcesList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		return "weixin/sysResources/sysResourcesList";
	}

	@RequestMapping(value = "/toGetAddSysResources.do")
	public Object toGetAddSysResources(HttpServletRequest request,
			@RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);

		return "weixin/sysResources/addSysResources";
	}

	@RequestMapping(value = "/toGetUpdateSysResources.do")
	public Object toGetUpdateSysResources(HttpServletRequest request,
			String id, @RequestParam(required = false) String sessionId) {
		request.setAttribute("sessionId", sessionId);
		SysResources resources = sysResourcesService.selectByPrimaryKey(id);
		if (resources != null) {
			request.setAttribute("resources", resources);
		}

		return "weixin/sysResources/updateSysResources";
	}

	@RequestMapping(value = "/toGetBindingResourcesList.do")
	public Object toGetBindingResourcesList(HttpServletRequest request,
			@RequestParam(required = false) String sessionId, String id) {
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("roleId", id);
		return "weixin/sysResources/bindingResourcesList";
	}

	@RequestMapping(value = "/getSysResourcesList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getSysResourcesList(HttpServletRequest request,
			Integer limit, Integer offset, Integer status)
			throws IllegalStateException, IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		List<SysResources> resourcesList = sysResourcesService
				.selectByMap(condition);

		Map<String, Object> rsMap = new HashMap<String, Object>();

		request.setAttribute("sessionId", "");
		rsMap.put("rows", resourcesList);
		rsMap.put("total", 30);
		return rsMap;

	}

	@RequestMapping(value = "/getSysResourcesVoList.do", method = RequestMethod.GET)
	@ResponseBody
	public Object getSysResourcesVoList(HttpServletRequest request,
			Integer limit, Integer offset, Integer status, String roleId)
			throws IllegalStateException, IOException {

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", status);
		condition.put("limit", limit);
		condition.put("offset", offset);
		List<SysRoleResourcesVo> resourcesList = sysResourcesService
				.getRoleResourcesVoList(roleId);

		Map<String, Object> rsMap = new HashMap<String, Object>();

		request.setAttribute("sessionId", "");
		rsMap.put("rows", resourcesList);
		rsMap.put("total", 30);
		return rsMap;

	}

	@RequestMapping(value = "/addSysResources.do", method = RequestMethod.POST)
	@ResponseBody
	public Object addSysResources(SysResources resources) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		sysResourcesService.insertSelective(resources);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateSysResources.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSysResources(SysResources resources) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		sysResourcesService.updateByPrimaryKeySelective(resources);

		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/updateSysResourcesStatus.do", method = RequestMethod.POST)
	@ResponseBody
	public Object updateSysResourcesStatus(@RequestParam String id,
			@RequestParam Integer status) {
		Map<String, Object> rsMap = new HashMap<String, Object>();

		SysResources resources = new SysResources();
		resources.setId(id);
		resources.setDelFlag(status);
		sysResourcesService.updateByPrimaryKeySelective(resources);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

	@RequestMapping(value = "/bindingRoleResources.do", method = RequestMethod.POST)
	@ResponseBody
	public Object bindingRoleResources(String roleId, String resourceIds) {
		Map<String, Object> rsMap = new HashMap<String, Object>();
		sysResourcesService.addRoleResourcesRef(roleId, resourceIds);
		rsMap.put("rtnCode", 1); // 1：成功 0：失败
		rsMap.put("rtnMsg", "操作成功.");
		return rsMap;
	}

}
