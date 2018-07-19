package com.bone.github.jedis;

import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author mike
 */
public class JedisSingleton {

    private static volatile JedisPool jedisPool;

    public static JedisPool getInstance() {
        if (jedisPool == null) {
            synchronized (JedisSingleton.class) {
                if (jedisPool == null) {
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379, 2000, null);
                }
            }
        }
        return jedisPool;
    }
}
