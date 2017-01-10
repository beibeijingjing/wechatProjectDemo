package weixin.manager.service;

import java.util.List;

import weixin.manager.bean.SysResources;
import weixin.manager.vo.SysRoleResourcesVo;
import core.service.IBaseService;

public interface SysResourcesService extends IBaseService<SysResources> {

	public void addRoleResourcesRef(String roleId, String resourceIds);

	public List<SysRoleResourcesVo> getRoleResourcesVoList(String roleId);

}
