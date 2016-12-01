package weixin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.WxUserLabel;
import weixin.manager.mapper.WxUserLabelMapper;
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

}
