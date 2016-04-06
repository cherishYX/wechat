package com.web.yx.spliter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.web.yx.common.util.NetUtil;
/**
 * 网页中解析出邮箱地址
 * @author YanXiang
 *
 */
public class ParseEmailAddressUtil {
	/*public static void main(String[] args) {
		try {
			Set<String> emails = new ParseEmailAddress("http://zhidao.baidu.com/question/69131130.html").call();
			for(String e:emails){
				System.out.println(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/
	/**
	 * 解析
	 */
	public static Set<String> parseEmailAddress(String url){
		return new ParseEmailAddress(url).call();
	}
}

class ParseEmailAddress implements Callable<Set<String>>{
	private String viaUrl ;
	private final String EMAIL_PATTERN = "\\w+(@+\\w+)+\\.\\w+";
	private Log log = LogFactory.getLog(ParseEmailAddress.class);
	public ParseEmailAddress(String viaUrl){
		this.viaUrl = viaUrl;
	}
	public Set<String> call() {
		Set<String> emails = new HashSet<String>();
		String charset = NetUtil.getNetCharset(viaUrl);
		InputStream in = null;
		BufferedReader br = null;
		try{
			URL url = new URL(viaUrl);
			in = url.openStream();
			br = new BufferedReader(new InputStreamReader(in,charset));
			String line = null;
			Pattern pattern = Pattern.compile(EMAIL_PATTERN);
			while((line = br.readLine()) != null){
				Matcher matcher = pattern.matcher(line);
				while(matcher.find()){
					emails.add(matcher.group());
				}
			}
		}catch(Exception e){
			log.error("ParseEmailAddress.call:",e);
		}finally{
			try {
				if(br!=null){
					br.close();
				}
				if(in!=null){
					in.close();
				}
			} catch (IOException e) {
				log.error(e);
			}
		}
		return emails;
	}
	
}