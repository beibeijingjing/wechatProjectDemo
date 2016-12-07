package weixin.manager.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import weixin.manager.bean.WxUserLabel;
import weixin.manager.mapper.WxUserLabelMapper;
import weixin.manager.wxentity.WxTag;
import weixin.server.utils.HttpRequest;
import weixin.server.utils.ResourceUtils;
import weixin.server.utils.StringUtils;

import com.google.gson.Gson;

import core.mapper.IBaseMapper;
import core.service.BaseService;

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
			if (idArr != null) {
				for (String id : idArr) {
					wxUserLabelMapper.deleteByPrimaryKey(id);
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
			/*
			 * String result = HttpRequest.postJson(
			 * ResourceUtils.getResource("wx_user_create_tag"), jsonStr);
			 */
			// 将结果入库
			Map<String, WxTag> model = new HashMap<String, WxTag>();
			model = gson.fromJson(jsonStr, model.getClass());
			if (model != null) {
				System.out.println(model.get("tag"));
				WxTag tag = model.get("tag");
				System.out.println(tag.getName());
			}

			// wxUserLabelMapper.insertSelective(label);
		}

	}

	@Override
	public void updateSynUserLabel(WxUserLabel label) {
		wxUserLabelMapper.updateByPrimaryKeySelective(label);
	}

}
