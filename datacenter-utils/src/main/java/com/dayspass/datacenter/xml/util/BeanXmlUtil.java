package com.dayspass.datacenter.xml.util;

import java.io.ByteArrayInputStream;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 对象与xml文件转换工具类
 * @author kouyi
 * @version 1.0 2015-11-04
 */
public class BeanXmlUtil {
	
	private static Logger logger = LoggerFactory.getLogger(BeanXmlUtil.class);

	/**
	 * 将xml字符串转换为对象
	 * 
	 * @param xmlString
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromXml(String xmlString, Class<T> obj) {
		T t = null;
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(obj);
			Unmarshaller uns = jaxbContext.createUnmarshaller();
			t = (T) uns.unmarshal(new ByteArrayInputStream(xmlString.getBytes("UTF-8")));
		} catch (Exception e) {
			logger.error("fail to fromXML：" + e.getMessage());
		}
		return t;
	}

	/**
	 * 将对象转换为xml字符串
	 * 
	 * @param obj
	 * @return
	 */
	public static <T> String toXML(T obj) {
		JAXBContext context;
		try {
			context = JAXBContext.newInstance(obj.getClass());
			Marshaller ms = context.createMarshaller();
			StringWriter sw = new StringWriter();
			ms.marshal(obj, sw);
			return sw.toString();
		} catch (JAXBException e) {
			logger.error("fail to fromXML：" + e.getMessage());
		}
		return null;
	}
}