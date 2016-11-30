package weixin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.SysUser;
import weixin.manager.mapper.SysUserMapper;
import core.mapper.IBaseMapper;
import core.service.BaseService;

@Repository
public class SysUserServiceImpl extends BaseService<SysUser> implements
		SysUserService {
	@Autowired
	private SysUserMapper sysUserMapper;

	@Override
	public IBaseMapper<SysUser> getBaseMapper() {
		return sysUserMapper;
	}

}
