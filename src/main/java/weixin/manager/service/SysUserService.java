package weixin.manager.service;

import weixin.manager.bean.SysUser;
import core.service.IBaseService;

public interface SysUserService extends IBaseService<SysUser> {

	public void batchBindingUserRole(String userId, String roleIds);

}
