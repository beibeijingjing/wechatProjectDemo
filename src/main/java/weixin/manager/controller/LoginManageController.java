package weixin.manager.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import core.controller.BaseController;

@Controller
@RequestMapping(value = "/pc")
public class LoginManageController extends BaseController {


	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public Object toLogin() {
		return "weixin/login";
	}

	
	@RequestMapping(value="/toLogin.do", method=RequestMethod.POST)
	public Object toLogin(@RequestParam String userName,@RequestParam String password){
		
		
		return "weixin/index";
	}
}
