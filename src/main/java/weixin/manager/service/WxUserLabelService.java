package weixin.manager.service;

import weixin.manager.bean.WxUserLabel;
import core.service.IBaseService;

public interface WxUserLabelService extends IBaseService<WxUserLabel> {

	public void batchDeleteUserLabel(String ids);

	public void addSynUserLabel(WxUserLabel label);

	public void updateSynUserLabel(WxUserLabel label);

	public void batchSynUserLabel();

}
