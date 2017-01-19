package com.dayspass.datacenter.unit.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dayspass.datacenter.domain.ZqSchedule;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.dayspass.datacenter.service.football.match.ZqScheduleService;
import com.dayspass.datacenter.task.football.init.ScheduleInitTask;
import com.dayspass.datacenter.task.football.init.ScheduleMatchInitTask;
import com.dayspass.datacenter.task.football.jsbf.JsBfAllMatchTask;
import com.dayspass.datacenter.task.football.jsbf.JsBfBdMatchTask;
import com.dayspass.datacenter.task.football.jsbf.JsBfChangeTask;
import com.dayspass.datacenter.task.football.jsbf.JsBfJcMatchTask;
import com.dayspass.datacenter.task.football.jsbf.JsBfMatchTask;
import com.dayspass.datacenter.task.football.jsbf.JsBfStageTask;
import com.dayspass.datacenter.task.football.jsbf.JsBfZcMatchTask;
import com.dayspass.datacenter.task.football.match.MatchLotteryTask;
import com.dayspass.datacenter.task.football.match.ScheduleTask;
import com.dayspass.datacenter.task.football.player.PlayerDetailTask;
import com.dayspass.datacenter.task.football.stage.StageTask;
import com.dayspass.datacenter.task.lottery.MainMatchingTask;

@ActiveProfiles("development")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dev.xml" })
public class MatchTaskTest {
	@Autowired
	private ScheduleTask scheduleTask;
	
	@Autowired
	private JsBfMatchTask jsBfMatchTask;
	@Autowired
	private JsBfChangeTask jsBfChangeTask;
	@Autowired
	private JsBfAllMatchTask jsBfAllMatchTask;
	@Autowired
	private MatchLotteryTask matchLotteryTask;
	@Autowired
	private JsBfJcMatchTask jsBfJcMatchTask;
	@Autowired
	private JsBfBdMatchTask jsBfBdMatchTask;
	@Autowired
	private JsBfZcMatchTask jsBfZcMatchTask;
	@Autowired
	private JsBfStageTask jsBfStageTask;
	@Autowired
	private StageTask stageTask;
	@Autowired
	private PlayerDetailTask playerDetailTask;
	@Autowired
	private ZqScheduleService zqScheduleService;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private ScheduleMatchInitTask scheduleMatchInitTask;
	@Autowired
	private ScheduleInitTask scheduleInitTask;
	@Autowired
	private MainMatchingTask mainMatchingTask;
	

	@Test
	public void testMatch(){
		scheduleTask.spiderSchedule();
	}
	
	@Test
	public void testMatchTask(){
		jsBfMatchTask.spiderMatchScore();
	}
	
	@Test
	public void testChangeTask(){
		jsBfChangeTask.spiderCreateChange();
	}
	
	@Test
	public void testMatchLottery(){
		matchLotteryTask.handlerMatchLottry();
	}
	
	@Test
	public void testAllMatchTask(){
		jsBfAllMatchTask.spiderAllCurrentMatch();
		jsBfAllMatchTask.spiderAllFutureMatch();
		jsBfAllMatchTask.spiderAllHistoryMatch();
	}
	@Test
	public void testJCMatchTask(){
		jsBfJcMatchTask.spiderJcCurrentMatch();
		jsBfJcMatchTask.spiderJcFutureMatch();
		jsBfJcMatchTask.spiderJcHistoryMatch();
	}
	@Test
	public void testBDMatchTask(){
		jsBfBdMatchTask.spiderBdCurrentMatch();
		jsBfBdMatchTask.spiderBdHistoryMatch();
	}
	@Test
	public void testZCMatchTask(){
		jsBfZcMatchTask.spiderZcCurrentMatch();
		jsBfZcMatchTask.spiderZcHistoryMatch();
	}
	@Test
	public void teststageTask(){
		stageTask.spiderBdStage();
		stageTask.spiderJcStage();
		stageTask.spiderZcStage();
	}
	@Test
	public void testjsbfstageTask(){
		jsBfStageTask.spiderAllStage();
	}
	@Test
	public void playerDetailTask(){
		ZqSchedule  sc = zqScheduleService.selectBySid(1315206);
		System.out.println(sc.getHometeam() + "-" + sc.getGuestteam());
	}
	@Test
	public void initMatch(){
//		scheduleMatchInitTask.spiderSchedule();
		scheduleInitTask.spiderSchedule();
	}
	@Test
	public void matchingInterface(){
		mainMatchingTask.matchingInterface();
	}
}
