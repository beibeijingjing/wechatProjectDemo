package weixin.manager.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.WxKeyword;
import weixin.manager.mapper.WxKeywordMapper;
import core.mapper.IBaseMapper;
import core.service.BaseService;
import core.utils.StringUtils;

@Repository
public class WxKeywordServiceImpl extends BaseService<WxKeyword> implements
		WxKeywordService {
	@Autowired
	private WxKeywordMapper wxKeywordMapper;

	@Override
	public IBaseMapper<WxKeyword> getBaseMapper() {
		return wxKeywordMapper;
	}

	@Override
	public void batchDeleteKeyword(String ids) {
		if (StringUtils.isNotEmpty(ids)) {
			String[] idArr = ids.split("@");
			if (idArr != null) {
				for (String id : idArr) {
					wxKeywordMapper.deleteByPrimaryKey(id);
				}
			}
		}

	}

	@Override
	public String getWxKeywordByKey(String key) {
		String wxMessage = "";
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("wx_key", key);
		List<WxKeyword> keywordList = wxKeywordMapper.selectByMap(condition);
		if (keywordList != null && keywordList.size() > 0) {
			wxMessage = keywordList.get(0).getWxMessage();
		}
		return wxMessage;
	}

}
