package weixin.server.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.utils.AccessTokenGetThread;

/**
 * Servlet implementation class AccessTokenServlet
 */
@WebServlet(name = "AccessTokenServlet")
public class AccessTokenServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
			AccessTokenGetThread.appId = config.getInitParameter("appid"); //获取servlet初始参数appid和appsecret
			AccessTokenGetThread.appSecret = config.getInitParameter("appsecret");
		    System.out.println("appid:"+AccessTokenGetThread.appId);
		    System.out.println("appSecret:"+AccessTokenGetThread.appSecret);
		    new Thread(new AccessTokenGetThread()).start(); //启动进程
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
	}

}
