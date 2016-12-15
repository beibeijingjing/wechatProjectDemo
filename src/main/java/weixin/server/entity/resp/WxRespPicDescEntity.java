/**
 * 
 */
package weixin.server.entity.resp;

import java.util.List;

import weixin.server.entity.base.WxBaseRespEntity;
import weixin.server.entity.item.WxItemPicDescEntity;

/**
 * @author honey.zhao@aliyun.com
 * @version Jul 28, 2013
 * 
 */
public class WxRespPicDescEntity extends WxBaseRespEntity {
	// 图文消息个数，限制为10条以内
	private int ArticleCount;

	private List<WxItemPicDescEntity> Articles;

	public List<WxItemPicDescEntity> getArticles() {
		return Articles;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public void setArticles(List<WxItemPicDescEntity> articles) {
		this.Articles = articles;
	}

	public WxRespPicDescEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WxRespPicDescEntity(List<WxItemPicDescEntity> articles) {
		super();
		this.Articles = articles;
	}

	@Override
	public String toString() {
		return "WxRespPicDescEntity [articles=" + Articles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((Articles == null) ? 0 : Articles.hashCode());
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
		WxRespPicDescEntity other = (WxRespPicDescEntity) obj;
		if (Articles == null) {
			if (other.Articles != null)
				return false;
		} else if (!Articles.equals(other.Articles))
			return false;
		return true;
	}

}
