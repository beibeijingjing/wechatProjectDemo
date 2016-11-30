package weixin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.SysMenu;
import weixin.manager.mapper.SysMenuMapper;
import core.mapper.IBaseMapper;
import core.service.BaseService;

@Repository
public class SysMenuServiceImpl extends BaseService<SysMenu> implements
		SysMenuService {
	@Autowired
	private SysMenuMapper sysMenuMapper;

	@Override
	public IBaseMapper<SysMenu> getBaseMapper() {
		return sysMenuMapper;
	}

}
