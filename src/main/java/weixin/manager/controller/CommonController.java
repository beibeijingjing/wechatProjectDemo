package weixin.manager.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.EncoderException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import weixin.server.config.WxConfig;
import core.controller.BaseController;
import core.utils.upload.MdlUpload;
import core.utils.upload.Result;
import core.utils.upload.UploadFile;

@Controller
@RequestMapping(value = "/pc/common")
public class CommonController extends BaseController {

	private static final Log log = LogFactory.getLog(CommonController.class);

	@RequestMapping(value = "/uploadWxServer.do", method = RequestMethod.POST)
	@ResponseBody
	public Object upload(@RequestParam MultipartFile file,
			@RequestParam Integer fileType) throws IllegalStateException,
			IOException, EncoderException {
		String fileTypeStr = "image";
		if (fileType == 0) {
			fileTypeStr = "image";
		}
		if (fileType == 1) {
			fileTypeStr = "voice";
		}
		if (fileType == 2) {
			fileTypeStr = "video";
		}
		if (fileType == 3) {
			fileTypeStr = "thumb";
		}
		File f = (File) file;
		Result<MdlUpload> uploadRst = UploadFile.Upload(WxConfig.accessToken,
				fileTypeStr, f);
		log.info("上传结果：" + uploadRst.getErrmsg());
		System.out.println("======================" + uploadRst.getErrcode()
				+ "  msg:" + uploadRst.getErrmsg());
		System.out.println("======================"
				+ uploadRst.getObj().getMedia_id() + "  msg:"
				+ uploadRst.getObj().getType());

		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("rtnCode", 1);
		rsMap.put("rtnMsg", "操作成功.");
		rsMap.put("mediaId", uploadRst.getObj().getMedia_id());
		rsMap.put("url", uploadRst.getObj().getUrl());
		return rsMap;
	}

	@RequestMapping(value = "/ckeditorUploadImage.do", method = RequestMethod.POST)
	public void ckeditorUpload(@RequestParam MultipartFile upload,
			HttpServletRequest request, @RequestParam String CKEditorFuncNum,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		log.info("----------------------------上传输入参数----------------------------");
		log.info("CKEditorFuncNum：" + CKEditorFuncNum);
		// 上传富文本图片
		File f = (File) upload;
		if (upload != null) {
			Result<MdlUpload> uploadRst = UploadFile.Upload(
					WxConfig.accessToken, "content_image", f);

			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction("
					+ CKEditorFuncNum + ",'" + uploadRst.getObj().getUrl()
					+ "','')");
			out.println("</script>");
		}

	}

}
