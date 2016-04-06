package com.web.yx.common.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Encrytor {

	private static MessageDigest md5 =null; 
	
	private static MessageDigest sha1 =null; 
	
	static{
		try {
			md5 = MessageDigest.getInstance("MD5");
			sha1 = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}
	
	public static byte[] encrytByMD5(String src){
		return md5.digest(src.getBytes());
	}
	
	public static String encrytHexBySHA1(String...src){
		StringBuffer sb = new StringBuffer();
		for(String s : src) sb.append(s);
		return new BigInteger(1,sha1.digest( sb.toString().getBytes())).toString(16);
	}
	public static void main(String[] args) {
		System.out.println(encrytHexBySHA1("chenr0ngm00n"));
	}
}
	
