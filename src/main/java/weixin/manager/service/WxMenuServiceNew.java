package weixin.manager.service;

import weixin.manager.bean.WxMenu;
import core.exception.WxBaseException;
import core.service.IBaseService;

public interface WxMenuServiceNew extends IBaseService<WxMenu> {

	public void batchDeleteWxMenuById(String ids) throws WxBaseException;

	public void batchSynCreateWxMenuB() throws WxBaseException;

}
