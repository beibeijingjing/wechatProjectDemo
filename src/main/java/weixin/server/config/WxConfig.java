/**
 * 
 */
package weixin.server.config;


/**
 * @author honey.zhao@aliyun.com
 * @version Jul 29, 2013
 *
 */

public class WxConfig {
	public static final String TABLE_PREFIX = "wx_";
	public static final int COL_LEN_URL  = 1024;
	public static final int COL_LEN_CONTENT  = 4000;
	public static final int COL_LEN_TITLE  = 200;
	public static final int COL_LEN_USER_NAME  = 100;
	public static final int COL_LEN_INDICATOR  = 64;
	public static final String APP_ID  = "wx7484d1f5b81a3549";//wx7484d1f5b81a3549  测试   wx679bf77bfdd77e71
	public static final String APP_SECRET  = "bac44696d51b728b42c424e3ed032ff6";//bac44696d51b728b42c424e3ed032ff6  405f33af268284c8445221b410d5f9da
	
	public static String accessToken="";//接口调用凭证
	
	
	private String token;
	
	
	private  String menuCreateUrl;
	private  String menuGetUrl;
	private  String menuDeleteUrl;
	
	private  String accessTokenCreateUrl;
	
	private  String customSendUrl;

	private  String mediaUploadUrl;
	
	private  String qrcodeCreateUrl;
	
	private  String userInfoUrl;
	private  String userGetUrl;
	
	private  String groupsCreateUrl;
	private  String groupsGetUrl;
	private String groupsGetIdUrl;
	private  String groupsUpdateUrl;
	private  String groupsMembersUpdateUrl;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
	public String getMenuCreateUrl() {
		return menuCreateUrl;
	}
	public void setMenuCreateUrl(String menuCreateUrl) {
		this.menuCreateUrl = menuCreateUrl;
	}
	public String getMenuGetUrl() {
		return menuGetUrl;
	}
	public void setMenuGetUrl(String menuGetUrl) {
		this.menuGetUrl = menuGetUrl;
	}
	public String getMenuDeleteUrl() {
		return menuDeleteUrl;
	}
	public void setMenuDeleteUrl(String menuDeleteUrl) {
		this.menuDeleteUrl = menuDeleteUrl;
	}
	public String getAccessTokenCreateUrl() {
		return accessTokenCreateUrl;
	}
	public void setAccessTokenCreateUrl(String accessTokenCreateUrl) {
		this.accessTokenCreateUrl = accessTokenCreateUrl;
	}
	public String getCustomSendUrl() {
		return customSendUrl;
	}
	public void setCustomSendUrl(String customSendUrl) {
		this.customSendUrl = customSendUrl;
	}
	public String getMediaUploadUrl() {
		return mediaUploadUrl;
	}
	public void setMediaUploadUrl(String mediaUploadUrl) {
		this.mediaUploadUrl = mediaUploadUrl;
	}
	public String getQrcodeCreateUrl() {
		return qrcodeCreateUrl;
	}
	public void setQrcodeCreateUrl(String qrcodeCreateUrl) {
		this.qrcodeCreateUrl = qrcodeCreateUrl;
	}
	public String getUserInfoUrl() {
		return userInfoUrl;
	}
	public void setUserInfoUrl(String userInfoUrl) {
		this.userInfoUrl = userInfoUrl;
	}
	public String getUserGetUrl() {
		return userGetUrl;
	}
	public void setUserGetUrl(String userGetUrl) {
		this.userGetUrl = userGetUrl;
	}
	public String getGroupsCreateUrl() {
		return groupsCreateUrl;
	}
	public void setGroupsCreateUrl(String groupsCreateUrl) {
		this.groupsCreateUrl = groupsCreateUrl;
	}
	public String getGroupsGetUrl() {
		return groupsGetUrl;
	}
	public void setGroupsGetUrl(String groupsGetUrl) {
		this.groupsGetUrl = groupsGetUrl;
	}
	public String getGroupsGetIdUrl() {
		return groupsGetIdUrl;
	}
	public void setGroupsGetIdUrl(String groupsGetIdUrl) {
		this.groupsGetIdUrl = groupsGetIdUrl;
	}
	public String getGroupsUpdateUrl() {
		return groupsUpdateUrl;
	}
	public void setGroupsUpdateUrl(String groupsUpdateUrl) {
		this.groupsUpdateUrl = groupsUpdateUrl;
	}
	public String getGroupsMembersUpdateUrl() {
		return groupsMembersUpdateUrl;
	}
	public void setGroupsMembersUpdateUrl(String groupsMembersUpdateUrl) {
		this.groupsMembersUpdateUrl = groupsMembersUpdateUrl;
	}
	public static String getTablePrefix() {
		return TABLE_PREFIX;
	}
	public static int getColLenUrl() {
		return COL_LEN_URL;
	}
	public static int getColLenContent() {
		return COL_LEN_CONTENT;
	}
	public static int getColLenTitle() {
		return COL_LEN_TITLE;
	}
	public static int getColLenUserName() {
		return COL_LEN_USER_NAME;
	}
	public static int getColLenIndicator() {
		return COL_LEN_INDICATOR;
	}
	public WxConfig() {
		super();
	}
	public WxConfig(String token, String appid, String appsecret,
			String menuCreateUrl, String menuGetUrl, String menuDeleteUrl,
			String accessTokenCreateUrl, String customSendUrl,
			String mediaUploadUrl, String qrcodeCreateUrl, String userInfoUrl,
			String userGetUrl, String groupsCreateUrl, String groupsGetUrl,
			String groupsGetIdUrl, String groupsUpdateUrl,
			String groupsMembersUpdateUrl) {
		super();
		this.token = token;
		this.menuCreateUrl = menuCreateUrl;
		this.menuGetUrl = menuGetUrl;
		this.menuDeleteUrl = menuDeleteUrl;
		this.accessTokenCreateUrl = accessTokenCreateUrl;
		this.customSendUrl = customSendUrl;
		this.mediaUploadUrl = mediaUploadUrl;
		this.qrcodeCreateUrl = qrcodeCreateUrl;
		this.userInfoUrl = userInfoUrl;
		this.userGetUrl = userGetUrl;
		this.groupsCreateUrl = groupsCreateUrl;
		this.groupsGetUrl = groupsGetUrl;
		this.groupsGetIdUrl = groupsGetIdUrl;
		this.groupsUpdateUrl = groupsUpdateUrl;
		this.groupsMembersUpdateUrl = groupsMembersUpdateUrl;
	}
	@Override
	public String toString() {
		return "WxConfig [token=" + token + ", menuCreateUrl=" + menuCreateUrl
				+ ", menuGetUrl=" + menuGetUrl + ", menuDeleteUrl="
				+ menuDeleteUrl + ", accessTokenCreateUrl="
				+ accessTokenCreateUrl + ", customSendUrl=" + customSendUrl
				+ ", mediaUploadUrl=" + mediaUploadUrl + ", qrcodeCreateUrl="
				+ qrcodeCreateUrl + ", userInfoUrl=" + userInfoUrl
				+ ", userGetUrl=" + userGetUrl + ", groupsCreateUrl="
				+ groupsCreateUrl + ", groupsGetUrl=" + groupsGetUrl
				+ ", groupsGetIdUrl=" + groupsGetIdUrl + ", groupsUpdateUrl="
				+ groupsUpdateUrl + ", groupsMembersUpdateUrl="
				+ groupsMembersUpdateUrl + "]";
	}
	
	
}
