package weixin.manager.mapper;

import java.util.Map;

import weixin.manager.bean.SysRoleResourcesRef;
import core.mapper.IBaseMapper;

public interface SysRoleResourcesRefMapper extends
		IBaseMapper<SysRoleResourcesRef> {

	public void deleteRoleResourcesRefByMap(Map<String, String> condition);

}