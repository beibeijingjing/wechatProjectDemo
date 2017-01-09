package weixin.manager.service;

import java.util.List;

import weixin.manager.bean.SysRole;
import core.service.IBaseService;

public interface SysRoleService extends IBaseService<SysRole> {
	public List<SysRole> getSysRoleRefListByUserId(String userId);
}
