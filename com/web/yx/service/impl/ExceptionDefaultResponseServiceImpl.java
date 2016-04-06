package com.web.yx.service.impl;

import com.web.yx.model.RequestBean;
import com.web.yx.model.ResponseBean;
import com.web.yx.service.ExceptionDefaultResponseService;

public class ExceptionDefaultResponseServiceImpl implements ExceptionDefaultResponseService {

	public void exceptionResponse(RequestBean requestBean,ResponseBean responseBean) {
		responseBean.setMsgType("test");
		responseBean.setContent("exception");
	}

}
