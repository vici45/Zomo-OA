package com.zomo.oa.util;


import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.concurrent.TimeUnit;

public class TokenCache {
    private static LoadingCache<String,String> localCache =CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(5,TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key,String value){
        localCache.put(key,value);
    }

    public static String getValue(String key){
        String value=null;
        try {
            value=localCache.get(key);
            if (value=="null")
                return null;
        }catch (Exception e){
            e.printStackTrace();
        }
        return value;
    }
}
