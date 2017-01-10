package weixin.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import core.controller.BaseController;

@Controller
@RequestMapping(value = "pc")
public class LoginManageController extends BaseController {

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public Object toLogin() {
		return "weixin/login";
	}

	@RequestMapping(value = "/toIndex.do")
	public Object toLogin(String userName, String password) {

		return "weixin/index";
	}

	@RequestMapping(value = "/accessDenied.do")
	public Object accessDenied() {

		return "weixin/accessDenied";
	}
}
