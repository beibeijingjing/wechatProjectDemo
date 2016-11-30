package weixin.server.service;

import java.util.List;
import java.util.Map;

import weixin.server.entity.base.WxMenuEntity;
import core.service.IBaseService;

public interface IWxMenuService extends IBaseService<WxMenuEntity> {

	public void createWxMenu(WxMenuEntity menu);

	public void updateWxMenu(WxMenuEntity menu);

	public List<WxMenuEntity> getWxMenuList(Map<String, String> conditions);

	public WxMenuEntity getWxMenuById(String id);

}
