package com.web.yx.model.vo;

import com.web.yx.model.RequestBean;


public class ImageRequestBean extends RequestBean{

	
	private static final long serialVersionUID = -4234720624092691372L;

	private String picUrl;
	
	private Long msgId;

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public Long getMsgId() {
		return msgId;
	}

	@Override
	public String toString() {
		return "ImageRequestBean [picUrl=" + picUrl + ", msgId=" + msgId
				+ ", getToUserName()=" + getToUserName()
				+ ", getFromUserName()=" + getFromUserName()
				+ ", getCreateTime()=" + getCreateTime() + ", getMsgType()="
				+ getMsgType() + "]";
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}
}
