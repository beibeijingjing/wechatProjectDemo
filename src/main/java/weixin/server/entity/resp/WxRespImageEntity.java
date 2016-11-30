/**
 * 
 */
package weixin.server.entity.resp;

import weixin.server.entity.base.WxBaseRespEntity;
import weixin.server.entity.item.WxItemImageEntity;

/**
 * @author honey.zhao@aliyun.com
 * @version Dec 29, 2013
 * 
 */
public class WxRespImageEntity extends WxBaseRespEntity {
	WxItemImageEntity image;

	public WxItemImageEntity getImage() {
		return image;
	}

	public void setImage(WxItemImageEntity image) {
		this.image = image;
	}

	public WxRespImageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WxRespImageEntity(WxItemImageEntity image) {
		super();
		this.image = image;
	}

	@Override
	public String toString() {
		return "WxRespImageEntity [image=" + image + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((image == null) ? 0 : image.hashCode());
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
		WxRespImageEntity other = (WxRespImageEntity) obj;
		if (image == null) {
			if (other.image != null)
				return false;
		} else if (!image.equals(other.image))
			return false;
		return true;
	}
	
	
}
