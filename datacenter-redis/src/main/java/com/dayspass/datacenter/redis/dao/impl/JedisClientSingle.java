package com.dayspass.datacenter.redis.dao.impl;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.dayspass.datacenter.common.util.SerializableUtil;
import com.dayspass.datacenter.common.util.StringUtils;
import com.dayspass.datacenter.redis.dao.JedisClient;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.exceptions.JedisDataException;

/**
 * 单机版
 * @author zhangcb
 *
 */
public class JedisClientSingle implements JedisClient {

	private static Logger logger = LoggerFactory.getLogger(JedisClientSingle.class);
	
	@Autowired
	private JedisPool jedisPool; 
	
	@Override
	public String get(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.get(key);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public String set(String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.set(key, value);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public String hget(String key, String field) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            if(field == null)
                return null;
            return jedis.hget(key, field);
        } catch (JedisDataException e) {
            logger.warn("从Redis中获取key:"+key+"，Field:"+field+"的数据时出错："+e.getMessage());
            return null;
        } finally {
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Long hset(String hkey, String key, String value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.hset(hkey, key, value);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Long incr(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.incr(key);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Long expire(String key, int second) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.expire(key, second);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Long ttl(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.ttl(key);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}
	
	@Override
	public Long del(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.del(key);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}
	
	@Override
	public Long lpush(String key, String... value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.lpush(key, value);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public String lpop(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.lpop(key);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public List<String> lrange(String key, int start ,int end) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.lrange(key, start, end);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public List<String> mget(String... keys) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.mget(keys);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public String set(String key, String value, int expireSeconds) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.setex(key, expireSeconds, value);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hmget(key, fields);
        } catch (JedisDataException e) {
            logger.warn("从Redis中获取key:"+key+"，Field:"+fields+"的数据时出错："+e.getMessage());
            return null;
        }catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            return jedis.hgetAll(key);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public String hmset(String key, Map<String, String> hash) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hmset(key, hash);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Long decr(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.decr(key);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Long hdel(String key, String... fields) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.hdel(key,fields);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public String rpush(String key, String val) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.rpush(key,val)+"";
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Long llen(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.llen(key);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public List<String> lrange(String key, Long start, Long end) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.lrange(key, start, end);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public String rpop(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.rpop(key);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public List<String> brpop(int secTime, String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
            List<String>  ret = jedis.brpop(secTime,key);
            return ret;
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Boolean exists(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(key);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Long expireAt(String key, Long unixTime) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.expireAt(key,unixTime);
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}
	
	/**
	 * 获取redis键值-object
	 * 
	 * @param key
	 * @return
	 */
	public Object getObject(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			byte[] bytes = jedis.get(key.getBytes());
			if(!StringUtils.isEmpty(bytes)) {
				return SerializableUtil.unserializable(bytes);
			}
		} catch (Exception e) {
			logger.error("getObject获取redis键值异常:key=" + key + " cause:" + e.getMessage());
		} finally {
			jedis.close();
		}
		return null;
	}
	
	/**
	 * 设置redis键值-object
	 * @param key
	 * @param value
	 * @param expiretime
	 * @return
	 */
	public String setObject(String key, Object value) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.set(key.getBytes(), SerializableUtil.serializable(value));
		} catch (Exception e) {
			logger.error("setObject设置redis键值异常:key=" + key + " value=" + value + " cause:" + e.getMessage());
			return null;
		} finally {
			if(jedis != null)
        	{
        		jedis.close();
        	}
		}
	}
	
	public String setObject(String key, Object value,int expiretime) {
		String result = "";
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			result = jedis.set(key.getBytes(), SerializableUtil.serializable(value));
			if(result.equals("OK")) {
				jedis.expire(key.getBytes(), expiretime);
			}
			return result;
		} catch (Exception e) {
			logger.error("setObject设置redis键值异常:key=" + key + " value=" + value + " cause:" + e.getMessage());
		} finally {
			if(jedis != null)
        	{
        		jedis.close();
        	}
		}
		return result;
	}

	/**
	 * 删除key
	 */
	public Long delkeyObject(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.del(key.getBytes());
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}

	@Override
	public Boolean existsObject(String key) {
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			return jedis.exists(key.getBytes());
		}catch(Exception e) {
		    e.printStackTrace();
		    return null;
		}finally{
        	if(jedis != null)
        	{
        		jedis.close();
        	}
        }
	}
}
