package com.server.java.cache;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ICache {

	/**
	 * 写入字符串
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean set(String key, String value);
	
	/**
	 * 读取字符串
	 * 
	 * @param key
	 * @return
	 */
	public String get(String key);
	
	/**
	 * 写入对象的json字符串
	 * 
	 * @param key
	 * @param value
	 * @return
	 */
	public boolean setObj(String key, Object value);
	
	/**
	 * 获取字符串并转化为对象
	 * 
	 * @param key
	 * @return
	 */
	public <T> T getObj(String key, Class<T> clazz);
	
	/**
	 * 删除key value
	 * 
	 * @param key
	 * @return 删除的key的数量
	 */
	public Long del(String key);
	
	/**
	 * List头部加入元素
	 * 
	 * @param key
	 * @param values
	 * @return 返回操作后队列的长度
	 */
	public Long lpush(String key, String... values);
	
	/**
	 * List尾部加入元素
	 * 
	 * @param key
	 * @param values
	 * @return 返回操作后队列的长度
	 */
	public Long rpush(String key, String... values);
	
	/**
	 * List尾部加入元素
	 * 
	 * @param key
	 * @param values
	 * @return
	 */
	public Long rpush(String key, List<String> list);
	
	/**
	 * 弹出并获取List头部元素
	 * 
	 * @param key
	 * @return
	 */
	public String lpop(String key);
	
	/**
	 * 弹出并获取List尾部元素
	 * 
	 * @param key
	 * @return
	 */
	public String rpop(String key);
	
	/**
	 * 获取List指定index的元素
	 * 
	 * @param key
	 * @param index
	 * @return
	 */
	public String lindex(String key, int index);
	
	/**
	 * 获取List的长度
	 * 
	 * @param key
	 * @return
	 */
	public long llen(String key);
	
	/**
	 * 获取List的所有元素
	 * 
	 * @param key
	 * @return
	 */
	public List<String> lgetAll(String key);
	
	/**
	 * 获取map的长度
	 * @param key
	 * @return
	 */
	public Long hlen(String key);
	
	/**
	 * 写入hash的field value
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return 0 字段值直接覆盖 1 新建field并赋值
	 */
	public Long hset(String key, String field, String value);
	
	/**
	 * 获取hash的field value
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public String hget(String key, String field);
	
	/**
	 * 写入hash的field 对象的json
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return 0 字段值直接覆盖 1 新建field并赋值
	 */
	public Long hsetObj(String key, String field, Object value);
	
	/**
	 * 获取hash的field json转化的对象
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public <T> T hgetObj(String key, String field, Class<T> clazz);
	
	/**
	 * 写入hash的多个field value
	 * 
	 * @param key
	 * @param keyValues
	 * @return
	 */
	public boolean hmset(String key, Map<String, String> keyValues);
	
	/**
	 * 获取hash的多个field json转化的对象
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public List<String> hmget(String key, String... fields);
	
	/**
	 * 写入hash的多个field 对象的json
	 * 
	 * @param key
	 * @param field
	 * @param value
	 * @return
	 */
	public boolean hmsetObjects(String key, Map<String, Object> keyValues);
	
	/**
	 * 获取hash的多个field json转化的对象
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public <T> List<T> hmgetObjects(String key, Class<T> clazz, String... fields);
	
	/**
	 * hash中的field对应值 加 num
	 * 
	 * @param key
	 * @param field
	 * @param num
	 * @return 新增后的值
	 */
	public Long hincrby(String key, String field, int num);
	
	/**
	 * 删除hash的field
	 * 
	 * @param key
	 * @param field
	 * @return
	 */
	public boolean hdel(String key, String... field);
	
	/**
	 * 获取hash的所有field value
	 * 
	 * @param key
	 * @return 若key不存在，返回空的Map
	 */
	public Map<String, String> hgetAll(String key);
	
	/**
	 * 获取hash的所有field value 并转成对象
	 * 
	 * @param key
	 * @return 若key不存在，返回空的Map
	 */
	public <T> Map<String, T> hgetAll(String key, Class<T> clazz);
	
	/**
	 * 在List的pivot后插入value
	 * 
	 * @param key
	 * @param pivot
	 * @param value
	 * @return 返回插入后的队列长度，pivot不存在，返回-1，key存在，类型不对，异常
	 */
	public Long linsertAfter(String key, String pivot, String value);
	
	/**
	 * 在List的pivot前插入value
	 * 
	 * @param key
	 * @param pivot
	 * @param value
	 * @return 返回插入后的队列长度，pivot不存在，返回-1，key存在，类型不对，异常
	 */
	public Long linsertBefore(String key, String pivot, String value);
	
	/**
	 * 从存于 key 的列表里移除前 count 次出现的值为 value 的元素。 这个 count 参数通过下面几种方式影响这个操作：
     * 
     * count > 0: 从头往尾移除值为 value 的元素。 count < 0: 从尾往头移除值为 value 的元素。 count = 0:
     * 移除所有值为 value 的元素。
     * 
     * 比如， LREM list -2 "hello" 会从存于 list 的列表里移除最后两个出现的 "hello"。
     * 
     * 需要注意的是，如果list里没有存在key就会被当作空list处理，所以当 key 不存在的时候，这个命令会返回 0。
	 * 
	 * @param key
	 * @param count
	 * @param value
	 * @return 删除的个数
	 */
	public Long lrem(String key, long count, String value);
	
	/**
	 * 修改List指定index的值为value
	 * 
	 * @param key
	 * @param index
	 * @param value
	 * @return
	 */
	public boolean lset(String key, long index, String value);
	
	/**
	 * 获取匹配的key集合
	 * @param key
	 * @return
	 */
	public Set<String> keys(String key);
	
	/**
	 * 查询Set
	 * @param key
	 * @return
	 */
	public Set<String> smembers(String key);
	
	/**
	 * 在Set队尾加入value
	 * @param key
	 * @param members
	 * @return
	 */
	public Long sadd(String key, String... members);
	
	/**
	 * 删除Set指定的对象
	 * @param key
	 * @param members
	 * @return
	 */
	public Long srem(String key, String... members);
	
	/**
	 * 判断Set中是否包含指导对象
	 * @param key
	 * @param member
	 * @return
	 */
	public Boolean sismember(String key, String member);

	/**
	 * 如果key不存在，则set，如果key存在，则无操作
	 * @param key
	 * @param value
     * @return
     */
	public Long setnx(String key, String value);

	/**
	 * 设置key的超时时间
	 * @param key
	 * @param seconds
     * @return
     */
	public Long expire(String key, int seconds);

	/**
	 * 检查key是否存在
	 * @param key
	 * @return
	 */
	public boolean exist(String key);
}
