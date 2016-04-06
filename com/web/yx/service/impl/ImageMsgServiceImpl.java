package com.web.yx.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.web.yx.common.constant.Constants;
import com.web.yx.model.RequestBean;
import com.web.yx.model.ResponseBean;
import com.web.yx.model.vo.Article;
import com.web.yx.service.ImageMsgService;

public class ImageMsgServiceImpl implements ImageMsgService {

	public void imageMsgHander(RequestBean requestBean,ResponseBean responseBean) {
		responseBean.setMsgType(Constants.WECHAT_NEWS_MESSAGE);
		Article article = new Article();
		article.setArticleCount(1);
		/**
		 *  Title 	图文消息标题
			Description 	图文消息描述
			PicUrl 	图片链接，支持JPG、PNG格式，较好的效果为大图640*320，小图80*80。
			Url 	点击图文消息跳转链接 
		 */
		List<Map<String,String>> items = new ArrayList<Map<String,String>>();
		Map<String,String> item = new HashMap<String,String>();
		item.put("Title", "图文消息标题");
		item.put("Description", "图文消息描述");
		item.put("PicUrl", "http://img1.bdstatic.com/img/image/664f31fbe096b63f6241937df3c8544ebf81a4ca3a3.jpg");
		item.put("Url", "http://www.baidu.com");
		items.add(item);
		article.setItems(items);
		responseBean.setArticle(article);
		responseBean.setContent("image");
	}

}