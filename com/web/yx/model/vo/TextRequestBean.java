package com.web.yx.model.vo;

import com.web.yx.model.RequestBean;


public class TextRequestBean extends RequestBean{
	
	private static final long serialVersionUID = 1L;

	private String content;
	
	private Long msgId;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	@Override
	public String toString() {
		return "TextRequestBean [content=" + content + ", msgId=" + msgId
				+ ", getToUserName()=" + getToUserName()
				+ ", getFromUserName()=" + getFromUserName()
				+ ", getCreateTime()=" + getCreateTime() + ", getMsgType()="
				+ getMsgType() + ", toString()=" + super.toString()
				+ ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	
}
