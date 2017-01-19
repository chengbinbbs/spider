package com.storm.unit.test;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.druid.util.HttpClientUtils;
import com.dayspass.datacenter.grab.task.MatchInfoTask;
import com.dayspass.datacenter.grab.task.TaskChangeMatch;
import com.dayspass.datacenter.grab.task.TaskDayMatch;
import com.dayspass.datacenter.grab.task.jc.JczqMatchTask;
import com.dayspass.datacenter.redis.dao.JedisClient;
import com.google.common.collect.Maps;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dev.xml" })
public class MatchTest {

	@Autowired
	private TaskDayMatch taskMatch;
	@Autowired
	private TaskChangeMatch changeMatch;
	@Autowired
	private MatchInfoTask matchInfoTask;
	@Autowired
	private JedisClient jedisClient;
	@Autowired
	private JczqMatchTask jczqMatchTask;
	
	@Test
	public void testGrabMatchInfo(){
		matchInfoTask.handlerMatchLottry();
		jedisClient.lpush("list", "aa");
		jedisClient.lpush("list", "bb");
		jedisClient.lpush("list", "cc");
		System.out.println(jedisClient.lrange("list", 0, 0));
		System.out.println(jedisClient.lrange("list", 0, -1));
		System.out.println(jedisClient.lrange("list", 0, 1));
		System.out.println(jedisClient.lpop("list")); // 栈顶
		jedisClient.del("list");
		System.out.println(1 << 30);
		ConcurrentMap<String, String> map = new ConcurrentHashMap<>();
	}
	
	@Test
	public void grabJczqMatch(){
		jczqMatchTask.grabJczqMatch();
	}
	
	@Test
	public void testGrabSchedule(){
		//taskMatch.grabSchedule();
		//taskMatch.consumQueueOne();
		//taskMatch.consumQueueTwo();
		//taskMatch.consumQueueThree();
		//taskMatch.createJsbfMatch();
		changeMatch.grabChange();
		//taskMatch.createDistoryPeriod();
	}

	public static void main(String[] args) {
		/*String plain = ";|2;|4;3|90,2-1;3-3;1,2-1;1-3;2";
		plain = plain.substring(plain.indexOf("90"), plain.length());
		String[] plains = plain.split("\\;");
		StringBuffer buffer = new StringBuffer();
		buffer.append(plains[0].replaceAll(",", "分钟"));
		if(!StringUtils.isEmpty(plains[1])) {
			buffer.append(" 两回合" + plains[1]);
		}
		if(!StringUtils.isEmpty(plains[2])) {
			String[] ybe = plains[2].split("\\,");
			if(ybe.length == 2) {
				if(ybe[0].equals("1")) {
					buffer.append(" 120分钟");
				} else if(ybe[0].equals("2")) {
					buffer.append(" 加时");
				} else {
					buffer.append(" 加时中");
				}
				buffer.append(ybe[1]);
			}
		}
		if(!StringUtils.isEmpty(plains[3])) {
			buffer.append(" 点球" + plains[3]);
		}
		System.out.println(buffer.toString());*/
		
	}
}
