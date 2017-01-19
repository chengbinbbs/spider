package com.dayspass.datacenter.bean;

import java.io.Serializable;

public class News implements  Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1240987687761406466L;
	
	private Integer id;
	
	private String title;
	
	private String url;
	
	private String content;
	
	private String author;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}
	
}
