/** 
 * @Prject: ssm-master
 * @Package: core.utils 
 * @Title: AjaxDecode.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月21日 下午4:42:25   
 */
package core.utils;

/**
 * @ClassName: AjaxDecode
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月21日 下午4:42:25
 */
public class AjaxDecode {

	public static String ajax_decode(String str, boolean bsql) {
		if (StringUtils.isNotEmpty(str)) {
			str = str.replace("{@bai@}", "%");
			str = str.replace("{@dan@}", "'");
			str = str.replace("{@shuang@}", "\"");
			str = str.replace("{@kong@}", " ");
			str = str.replace("{@zuojian@}", "<");
			str = str.replace("{@youjian@}", ">");
			str = str.replace("{@and@}", "&");
			str = str.replace("{@tab@}", "\t");
			str = str.replace("{@jia@}", "+");
			if (bsql)
				str = str.replace("'", "''");
		}

		return str;
	}

	public static String ajax_decode(String str) {
		return ajax_decode(str, true);
	}
}
