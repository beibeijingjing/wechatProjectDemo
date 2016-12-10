package weixin.manager.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import weixin.manager.bean.WxUser;
import weixin.manager.mapper.WxUserMapper;
import weixin.manager.wxentity.WxUserEntity;
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

	@Override
	@Transactional
	public void synchronizeWxServerUser() {
		List<WxUserEntity> userList = new ArrayList<WxUserEntity>();
		// 1.获取用户openidList

		// 1.获取微信服务器userList

		// 2.获取关注的用户信息

		// 3.删除所有用户

		// 4.服务器用户入库
	}

	@Override
	public void batchPullBlackUser(String opendIds) {

	}

	@Override
	public void batchAddUserLabel(String labelIds, String opendIds) {

	}

}
