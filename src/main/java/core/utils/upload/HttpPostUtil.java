/** 
 * @Prject: ssm-master
 * @Package: core.utils.upload 
 * @Title: HttpPostUtil.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月16日 下午4:04:55   
 */
package core.utils.upload;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * @ClassName: HttpPostUtil
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月16日 下午4:04:55
 */
public class HttpPostUtil {
	private URL url;
	private HttpURLConnection conn;
	private final String boundary = "--------httppost123";
	private final HashMap<String, String> textParams = new HashMap<String, String>();
	private final HashMap<String, File> fileparams = new HashMap<String, File>();
	private DataOutputStream outputStream;

	public HttpPostUtil(String url) throws Exception {
		this.url = new URL(url);
	}

	/**
	 * 重新设置要请求的服务器地址，即上传文件的地址。
	 * 
	 * @param url
	 * @throws Exception
	 */
	public void setUrl(String url) throws Exception {
		this.url = new URL(url);
	}

	/**
	 * 增加一个普通字符串数据到form表单数据中
	 * 
	 * @param name
	 * @param value
	 */
	public void addParameter(String name, String value) {
		textParams.put(name, value);
	}

	/**
	 * 增加一个文件到form表单数据中
	 * 
	 * @param name
	 * @param value
	 */
	public void addParameter(String name, File value) {
		fileparams.put(name, value);
	}

	/**
	 * 清空所有已添加的form表单数据
	 */
	public void clearAllParameters() {
		textParams.clear();
		fileparams.clear();
	}

	/**
	 * 发送数据到服务器，返回一个字节包含服务器的返回结果的数组
	 * 
	 * @return
	 * @throws Exception
	 */
	public String send() throws Exception {
		initConnection();
		conn.connect();
		outputStream = new DataOutputStream(conn.getOutputStream());
		writeFileParams();
		writeStringParams();
		paramsEnd();
		int code = conn.getResponseCode();
		if (code == 200) {
			InputStream in = conn.getInputStream();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			byte[] buf = new byte[1024 * 8];
			int len;
			while ((len = in.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			conn.disconnect();
			String s = new String(out.toByteArray(), "utf-8");
			return s;
		}
		return null;
	}

	/**
	 * 文件上传的connection的一些必须设置
	 * 
	 * @throws Exception
	 */
	private void initConnection() throws Exception {
		conn = (HttpURLConnection) this.url.openConnection();
		conn.setDoOutput(true);
		conn.setUseCaches(false);
		conn.setConnectTimeout(10000); // 连接超时为10秒
		conn.setRequestMethod("POST");
		conn.setRequestProperty("Content-Type",
				"multipart/form-data; boundary=" + boundary);
	}

	/**
	 * 普通字符串数据
	 * 
	 * @throws Exception
	 */
	private void writeStringParams() throws Exception {
		Set<String> keySet = textParams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			String value = textParams.get(name);
			outputStream.writeBytes("--" + boundary + "\r\n");
			outputStream.writeBytes("Content-Disposition: form-data; name=\""
					+ name + "\"\r\n");
			outputStream.writeBytes("\r\n");
			outputStream.writeBytes(encode(value) + "\r\n");
		}
	}

	/**
	 * 文件数据
	 * 
	 * @throws Exception
	 */
	private void writeFileParams() throws Exception {
		Set<String> keySet = fileparams.keySet();
		for (Iterator<String> it = keySet.iterator(); it.hasNext();) {
			String name = it.next();
			File value = fileparams.get(name);
			outputStream.writeBytes("--" + boundary + "\r\n");
			outputStream.writeBytes("Content-Disposition: form-data; name=\""
					+ name + "\"; filename=\"" + encode(value.getName())
					+ "\"\r\n");
			outputStream.writeBytes("Content-Type: " + getContentType(value)
					+ "\r\n");
			outputStream.writeBytes("\r\n");
			outputStream.write(getBytes(value));
			outputStream.writeBytes("\r\n");
		}
	}

	/**
	 * 获取文件的上传类型，图片格式为image/png,image/jpeg等。非图片为application /octet-stream
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private String getContentType(File f) throws Exception {
		return "application/octet-stream";
	}

	/**
	 * 把文件转换成字节数组
	 * 
	 * @param f
	 * @return
	 * @throws Exception
	 */
	private byte[] getBytes(File f) throws Exception {
		FileInputStream in = new FileInputStream(f);
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] b = new byte[1024];
		int n;
		while ((n = in.read(b)) != -1) {
			out.write(b, 0, n);
		}
		in.close();
		return out.toByteArray();
	}

	/**
	 * 添加结尾数据
	 * 
	 * @throws Exception
	 */
	private void paramsEnd() throws Exception {
		outputStream.writeBytes("--" + boundary + "--" + "\r\n");
		outputStream.writeBytes("\r\n");
	}

	/**
	 * 对包含中文的字符串进行转码，此为UTF-8。服务器那边要进行一次解码
	 * 
	 * @param value
	 * @return
	 * @throws Exception
	 */
	private String encode(String value) throws Exception {
		return URLEncoder.encode(value, "UTF-8");
	}

	/**
	 * 使用方法示例 此方法需要修改成自己上传地址才可上传成功
	 * 
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		File file = new File("C:\\Users\\asdfgh\\Desktop\\图片视频文件\\1.jpg");
		// 此处修改为自己上传文件的地址
		HttpPostUtil post = new HttpPostUtil(
				"http://test.api.quanmingexing.com/v1/common/upload.do");
		// 此处参数类似 curl -F media=@test.jpg
		post.addParameter("file", file);
		post.addParameter("fileType", "1");
		post.addParameter("sType", "44");
		post.send();
	}
}
