package weixin.server.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import weixin.server.entity.base.WxMenuEntity;
import weixin.server.mapper.WxMenuMapper;
import weixin.server.service.IWxMenuService;
import core.mapper.IBaseMapper;
import core.service.BaseService;

/**
 * 
 * @Message: created by Liujishuai on 2015年9月24日
 * 
 * @Description:
 */
@Service
public class WxMenuService extends BaseService<WxMenuEntity> implements
		IWxMenuService {

	@Resource
	private WxMenuMapper mapper;

	@Override
	public IBaseMapper<WxMenuEntity> getBaseMapper() {
		return mapper;
	}

	@Override
	public void createWxMenu(WxMenuEntity menu) {
		mapper.insert(menu);
	}

	@Override
	public void updateWxMenu(WxMenuEntity menu) {
		mapper.updateByPrimaryKey(menu);
	}

	@Override
	public List<WxMenuEntity> getWxMenuList(Map<String, String> conditions) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WxMenuEntity getWxMenuById(String id) {
		return mapper.selectByPrimaryKey(id);
	}

}
