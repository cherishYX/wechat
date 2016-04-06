package com.web.yx.model;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.web.yx.model.vo.EventRequestBean;
import com.web.yx.model.vo.ImageRequestBean;
import com.web.yx.model.vo.LinkRequestBean;
import com.web.yx.model.vo.LocationRequestBean;
import com.web.yx.model.vo.TextRequestBean;

public class WechatXmlBeanFactory {
	
	private WechatXmlBeanFactory(){}

	
	/**
	 * 
	 * 通过xml的内容创建实体bean
	 * @param xmlData
	 * @return
	 */
	public static RequestBean createBeanByXml(String xmlData){
		InputStream is = new ByteArrayInputStream(xmlData.getBytes());
		return createBeanByXml(is);
	}
	
	public static RequestBean createBeanByXml(InputStream is){
		RequestBean bean = null;
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(is);
			bean = wireBean(document);
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return bean;
	}
	
	/*public static void main(String[] args) {
		RequestBean bean = createBeanByXml("<xml><ToUserName><![CDATA[gh_e688f1c1525f]]></ToUserName><FromUserName><![CDATA[oiUYLj8G4YxWUwMJXxUPnUfLyfkQ]]></FromUserName><CreateTime>1366349952</CreateTime><MsgType><![CDATA[text]]></MsgType><Content><![CDATA[lili]]></Content><MsgId>5868428358731169878</MsgId></xml>");
		System.out.println(bean);
	}*/
	
	/**
	 * 根据xml document内容装配bean
	 * @param element
	 * @return
	 */
	private static RequestBean wireBean(Document document){
		Element element = document.getRootElement();
		RequestBean bean = null;
		
		String msgType ="";
		@SuppressWarnings("unchecked")
		Iterator<Element> it = element.elementIterator();
		Map<String,String> mapValue = new HashMap<String, String>();
		while(it.hasNext()){
			Element el = it.next();
			mapValue.put(el.getName(), el.getStringValue());
		}
		msgType = mapValue.get("MsgType");
		if("text".equals(msgType)){
			TextRequestBean text = new TextRequestBean();
			text.setContent(mapValue.get("Content"));
			text.setMsgId(Long.valueOf(mapValue.get("MsgId")));
			bean = text;
		}else if("image".equals(msgType)){
			ImageRequestBean image= new ImageRequestBean();
			image.setPicUrl(mapValue.get("PicUrl"));
			image.setMsgId(Long.valueOf(mapValue.get("MsgId")));
			bean = image;
		}else if("location".equals(msgType)){
			LocationRequestBean location = new LocationRequestBean();
			location.setMsgId(Long.valueOf(mapValue.get("MsgId")));
			location.setLabel(mapValue.get("Label"));
			location.setLocation_X(Double.valueOf(mapValue.get("Location_X")));
			location.setLocation_Y(Double.valueOf(mapValue.get("Location_Y")));
			bean = location;
		}else if("link".equals(msgType)){
			LinkRequestBean link = new LinkRequestBean();
			link.setMsgId(Long.valueOf(mapValue.get("MsgId")));
			link.setTitle(mapValue.get("Title"));
			link.setUrl(mapValue.get("Url"));
			bean = link;
		}else if("event".equals(msgType)){
			EventRequestBean event = new EventRequestBean();
			event.setEvent(mapValue.get("Event"));
			event.setEventKey(mapValue.get("EventKey"));
			bean = event;
		}else{
			bean = new RequestBean();
		}
		bean.setCreateTime(Long.valueOf(mapValue.get("CreateTime")));
		bean.setFromUserName(mapValue.get("FromUserName"));
		bean.setMsgType(msgType);
		bean.setToUserName(mapValue.get("ToUserName"));
		
//		Iterator<Element> it = element.elementIterator();
//		Class clazz = bean.getClass();
//		Method[] methods=clazz.getMethods();
//		while(it.hasNext()){
//			Element el = it.next();
//			clazz.getm
//		}
		
		return bean;
		
		
	}
}
