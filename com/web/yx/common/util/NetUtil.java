package com.web.yx.common.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class NetUtil {
	private static final Log log = LogFactory.getLog(NetUtil.class);
	/*public static void main(String[] args) {
		System.out.println(getNetCharset("http://www.baidu.com"));
		System.out.println(getNetCharset("http://www.tucoo.com/"));
	}*/
	
	public static String getNetCharset(String url){
		String charset = "utf-8";
		try {
			URL uri = new URL(url);
			BufferedReader br = new BufferedReader(new InputStreamReader(uri.openStream()));
			String line = "";
			while((line=br.readLine()) != null){
				if(line.contains("charset")){
					charset = getEnc(line);
					break;
				}
			}
		} catch (Exception e) {
			log.error("getNetCharset:",e);
		}
		return charset;
	}

	private static String getEnc(String line) {// 根据正则匹配得到页面编码
		String enC = "utf-8";
		Pattern p = Pattern.compile("(charset|Charset|CHARSET)\\s*=\\s*\"?\\s*([-\\w]*?)[^-\\w]");
		Matcher m = p.matcher(line);
		if (m.find()) {
			enC = m.group(2);
		}
		return enC;
	}
	/**
	 * 截取网页编码
	 * @param line
	 * @return
	 */
	private static String parseCharset(String line){
		String charset = "utf-8";
		try{
			int start = line.indexOf("charset=")+8;
			String partString = line.substring(start, line.length());
			int end = partString.indexOf("\"");
			charset = partString.substring(0,end);
		}catch(Exception e){
			log.error("parseCharset:",e);
		}
		return charset;
	}
}
