package com.web.yx.common.util;

import java.io.IOException;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

@SuppressWarnings("restriction")
public class EncrytorBase {
    
    private static final Log logger = LogFactory.getLog( EncrytorBase.class);
    
	private static MessageDigest md5 = null;

	private static MessageDigest sha1 = null;
	
	private static byte[] PRIVATE_KEY = "apexsoft".getBytes();
	
	private static byte[] IMAGE_API_PRIVATE_KEY = "apexsoftpwd".getBytes();
	
	static BASE64Encoder encoder =  new BASE64Encoder();
	static BASE64Decoder decoder = new BASE64Decoder();

	private static Cipher encrytor = null;
	private static Cipher decrytor = null;
	
	private static Cipher image_encrytor = null;
	private static Cipher image_decrytor = null;
	static {
		try {
			SecureRandom sr = new SecureRandom();
			DESKeySpec dks = new DESKeySpec(PRIVATE_KEY);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey key = keyFactory.generateSecret(dks);
			encrytor = Cipher.getInstance("DES");
			encrytor.init(Cipher.ENCRYPT_MODE, key, sr);
			
			decrytor= Cipher.getInstance("DES");
			decrytor.init(Cipher.DECRYPT_MODE, key, sr);
			
			image_encrytor = Cipher.getInstance("DES");
			image_encrytor.init(Cipher.ENCRYPT_MODE, keyFactory.generateSecret(new DESKeySpec(IMAGE_API_PRIVATE_KEY)),sr);
			image_decrytor = Cipher.getInstance("DES");
			image_decrytor.init(Cipher.DECRYPT_MODE, keyFactory.generateSecret(new DESKeySpec(IMAGE_API_PRIVATE_KEY)),sr);	
			
			md5 = MessageDigest.getInstance("MD5");
			sha1 = MessageDigest.getInstance("SHA-1");
		} catch (NoSuchAlgorithmException e) {
			logger.error ("Encrytor error",e);
			
		} catch (InvalidKeyException e) {
			logger.error ("Encrytor error",e);
		} catch (NoSuchPaddingException e) {
			logger.error ("Encrytor error",e);
		} catch (InvalidKeySpecException e) {
			logger.error ("Encrytor error",e);
		}
	}

	public static byte[] encrytByMD5(String src) {
		return md5.digest(src.getBytes());
	}

	public static String encrytHexBySHA1(String... src) {
		StringBuffer sb = new StringBuffer();
		for (String s : src)
			sb.append(s);
		return new BigInteger(1, sha1.digest(sb.toString().getBytes()))
				.toString(16);
	}
	public static String encrytFormemcache(String  src) {
	        return new BigInteger(1, sha1.digest( encrytByMD5(src)))
	                .toString(16);
	}
	
	public static String imgAPIEncryt(String word) {
		try {
			byte[] data = image_encrytor.doFinal(word.getBytes());
			return encoder.encode(data);
		} catch (IllegalBlockSizeException e) {
			logger.error ("Encrytor error",e);
		} catch (BadPaddingException e) {
			logger.error ("Encrytor error",e);
		}
		return null;
	}
	
	public static String imgAPIDecryt(String word){
		try {
			byte[]data = decoder.decodeBuffer(word);
			return new String(image_decrytor.doFinal(data));
		} catch (IOException e) {
			logger.error ("Encrytor error",e);
		} catch (IllegalBlockSizeException e) {
			logger.error ("Encrytor error",e);
		} catch (BadPaddingException e) {
			logger.error ("Encrytor error",e);
		}
		return null;
	}
	
	/**
	 * 解密方法
	 * @throws IOException 
	 */
	
	public static String decrypt(String encryptedData) throws  BadPaddingException, IllegalBlockSizeException, IOException{
		byte decryptedData[] = decrytor.doFinal(fromUrlStr(encryptedData));
		return new String(decryptedData);
	}
	
	/**
	 * 加密方法
	 */
	public static String encrypt(String str) throws IllegalBlockSizeException, BadPaddingException{
		// 现在，获取数据并加密
		byte data[] = str.getBytes();
		// 正式执行加密操作
		byte[] encryptedData = encrytor.doFinal(data);
		return toUrlStr(encryptedData);
	}
	public static void main(String[] args) throws Exception {
		System.out.println(encrypt(("12345678,1,"+System.currentTimeMillis())));
	}
	
	/**	
	 * base64处理,替换特殊字符
	 * @author: yanxiang
	 * @time: 2013-8-9 上午10:05:00
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static String toUrlStr(byte[] bytes) {

		String str = new sun.misc.BASE64Encoder().encode(bytes);

		if (str == null)
			return "";
		
		char[] orginial = str.toCharArray();

		for(int i=0;i<orginial.length;i++){
			char c = orginial[i];
			switch (c) {
			case '+': orginial[i]='_'; continue;
			case '/': orginial[i]='-'; continue;
			case '=': orginial[i]='.'; continue;
			default:;
			}
		}

		return new String(orginial);
	}

	/**
	 * 是toUrlStr逆过程
	 * 
	 * @author: yanxiang
	 * @time: 2013-8-9 上午10:05:00
	 * @param str
	 * @return
	 * @throws IOException
	 */
	public static byte[] fromUrlStr(String str) throws IOException {

		if (str == null)
			return null;
		
		char[] replace = str.toCharArray();
		for(int i=0;i<replace.length;i++){
			char c = replace[i];
			switch (c) {
			case '_': replace[i]='+'; continue;
			case '-': replace[i]='/'; continue;
			case '.': replace[i]='='; continue;
			default:;
			}
		}

		byte[] dec = new sun.misc.BASE64Decoder().decodeBuffer(new String(replace));

		return dec;
	}
	
}
