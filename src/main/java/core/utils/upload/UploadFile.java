/** 
 * @Prject: ssm-master
 * @Package: core.utils.upload 
 * @Title: UploadFile.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月16日 下午5:23:36   
 */
package core.utils.upload;

import java.io.File;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONObject;

import core.utils.ResourceUtils;

/**
 * @ClassName: UploadFile
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月16日 下午5:23:36
 */
public class UploadFile {
	/**
	 * 上传文件
	 * 
	 * @param accessToken
	 * @param type
	 * @param file
	 * @return
	 */
	public static Result<MdlUpload> Upload(String accessToken, String type,
			File file) {
		Result<MdlUpload> result = new Result<MdlUpload>();
		String url = "";
		Integer flag = 0;
		if ("content_image".equals(type)) {
			// 图文内容图片上传地址
			url = ResourceUtils.getResource("wx_media_upload_content_img_url")
					+ "?access_token=" + accessToken;
			flag = 1;
		} else {
			url = ResourceUtils.getResource("wx_media_upload_other_url")
					+ "?access_token=" + accessToken + "&type=" + type;
		}
		JSONObject jsonObject;
		try {
			HttpPostUtil post = new HttpPostUtil(url);
			post.addParameter("media", file);
			String jsonStr = post.send();
			jsonObject = new JSONObject(jsonStr);
			if (StringUtils.isNotEmpty(jsonObject.getString("url"))) {
				MdlUpload upload = new MdlUpload();
				upload.setUrl(jsonObject.getString("url"));
				if (flag == 0) {
					upload.setMedia_id(jsonObject.getString("media_id"));
				}
				result.setObj(upload);
				result.setErrmsg("success");
				result.setErrcode("0");
			} else {
				result.setErrmsg(jsonObject.getString("errmsg"));
				result.setErrcode(jsonObject.getString("errcode"));
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.setErrmsg("Upload Exception:" + e.toString());
		}
		return result;
	}

	public static void main(String[] args) {
		File file = new File("E:\\Tulips.jpg");
		System.err.println(file.getName());
		Result<MdlUpload> result = UploadFile.Upload("image", null, file);
		System.out.println("Errcode=" + result.getErrcode() + "\tErrmsg="
				+ result.getErrmsg());
		System.out.println(result.getObj().toString());
	}
}
