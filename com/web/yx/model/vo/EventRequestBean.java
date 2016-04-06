package com.web.yx.model.vo;

import com.web.yx.model.RequestBean;



public class EventRequestBean extends RequestBean{

	
//	事件类型，subscribe(订阅)、unsubscribe(取消订阅)、CLICK(自定义菜单点击事件)
	
	private static final long serialVersionUID = -1354270036583193969L;

	private String event;
	
	private String eventKey;

	@Override
	public String toString() {
		return "EventRequestBean [event=" + event + ", eventKey=" + eventKey
				+ ", getToUserName()=" + getToUserName()
				+ ", getFromUserName()=" + getFromUserName()
				+ ", getCreateTime()=" + getCreateTime() + ", getMsgType()="
				+ getMsgType() + "]";
	}

	public String getEvent() {
		return event;
	}

	public void setEvent(String event) {
		this.event = event;
	}

	public String getEventKey() {
		return eventKey;
	}

	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}
}
