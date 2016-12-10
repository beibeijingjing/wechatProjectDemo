package weixin.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import weixin.manager.bean.WxUserLabel;
import weixin.manager.mapper.WxUserLabelMapper;
import weixin.manager.util.GsonUtil;
import weixin.manager.wxentity.ResponBaseEntity;
import weixin.manager.wxentity.WxTag;
import weixin.server.utils.HttpRequest;
import weixin.server.utils.ResourceUtils;
import weixin.server.utils.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import core.exception.WxBaseException;
import core.mapper.IBaseMapper;
import core.service.BaseService;
import core.utils.UUIDUtils;
import core.utils.WxResultHandleUtil;

@Repository
public class WxUserLabelServiceImpl extends BaseService<WxUserLabel> implements
		WxUserLabelService {
	@Autowired
	private WxUserLabelMapper wxUserLabelMapper;

	@Override
	public IBaseMapper<WxUserLabel> getBaseMapper() {
		return wxUserLabelMapper;
	}

	@Override
	public void batchDeleteUserLabel(String ids) throws WxBaseException {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArr = ids.split("@");
			Map<String, Object> data = null;
			Map<String, String> map = null;
			String jsonStr = "";
			if (idArr != null) {
				for (String id : idArr) {
					// 执行删除功能
					data = new HashMap<String, Object>();
					map = new HashMap<String, String>();
					map.put("id", id);
					data.put("tag", map);
					jsonStr = GsonUtil.GsonString(data);
					String result = HttpRequest.postJson(
							ResourceUtils.getResource("wx_user_delete_tag"),
							jsonStr);
					// 结果异常处理 有异常抛异常 没异常走下面流程
					WxResultHandleUtil.getWxResponResult(result,
							ResponBaseEntity.class);

					wxUserLabelMapper.deleteByPrimaryKey(id);
				}
			}
		}

	}

	@Override
	public void addSynUserLabel(WxUserLabel label) throws WxBaseException {
		if (label != null) {
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", label.getLabelName());
			data.put("tag", map);
			Gson gson = new Gson();
			String jsonStr = gson.toJson(data);

			String result = HttpRequest.postJson(
					ResourceUtils.getResource("wx_user_create_tag"), jsonStr);

			// 结果异常处理 有异常抛异常 没异常走下面流程
			WxResultHandleUtil
					.getWxResponResult(result, ResponBaseEntity.class);

			// 将结果入库
			Map<String, WxTag> model = new HashMap<String, WxTag>();
			model = gson.fromJson(result, new TypeToken<Map<String, WxTag>>() {
			}.getType());

			if (model != null) {
				WxTag tag = model.get("tag");
				System.out
						.println("---------" + tag.getName() + "------------");
				if (tag != null) {
					label.setLabelId(tag.getId());
					label.setIsSyn(1);
					wxUserLabelMapper.insertSelective(label);
				}

			}

		}

	}

	@Override
	public void updateSynUserLabel(WxUserLabel label) throws WxBaseException {

		if (label != null) {
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", label.getLabelName());
			map.put("id", label.getLabelId());
			data.put("tag", map);
			String jsonStr = GsonUtil.GsonString(data);
			if (StringUtils.isNotEmpty(jsonStr)) {
				String result = HttpRequest.postJson(
						ResourceUtils.getResource("wx_user_update_tag"),
						jsonStr);
				// 结果异常处理 有异常抛异常 没异常走下面流程
				WxResultHandleUtil.getWxResponResult(result,
						ResponBaseEntity.class);
				// 解析结果
				ResponBaseEntity responResult = GsonUtil.GsonToBean(result,
						ResponBaseEntity.class);
				if (responResult != null && responResult.getErrcode() == 0) {
					wxUserLabelMapper.updateByPrimaryKeySelective(label);
				}
			}
		}

	}

	@Override
	@Transactional
	public void batchSynUserLabel() throws WxBaseException {
		List<WxUserLabel> labelList = new ArrayList<WxUserLabel>();
		WxUserLabel userLabel = null;
		String result = HttpRequest.postJson(
				ResourceUtils.getResource("wx_user_get_tag"), "");

		// 结果异常处理 有异常抛异常 没异常走下面流程
		WxResultHandleUtil.getWxResponResult(result, ResponBaseEntity.class);

		if (StringUtils.isNotEmpty(result)) {
			Map<String, List<WxTag>> model = new HashMap<String, List<WxTag>>();
			model = new Gson().fromJson(result,
					new TypeToken<Map<String, List<WxTag>>>() {
					}.getType());
			// 获取到数据后 插入未更新的数据
			if (model != null) {
				List<WxTag> tagList = model.get("tags");
				if (tagList != null && tagList.size() > 0) {
					// 先删除 后批量插入
					Map<String, String> condition = new HashMap<String, String>();
					wxUserLabelMapper.batchDeleteUserLabel(condition);
					for (WxTag tag : tagList) {
						userLabel = new WxUserLabel();
						userLabel.setId(UUIDUtils.generate());
						userLabel.setLabelId(tag.getId() + "");
						userLabel.setLabelName(tag.getName());
						userLabel.setUserCount(tag.getCount());
						userLabel.setIsSyn(1);
						userLabel.setDelFlag(0);
						labelList.add(userLabel);
					}
				}
			}

		}

		wxUserLabelMapper.insertSelectiveBatch(labelList);

	}

}
