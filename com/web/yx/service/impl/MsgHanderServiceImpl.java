package com.web.yx.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.web.yx.common.constant.Constants;
import com.web.yx.model.RequestBean;
import com.web.yx.model.ResponseBean;
import com.web.yx.service.EventMsgService;
import com.web.yx.service.ImageMsgService;
import com.web.yx.service.MsgHanderService;
import com.web.yx.service.OtherMsgService;
import com.web.yx.service.TextMsgService;

public class MsgHanderServiceImpl implements MsgHanderService{
	private final Log log = LogFactory.getLog(MsgHanderServiceImpl.class);
	private TextMsgService textMsgService;
	private ImageMsgService imageMsgService;
	private EventMsgService eventMsgService;
	private OtherMsgService otherMsgService;
	
	public void resolveWechtMessage(RequestBean requestBean,ResponseBean responseBean) {
		String msgType = requestBean.getMsgType();
		if(msgType.equals(Constants.WECHAT_TEXT_MESSAGE)){//text
			textMsgService.textMsgHander(requestBean, responseBean);
		}else if(msgType.equals(Constants.WECHAT_IMAGE_MESSAGE)){//image
			imageMsgService.imageMsgHander(requestBean, responseBean);
		}else if(msgType.equals("event")){//event subscribe
			eventMsgService.eventMsgHander(requestBean, responseBean);
		}else{//other
			otherMsgService.otherMsgHander(requestBean, responseBean);
		}
		log.info(responseBean);
	}
	
	
	/**set**/
	public void setTextMsgService(TextMsgService textMsgService) {
		this.textMsgService = textMsgService;
	}
	public void setImageMsgService(ImageMsgService imageMsgService) {
		this.imageMsgService = imageMsgService;
	}
	public void setEventMsgService(EventMsgService eventMsgService) {
		this.eventMsgService = eventMsgService;
	}
	public void setOtherMsgService(OtherMsgService otherMsgService) {
		this.otherMsgService = otherMsgService;
	}
	
	
	
}
