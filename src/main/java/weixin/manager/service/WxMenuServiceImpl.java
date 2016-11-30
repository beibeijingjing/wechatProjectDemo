package weixin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.WxMenu;
import weixin.manager.mapper.WxMenuNewMapper;
import core.mapper.IBaseMapper;
import core.service.BaseService;

@Repository
public class WxMenuServiceImpl extends BaseService<WxMenu> implements
		WxMenuService {
	@Autowired
	private WxMenuNewMapper wxMenuMapper;

	@Override
	public IBaseMapper<WxMenu> getBaseMapper() {
		return wxMenuMapper;
	}

}
