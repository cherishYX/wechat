package com.web.yx.servlet.filter;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.web.yx.common.constant.Constants;
import com.web.yx.common.util.SpringUtil;
import com.web.yx.model.RequestBean;
import com.web.yx.model.ResponseBean;
import com.web.yx.service.ExceptionDefaultResponseService;
import com.web.yx.service.MsgHanderService;

public class ReceiveServlet extends HttpServlet{
	private static final long serialVersionUID = -6205986533765232695L;

	final Log log = LogFactory.getLog(ReceiveServlet.class);

	private MsgHanderService msgHanderService;

	public void init() throws ServletException {
		msgHanderService = SpringUtil.getBean("wechatService", MsgHanderService.class);
		super.init();
	}

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		
			String echoStr = req.getParameter("echostr");
			resp.getOutputStream().write(echoStr.getBytes());

	}

	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		RequestBean requestBean = (RequestBean) req.getAttribute(Constants.WECHAT_REQUEST_BEAN);
		ResponseBean responseBean = (ResponseBean)req.getAttribute(Constants.WECHAT_RESPONSE_BEAN);
		
		// 微信消息处理
		msgHanderService.resolveWechtMessage(requestBean,responseBean);
		resp.setCharacterEncoding("UTF-8");
		try{
			resp.getOutputStream().write(responseBean.toXML().getBytes());
		}catch(Exception e){
			log.error("error..."+e);
			ExceptionDefaultResponseService exceptionDefaultResponseService = SpringUtil.getBean("exceptionDefaultResponseService", ExceptionDefaultResponseService.class);
			exceptionDefaultResponseService.exceptionResponse(requestBean, responseBean);
			resp.getOutputStream().write(responseBean.toXML().getBytes());
		}
	}
}
