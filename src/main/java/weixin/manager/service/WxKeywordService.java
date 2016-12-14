package weixin.manager.service;

import weixin.manager.bean.WxKeyword;
import core.service.IBaseService;

public interface WxKeywordService extends IBaseService<WxKeyword> {

	public void batchDeleteKeyword(String ids);

	public String getWxKeywordByKey(String key);

}
