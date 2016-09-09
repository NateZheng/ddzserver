package com.server.java.cache.redis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.server.java.cache.ICache;

import redis.clients.jedis.BinaryClient.LIST_POSITION;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

public class CacheRedisImp implements ICache {

	private static ShardedJedisPool pool;

	private ShardedJedis jedis;

	private static final String RESULT_OK = "OK";

	private static final int RESULT_OK_CODE = 1;

	private CacheRedisImp() {

	}

	static {
		//pool = (ShardedJedisPool)ApplicationContextHelper.getBean("jedis");
		JedisPoolConfig config =new JedisPoolConfig();//Jedis池配置
		config.setMaxTotal(20);
		config.setMaxIdle(10);//对象最大空闲时间
		config.setMaxWaitMillis(1000);//
		config.setTestOnBorrow(true);//
		String host = "120.24.164.40";
		int port = 6379;
		
		List<JedisShardInfo> jdsInfoList =new ArrayList<JedisShardInfo>(1);
		JedisShardInfo info = new JedisShardInfo(host, port);
		info.setPassword("lingxin2014");		
		jdsInfoList.add(info);
		pool =new ShardedJedisPool(config, jdsInfoList);
	}

	public static CacheRedisImp getInstance() {
		CacheRedisImp instance = new CacheRedisImp();
		instance.jedis = pool.getResource();
		return instance;
	}

	public static void releaseCache(CacheRedisImp imp) {
		imp.jedis.close();
	}

	@Override
	public boolean set(String key, String value) {
			return RESULT_OK.equals(jedis.set(key, value));
	}

	@Override
	public String get(String key) {
		
		return jedis.get(key);
	}

	@Override
	public boolean setObj(String key, Object value) {
		
		return RESULT_OK.equals(jedis.set(key, JSON.toJSONString(value)));
	}

	@Override
	public <T> T getObj(String key, Class<T> clazz) {
		
		return JSON.parseObject(jedis.get(key), clazz);
	}

	@Override
    public Long hlen(String key) {
        return jedis.hlen(key);
    }

	@Override
	public List<String> lgetAll(String key) {
		
		return jedis.lrange(key, 0, -1);
	}

	@Override
	public Long del(String key) {
		
		return jedis.del(key);
	}

	@Override
	public Long hset(String key, String field, String value) {
		
		return jedis.hset(key, field, value);
	}

	@Override
	public String hget(String key, String field) {
		
		return jedis.hget(key, field);
	}

	@Override
	public Long hsetObj(String key, String field, Object value) {
		
		return jedis.hset(key, field, JSON.toJSONString(value));
	}

	@Override
	public <T> T hgetObj(String key, String field, Class<T> clazz) {
		
		return JSON.parseObject(jedis.hget(key, field), clazz);
	}

	@Override
	public boolean hmset(String key, Map<String, String> keyValues) {
		
		return RESULT_OK.equals(jedis.hmset(key, keyValues));
	}

	@Override
	public List<String> hmget(String key, String... fields) {
		
		return jedis.hmget(key, fields);
	}

	@Override
	public boolean hmsetObjects(String key, Map<String, Object> keyValues) {
		
		Map<String, String> map = new HashMap<String, String>();

		for (String keyTemp : keyValues.keySet()) {
			map.put(keyTemp, JSON.toJSONString(keyValues.get(keyTemp)));
		}

		return RESULT_OK.equals(jedis.hmset(key, map));
	}

	@Override
	public <T> List<T> hmgetObjects(String key, Class<T> clazz, String... fields) {
		
		List<String> jsonStrs = jedis.hmget(key, fields);

		if (jsonStrs != null) {
			List<T> list = new ArrayList<T>();
			for (String json : jsonStrs) {
				list.add(JSON.parseObject(json, clazz));
			}

			return list;
		}

		return null;
	}

	@Override
	public Long hincrby(String key, String field, int num) {
		
		return jedis.hincrBy(key, field, num);
	}

	@Override
	public boolean hdel(String key, String... fields) {
		
		return jedis.hdel(key, fields) == RESULT_OK_CODE;
	}

	@Override
	public Map<String, String> hgetAll(String key) {
		
		return jedis.hgetAll(key);
	}

	@Override
	public <T> Map<String, T> hgetAll(String key, Class<T> clazz) {
		Map<String, String> strMap = jedis.hgetAll(key);

		Map<String, T> result = new HashMap<String, T>();
		for (String keyT : strMap.keySet()) {
			result.put(keyT, JSON.parseObject(strMap.get(keyT), clazz));
		}

		return result;
	}

	@Override
	public Long lpush(String key, String... values) {
		
		return jedis.lpush(key, values);
	}

	@Override
	public Long rpush(String key, String... values) {
		
		return jedis.rpush(key, values);
	}

	@Override
	public Long rpush(String key, List<String> list) {
	    String[] values = list.toArray(new String[list.size()]);
	    return jedis.rpush(key, values);
	}

	@Override
	public String lpop(String key) {
		
		return jedis.lpop(key);
	}

	@Override
	public String rpop(String key) {
		
		return jedis.rpop(key);
	}

	@Override
	public String lindex(String key, int index) {
		
		return jedis.lindex(key, index);
	}

	@Override
	public long llen(String key) {
		
		return jedis.llen(key);
	}

	@Override
	public Long linsertAfter(String key, String pivot, String value) {
		
		return jedis.linsert(key, LIST_POSITION.AFTER, pivot, value);
	}

	@Override
	public Long linsertBefore(String key, String pivot, String value) {
		
		return jedis.linsert(key, LIST_POSITION.BEFORE, pivot, value);
	}

	@Override
	public Long lrem(String key, long count, String value) {
		
		return jedis.lrem(key, count, value);
	}

	@Override
	public boolean lset(String key, long index, String value) {
		
		return jedis.lset(key, index, value).equals(RESULT_OK);
	}

    @Override
    public Set<String> keys(String key) {
        
        return jedis.getShard("").keys(key);
    }

    @Override
    public Long sadd(String key, String... members) {
        
        return jedis.sadd(key, members);
    }

    @Override
    public Set<String> smembers(String key) {
        
        return jedis.smembers(key);
    }

    @Override
    public Long srem(String key, String... members) {
        
        return jedis.srem(key, members);
    }

    @Override
    public Boolean sismember(String key, String member) {
        
        return jedis.sismember(key, member);
    }

	@Override
	public Long setnx(String key, String value) {
		return jedis.setnx(key, value);
	}

	@Override
	public Long expire(String key, int seconds) {
		return jedis.expire(key, seconds);
	}

	@Override
	public boolean exist(String key) {
		return jedis.exists(key);
	}
}
