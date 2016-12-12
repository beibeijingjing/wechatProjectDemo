package weixin.manager.service;

import weixin.manager.bean.WxUser;
import core.exception.WxBaseException;
import core.service.IBaseService;

public interface WxUserService extends IBaseService<WxUser> {

	public void synchronizeWxServerUser() throws WxBaseException;

	public void batchPullBlackUser(String opendIds);

	public void batchAddUserLabel(String labelIds, String opendIds);

}
