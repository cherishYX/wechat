package com.web.yx.common.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * MD5加密处理类
 * @author YanXiang
 *
 */
public class MD5Util {
	private static Log log = LogFactory.getLog(MD5Util.class);
	private static MessageDigest messageDigest = null;
	static{
		try {
			messageDigest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			log.error("MD5Util:",e);
		}
	}
	
	/**
	 * MD5
	 * @param entry
	 * @return
	 */
	public static String getMD5Value(String entry) {
		messageDigest.update(entry.getBytes());// update处理
		byte[] encryContext = messageDigest.digest();// 调用该方法完成计算
		int i;
		StringBuffer buf = new StringBuffer("");
		for (int offset = 0; offset < encryContext.length; offset++) {// 做相应的转化（十六进制）
			i = encryContext[offset];
			if (i < 0)
				i += 256;
			if (i < 16)
				buf.append("0");
			buf.append(Integer.toHexString(i));
		}
		return buf.toString();
	}
}
