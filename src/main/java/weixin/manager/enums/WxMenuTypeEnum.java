/** 
 * @Prject: ssm-master
 * @Package: weixin.manager.enums 
 * @Title: WxMenuEnum.java 
 * Copyright © 2016Quanmingexing.All rights reserved.
 * @author: 曹亚伟   
 * @date: 2016年12月13日 上午11:37:45   
 */
package weixin.manager.enums;

/**
 * @ClassName: WxMenuEnum
 * @Description: TODO
 * @author: 曹亚伟
 * @date: 2016年12月13日 上午11:37:45
 */
public enum WxMenuTypeEnum {

	ClickEnum(0, "click", "点击"), ViewEnum(1, "view", "跳转url"), ScancodePushEnum(
			2, "scancode_push", "扫码"), ScancodeWaitmsgEnum(3,
			"scancode_waitmsg", "扫码等待"), PicSysphotoEnum(4, "pic_sysphoto",
			"拍照"), PicPhotoOrAlbumEnum(5, "pic_photo_or_album", "拍照和相册"), PicWeixinbumEnum(
			6, "pic_weixin", "相册"), LocationSelectEnum(7, "location_select",
			"位置"), MediaIdEnum(8, "media_id", "下发消息"), ViewLimitedEnum(9,
			"view_limited", "跳转图文");

	private Integer num;
	private String theVal;
	private String chineseName;

	WxMenuTypeEnum(Integer num, String theVal, String chineseName) {
		this.num = num;
		this.theVal = theVal;
		this.chineseName = chineseName;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getTheVal() {
		return theVal;
	}

	public void setTheVal(String theVal) {
		this.theVal = theVal;
	}

	public String getChineseName() {
		return chineseName;
	}

	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	public static WxMenuTypeEnum getWxMenuTypeEnum(int num) {
		for (WxMenuTypeEnum menuTypeEnum : WxMenuTypeEnum.values()) {
			if (menuTypeEnum.num == num)
				return menuTypeEnum;
		}
		return null;
	}

	public static String getChineseNameByNum(int num) {
		for (WxMenuTypeEnum menuTypeEnum : WxMenuTypeEnum.values()) {
			if (menuTypeEnum.num == num)
				return menuTypeEnum.chineseName;
		}
		return "";
	}

	public static String getMenuTypeValByNum(int num) {
		for (WxMenuTypeEnum menuTypeEnum : WxMenuTypeEnum.values()) {
			if (menuTypeEnum.num == num)
				return menuTypeEnum.theVal;
		}
		return "";
	}

	public static void main(String args[]) {
		System.out.println("==========="
				+ WxMenuTypeEnum.getMenuTypeValByNum(4));
	}

}
