package com.web.yx.common.util;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;


public class WebServiceUtil {

	public static String getResult() {
		String result = "";
		/*
		 * HttpPost post = new
		 * HttpPost("http://117.25.182.170:118/WebService.asmx/Fun_Query"); try
		 * { HttpEntity entity = new StringEntity(
		 * "strOpercd=fun_bill&strXMLdoc=<record><cuscode>101497</cuscode></record>&sessionid=10732949-A052-4C50-A738-06E380DF420E&dataType=json"
		 * ,ContentType.APPLICATION_FORM_URLENCODED); post.setEntity(entity);
		 * DefaultHttpClient demo = new DefaultHttpClient(); HttpResponse
		 * response = demo.execute(post);
		 * 
		 * String str = EntityUtils.toString(response.getEntity()); SAXReader
		 * saxReader = new SAXReader(); Document document = saxReader.read(new
		 * ByteArrayInputStream(str.getBytes())); Element e =
		 * document.getRootElement(); result = e.getStringValue(); } catch
		 * (Exception e) { e.printStackTrace(); result = "error"; }
		 */
		return result;
	}

	public static void main(String[] args) {
		try {
			System.out.println(getPostResponse("101497"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getPostResponse(String code) {
		try {
		String url = "http://117.25.182.170:118/WebService.asmx/Fun_Query";
		URL postUrl = new URL(url);
		
		HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
		connection.setDoOutput(true);
		connection.setDoInput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);
		connection.setInstanceFollowRedirects(true);
		connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		connection.connect();
		DataOutputStream out = new DataOutputStream(connection.getOutputStream());
		String content = "strOpercd=fun_bill&strXMLdoc=<record><cuscode>%s</cuscode></record>&sessionid=10732949-A052-4C50-A738-06E380DF420E&dataType=json";
		content = String.format(content, code);
		out.writeBytes(content);

		out.flush();
		out.close(); // flush and close
		BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String line;
		StringBuffer sb = new StringBuffer();
		while ((line = reader.readLine()) != null) {
			sb.append(line);
		}
		reader.close();
		connection.disconnect();
		
		SAXReader saxReader = new SAXReader();
		Document document = saxReader.read(new ByteArrayInputStream(sb.toString().getBytes()));
		return document.getStringValue();
		} catch (Exception e) {
			e.printStackTrace();
			return "error";
		}
	}
	
}
