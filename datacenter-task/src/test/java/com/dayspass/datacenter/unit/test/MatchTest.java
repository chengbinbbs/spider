package com.dayspass.datacenter.unit.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.dayspass.datacenter.redis.RedisPool;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring-dev.xml" })
public class MatchTest {

	@Autowired
	private RedisPool redisPool;
	
	@SuppressWarnings("unchecked")
	@Test
	public void testMatchTask(){
		redisPool.set("11", "test1", 50);
		System.out.println(redisPool.get("11"));
		User user = new User();
		user.setId(1);
		user.setName("kouyi");
		redisPool.setObject("22", user, 5);
		User aa = (User) redisPool.getObject("22");
		System.out.println(aa.getName());
		User user2 = new User();
		user2.setId(2);
		user2.setName("kouyi444");
		Map<String, User> dl = new HashMap<>();
		dl.put("33", user);
		dl.put("44", user2);
		redisPool.setObject("aa", dl, 10);
		Map<String, User> bb = (Map<String, User>) redisPool.getObject("aa");
		System.out.println(bb.get("44").getName());
		List<User> list = new ArrayList<>();
		list.add(user);
		list.add(user2);
		redisPool.setObject("ls", list, 10);
		List<User> cc = (List<User>) redisPool.getObject("ls");
		System.out.println(cc.get(0).getName());
		
		System.out.println(redisPool.containsKey("11"));
		System.out.println(redisPool.containsKeyObj("22"));
	}
}


class User implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
