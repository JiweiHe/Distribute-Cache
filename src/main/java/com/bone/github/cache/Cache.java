package com.bone.github.cache;

/**
 * Created by mike on 17/3/16.
 */
public interface Cache<K, V> {

    /**
     * 通过键值获取获取缓存值
     *
     * @param k k
     * @return
     */
    V get(K k);

    /**
     * 通过键值刷新缓存值
     *
     * @param k k
     */
    void refresh(K k);
}
