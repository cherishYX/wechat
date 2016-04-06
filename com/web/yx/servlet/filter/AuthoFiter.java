package com.web.yx.servlet.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSON;
import com.web.yx.common.constant.Constants;
import com.web.yx.common.util.Encrytor;
import com.web.yx.model.RequestBean;
import com.web.yx.model.ResponseBean;
import com.web.yx.model.WechatXmlBeanFactory;



public class AuthoFiter implements Filter {
	private final Log log = LogFactory.getLog(AuthoFiter.class);
	private String token;

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		
		if (checkSignature(req)) {
			String xml = getPostXMLData(req);
			String method = req.getMethod();
			if (method.equalsIgnoreCase("POST") && xml != null && !xml.equals("")) {
				RequestBean requestBean = WechatXmlBeanFactory.createBeanByXml(xml);
				log.info("post method..." + requestBean);
				
				ResponseBean responseBean = new ResponseBean();
				responseBean.setCreateTime(requestBean.getCreateTime());
				responseBean.setFromUserName(requestBean.getToUserName());
				responseBean.setToUserName(requestBean.getFromUserName());
System.out.println(JSON.toJSONString(requestBean));
				request.setAttribute(Constants.WECHAT_REQUEST_BEAN, requestBean);
				request.setAttribute(Constants.WECHAT_RESPONSE_BEAN, responseBean);
			}
			chain.doFilter(request, response);
		} else {

		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		token = Constants.token;
	}

	private String getPostXMLData(HttpServletRequest req) {
		BufferedReader br = null;
		StringBuffer xmlData = new StringBuffer();
		;
		try {
			String data = "";
			br = req.getReader();
			while ((data = br.readLine()) != null) {
				xmlData.append(data);
			}
		} catch (Exception e) {
			log.error("read post data error", e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
				}
		}
		return xmlData.toString();
	}

	/**
	 * 判断是否来自微信服务器
	 * 
	 * @param req
	 * @return
	 */
	private boolean checkSignature(HttpServletRequest req) {
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String arr[] = new String[] { timestamp, nonce, token };
		Arrays.sort(arr);
		String encryt = Encrytor.encrytHexBySHA1(arr);
		return encryt.equals(signature);
	}
	/*public static void main(String[] args) {
		EventRequestBean event = new EventRequestBean();
		event.setCreateTime(System.currentTimeMillis());
		event.setEvent("subscribe");
		event.setEventKey("");
		event.setFromUserName("yanxiang");
		event.setToUserName("jinwanchishenme");
		event.setMsgType("event");
		System.out.println(JSONObject.toJSONString(event));
	}*/
}
