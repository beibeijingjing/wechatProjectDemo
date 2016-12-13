package weixin.manager.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import weixin.manager.bean.WxUser;
import weixin.manager.mapper.WxUserMapper;
import weixin.manager.wxentity.ResponBaseEntity;
import weixin.manager.wxentity.WxUserEntity;
import weixin.manager.wxentity.WxUserOpenIdList;
import weixin.server.config.WxConfig;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import core.exception.WxBaseException;
import core.mapper.IBaseMapper;
import core.service.BaseService;
import core.utils.GsonUtil;
import core.utils.HttpRequest;
import core.utils.ResourceUtils;
import core.utils.UUIDUtils;
import core.utils.WxResultHandleUtil;

@Repository
public class WxUserServiceImpl extends BaseService<WxUser> implements
		WxUserService {
	@Autowired
	private WxUserMapper wxUserMapper;

	@Override
	public IBaseMapper<WxUser> getBaseMapper() {
		return wxUserMapper;
	}

	@Override
	@Transactional
	public void synchronizeWxServerUser() throws WxBaseException {
		List<WxUserEntity> userList = new ArrayList<WxUserEntity>();
		List<String> openIdList = new ArrayList<String>();
		// 1.获取用户openidList
		getOpenIdList(openIdList, null);
		// 1.获取微信服务器userList
		getWxServerUserInfoList(userList, openIdList);
		// 2.获取关注的用户信息
		List<WxUserEntity> subscribeUserList = getSubscribeServerUserList(userList);
		// 3.删除所有用户
		Map<String, String> condition = new HashMap<String, String>();
		wxUserMapper.batchDeleteUser(condition);
		// 4.复制用户信息到bean 服务器用户入库
		List<WxUser> wxUserList = getWxUserInfoList(subscribeUserList);
		wxUserMapper.insertSelectiveBatch(wxUserList);

	}

	/*
	 * 获取所有openIdList
	 */
	private List<String> getOpenIdList(List<String> openIdList,
			String nextOpenid) throws WxBaseException {
		String param = "access_token=" + WxConfig.accessToken;
		if (nextOpenid != null) {
			param = "access_token=" + WxConfig.accessToken + "&next_openid="
					+ nextOpenid;
		}
		String result = HttpRequest.sendGet(
				ResourceUtils.getResource("wx_user_openid_list_get"), param);

		// 结果异常处理 有异常抛异常 没异常走下面流程
		WxResultHandleUtil.getWxResponResult(result, ResponBaseEntity.class);

		// 将结果入库
		WxUserOpenIdList openIdListEntity = GsonUtil.GsonToBean(result,
				WxUserOpenIdList.class);
		if (openIdListEntity != null && openIdListEntity.getData() != null
				&& openIdListEntity.getData().get("openid") != null
				&& openIdListEntity.getData().get("openid").size() > 0) {
			openIdList.addAll(openIdListEntity.getData().get("openid"));
			// 递归查询openId
			getOpenIdList(openIdList, openIdListEntity.getNext_openid());
		}

		return openIdList;
	}

	private List<WxUserEntity> getWxServerUserInfoList(
			List<WxUserEntity> userList, List<String> openIdList)
			throws WxBaseException {
		Gson gson = new Gson();
		Map<String, List<Map<String, String>>> model = null;
		List<Map<String, String>> openidListModel = null;
		Map<String, String> map = null;
		String result = "";
		for (int i = 0; i < openIdList.size(); i = i + 100) {
			// 拼接查询用户列表 请求信息
			openidListModel = new ArrayList<Map<String, String>>();
			model = new HashMap<String, List<Map<String, String>>>();
			for (int j = i; j < j + 100; j++) {
				if (j < openIdList.size()) {
					map = new HashMap<String, String>();
					map.put("openid", openIdList.get(j) + "");
					map.put("lang", "zh-CN");
					openidListModel.add(map);
				} else {
					break;
				}

			}
			model.put("user_list", openidListModel);
			// 批量获取用户信息
			result = HttpRequest.postJson(
					ResourceUtils.getResource("wx_user_info_batch_get"),
					GsonUtil.GsonString(model));

			// 结果异常处理 有异常抛异常 没异常走下面流程
			WxResultHandleUtil
					.getWxResponResult(result, ResponBaseEntity.class);
			// 将结果入库
			Map<String, List<WxUserEntity>> resultModel = new HashMap<String, List<WxUserEntity>>();
			resultModel = gson.fromJson(result,
					new TypeToken<Map<String, List<WxUserEntity>>>() {
					}.getType());
			if (resultModel.get("user_info_list") != null) {
				userList.addAll(resultModel.get("user_info_list"));
			}

		}

		return userList;
	}

	private List<WxUserEntity> getSubscribeServerUserList(
			List<WxUserEntity> userList) {
		List<WxUserEntity> subscribeUserList = new ArrayList<WxUserEntity>();
		if (userList != null && userList.size() > 0) {
			for (WxUserEntity wxUser : userList) {
				if (wxUser.getSubscribe() == 1) {
					subscribeUserList.add(wxUser);
				}
			}
		}
		return subscribeUserList;
	}

	private List<WxUser> getWxUserInfoList(List<WxUserEntity> userList) {
		List<WxUser> wxUserList = new ArrayList<WxUser>();
		WxUser wxUser = null;
		for (WxUserEntity userEntity : userList) {
			wxUser = new WxUser();
			wxUser.setId(UUIDUtils.generate());
			wxUser.setOpenid(userEntity.getOpenid());
			wxUser.setNickname(userEntity.getNickname());
			wxUser.setSex(userEntity.getSex());
			wxUser.setCity(userEntity.getCity());
			wxUser.setCountry(userEntity.getCountry());
			wxUser.setProvince(userEntity.getProvince());
			wxUser.setLanguage(userEntity.getLanguage());
			wxUser.setHeadimgurl(userEntity.getHeadimgurl());
			wxUser.setSubscribeTime(userEntity.getSubscribe_time());
			wxUser.setUnionid(userEntity.getUnionid());
			wxUser.setRemark(userEntity.getRemark());
			wxUser.setGroupid(userEntity.getGroupid());
			wxUser.setTagidList(userEntity.getTagid_list().toString());
			wxUserList.add(wxUser);
		}

		return wxUserList;
	}

	@Override
	public void batchPullBlackUser(String opendIds) {

	}

	@Override
	public void batchAddUserLabel(String labelIds, String opendIds) {

	}

}
