package weixin.test;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.junit.Before;
import org.junit.Test;

import weixin.test.util.HttpRequest;

public class WeixinControllerTest {

	public static String TOKEN = "";

	public static String domain = "localhost:8080/ssm";
//	public static String domain = "192.168.0.10:8080/qmgx";
//	public static String domain = "test.api.quanminshishang.com";

	@Before
	public void init() throws JsonParseException, JsonMappingException,
			IOException {
		// String getToken = HttpRequest.sendPost(domain + "","");
		// Map<?, ?> map = JsonConvert.DeserializeObject(getToken);
		// TOKEN = (String) map.get("access_token");
		// System.out.println(TOKEN);
		System.out.println("----------------test start----------------");
	}

	@Test
	public void loginTest() throws JsonGenerationException,
			JsonMappingException, IOException {
//		String url = "http://" + domain + "/v1/common/getVerifyCode.do";
//		String param = "phone=18210370706&tags=tags";
//		String rs = HttpRequest.sendGet(url, param);
//		System.out.println("获取验证码操作：" + rs);
//		Map<?, ?> verifyCode = JsonConvert.DeserializeObject(rs);

		String url = "http://" + domain + "/v1/users/login.do";
		String param = "phone=222&tags=tags&verifyCode=1";
		String rs = HttpRequest.sendPost(url, param);
		System.out.println(rs);
	}

	@Test
	public void otherUserLoginTest() throws JsonGenerationException,
			JsonMappingException, IOException {
		String url = "http://" + domain + "/v1/users/otherUserLogin.do";
		String param = "uId=aaa&tags=tags&nickName=testNickName&avatarUrl=";
		String rs = HttpRequest.sendPost(url, param);
		System.out.println(rs);
	}

	@Test
	public void getUserInfoTest() throws JsonGenerationException,
			JsonMappingException, IOException {
		String url = "http://" + domain + "/v1/users/getUserInfo.do";
		String param = "token=efa515edaa4f43d3ac72ca5b6a4f6d32&tags=tags";
		String rs = HttpRequest.sendGet(url, param);
		System.out.println(rs);
	}

	@Test
	public void updateUserInfoTest() throws JsonGenerationException,
			JsonMappingException, IOException {
		String url = "http://" + domain + "/v1/users/updateUserInfo.do";
		String param = "token=000241dd05124de79567130eac482b0e&tags=tags"
				+ "&nickName=高圆圆";
		String rs = HttpRequest.sendPost(url, param);
		System.out.println(rs);
	}
	
	@Test
	public void getUserIndexTest() throws JsonGenerationException,
			JsonMappingException, IOException {
		String url = "http://" + domain + "/v1/users/getUserIndex.do";
		String param = "token=c0ebf31bbd924f3d8d6efdce1d93d7d9&userId=&tags=tags";
		String rs = HttpRequest.sendGet(url, param);
		System.out.println(rs);
	}
	@Test
	public void addFeedBackInfoTest() throws JsonGenerationException,
			JsonMappingException, IOException {
		String url = "http://" + domain + "/v1/users/addFeedBackInfo.do";
		String param = "token=0003d10a098e426cabe04244c199ea46&tags=tags&feedBackMessage=我的意见反馈测试222&feedBackTel=18810000000";
		String rs = HttpRequest.sendPost(url, param);
		System.out.println(rs);
	}
	
	
	@Test
	public void authGetTest() throws JsonGenerationException,
			JsonMappingException, IOException {
		String url = "http://" + domain + "/wx/authGet.do";
		String param = "signature=1&timestamp=2&nonce=3&echostr=4";
		String rs = HttpRequest.sendGet(url, param);
		System.out.println(rs);
	}

}
