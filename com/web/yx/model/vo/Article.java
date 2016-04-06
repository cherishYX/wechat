package com.web.yx.model.vo;

import java.util.List;
import java.util.Map;

public class Article {

	/**
	 * 图文消息个数，限制为10条以内 
	 */
	private Integer articleCount;
	
	/**
	 * 多条图文消息信息，默认第一个item为大图 
	 * Title 	图文消息标题
	 * Description 	图文消息描述
	 * PicUrl 	图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。
	 * Url 	点击图文消息跳转链接 
	 * List<Map<String,String>> items
	 */
	private List<Map<String,String>> items;

	public Integer getArticleCount() {
		return articleCount;
	}

	public void setArticleCount(Integer articleCount) {
		this.articleCount = articleCount;
	}

	public List<Map<String, String>> getItems() {
		return items;
	}

	public void setItems(List<Map<String, String>> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "Article [articleCount=" + articleCount + ", items=" + items
				+ "]";
	}

}
