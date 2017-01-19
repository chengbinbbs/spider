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
import org.springframework.web.bind.annotation.RequestParam;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.domain.ZqMatchLottery;
import com.dayspass.datacenter.error.code.ErrorCode;
import com.dayspass.datacenter.interf.base.BaseController;
import com.dayspass.datacenter.interf.bean.ResultBean;
import com.dayspass.datacenter.interf.ecode.ErrorCode_Interface;
import com.dayspass.datacenter.service.football.match.MatchLotteryService;
import com.google.common.collect.Maps;

/**
 * 竞彩跟球探的球队id匹配接口
 * @author zhangcb
 * @date 2017-01-18
 */
@Controller("lotteryMatchController")
public class LotteryMatchController extends BaseController {
	private static Logger logger = LoggerFactory.getLogger(LotteryMatchController.class);
	
	@Autowired
	private MatchLotteryService matchLotteryService;
	
	private static Map<String, String> columns = new HashMap<>();
	static{
		/* 前端展示过滤属性 */
		columns.put("id", "id");
		columns.put("lotteryname", "lotteryname");
		columns.put("sort", "sort");
		columns.put("stageid", "stageid");
		columns.put("swapteam", "swapteam");
		columns.put("createtime", "createtime");
		columns.put("updatetime", "updatetime");
	}
	/**
	 * 查询竞彩跟球探球队匹配数据
	 * @author zhangcb
	 * @param bean
	 * @param request
	 * @param response
	 */
	@RequestMapping(value="/team/matching", method=RequestMethod.GET)
	public void teamMatching(@RequestParam Map<String,String> param, HttpServletRequest request, HttpServletResponse response) {
		try
		{
			if(StringUtils.isEmpty(param.get("issue"))){
				ResultBean result = new ResultBean(ErrorCode_Interface.VALID_PARAM);
				writeJson_response(result, columns, response);
				return;
			}
			if(StringUtils.isEmpty(param.get("teamId"))){
				ResultBean result = new ResultBean(ErrorCode_Interface.VALID_PARAM);
				writeJson_response(result, columns, response);
				return;
			}
			Map<String, Object> params = Maps.newHashMap();
			params.put("type", 1);
			params.put("name", param.get("issue"));
			params.put("sort", new String(param.get("teamId").getBytes("iso8859-1"),"UTF-8"));
			List<ZqMatchLottery> list = matchLotteryService.queryMatchLottery(params);
			ZqMatchLottery match = null;
			if(!StringUtils.isEmpty(list))
			{
				match = list.get(0);
			}
			ResultBean result = new ResultBean(ErrorCode_Interface.SUCCESS, match);
			writeJson_response(result, columns, response);
			return;
		}
		catch(Exception e)
		{
			logger.error("查询竞彩跟球探球队匹配接口/team/matching异常", e);
		}
		writeJson_response(ErrorCode.ERROR, response);
	}
	
}
