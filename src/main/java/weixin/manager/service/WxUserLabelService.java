package weixin.manager.service;

import weixin.manager.bean.WxUserLabel;
import core.exception.WxBaseException;
import core.service.IBaseService;

public interface WxUserLabelService extends IBaseService<WxUserLabel> {

	public void batchDeleteUserLabel(String ids) throws WxBaseException;

	public void addSynUserLabel(WxUserLabel label) throws WxBaseException;

	public void updateSynUserLabel(WxUserLabel label) throws WxBaseException;

	public void batchSynUserLabel() throws WxBaseException;

}
