package com.dayspass.datacenter.task.lottery;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.date.util.DateUtils;
import com.dayspass.datacenter.domain.ZqMatchLottery;
import com.dayspass.datacenter.domain.ZqStage;
import com.dayspass.datacenter.service.football.match.MatchLotteryService;
import com.dayspass.datacenter.service.football.match.ZqStageService;
import com.google.common.collect.Maps;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 生成主站跟数据中心的匹配接口
 * @author zhangcb
 *
 */
@Component("mainMatchingTask")
public class MainMatchingTask {

private static Logger logger = LoggerFactory.getLogger(MainMatchingTask.class);
	
	@Autowired
	private ZqStageService stageService;
	@Autowired
	private MatchLotteryService matchLotteryService;
	
	@Value("${zq.matching.path}")
	private String position;
	
	public void matchingInterface(){
		try {
			DateTime today = new DateTime();
			if(Integer.parseInt(today.toString("HHmm")) < 1130) {
				today = today.minusDays(1);
			}
			String qc = today.toString("yyyyMMdd");
			Map<String,Object> params = Maps.newHashMap();
			params.put("type", 1);
			params.put("name", qc);
			
			List<ZqStage> stagelist = stageService.queryJcStageList(params);
			if(!StringUtils.isEmpty(stagelist))
			{
				JSONArray array = new JSONArray();
				Map<String,Object> param = Maps.newHashMap();
				for (ZqStage stage : stagelist) {
					String name = stage.getName();
					param.put("stageid", stage.getId());
					List<ZqMatchLottery> matchList = matchLotteryService.queryAll(param);
					if(!StringUtils.isEmpty(matchList))
					{
						for (ZqMatchLottery match : matchList) {
							JSONObject json = new JSONObject();
							String matchCode = name + match.getSort().substring(2, 5);
							json.put("scheduleid", match.getScheduleid());
							json.put("matchCode", matchCode);
							json.put("matchtime", DateUtils.formatDate(match.getMtime(), "yyyy-MM-dd HH:mm:ss"));
							json.put("homeTeamid", match.getHometeamid());
							json.put("homeTeamname", match.getHometeamname());
							json.put("guestTeamid", match.getGuestteamid());
							json.put("guestTeamname", match.getGuestteamname());
							array.add(json);
						}
					}
				}
				FileUtils.write(new File(position + "matching.json"), array.toString(),"UTF-8"); 
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("生成主站跟球探的匹配关系对阵文件异常，异常信息:{}", e.getMessage());
		}
	}
}
