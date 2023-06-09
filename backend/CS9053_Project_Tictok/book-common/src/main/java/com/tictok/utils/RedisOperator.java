package com.tictok.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.StringRedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisOperator {
	
	@Autowired
	private StringRedisTemplate redisTemplate;

	public boolean keyIsExist(String key) {
		return redisTemplate.hasKey(key);
	}

	public long ttl(String key) {
		return redisTemplate.getExpire(key);
	}

	public void expire(String key, long timeout) {
		redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
	}

	public long increment(String key, long delta) {
		return redisTemplate.opsForValue().increment(key, delta);
	}

	public long incrementHash(String name, String key, long delta) {
		return redisTemplate.opsForHash().increment(name, key, delta);
	}

	public long decrementHash(String name, String key, long delta) {
		delta = delta * (-1);
		return redisTemplate.opsForHash().increment(name, key, delta);
	}

	public void setHashValue(String name, String key, String value) {
		redisTemplate.opsForHash().put(name, key, value);
	}

	public String getHashValue(String name, String key) {
		return (String)redisTemplate.opsForHash().get(name, key);
	}


	public long decrement(String key, long delta) {
		return redisTemplate.opsForValue().decrement(key, delta);
	}

	public Set<String> keys(String pattern) {
		return redisTemplate.keys(pattern);
	}


	public void del(String key) {
		redisTemplate.delete(key);
	}

	public void set(String key, String value) {
		redisTemplate.opsForValue().set(key, value);
	}


	public void set(String key, String value, long timeout) {
		redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
	}

	public void setnx60s(String key, String value) {
		redisTemplate.opsForValue().setIfAbsent(key, value, 60, TimeUnit.SECONDS);
	}


	public void setnx(String key, String value) {
		redisTemplate.opsForValue().setIfAbsent(key, value);
	}

	public String get(String key) {
		return (String)redisTemplate.opsForValue().get(key);
	}


	public List<String> mget(List<String> keys) {
		return redisTemplate.opsForValue().multiGet(keys);
	}


	public List<Object> batchGet(List<String> keys) {

//		nginx -> keepalive
//		redis -> pipeline

		List<Object> result = redisTemplate.executePipelined(new RedisCallback<String>() {
			@Override
			public String doInRedis(RedisConnection connection) throws DataAccessException {
				StringRedisConnection src = (StringRedisConnection)connection;

				for (String k : keys) {
					src.get(k);
				}
				return null;
			}
		});

		return result;
	}

	public void hset(String key, String field, Object value) {
		redisTemplate.opsForHash().put(key, field, value);
	}


	public String hget(String key, String field) {
		return (String) redisTemplate.opsForHash().get(key, field);
	}

	public void hdel(String key, Object... fields) {
		redisTemplate.opsForHash().delete(key, fields);
	}


	public Map<Object, Object> hgetall(String key) {
		return redisTemplate.opsForHash().entries(key);
	}

	public long lpush(String key, String value) {
		return redisTemplate.opsForList().leftPush(key, value);
	}


	public String lpop(String key) {
		return (String)redisTemplate.opsForList().leftPop(key);
	}


	public long rpush(String key, String value) {
		return redisTemplate.opsForList().rightPush(key, value);
	}

}