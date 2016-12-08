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

import core.mapper.IBaseMapper;
import core.service.BaseService;
import core.utils.UUIDUtils;

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
	@Transactional
	public void batchDeleteUserLabel(String ids) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArr = ids.split("@");
			Map<String, Object> data = null;
			Map<String, String> map = null;
			String jsonStr = "";
			if (idArr != null) {
				for (String id : idArr) {
					data = new HashMap<String, Object>();
					map = new HashMap<String, String>();
					map.put("id", id);
					data.put("tag", map);
					jsonStr = GsonUtil.GsonString(data);
					String result = HttpRequest.postJson(
							ResourceUtils.getResource("wx_user_delete_tag"),
							jsonStr);
					// 解析结果
					ResponBaseEntity responResult = GsonUtil.GsonToBean(result,
							ResponBaseEntity.class);
					if (responResult != null && responResult.getErrcode() == 0) {
						wxUserLabelMapper.deleteByPrimaryKey(id);
					}

				}
			}
		}

	}

	@Override
	public void addSynUserLabel(WxUserLabel label) {
		if (label != null) {
			Map<String, Object> data = new HashMap<String, Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", label.getLabelName());
			data.put("tag", map);
			Gson gson = new Gson();
			String jsonStr = gson.toJson(data);

			String result = HttpRequest.postJson(
					ResourceUtils.getResource("wx_user_create_tag"), jsonStr);

			// 将结果入库
			Map<String, WxTag> model = new HashMap<String, WxTag>();
			model = gson.fromJson(result,
					new TypeToken<Map<String, Map<String, WxTag>>>() {
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
	public void updateSynUserLabel(WxUserLabel label) {

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
	public void batchSynUserLabel() {
		List<WxUserLabel> labelList = new ArrayList<WxUserLabel>();
		WxUserLabel label = new WxUserLabel();
		label.setId("id_4");
		label.setLabelId("4444");
		label.setLabelName("name4444");
		labelList.add(label);

		WxUserLabel label1 = new WxUserLabel();
		label1.setId("id_5");
		label1.setLabelId("5555");
		label1.setLabelName("name555");
		labelList.add(label1);

		wxUserLabelMapper.insertSelectiveBatch(labelList);

	}

}
