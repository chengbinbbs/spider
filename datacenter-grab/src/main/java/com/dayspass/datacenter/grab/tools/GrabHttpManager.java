package com.dayspass.datacenter.grab.tools;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.HttpParams;

/**
 * httpclient抓取连接池管理
 * @author kouyi
 */
public class GrabHttpManager {
	private static HttpParams httpParams;
	private static PoolingClientConnectionManager cm;
	//最大连接数
	public final static int MAX_TOTAL_CONNECTIONS = 1000;
	//获取连接的最大等待时间
	public final static int WAIT_TIMEOUT = 60000;
	//每个路由最大连接数
	public final static int MAX_ROUTE_CONNECTIONS = 100;
	//连接超时时间
	public final static int CONNECT_TIMEOUT = 10000;
	//读取超时时间
	public final static int READ_TIMEOUT = 20000;

	static {
		SchemeRegistry schemeRegistry = new SchemeRegistry();
		schemeRegistry.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		schemeRegistry.register(new Scheme("https", 443, SSLSocketFactory.getSocketFactory()));
		cm = new PoolingClientConnectionManager(schemeRegistry);
		cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
		cm.setDefaultMaxPerRoute(MAX_ROUTE_CONNECTIONS);
		httpParams = new BasicHttpParams();
		httpParams.setParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, CONNECT_TIMEOUT);
		httpParams.setParameter(CoreConnectionPNames.SO_TIMEOUT, READ_TIMEOUT);
	}

	public synchronized static HttpClient getHttpClient() {
		return new DefaultHttpClient(cm, httpParams);
	}

}