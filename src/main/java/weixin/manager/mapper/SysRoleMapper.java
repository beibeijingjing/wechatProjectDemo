package weixin.manager.mapper;

import java.util.List;

import weixin.manager.bean.SysRole;
import core.mapper.IBaseMapper;

public interface SysRoleMapper extends IBaseMapper<SysRole> {
	public List<SysRole> getSysRoleListByUserId(String userId);
}