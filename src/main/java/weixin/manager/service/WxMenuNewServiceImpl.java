package weixin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.WxMenu;
import weixin.manager.mapper.WxMenuNewMapper;
import core.mapper.IBaseMapper;
import core.service.BaseService;

@Repository(value = "wxMenuServiceNew")
public class WxMenuNewServiceImpl extends BaseService<WxMenu> implements
		WxMenuServiceNew {
	@Autowired
	private WxMenuNewMapper wxMenuNewMapper;

	@Override
	public IBaseMapper<WxMenu> getBaseMapper() {
		return wxMenuNewMapper;
	}

}
