package com.web.yx.common;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class AppConfig {
	private static Properties properties = new Properties();

	/*static{
		try {
			String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
			InputStream in = new FileInputStream(path);
			properties.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}*/
	
	
	public static String getProperty(String key){
		return properties.getProperty(key);
	}
}
