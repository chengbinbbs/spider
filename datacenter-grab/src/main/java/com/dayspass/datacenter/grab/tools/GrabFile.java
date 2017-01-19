package com.dayspass.datacenter.grab.tools;

import java.io.FileOutputStream;
import java.io.InputStream;

import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 文件抓取
 * @author kouyi
 */
public class GrabFile {
	private static Logger logger = LoggerFactory.getLogger(GrabFile.class);
	
	public static boolean download(String url, String fileName) {  
        HttpClient httpClient = new DefaultHttpClient();  
        HttpGet get = new HttpGet(url); 
        get.addHeader("Accept-Charset", "utf-8");  
        get.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        get.addHeader("Accept-Language", "zh-CN,zh"); 
        get.addHeader("Cookie","");
        get.addHeader("referer", "data2.7m.cn");
        get.addHeader("User-Agent", "Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.22 (KHTML, like Gecko) Chrome/25.0.1364.160 Safari/537.22");
        try {  
        	HttpResponse response = httpClient.execute(get);  
        	StatusLine statusLine = response.getStatusLine();
        	if(statusLine.getStatusCode() == 200){
        		  FileOutputStream outputStream = new FileOutputStream(fileName);
        		  InputStream inputStream =response.getEntity().getContent();
        		  byte b[] = new byte[2048];  
                  int j = 0;
                  while ((j = inputStream.read(b)) != -1) {  
                	  outputStream.write(b, 0, j);  
                  }
                  outputStream.flush();  
                  outputStream.close(); 
                  return true;
        	}
        } catch (Exception e) {  
            logger.error("抓取文件异常,URL:{}",url,e);
        } finally {  
            httpClient.getConnectionManager().shutdown();  
        }  
        return false;
    }  
}
