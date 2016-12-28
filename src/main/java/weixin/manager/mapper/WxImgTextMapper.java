package weixin.manager.mapper;

import java.util.Map;

import weixin.manager.bean.WxImgText;
import core.mapper.IBaseMapper;

public interface WxImgTextMapper extends IBaseMapper<WxImgText> {

	public void updateImgTextById(WxImgText imgText);

	public int deleteImgTextByMap(Map<String, String> map);
}