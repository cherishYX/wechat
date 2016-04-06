package com.web.yx.common.constant;

public class Constants {
	/**
	 * 请求bean
	 */
	public static final String WECHAT_REQUEST_BEAN = "eactivity_request_bean";
	public static final String WECHAT_RESPONSE_BEAN = "eactivity_response_bean";
	/**
	 * 微信自定义token
	 */
	public static final String token = "apexsoft";
	
	/**消息类型*/
	public static final String WECHAT_TEXT_MESSAGE = "text";
	public static final String WECHAT_IMAGE_MESSAGE = "image";
	public static final String WECHAT_NEWS_MESSAGE = "news";
	
	/**事件类型*/
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";
	public static final String EVENT_TYPE_CLICK = "click";
	
	
	/**取消关注*/
	public static final Integer UNSUBSCRIBE = 0;
	/**关注*/
	public static final Integer SUBSCRIBE = 1;
	
}
