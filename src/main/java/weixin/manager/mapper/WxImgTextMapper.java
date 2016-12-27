package weixin.manager.mapper;

import weixin.manager.bean.WxImgText;
import core.mapper.IBaseMapper;

public interface WxImgTextMapper extends IBaseMapper<WxImgText> {

	public void updateImgTextById(WxImgText imgText);
}