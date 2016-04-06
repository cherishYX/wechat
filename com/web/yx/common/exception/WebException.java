package com.web.yx.common.exception;

public class WebException extends Exception {
	private static final long serialVersionUID = -1814801127372815084L;
	//*网络连接失败*/
	public static final String NETWORD_CONNECTION_ERROR = "0";
	public static final String READ_FILE_ERROR = "1";
	public WebException(String exceptionCode){
		super(exceptionCode);
	}
}
