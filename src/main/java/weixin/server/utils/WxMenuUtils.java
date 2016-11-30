package weixin.server.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import weixin.server.config.WxConfig;
import weixin.server.entity.auth.WxAuth;
import weixin.server.entity.base.WxMenuEntity;
import weixin.server.exception.WxException;
import weixin.server.service.WxAuthService;

import com.google.gson.Gson;

public class WxMenuUtils {
	

	 /**
	 * @throws WxException 
	  * 创建Menu
	 * @Title: createMenu
	 * @Description: 创建Menu
	 * @param @return
	 * @param @throws IOException    设定文件
	 * @return int    返回类型
	 * @throws
	  */
	    public static String createMenu() throws WxException {
	      //String menu = "{\"button\":[{\"type\":\"click\",\"name\":\"MENU01\",\"key\":\"1\"},{\"type\":\"click\",\"name\":\"天气查询\",\"key\":\"西安\"},{\"name\":\"日常工作\",\"sub_button\":[{\"type\":\"click\",\"name\":\"待办工单\",\"key\":\"01_WAITING\"},{\"type\":\"click\",\"name\":\"已办工单\",\"key\":\"02_FINISH\"},{\"type\":\"click\",\"name\":\"我的工单\",\"key\":\"03_MYJOB\"},{\"type\":\"click\",\"name\":\"公告消息箱\",\"key\":\"04_MESSAGEBOX\"},{\"type\":\"click\",\"name\":\"签到\",\"key\":\"05_SIGN\"}]}]}";
	    	//String menu="{\"button\":[{\"type\":\"click\",\"name\":\"今日歌曲\",\"key\":\"V1001_TODAY_MUSIC\"}]}";
	    	
	    	  Map<String,Object> map=null;
	          List<Object> firstList=new ArrayList<Object>();

	          WxMenuEntity button=new WxMenuEntity();
	          button.setName("今日歌曲");
	          button.setKey("music");
	          button.setType("click");
	          firstList.add(button);
	          button=new WxMenuEntity();
	          button.setName("我的位置");
	          button.setKey("location");
	          button.setType("location_select");
	          firstList.add(button);

	          List<Object> secondList=new ArrayList<Object>();
	          button=new WxMenuEntity();
	          button.setName("拍照");
	          button.setKey("sysphoto");
	          button.setType("pic_sysphoto");
	          secondList.add(button);
	          button=new WxMenuEntity();
	          button.setName("拍照或系统相册");
	          button.setKey("pic_photo_or_album");
	          button.setType("pic_photo_or_album");
	          secondList.add(button);
	          button=new WxMenuEntity();
	          button.setName("视频");
	          button.setUrl("http://v.qq.com/");
	          button.setType("view");
	          secondList.add(button);
	          map=new HashMap<String,Object>();
	          map.put("name", "菜单");
	          map.put("sub_button", secondList);
	          firstList.add(map);

	          map=new HashMap<String,Object>();
	          map.put("button", firstList);

	          Gson gson=new Gson();
	          String jsonStr=gson.toJson(map);

	          System.out.println("==================jsonStr:"+jsonStr);
	          String menu=jsonStr;
	    	
	        //此处改为自己想要的结构体，替换即可
	      WxAuthService authService=new WxAuthService();
		  WxAuth wxAuth=authService.getAccessToken(WxConfig.APP_ID, WxConfig.APP_SECRET);
		  if(wxAuth==null){
			  return null;
		  }
	        String access_token= wxAuth.getAccessToken();
	        String action = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token="+access_token;
	        try {
	           URL url = new URL(action);
	           HttpURLConnection http =   (HttpURLConnection) url.openConnection();    

	           http.setRequestMethod("POST");        
	           http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
	           http.setDoOutput(true);        
	           http.setDoInput(true);
	           System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时3ss0秒
	           System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
	           http.connect();
	           OutputStream os= http.getOutputStream();    
	           os.write(menu.getBytes("UTF-8"));//传入参数    
	           os.flush();
	           os.close();

	           InputStream is =http.getInputStream();
	           int size =is.available();
	           byte[] jsonBytes =new byte[size];
	           is.read(jsonBytes);
	           String message=new String(jsonBytes,"UTF-8");
	           System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||");
	           System.out.println("=========================message:创建菜单结果："+message+"=======================");
	           System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||");
	           return "返回信息"+message;
	           } catch (MalformedURLException e) {
	               e.printStackTrace();
	           } catch (IOException e) {
	               e.printStackTrace();
	           }    
	        return "createMenu 失败";
	   }

	    
	    /**
	     * @throws WxException 
	     * 删除当前Menu
	    * @Title: deleteMenu
	    * @Description: 删除当前Menu
	    * @param @return    设定文件
	    * @return String    返回类型
	    * @throws
	     */
	   public static String deleteMenu() throws WxException
	   {
		   WxAuthService authService=new WxAuthService();
			  WxAuth wxAuth=authService.getAccessToken(WxConfig.APP_ID, WxConfig.APP_SECRET);
			  if(wxAuth==null){
				  return null;
			  }
		        String access_token= wxAuth.getAccessToken();
	       String action = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token="+access_token;
	       try {
	          URL url = new URL(action);
	          HttpURLConnection http =   (HttpURLConnection) url.openConnection();    

	          http.setRequestMethod("GET");        
	          http.setRequestProperty("Content-Type","application/x-www-form-urlencoded");    
	          http.setDoOutput(true);        
	          http.setDoInput(true);
	          System.setProperty("sun.net.client.defaultConnectTimeout", "30000");//连接超时30秒
	          System.setProperty("sun.net.client.defaultReadTimeout", "30000"); //读取超时30秒
	          http.connect();
	          OutputStream os= http.getOutputStream();    
	          os.flush();
	          os.close();


	          InputStream is =http.getInputStream();
	          int size =is.available();
	          byte[] jsonBytes =new byte[size];
	          is.read(jsonBytes);
	          String message=new String(jsonBytes,"UTF-8");
	          
	          System.out.println("=========================deleteMenu返回信息:"+message+"=========================");
	          System.out.println("=========================deleteMenu返回信息:"+message+"=========================");
	          return "deleteMenu返回信息:"+message;
	          } catch (MalformedURLException e) {
	              e.printStackTrace();
	          } catch (IOException e) {
	              e.printStackTrace();
	          }
	       return "deleteMenu 失败";   
	   }
	   
	   
	    public static void main (String []args){
	    	try {
				WxMenuUtils.createMenu();
			} catch (WxException e) {
				e.printStackTrace();
			}
	    	
	    	/*try {
				WxMenuUtils.deleteMenu();
			} catch (WxException e) {
				e.printStackTrace();
			}*/
	    	
	    }
}
