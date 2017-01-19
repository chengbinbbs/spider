package com.dayspass.datacenter.properties.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 读取properties文件
 * @author kouyi
 * @version 1.0  2015-11-04
 *
 */
public class PropertiesUtil {
	private Logger logger = LoggerFactory.getLogger(PropertiesUtil.class);
	private Map<String, String> propmap;
	private static PropertiesUtil leagueinstance;

	
	public PropertiesUtil() {}
	public PropertiesUtil(String fileName) {
		BufferedReader in = null;
		try {
			propmap = new Hashtable<String, String>();
			in = new BufferedReader(new InputStreamReader(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream(fileName), "UTF-8"));  
			Properties p = new Properties();
			p.load(in);
			Set<Object> set = p.keySet();
			for (Object s : set) {
				String value = p.getProperty((String) s);
				propmap.put((String) s, value);
			}
		} catch (Exception e) {
			logger.error("读取" + fileName + "异常", e);
		} finally {
			try {
				if(in != null) {
					in.close();
				}
			} catch (IOException e) {
				logger.error("###关闭流失败###");
			}
		}
	}

	public int getIntValue(String name) {
		if (propmap != null) {
			return Integer.parseInt(propmap.get(name));
		}
		return 0;
	}
	
	public String getStringValue(String name) {
		if (propmap != null) {
			return propmap.get(name);
		}
		return "";
	}
	
	public boolean getBooleanValue(String name) {
		if (propmap != null) {
			return Boolean.parseBoolean(propmap.get(name));
		}
		return true;
	}
	
	public static PropertiesUtil getInstanceLeague(String fileName) {
		if (leagueinstance == null) {
			leagueinstance = new PropertiesUtil(fileName);
		}
		return leagueinstance;
	}

}
