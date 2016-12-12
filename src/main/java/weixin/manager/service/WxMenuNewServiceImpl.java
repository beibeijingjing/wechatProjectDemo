package weixin.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import weixin.manager.bean.WxMenu;
import weixin.manager.mapper.WxMenuNewMapper;
import weixin.manager.wxentity.ResponBaseEntity;
import weixin.server.config.WxConfig;
import core.exception.WxBaseException;
import core.mapper.IBaseMapper;
import core.service.BaseService;
import core.utils.GsonUtil;
import core.utils.HttpRequest;
import core.utils.ResourceUtils;
import core.utils.StringUtils;
import core.utils.WxResultHandleUtil;

@Repository(value = "wxMenuServiceNew")
public class WxMenuNewServiceImpl extends BaseService<WxMenu> implements
		WxMenuServiceNew {
	@Autowired
	private WxMenuNewMapper wxMenuNewMapper;

	@Override
	public IBaseMapper<WxMenu> getBaseMapper() {
		return wxMenuNewMapper;
	}

	@Override
	@Transactional
	public void batchDeleteWxMenuById(String ids) throws WxBaseException {
		// 1.先删除线上
		String param = "access_token" + WxConfig.accessToken;
		String result = HttpRequest.sendGet(
				ResourceUtils.getResource("wx_menu_delete_url"), param);

		// 结果异常处理 有异常抛异常 没异常走下面流程
		WxResultHandleUtil.getWxResponResult(result, ResponBaseEntity.class);
		// 2.删除本地
		List<String> idList = new ArrayList<String>();
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArr = ids.split("@");
			if (idArr != null) {
				for (String id : idArr) {
					idList.add(id);
				}
			}
		}
		// 执行删除
		wxMenuNewMapper.batchDeleteMenuById(idList);
		wxMenuNewMapper.batchDeleteMenuByParentId(idList);
		// 同步菜单
		synLocalMenuToWxServer();

	}

	private void synLocalMenuToWxServer() throws WxBaseException {
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("del_flag", "0");
		List<WxMenu> menuList = wxMenuNewMapper.selectByMap(condition);
		Map<String, Object> data = new HashMap<String, Object>();
		List<Map<String, Object>> button = new ArrayList<Map<String, Object>>();
		List<Map<String, String>> subButton = null;
		Map<String, String> subMap = null;
		Map<String, Object> map = null;
		// 拼接json
		if (menuList != null && menuList.size() > 0) {
			for (WxMenu menu : menuList) {
				if ("0".equals(menu.getParentId())) {
					button = new ArrayList<Map<String, Object>>();
					if (menu.getIsExtend() == 0) {
						// 只有一级的菜单
						map = new HashMap<String, Object>();
						map.put("key", menu.getWxMenuKey());
						map.put("name", menu.getWxMenuName());
						map.put("type", menu.getWxMenuKey());
						map.put("url", menu.getWxMenuUrl());
						map.put("media_id", menu.getWxMediaId());
					}
					if (menu.getIsExtend() == 1) {
						// 有二级的菜单
						subButton = new ArrayList<Map<String, String>>();
						map.put("name", menu.getWxMenuName());
						for (WxMenu childMenu : menuList) {
							if (menu.getId().equals(childMenu.getParentId())) {
								subMap = new HashMap<String, String>();
								subMap.put("key", menu.getWxMenuKey());
								subMap.put("name", menu.getWxMenuName());
								subMap.put("type", menu.getWxMenuKey());
								subMap.put("url", menu.getWxMenuUrl());
								subMap.put("media_id", menu.getWxMediaId());
								subButton.add(subMap);
							}
						}
						map.put("sub_button", subButton);
					}
					button.add(map);
				}
			}
		}
		data.put("button", button);
		String jsonStr = GsonUtil.GsonString(data);
		String result = HttpRequest.postJson(
				ResourceUtils.getResource("wx_menu_create_url"), jsonStr);
		// 结果异常处理 有异常抛异常 没异常走下面流程
		WxResultHandleUtil.getWxResponResult(result, ResponBaseEntity.class);

	}

	@Override
	public void batchSynCreateWxMenuB() throws WxBaseException {
		// 同步菜单
		synLocalMenuToWxServer();

	}
}
