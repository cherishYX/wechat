package com.web.yx.common.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class FileUtil {
	private String compilerPath ;
	private final static String FILE_NAME = "user-status.properties";
	private final static String ENTER_SPLIT_CHAR = System.getProperty("line.separator", "/n");
//	private final static String EQUAL_SPLIT_CHAR = ;
	{
		compilerPath = getClass().getClassLoader().getResource("").getPath();
	}
	/**获取用户输入状态*/
	public synchronized String getCurrentStatusByWechatId(String wechatId){
		Properties preperties = new Properties();
		File file = new File(compilerPath+FILE_NAME);
		try {
			preperties.load(new FileInputStream(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return preperties.getProperty(wechatId);
	}
	
	/**状态写入文件*/
	public synchronized void writeUserStatus(String wechatId,String userStatus){
		String newLine = wechatId+"="+userStatus+ENTER_SPLIT_CHAR;
		FileWriter writer = null;
		try {
			writer = new FileWriter(compilerPath+FILE_NAME, true);
			writer.write(newLine);
			writer.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(writer!=null){
				try {
					writer.close();
				} catch (IOException e) {
				}
			}
		}
	}
	/**删除个人状态*/
	public synchronized void deleteUserStatus(String wechatId){
		Properties preperties = new Properties();
		File file = new File(compilerPath+FILE_NAME);
		try {
			preperties.load(new FileInputStream(file));
			preperties.remove(wechatId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		FileUtil util = new FileUtil();
		util.writeUserStatus("78479","qw6er");
		System.out.println(new FileUtil().getCurrentStatusByWechatId("78479"));
		util.writeUserStatus("123","54");
		util.deleteUserStatus("123");
		System.out.println(new FileUtil().getCurrentStatusByWechatId("123"));
	}
}
