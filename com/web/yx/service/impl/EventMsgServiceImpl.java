package com.web.yx.service.impl;

import com.web.yx.common.constant.Constants;
import com.web.yx.model.RequestBean;
import com.web.yx.model.ResponseBean;
import com.web.yx.model.vo.EventRequestBean;
import com.web.yx.service.EventMsgService;

public class EventMsgServiceImpl implements EventMsgService {
	public void eventMsgHander(RequestBean requestBean,ResponseBean responseBean) {
		//加关注
		EventRequestBean event = (EventRequestBean)requestBean;
		String eventType = event.getEvent();
		String wechatid = event.getFromUserName();
		responseBean.setMsgType(Constants.WECHAT_TEXT_MESSAGE);
		if(eventType.equals(Constants.EVENT_TYPE_SUBSCRIBE)){
			// 关注自动回复
			/*String content = mainMenuService.getAllMenusDesc(Constants.PARENT_ID);
			responseBean.setContent(content);*/
		}else if(eventType.equals(Constants.EVENT_TYPE_UNSUBSCRIBE)){
			responseBean.setContent("取消关注");
		}
	}
	
}

