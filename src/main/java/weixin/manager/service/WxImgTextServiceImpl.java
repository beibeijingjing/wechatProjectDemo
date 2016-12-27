package weixin.manager.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import weixin.manager.bean.WxImgText;
import weixin.manager.mapper.WxImgTextMapper;
import weixin.manager.wxentity.ResponBaseEntity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import core.exception.WxBaseException;
import core.mapper.IBaseMapper;
import core.service.BaseService;
import core.utils.AjaxDecode;
import core.utils.HttpRequest;
import core.utils.ResourceUtils;
import core.utils.WxResultHandleUtil;

@Repository
public class WxImgTextServiceImpl extends BaseService<WxImgText> implements
		WxImgTextService {
	private static final String priUrl = "http://read.html5.qq.com/image?src=forum&amp;q=5&amp;r=0&amp;imgflag=7&amp;imageUrl=";

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
			String contentStr = AjaxDecode.ajax_decode(imgText.getContent())
					.replace(priUrl, "");
			news.put("content", contentStr);
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
			Map<String, String> news = new HashMap<String, String>();
			news.put("title", imgText.getTitle());
			news.put("thumb_media_id", imgText.getThumb_media_id());
			news.put("author", imgText.getAuthor());
			news.put("digest", imgText.getDigest());
			news.put("show_cover_pic", "1");
			String contentStr = AjaxDecode.ajax_decode(imgText.getContent())
					.replace(priUrl, "");
			news.put("content", contentStr);
			news.put("content_source_url", imgText.getContent_source_url());
			data.put("articles", news);
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

	@Override
	public String addImgTextMore(WxImgText imgText) {
		String imgTextId = "";
		if (imgText != null) {
			imgText.setType(1);
			imgText.setContent(AjaxDecode.ajax_decode(imgText.getContent()));
			if (StringUtils.isNotEmpty(imgText.getId())) {
				wxImgTextMapper.updateImgTextById(imgText);
			} else {
				wxImgTextMapper.insertSelective(imgText);
			}
			imgTextId = imgText.getId();
		}
		return imgTextId;

	}

	@Override
	public void updateImgTextMore(WxImgText imgText) {
		if (imgText != null) {
			imgText.setContent(AjaxDecode.ajax_decode(imgText.getContent()));
			wxImgTextMapper.updateImgTextById(imgText);
		}
	}

	public static void main(String[] args) {
		String str = "<p>我的第一条单图文马上就要成功了（修改）</p>"
				+ "<p>好开心哈哈</p>"
				+ "<p><img alt=\"\" src=\"http://read.html5.qq.com/image?src=forum&amp;q=5&amp;r=0&amp;imgflag=7&amp;imageUrl=http://mmbiz.qpic.cn/mmbiz_png/lTrSibc57n2ZaGKNdKNYoPF0UOc92rlBiaVC2WRulNdviaUpbibqnTPOeEe0FJ8l5NicnoOVFUa3bGzIyVFd9O7hQ9g/0\" style=\"height:566px; width:513px\" /></p>"
				+ "<p><img alt=\"\" src=\"http://read.html5.qq.com/image?src=forum&amp;q=5&amp;r=0&amp;imgflag=7&amp;imageUrl=http://mmbiz.qpic.cn/mmbiz_png/lTrSibc57n2ZaGKNdKNYoPF0UOc92rlBiaVC2WRulNdviaUpbibqnTPOeEe0FJ8l5NicnoOVFUa3bGzIyVFd9O7hQ9g/0\" style=\"height:566px; width:513px\" /></p>";
		String strOld = str
				.replace(
						"http://read.html5.qq.com/image?src=forum&amp;q=5&amp;r=0&amp;imgflag=7&amp;imageUrl=",
						"");
		String strNew = StringEscapeUtils
				.unescapeHtml4(str)
				.replace(
						"http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=",
						"");
		System.out.println(strOld);
		System.out.println(strNew);

	}
}
