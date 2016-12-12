package weixin.manager.mapper;

import java.util.Map;

import weixin.manager.bean.WxUser;
import core.mapper.IBaseMapper;

public interface WxUserMapper extends IBaseMapper<WxUser> {
	public void batchDeleteUser(Map<String, String> condition);
}