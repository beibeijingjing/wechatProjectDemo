/**
 * 
 */
package weixin.server.entity.resp;

import weixin.server.entity.base.WxBaseRespEntity;


/**
 * @author honey.zhao@aliyun.com
 * @version Jul 28, 2013
 * 
 */
public class WxRespTextEntity extends WxBaseRespEntity {
	private String content;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public WxRespTextEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WxRespTextEntity(String content) {
		super();
		this.content = content;
	}

	@Override
	public String toString() {
		return "WxRespTextEntity [content=" + content + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((content == null) ? 0 : content.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		WxRespTextEntity other = (WxRespTextEntity) obj;
		if (content == null) {
			if (other.content != null)
				return false;
		} else if (!content.equals(other.content))
			return false;
		return true;
	}
	
	
}
