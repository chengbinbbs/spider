package com.dayspass.datacenter.http.util;

import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.security.KeyStore;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.NTCredentials;
import org.apache.http.auth.params.AuthPNames;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.params.AuthPolicy;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dayspass.datacenter.common.util.StringUtils;

/**
 * HttpClient-4.2工具类
 * 
 * @author kouyi
 * @version 2.0
 */
public class HttpClientUtil {
	private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
	// http代理参数
	private static final boolean isproxy = false; // 设置代理开关
	private static final String proxy_IP = "127.0.0.1";
	private static final int proxy_POST = 8088;
	private static final String proxy_USER = "test";
	private static final String proxy_PASSWD = "test";
	private static final String proxy_DOMAIN = "domain";
	// http连接参数
	private static final int proxy_CONNECTTIMEOUT = 30000; // 连接超时毫秒
	private static final int proxy_SOTIMEOUT = 60000; // 读取超时毫秒
	private static final boolean proxy_SO_REUSEADDR = true; // 是否重用地址
	private static final boolean proxy_TCP_NODELAY = true; // 是否禁用nodelay算法
	private static final boolean proxy_STALE_CONNECTION_CHECK = false; // 是否开启陈旧的连接检查
	
	/**
	 * 执行HttpClient GET请求
	 * 
	 * @param url
	 * @return
	 */
	public static String callHttpGet(String url, String charset) {
		return httpExcute(new HttpGet(url), charset);
	}
	
	/**
	 * 执行HttpClient GET请求
	 * header 请求头参数
	 * @param url
	 * @return
	 */
	public static String callHttpGet(String url, String charset, Map<String, String> header) {
		HttpRequestBase base = new HttpGet(url);
		base.setHeader("Host", header.get("Host"));  
		base.setHeader("User-Agent", header.get("User-Agent"));  
		return httpExcute(base,charset);
	}

	/**
	 * 执行HttpClient POST请求
	 * @param url
	 * @param mapParams
	 *            map参数
	 * @return
	 * @throws Exception
	 */
	public static String callHttpPost_Map(String url, String charset, Map<String, String> params) {
		HttpPost post = new HttpPost(url);
		List<NameValuePair> data = null;
		UrlEncodedFormEntity formEntity = null;
		if (params != null) {
			data = new ArrayList<NameValuePair>(params.size());
			for (Entry<String, String> entry : params.entrySet()) {
				data.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			// 对参数编码
			try {
				formEntity = new UrlEncodedFormEntity(data, charset == null ? "UTF-8" : charset);
			} catch (UnsupportedEncodingException e) {
				logger.error("HttpClient：编码POST参数失败,原因["+ e.getLocalizedMessage() + "]");
				return null;
			}
			post.setEntity(formEntity);
		}
		return httpExcute(post,charset);
	}
	
	/**
	 * 执行HttpClient POST请求
	 * @param url
	 * @param mapParams
	 *            map参数
	 * @return
	 * @throws Exception
	 */
	public static String callHttpPost_Map(String url, String charset, Map<String, String> params, Map<String, String> headers) {
		HttpPost post = new HttpPost(url);
		if(!StringUtils.isEmpty(headers)) {
			//设置http头
			for(Map.Entry<String, String> entry : headers.entrySet()) {
				post.setHeader(entry.getKey(), entry.getValue());  
			}  
		}
		List<NameValuePair> data = null;
		UrlEncodedFormEntity formEntity = null;
		if (params != null) {
			data = new ArrayList<NameValuePair>(params.size());
			for (Entry<String, String> entry : params.entrySet()) {
				data.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			// 对参数编码
			try {
				formEntity = new UrlEncodedFormEntity(data, charset == null ? "UTF-8" : charset);
			} catch (UnsupportedEncodingException e) {
				logger.error("HttpClient：编码POST参数失败,原因["+ e.getLocalizedMessage() + "]");
				return null;
			}
			post.setEntity(formEntity);
		}
		return httpExcute(post,charset);
	}
	
	/**
	 * 执行HttpClient POST请求 带证书认证
	 * @param url
	 * @param mapParams
	 *            map参数
	 * @return
	 * @throws Exception
	 */
	public static String callHttpPostSSL_Map(String url, String charset, Map<String, String> params, String cerpath, String cerpwd) {		
		HttpPost post = new HttpPost(url);
		List<NameValuePair> data = null;
		UrlEncodedFormEntity formEntity = null;
		if (params != null) {
			data = new ArrayList<NameValuePair>(params.size());
			for (Entry<String, String> entry : params.entrySet()) {
				data.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
			}
			// 对参数编码
			try {
				formEntity = new UrlEncodedFormEntity(data, charset == null?"utf-8":charset);
			} catch (UnsupportedEncodingException e) {
				logger.error("HttpClient：编码POST参数失败,原因["+ e.getLocalizedMessage() + "]");
				return null;
			}
			post.setEntity(formEntity);
		}
		return httpSSLExcute(post, cerpath, cerpwd);
	}
	
	/**
	 * 执行HttpClient POST请求
	 * 
	 * @param url 
	 * @param body 
	 * 			字符串参数
	 * @param bNeedProxy 是否需要代理
	 * @return 请求返回的串
	 */
	public static String callHttpPost_String(String url, String charset, String body) throws Exception {
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(body, charset == null?"utf-8":charset);
		entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING,charset == null?"utf-8":charset));
		post.setEntity(entity);
		return httpExcute(post, charset);
	}
	
	/**
	 * 执行HttpClient POST请求 带证书认证
	 * 
	 * @param url 
	 * @param body 
	 * 			字符串参数
	 * @param bNeedProxy 是否需要代理
	 * @return 请求返回的串
	 */
	public static String callHttpPostSSL_String(String url, String charset,String body, String cerpath, String cerpwd) throws Exception {
		HttpPost post = new HttpPost(url);
		StringEntity entity = new StringEntity(body, charset == null?"utf-8":charset);
		entity.setContentEncoding(new BasicHeader(HTTP.CONTENT_ENCODING,charset == null?"utf-8":charset));
		post.setEntity(entity);
		return httpSSLExcute(post, cerpath, cerpwd);
	}
	
	
	/**
	 * 调用http
	 * @param base
	 * @return
	 */
	private static String httpExcute(HttpRequestBase base, String charset){
		HttpClient httpClient = getHttpClient();
		return call(httpClient, base, charset);
	}
	
	/**
	 * 调用http 证书方式
	 * @param base
	 * @param cerpath
	 * @param cerpwd
	 * @return
	 */
	private static String httpSSLExcute(HttpRequestBase base, String cerpath, String cerpwd){
		HttpClient httpClient = getHttpClientSSL(cerpath, cerpwd);
		return call(httpClient, base, null);
	}

	/**
	 * HttpClient调用执行 
	 * @param base
	 * @return
	 */
	private static String call(HttpClient httpClient, HttpRequestBase base, String charset) {
		try {
			HttpResponse response = httpClient.execute(base);
			StatusLine httpstatus = response.getStatusLine();
			if (httpstatus.getStatusCode() != HttpStatus.SC_OK) {
				logger.error("HttpClient：调用[" + base.getURI()+ "]失败,状态码[" + httpstatus.getStatusCode() + "]");
				return null;
			}
			HttpEntity resEntity = response.getEntity();
//			Charset cs= ContentType.getOrDefault(resEntity).getCharset();
//			String charSet = cs != null ? cs.name() : "UTF-8"; // 获取字符编码
			String relsult = EntityUtils.toString(resEntity, charset == null ? "UTF-8" : charset);
			EntityUtils.consume(resEntity); // 关闭流
			return relsult;
		} catch (Exception e) {
			logger.error("HttpClient：调用[" + base.getURI() + "]失败,原因["+ e.getLocalizedMessage() + "]");
			return null;
		} finally {
			if (httpClient != null) { // 关闭连接
				httpClient.getConnectionManager().shutdown();
			}
		}
	}

	/**
	 * 创建HttpClient对象_不带ssl验证
	 * @return
	 * 		HttpClient
	 */
	private static HttpClient getHttpClient() {
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance(SSLSocketFactory.SSL);		
	        // 重写取消证书验证
			TrustManager trust = new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
				}
			};
	        ctx.init(null, new TrustManager[]{trust}, null);		
		} catch (Exception e) {
			System.out.println("初始化HttpClient(无需证书验证)异常： [" +e.getMessage()+ "]");
			return null;
		}
		
		SSLSocketFactory socketFactory = new SSLSocketFactory(ctx, SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);
		
		SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("http", 80, PlainSocketFactory.getSocketFactory()));
		schReg.register(new Scheme("https", 443, socketFactory));
		
		DefaultHttpClient httpClient = new DefaultHttpClient(new PoolingClientConnectionManager(schReg));
		
		initParameter(httpClient);
		return httpClient;
	}
	
	/**
	 * 创建HttpClient对象_带证书SSL双向验证
	 * @return
	 * 		HttpClient
	 */
	private static HttpClient getHttpClientSSL(String cerpath, String cerpwd) {
		if(StringUtils.isEmpty(cerpath) || StringUtils.isEmpty(cerpwd)){
			System.out.println("初始化SSL_HttpClient(证书验证)参数cerpath、cerpwd不能为空");
			return null;
		}
		
		SSLContext ctx = null;
		try {
			ctx = SSLContext.getInstance(SSLSocketFactory.SSL);
		
	        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");//客户端私钥
	        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");//服务端授权证书
	
	        KeyStore ks = KeyStore.getInstance(KeyStore.getDefaultType());
	        KeyStore tks = KeyStore.getInstance(KeyStore.getDefaultType());
	
	        ks.load(new FileInputStream(cerpath), cerpwd.toCharArray());
	        tks.load(new FileInputStream(cerpath), cerpwd.toCharArray());
	
	        kmf.init(ks, cerpwd.toCharArray());
	        tmf.init(tks);
	        
	        ctx.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
			
		} catch (Exception e) {
			System.out.println("初始化SSL_HttpClient(证书验证)异常： [" +e.getMessage()+ "]");
			return null;
		}

		SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
        
        SchemeRegistry schReg = new SchemeRegistry();
		schReg.register(new Scheme("https", 443, socketFactory));
		
		DefaultHttpClient httpClient = new DefaultHttpClient(new PoolingClientConnectionManager(schReg));
		
		initParameter(httpClient);
		return httpClient;
	}
	
	/**
	 * httpclient设置代理及参数
	 * @param httpClient
	 * @return
	 */
	private static HttpClient initParameter(DefaultHttpClient httpClient){
		// 处理是否需要代理
		if (isproxy) {
			// 加载代理配置
			HttpHost proxy = new HttpHost(proxy_IP, proxy_POST,HttpHost.DEFAULT_SCHEME_NAME);
			httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY,proxy);
			// NT认证
			NTCredentials ntCredentials = new NTCredentials(proxy_USER,proxy_PASSWD, proxy_IP, proxy_DOMAIN);
			httpClient.getCredentialsProvider().setCredentials(
					new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT,AuthScope.ANY_REALM, AuthScope.ANY_SCHEME),ntCredentials);
			// 认证协议类型，
			List<String> authpref = new ArrayList<String>();
			authpref.add(AuthPolicy.NTLM);
			authpref.add(AuthPolicy.BASIC);
			authpref.add(AuthPolicy.DIGEST);
			httpClient.getParams().setParameter(AuthPNames.PROXY_AUTH_PREF,authpref);
		}
		httpClient.getParams().setParameter(CoreProtocolPNames.PROTOCOL_VERSION, HttpVersion.HTTP_1_1);
		httpClient.getParams().setIntParameter(CoreConnectionPNames.CONNECTION_TIMEOUT, proxy_CONNECTTIMEOUT);
		httpClient.getParams().setIntParameter(CoreConnectionPNames.SO_TIMEOUT, proxy_SOTIMEOUT);
		httpClient.getParams().setBooleanParameter(CoreConnectionPNames.TCP_NODELAY, proxy_TCP_NODELAY);
		httpClient.getParams().setBooleanParameter(CoreConnectionPNames.SO_REUSEADDR, proxy_SO_REUSEADDR);
		httpClient.getParams().setBooleanParameter(CoreConnectionPNames.STALE_CONNECTION_CHECK, proxy_STALE_CONNECTION_CHECK);
		return httpClient;
	}
}
