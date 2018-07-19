package com.bone.github.cache;

import java.io.Serializable;

import com.bone.github.cache.util.SerialUtil;
import com.bone.github.jedis.JedisSingleton;

import redis.clients.jedis.Jedis;

public class RedisCache<K, V extends Serializable> implements Cache<K, V> {
    private long expireTime;
    private String redisCachePrefix;
    private CacheLoader<? super K, V> loader;

    public RedisCache(CacheLoader<? super K, V> loader, long expireTime, String redisCachePrefix) {
        this.expireTime = expireTime;
        this.redisCachePrefix = redisCachePrefix;
        this.loader = loader;
    }

    @Override
    public void refresh(K k) {
        try (Jedis jedis = JedisSingleton.getInstance().getResource()) {
            jedis.expire(getKey(k), 0);
        }
    }

    @Override
    public V get(K k) {
        V result = null;
        byte[] redisKey = getKey(k).getBytes();
        try (Jedis jedis = JedisSingleton.getInstance().getResource()) {
            byte[] cacheValue = jedis.get(redisKey);
            try {
                if (cacheValue == null) {
                    cacheValue = put(redisKey, k);
                }
                result = SerialUtil.readObject(cacheValue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private String getKey(K k) {
        return this.redisCachePrefix + k;
    }

    @SuppressWarnings("unchecked")
    private byte[] put(byte[] key, K k) {
        V result = (V) loader.load(k);
        try (Jedis jedis = JedisSingleton.getInstance().getResource()) {
            if (result != null) {
                byte[] cacheValue = SerialUtil.writeObject(result);
                jedis.set(key, cacheValue, "NX".getBytes(), "EX".getBytes(), this.expireTime);
                return cacheValue;
            } else {
                throw new NullPointerException("can't fetch data from mongo on cache data");
            }
        }
    }
}
