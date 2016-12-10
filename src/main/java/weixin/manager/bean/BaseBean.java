package weixin.manager.bean;

import org.codehaus.jackson.map.annotate.JsonSerialize;

import core.utils.JsonDateSerializer;

public class BaseBean {
	private String id;
	@JsonSerialize(using = JsonDateSerializer.class, include = JsonSerialize.Inclusion.NON_EMPTY)
	private java.sql.Timestamp createTime;
	@JsonSerialize(using = JsonDateSerializer.class, include = JsonSerialize.Inclusion.NON_EMPTY)
	private java.sql.Timestamp updateTime;
	private Integer delFlag;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public java.sql.Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.sql.Timestamp createTime) {
		this.createTime = createTime;
	}

	public java.sql.Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.sql.Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Integer delFlag) {
		this.delFlag = delFlag;
	}

}
