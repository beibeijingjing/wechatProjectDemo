package weixin.manager.mapper;

import java.util.Map;

import weixin.manager.bean.WxUserLabel;
import core.mapper.IBaseMapper;

public interface WxUserLabelMapper extends IBaseMapper<WxUserLabel> {

	public void batchDeleteUserLabel(Map<String, String> condition);
}