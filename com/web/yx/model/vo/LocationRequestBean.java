package com.web.yx.model.vo;

import com.web.yx.model.RequestBean;


public class LocationRequestBean extends RequestBean{

	
	private static final long serialVersionUID = 1L;

	private Double location_X;
	
	private Double location_Y;
	
	private Integer scale;
	
	public Double getLocation_X() {
		return location_X;
	}

	public void setLocation_X(Double location_X) {
		this.location_X = location_X;
	}

	public Double getLocation_Y() {
		return location_Y;
	}

	@Override
	public String toString() {
		return "LocationRequestBean [location_X=" + location_X
				+ ", location_Y=" + location_Y + ", scale=" + scale
				+ ", label=" + label + ", msgId=" + msgId
				+ ", getToUserName()=" + getToUserName()
				+ ", getFromUserName()=" + getFromUserName()
				+ ", getCreateTime()=" + getCreateTime() + ", getMsgType()="
				+ getMsgType() + "]";
	}

	public void setLocation_Y(Double location_Y) {
		this.location_Y = location_Y;
	}

	private String label;
	

	public Integer getScale() {
		return scale;
	}

	public void setScale(Integer scale) {
		this.scale = scale;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public Long getMsgId() {
		return msgId;
	}

	public void setMsgId(Long msgId) {
		this.msgId = msgId;
	}

	private Long msgId;
}
