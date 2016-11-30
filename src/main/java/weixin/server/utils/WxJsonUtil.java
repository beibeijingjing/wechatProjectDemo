/**
 * 
 */
package weixin.server.utils;




/**
 * @author honey.zhao@aliyun.com
 * @version Aug 4, 2013
 *
 */
public class WxJsonUtil {

	private WxJsonUtil() {
	}
	
	/*public static final String toMenuCreateReqBody(List<WxMenuBtnEntity> wxMenuBtnList) {
		Gson gson = new Gson();
		WxMenuCreateJson wxMenuCreateJson = new WxMenuCreateJson();
		wxMenuCreateJson.setButton(wxMenuBtnList);
		return gson.toJson(wxMenuCreateJson);
	}
	
	public static final WxRespCode toWxErrorJson(String errorResult) {
		if (errorResult != null && (errorResult.startsWith("{\"errcode")
				|| errorResult.startsWith("{\"errmsg"))) {
			return new Gson().fromJson(errorResult, WxRespCode.class);
		}
		return null;
	}
	
	public static final WxAccessTokenJson toAccessTokenJson(String result) {
		return new Gson().fromJson(result, WxAccessTokenJson.class);
	}
	*/

}