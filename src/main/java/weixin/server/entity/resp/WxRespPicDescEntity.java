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

	private List<WxItemPicDescEntity> articles;

	public List<WxItemPicDescEntity> getArticles() {
		return articles;
	}

	public int getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	public void setArticles(List<WxItemPicDescEntity> articles) {
		this.articles = articles;
	}

	public WxRespPicDescEntity() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WxRespPicDescEntity(List<WxItemPicDescEntity> articles) {
		super();
		this.articles = articles;
	}

	@Override
	public String toString() {
		return "WxRespPicDescEntity [articles=" + articles + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((articles == null) ? 0 : articles.hashCode());
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
		if (articles == null) {
			if (other.articles != null)
				return false;
		} else if (!articles.equals(other.articles))
			return false;
		return true;
	}
	
	
}
