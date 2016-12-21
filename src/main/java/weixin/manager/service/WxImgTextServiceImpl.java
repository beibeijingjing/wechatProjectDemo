package weixin.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.WxImgText;
import weixin.manager.mapper.WxImgTextMapper;
import weixin.manager.wxentity.ResponBaseEntity;
import weixin.manager.wxentity.WxTag;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import core.exception.WxBaseException;
import core.mapper.IBaseMapper;
import core.service.BaseService;
import core.utils.AjaxDecode;
import core.utils.HttpRequest;
import core.utils.ResourceUtils;
import core.utils.StringUtil;
import core.utils.WxResultHandleUtil;

@Repository
public class WxImgTextServiceImpl extends BaseService<WxImgText> implements
		WxImgTextService {
	@Autowired
	private WxImgTextMapper wxImgTextMapper;

	@Override
	public IBaseMapper<WxImgText> getBaseMapper() {
		return wxImgTextMapper;
	}

	@Override
	public void addSynImgTextOne(WxImgText imgText) throws WxBaseException {
		if (imgText != null) {
			// 同步微信服务器
			Map<String, List<Map<String, String>>> data = new HashMap<String, List<Map<String, String>>>();
			List<Map<String, String>> newsList = new ArrayList<Map<String, String>>();
			Map<String, String> news = new HashMap<String, String>();
			news.put("title", imgText.getTitle());
			news.put("thumb_media_id", imgText.getThumb_media_id());
			news.put("author", imgText.getAuthor());
			news.put("digest", imgText.getDigest());
			news.put("show_cover_pic", "1");
			news.put("content", AjaxDecode.ajax_decode(imgText.getContent()));
			news.put("content_source_url", imgText.getContent_source_url());
			newsList.add(news);
			data.put("articles", newsList);

			Gson gson = new Gson();
			String jsonStr = gson.toJson(data);

			String result = HttpRequest.postJson(
					ResourceUtils.getResource("wx_materia_add_news_url"),
					jsonStr);

			// 结果异常处理 有异常抛异常 没异常走下面流程
			WxResultHandleUtil
					.getWxResponResult(result, ResponBaseEntity.class);
			// 将结果入库
			Map<String, String> model = new HashMap<String, String>();
			model = gson.fromJson(result, new TypeToken<Map<String, String>>() {
			}.getType());
			if (model != null) {
				imgText.setArticle_id(model.get("media_id"));
			}
			// 入库操作
			imgText.setContent(AjaxDecode.ajax_decode(imgText.getContent()));
			imgText.setType(0);
			wxImgTextMapper.insertSelective(imgText);
		}

	}

	@Override
	public void updateSynImgTextOne(WxImgText imgText) throws WxBaseException {
		if (imgText != null) {
			// 同步微信服务器
			Map<String, Object> data = new HashMap<String, Object>();
			List<Map<String, String>> newsList = new ArrayList<Map<String, String>>();
			Map<String, String> news = new HashMap<String, String>();
			news.put("title", imgText.getTitle());
			news.put("thumb_media_id", imgText.getThumb_media_id());
			news.put("author", imgText.getAuthor());
			news.put("digest", imgText.getDigest());
			news.put("show_cover_pic", "1");
			news.put("content", AjaxDecode.ajax_decode(imgText.getContent()));
			news.put("content_source_url", imgText.getContent_source_url());
			newsList.add(news);
			data.put("articles", newsList);
			data.put("media_id", imgText.getArticle_id());
			data.put("index", "0");

			Gson gson = new Gson();
			String jsonStr = gson.toJson(data);

			String result = HttpRequest.postJson(
					ResourceUtils.getResource("wx_materia_update_news_url"),
					jsonStr);

			// 结果异常处理 有异常抛异常 没异常走下面流程
			WxResultHandleUtil
					.getWxResponResult(result, ResponBaseEntity.class);

			// 入库操作
			imgText.setContent(AjaxDecode.ajax_decode(imgText.getContent()));
			imgText.setType(0);
			wxImgTextMapper.updateByPrimaryKeySelective(imgText);
		}
	}

	@Override
	public void deleteSynImgText(String mediaId) throws WxBaseException {
		if (StringUtils.isNotEmpty(mediaId)) {
			Map<String, String> data = new HashMap<String, String>();
			data.put("media_id", mediaId);
			Gson gson = new Gson();
			String jsonStr = gson.toJson(data);

			String result = HttpRequest.postJson(
					ResourceUtils.getResource("wx_materia_delete_news_url"),
					jsonStr);

			// 结果异常处理 有异常抛异常 没异常走下面流程
			WxResultHandleUtil
					.getWxResponResult(result, ResponBaseEntity.class);
			wxImgTextMapper.deleteByPrimaryKey(mediaId);
		}

	}

}