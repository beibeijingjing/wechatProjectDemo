package weixin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.WxUser;
import weixin.manager.mapper.WxUserMapper;
import core.mapper.IBaseMapper;
import core.service.BaseService;

@Repository
public class WxUserServiceImpl extends BaseService<WxUser> implements
		WxUserService {
	@Autowired
	private WxUserMapper wxUserMapper;

	@Override
	public IBaseMapper<WxUser> getBaseMapper() {
		return wxUserMapper;
	}

}
