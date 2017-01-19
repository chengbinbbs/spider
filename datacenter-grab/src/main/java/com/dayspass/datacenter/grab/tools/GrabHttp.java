package com.dayspass.datacenter.grab.tools;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.dom4j.Document;
import org.dom4j.io.SAXReader;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dayspass.datacenter.properties.util.PropertiesUtil;

/**
 * httpclient执行抓取动作
 * @author kouyi
 */
public class GrabHttp {
	private static final Logger logger = LoggerFactory.getLogger(GrabHttp.class);
	private DefaultHttpClient client;
	private static PropertiesUtil config = null;
	static {
		config = new PropertiesUtil("graburl.properties");
	}

	/**
	 * 创建HttpClient实例，并初始化连接参数
	 */
	public GrabHttp() {
		client = (DefaultHttpClient) GrabHttpManager.getHttpClient();
	}

	/**
	 * 根据url爬取网页内容-常规
	 * 
	 * @param url
	 * @return
	 */
	public GrabResult getContentFromUrl(String url,String charset) {
		String content = null;
		int statusCode = 500;
		// 创建Get请求，并设置Header
		HttpGet getHttp = new HttpGet(url);
		getHttp.addHeader("Accept-Charset", "GBK,utf-8");
		getHttp.addHeader("Accept", "*/*");
		getHttp.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		getHttp.addHeader("Cookie", "");
		getHttp.addHeader("Host", config.getStringValue("host"));
		getHttp.addHeader("Referer", config.getStringValue("host"));
		getHttp.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
		HttpResponse response;
		try {
			// 获得信息载体Oa
			response = client.execute(getHttp);
			statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 转化为文本信息, 设置爬取网页的字符集，防止乱码
				content = EntityUtils.toString(entity, charset);
			}
		} catch (Exception e) {
			logger.error("getContentFromUrl抓取异常{}", url, e);
		} finally {
			getHttp.releaseConnection();
		}
		return new GrabResult(url, content, statusCode);
	}
	
	/**
	 * 根据url爬取网页内容-Vip
	 * 
	 * @param url
	 * @return
	 */
	public GrabResult getContentFromUrlVip(String url,String charset) {
		String content = null;
		int statusCode = 500;
		// 创建Get请求，并设置Header
		HttpGet getHttp = new HttpGet(url);
		getHttp.addHeader("Accept-Charset", "GBK,utf-8");
		getHttp.addHeader("Accept", "*/*");
		getHttp.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		getHttp.addHeader("Cookie", "");
		getHttp.addHeader("Host", config.getStringValue("host2"));
		getHttp.addHeader("Referer", config.getStringValue("host2"));
		getHttp.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
		HttpResponse response;
		try {
			// 获得信息载体Oa
			response = client.execute(getHttp);
			statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 转化为文本信息, 设置爬取网页的字符集，防止乱码
				content = EntityUtils.toString(entity, charset);
			}
		} catch (Exception e) {
			logger.error("getContentFromUrl抓取异常{}", url, e);
		} finally {
			getHttp.releaseConnection();
		}
		return new GrabResult(url, content, statusCode);
	}
	
	/**
	 * 根据url爬取网页内容-Zq
	 * 
	 * @param url
	 * @return
	 */
	public GrabResult getContentFromUrlZq(String url,String charset) {
		String content = null;
		int statusCode = 500;
		// 创建Get请求，并设置Header
		HttpGet getHttp = new HttpGet(url);
		getHttp.addHeader("Accept-Charset", "GBK,utf-8");
		getHttp.addHeader("Accept", "*/*");
		getHttp.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		getHttp.addHeader("Cookie", "");
		getHttp.addHeader("Host", config.getStringValue("host3"));
		getHttp.addHeader("Referer", config.getStringValue("host3"));
		getHttp.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
		HttpResponse response;
		try {
			// 获得信息载体Oa
			response = client.execute(getHttp);
			statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				// 转化为文本信息, 设置爬取网页的字符集，防止乱码
				content = EntityUtils.toString(entity, charset);
			}
		} catch (Exception e) {
			logger.error("getContentFromUrl抓取异常{}", url, e);
		} finally {
			getHttp.releaseConnection();
		}
		return new GrabResult(url, content, statusCode);
	}

	/**
	 * 根据url爬取网页内容-参数查询
	 * 
	 * @param url
	 * @return
	 */
	public GrabResult getContentFromUrlByVerify(String url, List<NameValuePair> params) {
		String content = null;
		int statusCode = 500;
		HttpPost postHttp = new HttpPost(url);
		postHttp.addHeader("Accept-Charset", "GBK,utf-8");
		postHttp.addHeader("Accept", "*/*");
		postHttp.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		postHttp.addHeader("Cookie", "");
		postHttp.addHeader("Host", config.getStringValue("host"));
		postHttp.addHeader("Referer", config.getStringValue("host"));
		postHttp.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
		HttpResponse response;
		try {
			response = client.execute(postHttp);
			HttpEntity entity = response.getEntity();
			if (entity != null) {
				content = EntityUtils.toString(entity, "UTF-8");
			}
			org.jsoup.nodes.Document doc = Jsoup.parse(content);
			params.add(new BasicNameValuePair("__VIEWSTATE", doc.select("input[name=__VIEWSTATE]").val()));
			params.add(new BasicNameValuePair("__EVENTVALIDATION", doc.select("input[name=__EVENTVALIDATION]").val()));
			postHttp.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
			response = client.execute(postHttp);
			statusCode = response.getStatusLine().getStatusCode();
			entity = response.getEntity();
			if (entity != null) {
				content = EntityUtils.toString(entity, "UTF-8");
			}
		} catch (Exception e) {
			// 因请求超时等问题产生的异常
			logger.error("getContentFromUrlByVerify抓取异常{}", url, e);
		} finally {
			postHttp.releaseConnection();
		}

		return new GrabResult(url, content, statusCode);
	}

	/**
	 * 根据url爬取网页内容-返回sax解析document
	 * 
	 * @param url
	 * @return
	 */
	public GrabResult getDocumentFromUrl(String url,String charset) {
		int statusCode = 500;
		// 创建Get请求，并设置Header
		HttpGet getHttp = new HttpGet(url);
		getHttp.addHeader("Accept-Charset", "GBK,utf-8");
		getHttp.addHeader("Accept", "*/*");
		getHttp.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
		getHttp.addHeader("Cookie", "");
		getHttp.addHeader("Host", config.getStringValue("host"));
		getHttp.addHeader("Referer", config.getStringValue("host"));
		getHttp.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:49.0) Gecko/20100101 Firefox/49.0");
		HttpResponse response;
		Document document = null;
		try {
			// 获得信息载体
			response = client.execute(getHttp);
			statusCode = response.getStatusLine().getStatusCode();
			HttpEntity entity = response.getEntity();
			SAXReader saxreader = new SAXReader();
			if(charset!=null){
				InputStream is = entity.getContent();
				Reader reader = new BufferedReader(new InputStreamReader(is,charset));
				document = saxreader.read(reader);
			}else{
				document = saxreader.read(entity.getContent());
			}
		} catch (Exception e) {
			// 因请求超时等问题产生的异常
			logger.error("getDocumentFromUrl抓取异常{}", url, e);
		} finally {
			getHttp.releaseConnection();
		}
		return new GrabResult(url, document, statusCode);
	}
}
