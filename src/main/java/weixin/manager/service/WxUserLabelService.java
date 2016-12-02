package weixin.manager.service;

import weixin.manager.bean.WxUserLabel;
import core.service.IBaseService;

public interface WxUserLabelService extends IBaseService<WxUserLabel> {

	public void batchDeleteUserLabel(String ids);

}
