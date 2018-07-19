package com.bone.github.cache;

import java.io.Serializable;

/**
 * @author hejiwei
 * @date 17/3/16
 */
public class CacheBuilder<K, V extends Serializable> {

    private String cachePrefix;
    /**
     * 过期时间
     * 单位(s)
     */
    private long expireTime = 365 * 24 * 60 * 60;

    private CacheBuilder(String cachePrefix) {
        this.cachePrefix = cachePrefix;
    }

    public static CacheBuilder<Object, Serializable> newBuilder(String redisCachePrefix) {
        return new CacheBuilder<>(redisCachePrefix);
    }

    public CacheBuilder<K, V> expireTime(long expireTime) {
        this.expireTime = expireTime;
        return this;
    }

    public <K1 extends K, V1 extends V> Cache<K1, V1> build(CacheLoader<? super K1, V1> loader) {
        return new RedisCache<>(loader, this.expireTime, this.cachePrefix);
    }
}
