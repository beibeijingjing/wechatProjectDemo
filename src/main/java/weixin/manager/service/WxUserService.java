package weixin.manager.service;

import weixin.manager.bean.WxUser;
import core.service.IBaseService;

public interface WxUserService extends IBaseService<WxUser> {

	public void synchronizeWxServerUser();

	public void batchPullBlackUser(String opendIds);

	public void batchAddUserLabel(String labelIds, String opendIds);

}
