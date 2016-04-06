package com.web.yx.model.vo;

import com.web.yx.model.RequestBean;


public class LinkRequestBean extends RequestBean{

	private static final long serialVersionUID = 1L;

	private String title;
	
	private String description;
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "LinkRequestBean [title=" + title + ", description="
				+ description + ", url=" + url + ", msgId=" + msgId
				+ ", getToUserName()=" + getToUserName()
				+ ", getFromUserName()=" + getFromUserName()
				+ ", getCreateTime()=" + getCreateTime() + ", getMsgType()="
				+ getMsgType() + "]";
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	private String url;
	
	private Long msgId;
	
}
