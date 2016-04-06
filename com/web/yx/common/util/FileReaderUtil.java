package com.web.yx.common.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.web.yx.common.exception.WebException;

public class FileReaderUtil {
	private static Properties property ;
	private final static String FILE_NAME = "weather.txt";
	private static Log log = LogFactory.getLog(FileReaderUtil.class);
	
	
	public static String getCityCodeByCityName(String cityName){
		if(property==null){
			initProperty();
		}
		Object obj = property.get(cityName);
		if(obj!=null){
			return (String)obj;
		}else{
			return null;
		}
	}
	
	
	public static void initProperty(){
		property = new Properties();
		File file = new File(FILE_NAME);
		BufferedReader reader = null;
		try {
			try{
				reader = new BufferedReader(new FileReader(file));
				log.info("reader:"+reader);
			}catch(IOException ee){
				log.info("exception:"+reader);
				URL u = Thread.currentThread().getContextClassLoader().getResource("/");
				String path = u.toString();
				log.info("path:"+path);
				reader = new BufferedReader(new FileReader(new File(path+FILE_NAME)));
				log.info("reader2:"+reader);
			}
			String line = null;
			while((line = reader.readLine()) != null){
				String[] lineArr = line.split("=");
				property.put(lineArr[1], lineArr[0]);
			}
		} catch (Exception e) {
			throw new RuntimeException(WebException.READ_FILE_ERROR);
		} finally{
			if(reader != null){
				try {
					reader.close();
				} catch (IOException e) {
				}
			}
		}
	}
	public static void main(String[] args) {
		/*File file = new File("weather.txt");
		System.out.println(file.exists());
		if(!file.exists()){
			try {
				System.out.println(file.createNewFile());
				System.out.println(file.exists());
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}*/
		
		System.out.println(getCityCodeByCityName("北京"));
	}
}
