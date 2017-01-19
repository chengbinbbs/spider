package com.dayspass.datacenter.redis.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.dayspass.datacenter.redis.dao.JedisClient;

import redis.clients.jedis.JedisCluster;

/**
 * 集群版
 * @author zhangcb
 *
 */
public class JedisClientCluster implements JedisClient {

	@Autowired
	private JedisCluster jedisCluster;
	
	@Override
	public String get(String key) {
		return jedisCluster.get(key);
		
	}

	@Override
	public String set(String key, String value) {
		return jedisCluster.set(key, value);
	}
	
	/**
	 * 设置redis键值-object
	 * @param key
	 * @param value
	 * @param expiretime
	 * @return
	 */
	public String setObject(String key, Object value) {
		//TODO
		return null;
	}

	@Override
	public String hget(String hkey, String key) {
		return jedisCluster.hget(hkey, key);
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		return jedisCluster.hset(hkey, key, value);
	}

	@Override
	public Long incr(String key) {
		return jedisCluster.incr(key);
	}

	@Override
	public Long expire(String key, int second) {
		return jedisCluster.expire(key, second);
	}

	@Override
	public Long ttl(String key) {
		return jedisCluster.ttl(key);
	}
	
	@Override
	public Long del(String key) {
		return jedisCluster.del(key);
	}

	@Override
	public Long lpush(String key, String... value) {
		return jedisCluster.lpush(key, value);
	}

	@Override
	public String lpop(String key) {
		return jedisCluster.lpop(key);
	}

	@Override
	public List<String> lrange(String key, int start, int end) {
		return jedisCluster.lrange(key, start, end);
	}

	/**
	 * 手动实现
	 */
	public List<String> mget(String... keys) {
		List<String> list = new ArrayList<String>();
		for (String key : keys)
		{
			jedisCluster.get(key);  
		}
		return list;
	}

	@Override
	public String set(String key, String value, int expireSeconds) {
		return jedisCluster.setex(key, expireSeconds, value);
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		return jedisCluster.hmget(key,fields);
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		return jedisCluster.hgetAll(key);
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		return jedisCluster.hmset(key,hash);
	}

	@Override
	public Long decr(String key) {
		return jedisCluster.decr(key);
	}

	@Override
	public Long hdel(String key, String... fields) {
		return jedisCluster.hdel(key,fields);
	}

	@Override
	public String rpush(String key, String val) {
		return jedisCluster.rpush(key,val)+"";
	}

	@Override
	public Long llen(String key) {
		return jedisCluster.llen(key);
	}

	@Override
	public List<String> lrange(String key, Long start, Long end) {
		return jedisCluster.lrange(key,start,end);
	}

	@Override
	public String rpop(String key) {
		return jedisCluster.rpop(key);
	}

	@Override
	public List<String> brpop(int secTime, String key) {
		return jedisCluster.brpop(secTime,key);
	}

	@Override
	public Boolean exists(String key) {
		return jedisCluster.exists(key);
	}

	@Override
	public Long expireAt(String key, Long unixTime) {
		return jedisCluster.expireAt(key,unixTime);
	}

	@Override
	public Object getObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String setObject(String key, Object value, int expireSeconds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long delkeyObject(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean existsObject(String key) {
		// TODO Auto-generated method stub
		return false;
	}

}
