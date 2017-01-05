package weixin.manager.mapper;

import java.util.Map;

import weixin.manager.bean.SysUserRoleRef;
import core.mapper.IBaseMapper;

public interface SysUserRoleRefMapper extends IBaseMapper<SysUserRoleRef> {
	public void deleteUserRoleRefByMap(Map<String, String> condition);
}