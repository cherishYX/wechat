package com.web.yx.common.util;

import java.net.URL;
import java.net.URLConnection;

import com.web.yx.common.exception.WebException;
/**
 * 获取网络连接
 * @author Administrator
 *
 */
public class HttpClientUtil {
	
	public static URLConnection getConnection(String httpUrl){
		URLConnection conn = null;
		try {
			URL url = new URL(httpUrl);
			conn = url.openConnection();
		} catch (Exception e) {
			throw new RuntimeException(WebException.NETWORD_CONNECTION_ERROR);
		}
		return conn;
	}
}
