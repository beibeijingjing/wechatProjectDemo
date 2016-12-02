package weixin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import weixin.manager.bean.WxUserLabel;
import weixin.manager.mapper.WxUserLabelMapper;
import weixin.server.utils.StringUtils;
import core.mapper.IBaseMapper;
import core.service.BaseService;

@Repository
public class WxUserLabelServiceImpl extends BaseService<WxUserLabel> implements
		WxUserLabelService {
	@Autowired
	private WxUserLabelMapper wxUserLabelMapper;

	@Override
	public IBaseMapper<WxUserLabel> getBaseMapper() {
		return wxUserLabelMapper;
	}

	@Override
	@Transactional
	public void batchDeleteUserLabel(String ids) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArr = ids.split("@");
			if (idArr != null) {
				for (String id : idArr) {
					wxUserLabelMapper.deleteByPrimaryKey(id);
				}
			}
		}

	}

}
