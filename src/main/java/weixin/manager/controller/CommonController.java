package weixin.manager.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import core.utils.ResourceUtils;
import core.utils.UUIDUtils;
import core.utils.upload.MdlUpload;
import core.utils.upload.Result;
import core.utils.upload.UploadFile;

@Controller
@RequestMapping(value = "/pc/common")
public class CommonController extends BaseController {

	private static final Log log = LogFactory.getLog(CommonController.class);
	private static final String priUrl = "http://read.html5.qq.com/image?src=forum&q=5&r=0&imgflag=7&imageUrl=";

	@RequestMapping(value = "/uploadWxServer.do", method = RequestMethod.POST)
	@ResponseBody
	public Object upload(@RequestParam MultipartFile file,
			@RequestParam Integer fileType, HttpServletRequest request,
			HttpServletResponse response) throws IllegalStateException,
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
		Result<MdlUpload> uploadRst = new Result<MdlUpload>();
		if (!file.isEmpty()) {
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
			String uploadPathDir = "/image/" + dateformat.format(new Date());
			String uploadRealPathDir = ResourceUtils.getResource("upload.path")
					+ uploadPathDir;
			File uploadSaveFile = new File(uploadRealPathDir);
			if (!uploadSaveFile.exists()) {
				uploadSaveFile.mkdirs();
			}
			String suffix = "";
			if (file.getOriginalFilename().lastIndexOf(".") != -1) {
				suffix = file.getOriginalFilename().substring(
						file.getOriginalFilename().lastIndexOf("."));
			}
			String uploadName = UUIDUtils.generate() + suffix;
			String fileName = uploadRealPathDir + "/" + uploadName;
			File files = new File(fileName);
			file.transferTo(files);

			uploadRst = UploadFile.Upload(WxConfig.accessToken, fileTypeStr,
					files);

			// 删除文件
			if (files.exists())
				files.delete();
		}

		Map<String, Object> rsMap = new HashMap<String, Object>();
		rsMap.put("rtnCode", 1);
		rsMap.put("rtnMsg", "操作成功.");
		rsMap.put("mediaId", uploadRst.getObj().getMedia_id());
		rsMap.put("url", priUrl + uploadRst.getObj().getUrl());
		/*
		 * rsMap.put( "url",
		 * "http://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E5%8A%A8%E7%89%A9&step_word=&hs=0&pn=5&spn=0&di=177136290320&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=2497863998%2C1219147879&os=3947002739%2C3111971699&simid=3506484257%2C177768363&adpicid=0&ln=1974&fr=&fmq=1482301290493_R&fm=result&ic=0&s=undefined&se=&sme=&tab=0&width=&height=&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2F2012%2F012%2F97R9L602I98Y.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Frtv_z%26e3Byjfhy_z%26e3Bv54AzdH3FclAzdH3Fnalbaccl1_b_z%26e3Bfip4s&gsm=0&rpstart=0&rpnum=0"
		 * ); rsMap.put("mediaId", new Date());
		 */
		return rsMap;
	}

	@RequestMapping(value = "/ckeditorUploadImage.do", method = RequestMethod.POST)
	public void ckeditorUpload(@RequestParam MultipartFile upload,
			HttpServletRequest request, @RequestParam String CKEditorFuncNum,
			HttpServletResponse response) throws IllegalStateException,
			IOException {
		log.info("----------------------------上传输入参数----------------------------");
		log.info("CKEditorFuncNum：" + CKEditorFuncNum);

		if (!upload.isEmpty()) {

			SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMdd");
			String uploadPathDir = "/image/" + dateformat.format(new Date());
			String uploadRealPathDir = ResourceUtils.getResource("upload.path")
					+ uploadPathDir;
			File uploadSaveFile = new File(uploadRealPathDir);
			if (!uploadSaveFile.exists()) {
				uploadSaveFile.mkdirs();
			}
			String suffix = "";
			if (upload.getOriginalFilename().lastIndexOf(".") != -1) {
				suffix = upload.getOriginalFilename().substring(
						upload.getOriginalFilename().lastIndexOf("."));
			}
			String uploadName = UUIDUtils.generate() + suffix;
			String fileName = uploadRealPathDir + "/" + uploadName;
			File files = new File(fileName);
			upload.transferTo(files);

			Result<MdlUpload> uploadRst = UploadFile.Upload(
					WxConfig.accessToken, "content_image", files);

			PrintWriter out = response.getWriter();
			out.println("<script type=\"text/javascript\">");
			out.println("window.parent.CKEDITOR.tools.callFunction("
					+ CKEditorFuncNum + ",'" + priUrl
					+ uploadRst.getObj().getUrl() + "" + "','')");
			out.println("</script>");

			// 删除文件

			if (files.exists())
				files.delete();

		}
	}

}
