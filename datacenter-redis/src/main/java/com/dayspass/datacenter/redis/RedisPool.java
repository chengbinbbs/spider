package com.dayspass.datacenter.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dayspass.datacenter.common.util.SerializableUtil;
import com.dayspass.datacenter.common.util.StringUtils;

import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

//@Component("redisPool")
public class RedisPool {
	private static Logger logger = LoggerFactory.getLogger(RedisPool.class);
	@Autowired
	private ShardedJedisPool shardedJedisPool;//切片
	private static String SUCCESS = "OK"; //返回值
	
	/**
	 * 初始化redis连接池 //test使用
	 * 
	 * @param host
	 * @param port
	 * @return JedisPool
	 */
/*	public static JedisPool getPool() {
		if (pool == null) {
			JedisPoolConfig config = new JedisPoolConfig(); // 总连接数
			config.setMaxTotal(maxTotal); // 控制一个pool最多有多少个状态为idle(空闲的)的jedis实例。
			config.setMaxIdle(maxIdle);
			config.setMinIdle(minIdle); // 获取实例的最大等待时间
			config.setMaxWaitMillis(maxWaitMillis); // 获取的实例均可用
			config.setTestOnBorrow(testOnBorrow);
			pool = new JedisPool(config, "", 11);
		}
		return pool;
	}*/
		 

	/**
	 * 获取Jedis实例 //test使用
	 * 
	 * @return
	 */
/*	public synchronized static Jedis getJedis() {
		Jedis jedis = null;
		try {
			//getPool(); 
			if (pool != null && jedis == null) {
				jedis = pool.getResource();
			}
		} catch (Exception e) {
			logger.error("初始化jedis异常:" + e.getLocalizedMessage());
		}
		return jedis;
	}*/

	/**
	 * 获取redis键值-string
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key) {
		String value = null;
		ShardedJedis redis = null;
		try {
			redis = shardedJedisPool.getResource();
			value = redis.get(key);
		} catch (Exception e) {
			logger.error("get获取redis键值异常:key=" + key + " cause:" + e.getMessage());
		} finally {
			redis.close();
		}
		return value;
	}
	
	/**
	 * 获取redis键值-object
	 * 
	 * @param key
	 * @return
	 */
	public Object getObject(String key) {
		ShardedJedis redis = null;
		try {
			redis = shardedJedisPool.getResource();
			byte[] bytes = redis.get(key.getBytes());
			if(!StringUtils.isEmpty(bytes)) {
				return SerializableUtil.unserializable(bytes);
			}
		} catch (Exception e) {
			logger.error("getObject获取redis键值异常:key=" + key + " cause:" + e.getMessage());
		} finally {
			redis.close();
		}
		return null;
	}
	
	/**
	 * 设置redis键值-string
	 * @param key
	 * @param value
	 * @param expiretime
	 * @return
	 */
	public String set(String key, String value, int expiretime) {
		String result = null;
		ShardedJedis redis = null;
		try {
			redis = shardedJedisPool.getResource();
			result = redis.set(key, value);
			if(result.equals(SUCCESS)) {
				redis.expire(key, expiretime);
			}
		} catch (Exception e) {
			logger.error("set设置redis键值异常:key=" + key + " value=" + value + " cause:" + e.getMessage());
		} finally {
			redis.close();
		}
		return result;
	}
	
	/**
	 * 设置redis键值-object
	 * @param key
	 * @param value
	 * @param expiretime
	 * @return
	 */
	public String setObject(String key, Object value, int expiretime) {
		String result = null;
		ShardedJedis redis = null;
		try {
			redis = shardedJedisPool.getResource();
			result = redis.set(key.getBytes(), SerializableUtil.serializable(value));
			if(result.equals(SUCCESS)) {
				redis.expire(key.getBytes(), expiretime);
			}
		} catch (Exception e) {
			logger.error("setObject设置redis键值异常:key=" + key + " value=" + value + " cause:" + e.getMessage());
		} finally {
			redis.close();
		}
		return result;
	}
	
	/**
	 * 删除redis指定键-string
	 * @param key
	 * @return
	 */
	public void deleteKey(String key) {
		ShardedJedis redis = null;
		try {
			redis = shardedJedisPool.getResource();
			redis.del(key);
		} catch (Exception e) {
			logger.error("delStrKey删除redis键异常:key=" + key + " cause:" + e.getMessage());
		} finally {
			redis.close();
		}
	}
	
	/**
	 * 删除redis指定键-object
	 * @param key
	 * @return
	 */
	public void deleteKeyObj(String objkey) {
		ShardedJedis redis = null;
		try {
			redis = shardedJedisPool.getResource();
			redis.del(objkey.getBytes());
		} catch (Exception e) {
			logger.error("delObjKey删除redis键异常:key=" + objkey + " cause:" + e.getMessage());
		} finally {
			redis.close();
		}
	}
	
	/**
	 * 判断redis指定键是否存在-string
	 * @param key
	 * @return
	 */
	public boolean containsKey(String key) {
		ShardedJedis redis = null;
		try {
			redis = shardedJedisPool.getResource();
			return redis.exists(key);
		} catch (Exception e) {
			logger.error("containsKey判断redis键是否存在异常:key=" + key + " cause:" + e.getMessage());
		} finally {
			redis.close();
		}
		return false;
	}
	
	/**
	 * 判断redis指定键是否存在-object
	 * @param key
	 * @return
	 */
	public boolean containsKeyObj(String key) {
		ShardedJedis redis = null;
		try {
			redis = shardedJedisPool.getResource();
			return redis.exists(key.getBytes());
		} catch (Exception e) {
			logger.error("containsKeyObj判断redis键是否存在异常:key=" + key + " cause:" + e.getMessage());
		} finally {
			redis.close();
		}
		return false;
	}

}
