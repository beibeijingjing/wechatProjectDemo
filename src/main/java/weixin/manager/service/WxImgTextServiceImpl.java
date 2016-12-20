package weixin.manager.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.WxImgText;
import weixin.manager.mapper.WxImgTextMapper;
import core.mapper.IBaseMapper;
import core.service.BaseService;

@Repository
public class WxImgTextServiceImpl extends BaseService<WxImgText> implements
		WxImgTextService {
	@Autowired
	private WxImgTextMapper wxImgTextMapper;

	@Override
	public IBaseMapper<WxImgText> getBaseMapper() {
		return wxImgTextMapper;
	}

}
