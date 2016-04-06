package com.web.yx.model;

import java.io.Serializable;
/**
 * 请求参数
 * @author Administrator
 *
 */
public class RequestBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 开发者微信号 
	 */
	private String toUserName;
	
	/**
	 * 用户名
	 */
	private String fromUserName;
	
	/**
	 * 消息创建时间 
	 */
	private long createTime;
	
	/**
	 * 消息类型
	 */
	private String msgType;
	/**
	 * 开发者微信号
	 * @return
	 */
	public String getToUserName() {
		return toUserName;
	}

	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}
	/**
	 * 用户名
	 * @return
	 */
	public String getFromUserName() {
		return fromUserName;
	}

	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	public long getCreateTime() {
		return createTime;
	}

	public void setCreateTime(long createTime) {
		this.createTime = createTime;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	@Override
	public String toString() {
		return "RequestBean [toUserName=" + toUserName + ", fromUserName="
				+ fromUserName + ", createTime=" + createTime + ", msgType="
				+ msgType + "]";
	}
	
}
