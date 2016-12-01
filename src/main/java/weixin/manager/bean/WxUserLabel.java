package weixin.manager.bean;

import java.io.Serializable;

public class WxUserLabel extends BaseBean implements Serializable {

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -8640707612105533623L;

	private String labelId;
	private String labelName;
	private Integer userCount;
	private Integer isSyn;

	public String getLabelId() {
		return labelId;
	}

	public void setLabelId(String labelId) {
		this.labelId = labelId;
	}

	public String getLabelName() {
		return labelName;
	}

	public void setLabelName(String labelName) {
		this.labelName = labelName;
	}

	public Integer getUserCount() {
		return userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public Integer getIsSyn() {
		return isSyn;
	}

	public void setIsSyn(Integer isSyn) {
		this.isSyn = isSyn;
	}

}
