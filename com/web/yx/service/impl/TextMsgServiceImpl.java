package com.web.yx.service.impl;

import com.web.yx.model.RequestBean;
import com.web.yx.model.ResponseBean;
import com.web.yx.model.vo.TextRequestBean;
import com.web.yx.service.TextMsgService;

public class TextMsgServiceImpl implements TextMsgService{
	
	
	public void textMsgHander(RequestBean requestBean, ResponseBean responseBean) {
		TextRequestBean textBean = (TextRequestBean)requestBean;
		String inputCur = textBean.getContent().trim();
		
	}
	
}
