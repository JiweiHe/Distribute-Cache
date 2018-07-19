package com.bone.github;

import com.bone.github.cache.Cache;
import com.bone.github.cache.CacheBuilder;
import com.bone.github.cache.CacheLoader;

public class CacheTest {

    public static void main(String[] args) {
        int time = 10;
        String redisPrefixKey = "cachePrefix";
        Cache<String, String> cache = CacheBuilder
                .newBuilder(redisPrefixKey)
                .expireTime(time)
                .build(new CacheLoader<String, String>() {
                    @Override
                    public String load(String s) {
                        //do something to get data
                        String result = "to to cache data";
                        return result;
                    }
                });
        int count = 10 * 5;
        while (count > 0) {
            System.out.println(cache.get(redisPrefixKey));
            try {
                count--;
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
