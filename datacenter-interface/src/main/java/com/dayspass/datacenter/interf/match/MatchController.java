package com.dayspass.datacenter.interf.match;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dayspass.datacenter.commons.QueryBean;
import com.dayspass.datacenter.domain.Match;
import com.dayspass.datacenter.error.code.ErrorCode;
import com.dayspass.datacenter.interf.base.BaseController;
import com.dayspass.datacenter.interf.bean.ResultBean;
import com.dayspass.datacenter.interf.ecode.ErrorCode_Interface;
import com.dayspass.datacenter.service.match.MatchService;

/**
 * 对阵相关接口
 * @author kouyi
 * @date 2016-12-05
 */
@Controller("matchController")
public class MatchController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(MatchController.class);
	private static Map<String, String> columns = new HashMap<>();
	private static Map<String, String> find_columns = new HashMap<>();
	
	@Autowired
	private MatchService matchService;
	
	static{
		/* 前端展示过滤属性 */
		columns.put("endTime", "endTime");
	}
	
	/**
	 * 查询竞彩对阵数据
	 * @author kouyi
	 * @param bean
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/match/jcquery", method=RequestMethod.POST)
	public void modelCurrentList(QueryBean bean, HttpServletRequest request, HttpServletResponse response) {
		try
		{
			List<Match> list = matchService.queryScheduleList("", "");
			ResultBean result = new ResultBean(ErrorCode_Interface.SUCCESS, list);
			writeJson_response(result, columns, response);
			return;
		}
		catch(Exception e)
		{
			logger.error("查询竞彩对阵数据接口/match/jcquery异常", e);
		}
		writeJson_response(ErrorCode.ERROR, response);
	}
	
}
