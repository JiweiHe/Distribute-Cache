基于redis的分布式缓存实现
===


    Cache<String, String> cache = CacheBuilder
                    .newBuilder("redisCacheKeyPrefix") //redis中缓存值的key前缀
                    .expireTime(100) //过期时间 单位(秒) 默认过期时间为一年 
                   .build(new CacheLoader<String, String>() {
                                       @Override
                                       public String load(String s) {
                                           //do something to get data
                                           String result = "to to cache data";
                                           return result;
                                       }
                                   });

    String cacheData = cache.get("cacheKey"); //get cache data

    cache.refresh("cacheKey");// refresh cache