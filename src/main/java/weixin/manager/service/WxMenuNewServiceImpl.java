package weixin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import weixin.manager.bean.WxMenu;
import weixin.manager.mapper.WxMenuNewMapper;
import core.mapper.IBaseMapper;
import core.service.BaseService;
import core.utils.StringUtils;

@Repository(value = "wxMenuServiceNew")
public class WxMenuNewServiceImpl extends BaseService<WxMenu> implements
		WxMenuServiceNew {
	@Autowired
	private WxMenuNewMapper wxMenuNewMapper;

	@Override
	public IBaseMapper<WxMenu> getBaseMapper() {
		return wxMenuNewMapper;
	}

	@Override
	@Transactional
	public void batchDeleteWxMenuById(String ids) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArr = ids.split("@");
			if (idArr != null) {
				for (String id : idArr) {
					wxMenuNewMapper.deleteByPrimaryKey(id);
				}
			}
		}

	}

}
