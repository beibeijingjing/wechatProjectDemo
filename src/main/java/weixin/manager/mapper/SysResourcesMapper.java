package weixin.manager.mapper;

import java.util.List;

import weixin.manager.bean.SysResources;
import weixin.manager.vo.SysRoleResourcesVo;
import core.mapper.IBaseMapper;

public interface SysResourcesMapper extends IBaseMapper<SysResources> {
	public List<SysRoleResourcesVo> getAllResRole();

	public List<SysRoleResourcesVo> getRoleResourcesVoList(String roleId);
}