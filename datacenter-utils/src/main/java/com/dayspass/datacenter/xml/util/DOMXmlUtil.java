package com.dayspass.datacenter.xml.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * SAX方式解析xml文件
 * @author kouyi
 * @version 1.0 2016-02-19
 * 
 */
public class DOMXmlUtil {
	private static Logger logger = LoggerFactory.getLogger(DOMXmlUtil.class);

	/**
	 * 将xml字符串按照指定标签获取到list-处理BOM头
	 * 
	 * @param xmlString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> fromXml(String xmlString) {
		try {
			if(StringUtils.isBlank(xmlString)) {
				return null;
			}
			byte[] bytes = xmlString.getBytes("UTF-8"); 
			xmlString = new String(bytes, 3, bytes.length-3,"UTF-8"); //解决BOM头解析
			Document docment = DocumentHelper.parseText(xmlString);
			Element ele = docment.getRootElement();
			List<Element> it = ele.elements();
			return it;
		} catch (Exception e) {
			logger.error("dom4j解析异常", e);
		}
		return null;
	}
	
	/**
	 * 将xml字符串按照指定标签获取到list
	 * 
	 * @param xmlString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<Element> fromtoXml(String xmlString) {
		try {
			if(StringUtils.isBlank(xmlString)) {
				return null;
			}
			Document docment = DocumentHelper.parseText(xmlString);
			Element ele = docment.getRootElement();
			List<Element> it = ele.elements();
			return it;
		} catch (Exception e) {
			logger.error("dom4j解析异常", e);
		}
		return null;
	}
}
