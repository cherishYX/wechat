package com.web.yx.common.util;

import java.io.IOException;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.BufferedHttpEntity;
import org.apache.http.impl.DefaultConnectionReuseStrategy;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.DefaultHttpRequestRetryHandler;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.pool.PoolStats;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;




public class HttpClientPool {
	private final Log log = LogFactory.getLog(HttpClientPool.class);

	private static volatile HttpClientPool instance = null;
	
	private PoolingClientConnectionManager cm;
	private DefaultHttpClient httpClient;
	private static int threadIndex = 0;

	public DefaultHttpClient getHttpClient() {
		return httpClient;
	}
	public static synchronized HttpClientPool getInstance(){
		if(instance==null) instance = new HttpClientPool();
		return instance;
	}

	private static final ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(
			50, 50, 5, TimeUnit.MINUTES, new LinkedBlockingQueue<Runnable>(),
			new ThreadFactory() {
				
				@Override
				public Thread newThread(Runnable r) {
					Thread t = new Thread(r);
					t.setName("HttpClientPool-" + threadIndex++);
					t.setDaemon(true);
					return t;
				}
			});

	private HttpClientPool() {
		SchemeRegistry registry = new SchemeRegistry();
		registry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		registry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
		cm = new PoolingClientConnectionManager(registry);
		cm.setDefaultMaxPerRoute(100);
		cm.setMaxTotal(200);
		httpClient = new DefaultHttpClient(cm);
		httpClient.setHttpRequestRetryHandler(new DefaultHttpRequestRetryHandler());
		httpClient.setReuseStrategy(new DefaultConnectionReuseStrategy());
		httpClient.setKeepAliveStrategy(new DefaultConnectionKeepAliveStrategy());
		new IdleConnectionMonitorThread(cm);
	}
	
	public HttpResponse execute(HttpUriRequest request) throws ClientProtocolException, IOException{
		return httpClient.execute(request);
	}
	
	public HttpResponse execute(HttpUriRequest request,HttpContext context) throws ClientProtocolException, IOException{
		return httpClient.execute(request,context);
	}
	
	public Future<HttpResponse> getContent(HttpUriRequest request){
		return EXECUTOR.submit(new Task(request));
	}
	
	public PoolStats getPoolStatus(){
		return this.cm.getTotalStats();
	}
	
	
	class Task implements Callable<HttpResponse>{
		private HttpUriRequest request;
		Task(HttpUriRequest request){
			this.request = request;
		}


		@Override
		public HttpResponse call() throws Exception {
			HttpResponse response = null;
			try {
				 response = httpClient.execute(request);
				 if(log.isDebugEnabled()){
					 log.debug("http header--------:"+Arrays.toString(response.getAllHeaders()));
				 }
				HttpEntity entity = new BufferedHttpEntity(response.getEntity());
				EntityUtils.consume(response.getEntity());
				response.setEntity(entity);
			} catch (ClientProtocolException e) {
				log.error(e);
			} catch (IOException e) {
				log.error(e);
			}
			return response;
		}
		
	}

	  class IdleConnectionMonitorThread extends Thread {
		private final ClientConnectionManager connMgr;
		private volatile boolean shutdown;

		public IdleConnectionMonitorThread(ClientConnectionManager connMgr) {
			super();
			this.setName("idle-connection-monitor");
			this.setDaemon(true);
			this.connMgr = connMgr;
			this.start();
		}

		@Override
		public void run() {
			try {
				while (!shutdown) {
					synchronized (this) {
						wait(5000);
						// Close expired connections
						connMgr.closeExpiredConnections();
						// Optionally, close connections
						// that have been idle longer than 30 sec
						connMgr.closeIdleConnections(60, TimeUnit.SECONDS);
					}
				}
			} catch (InterruptedException ex) {
				// terminate
			}
		}

		public void shutdown() {
			synchronized (this) {
				shutdown = true;
				notifyAll();
			}
		}
	}
}
