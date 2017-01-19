package com.dayspass.datacenter.grab.util;

import org.dom4j.Document;

/**
 * 爬虫抓取页面返回对象
 * @author kouyi
 */ 
public class GrabResult  {
	private String url;
	private String content;
	private int statusCode;
	private String remark;
	private Document document;
	public GrabResult(){
		
	}
	public GrabResult(String url, String content, int statusCode){
		this.url = url;
		this.content = content;
		this.statusCode = statusCode;
		
	}
	public GrabResult(String url, Document document, int statusCode){
		this.url = url;
		this.document = document;
		this.statusCode = statusCode;
		
	}
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	public int getStatusCode() {
		return statusCode;
	}
	
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
}
