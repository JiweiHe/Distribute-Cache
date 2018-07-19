package com.bone.github.cache;

import java.io.Serializable;

/**
 * 缓存数据加载器
 *
 * @author hejiwei
 */
public interface CacheLoader<K, V extends Serializable> {
    /**
     * 缓存为空时的数据加载方法
     *
     * @param k k
     * @return
     */
    V load(K k);
}
