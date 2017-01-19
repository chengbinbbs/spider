package com.dayspass.datacenter.interf.base;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dayspass.datacenter.interf.bean.ResultBean;
import com.dayspass.datacenter.json.util.JsonUtil;


/**
 * @user kouyi
 * @date 2016-12-05
 */
public class BaseController {

	/**
	 * 将处理后的结果返回给客户端
	 * @author kouyi
	 * @param 
	 */
	protected void writeJson_response(int code, HttpServletResponse response) {
		ResultBean result = new ResultBean();
		result.setCode(code);
		Map<String, String> columns = new HashMap<>();
		columns.put("data", "data");//只显示状态码
		writeJson_response(result, columns, response);
	}
	
	/**
	 * 将处理后的结果返回给客户端
	 * @author kouyi
	 * @param 
	 */
	protected void writeJson_response(ResultBean bean, Map<String, String> columns, HttpServletResponse response) {
		DataOutputStream out = null;
		try {
			//设置页面不缓存
			response.setHeader("Pragma", "No-cache");
			response.setHeader("Cache-Control", "no-cache");
			response.addHeader("Access-Control-Allow-Origin", "http://www.tuiqiuxiong.com");
			response.setHeader("Access-Control-Allow-Origin", "*");
		    response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		    response.setHeader("Access-Control-Max-Age", "3600");
		    response.setHeader("Access-Control-Allow-Headers", "x-requested-with");
			response.setStatus(200);
			response.setContentType("text/json; charset=UTF-8");
			response.setCharacterEncoding("UTF-8");
			out = new DataOutputStream(response.getOutputStream());
			StringBuffer buffer = new StringBuffer();
			buffer.append(JsonUtil.JsonObject(bean, columns));
			out.write((new String(buffer)).getBytes("UTF-8"));
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (out != null) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/** 
     * 获取客户端ip 
     * @param request 
     * @return 
     */  
    public String getRequestIP(HttpServletRequest request) {  
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				// 根据网卡取本机配置的IP
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}
		// 多个代理时第一个IP为客户端真实IP
		if (ipAddress != null && ipAddress.length() > 15) { // "***.***.***.***".length()
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}
}