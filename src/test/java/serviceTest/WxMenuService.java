package serviceTest;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import weixin.server.entity.base.WxMenuEntity;
import weixin.server.service.IWxMenuService;
import base.BaseControllerTest;

/**
 * 
 * create by Liujishuai on 2015年9月21日
 */
public class WxMenuService extends BaseControllerTest {
	@Autowired
	private IWxMenuService wxMenuService;

	@Test
	public void createWxMenu() {
		WxMenuEntity menu = new WxMenuEntity();
		wxMenuService.insert(menu);
	}

	@Test
	public void getWxMenuById() {
		WxMenuEntity entity = wxMenuService.getWxMenuById("1");
		System.out.println("===========================================");
		System.out.println(entity);
		System.out.println("===========================================");
	}

}
