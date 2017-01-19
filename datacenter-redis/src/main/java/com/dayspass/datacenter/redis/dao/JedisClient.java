package com.dayspass.datacenter.redis.dao;

import java.util.List;
import java.util.Map;



/**
 * ━━━━━━神兽出没━━━━━━
 * 　　  ┏┓　      ┏┓
 * 　　┏┛┻━━━━━┛┻┓
 * 　　┃　　　　    ┃
 * 　　┃　　　━   ┃
 * 　　┃　┳┛　┗┳　 ┃
 * 　　┃          ┃
 * 　　┃　　 ┻　      ┃
 * 　　┃　　　　　 ┃
 * 　　┗━┓　　    ┏━┛
 * 　　　┃　　　┃神兽保佑, 永无BUG!
 * 　　　┃　　　┃Code is far away from bug with the animal protecting
 * 　　　┃　　　┗━━━┓
 * 　　　┃　　　　　 ┣┓
 * 　　　┃　　　　　 ┏┛
 * 　　　┗┓┓┏━   ┳┓┏┛
 * 　　　  ┃┫┫　    ┃┫┫
 * 　　　  ┗┻┛　    ┗┻┛
 * ━━━━━━感觉萌萌哒━━━━━━
 * @author: zhangcb
 * @date: 2016/11/21
 */
public interface JedisClient {

	/**
     * 得到单个key的值
     */
	public String get(String key);
	
	/**
     * 得到多个Key的值
     */
	public List<String> mget(final String... keys);
	/**
     * 设置单个key和value
     */
	public String set(String key, String value);
	
	/**
     * 带过期时间的设置，注意：此设置是对整个Key的设置，而非当前值。
     * @param key
     * @param expireSeconds 多少秒后过期
     * @param value
     */
    String set(String key, String value, int expireSeconds);
    /**
     * 从Hash类型的数据中取值
     * @param key redis的Key名称
     * @param field Hash中的某个Key
     * @return 返回具体的Value。
     */
	public String hget(String key, String field);
	/**
     * 对于Hash类型的参数，从给定的Key中获得指定的field。相对于hgetAll方法来说可以仅取得需要的那部分缓存。
     */
	public List<String> hmget(final String key, final String... fields);

    /**
     * 取得整个Hash的全部值
     */
	public Map<String, String> hgetAll(final String key);

    /**
     * Hash类型的设值
     * @param key redis的Key名称
     * @param field Hash中的某个Key
     * @param value Hash中的Key对应的值
     * @return 返回具体的Value。
     */
	public Long hset(String hkey, String key, String value);
	/**
     * 直接将一个Map都放入到Redis中
     */
	public String hmset(final String key, final Map<String, String> hash);
    /**
     * 自增序列
     * @param key
     * @return
     */
	public Long incr(String key);
	/**
     * 自减序列
     * @param key
     * @return
     */
    public Long decr(final String key);
	/**
     * 删除指定的Key的缓存，key还可以用通配符，可以将匹配的值全部删除。
     */
	public Long del(String key);
	/**
     * 删除Hash中的某些具体的field。
     */
	public Long hdel(final String key, final String... fields);
	/**
     *向链表添加元素
     * @param key
     * @param val
     * @return
     */
	public Long lpush(String key, String... value);
	
    public String rpush(final String key, final String val);
	
	public String lpop(String key);
	
	public List<String> lrange(String key, int start, int end);
    /**
     * 获得List类型缓存的大小
     * @param key
     * @return
     */
	public Long llen(String key);

    /**
     * 获取列表数据范围
     * @param key
     * @param start
     * @param end
     * @return
     */
	public List<String> lrange(String key, Long start, Long end);

	public String rpop(final String key);

	public List<String> brpop(final int secTime, final String key);

    /**
     * 是否包含某个key值
     * @param key
     * @return
     */
	public Boolean exists(String key);
    
	public Long expireAt(String key, Long unixTime);
	
    public Long expire(String key, int second);

	/**
     * 是否设置了失效时间
     * 当 key 不存在时,返回 -2 .
     * 当 key 存在但没有设置剩余生存时间时,返回 -1
     * 否则,以秒为单位,返回 key 的剩余生存时间.
     *
     * @param key
     * @return
     */
	public Long ttl(String key);
	
	/**
     * 得到单个key的值
     */
	public Object getObject(String key);
	
	/**
     * 设置单个key和Object
     */
	public String setObject(String key, Object value);
	
	/**
     * 设置单个key和Object
     */
	public String setObject(String key, Object value,int expireSeconds);
	
	/**
     * 得到单个key的值
     */
	public Long delkeyObject(String key);
	/**
     * 是否包含某个key值
     * @param key
     * @return
     */
	public Boolean existsObject(String key);
}
