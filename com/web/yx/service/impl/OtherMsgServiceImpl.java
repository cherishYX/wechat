package com.web.yx.service.impl;

import com.web.yx.common.constant.Constants;
import com.web.yx.model.RequestBean;
import com.web.yx.model.ResponseBean;
import com.web.yx.service.OtherMsgService;

public class OtherMsgServiceImpl implements OtherMsgService {

	public void otherMsgHander(RequestBean requestBean,ResponseBean responseBean) {
		responseBean.setMsgType(Constants.WECHAT_TEXT_MESSAGE);
		responseBean.setContent("other");
	}

}
