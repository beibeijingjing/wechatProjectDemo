package core.controller;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.TransientDataAccessResourceException;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.UncategorizedSQLException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import core.exception.WxBaseException;

public class BaseController {

	private static final Log log = LogFactory.getLog(BaseController.class);

	@ExceptionHandler
	@ResponseBody
	public Object exp(HttpServletRequest request, Exception ex) {
		Map<String, Object> exMap = new HashMap<String, Object>();

		// 保存异常信息
		Writer result = new StringWriter();
		PrintWriter printWriter = new PrintWriter(result);
		ex.printStackTrace(printWriter);

		if (ex instanceof ParseException) {
			exMap.put("rtnCode", 1); // 0：成功 1：失败
			exMap.put("rtnMsg", "出现解析异常,操作失败.");
			exMap.put("stackMsg",
					"异常请求：" + request.getRequestURL() + "," + ex.getMessage());
			log.error("异常请求：" + request.getRequestURL(), ex);
		} else if (ex instanceof MissingServletRequestParameterException) {
			exMap.put("rtnCode", 1); // 1：成功 0：失败
			exMap.put("rtnMsg", "参数缺失,操作失败.");
			exMap.put("stackMsg",
					"异常请求：" + request.getRequestURL() + "," + ex.getMessage());
			log.error("异常请求：" + request.getRequestURL(), ex);
		} else if (ex instanceof TypeMismatchException) {
			exMap.put("rtnCode", 1); // 1：成功 0：失败
			exMap.put("rtnMsg", "参数类型不匹配,操作失败.");
			exMap.put("stackMsg",
					"异常请求：" + request.getRequestURL() + "," + ex.getMessage());
			log.error("异常请求：" + request.getRequestURL(), ex);
		} else if (ex instanceof SQLException
				|| ex instanceof TransientDataAccessResourceException
				|| ex instanceof BadSqlGrammarException
				|| ex instanceof UncategorizedSQLException) {
			exMap.put("rtnCode", 1); // 1：成功 0：失败
			exMap.put("rtnMsg", "sql异常,操作失败.");
			exMap.put("stackMsg",
					"异常请求：" + request.getRequestURL() + "," + ex.getMessage());
			log.error("异常请求：" + request.getRequestURL(), ex);
		} else if (ex instanceof WxBaseException) {
			exMap.put("rtnCode", ((WxBaseException) ex).getError().getErrcode());
			exMap.put("rtnMsg", ((WxBaseException) ex).getError().getErrmsg());
			exMap.put("stackMsg",
					"异常请求：" + request.getRequestURL() + "," + ex.getMessage());
			log.error("异常请求：" + request.getRequestURL(), ex);
		} else {
			exMap.put("rtnCode", 1); // 1：成功 0：失败
			exMap.put("rtnMsg", "未知异常,操作失败.");
			exMap.put("stackMsg",
					"异常请求：" + request.getRequestURL() + "," + ex.getMessage());
			log.error("异常请求：" + request.getRequestURL(), ex);
		}
		return exMap;
	}

}
