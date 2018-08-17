package com.xin.cache;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableSet;

import java.util.concurrent.TimeUnit;

public class GuavaCache {
    public static void main(String[] args) throws Exception {
        System.out.println("guava cache 是单应用本地缓存");

        LoadingCache<String, String> test = CacheBuilder.newBuilder().maximumSize(3000)//缓存项目最大条数
                .expireAfterWrite(5000, TimeUnit.SECONDS)//写后有效时间
                .expireAfterAccess(5000, TimeUnit.SECONDS)//访问后有效时间，包括读写
                .removalListener(//移除时候回调
                        removalNotification -> {
                            System.out.println(removalNotification.getCause());
                            System.out.println(removalNotification.getKey());
                            System.out.println(removalNotification.getValue());
                        }
                )
                .build(new CacheLoader<String, String>() {
                    //缓存中没有值的时候，执行此方法，往缓存中写入
                    @Override
                    public String load(String s) {
                        return "auto";
                    }
                });
        test.put("a", "aValue");
        System.out.println(test.get("a"));
        test.invalidate("a");//移除缓存
        System.out.println(test.get("a"));//缓存中没有该key，执行load方法读取
        test.invalidateAll(ImmutableSet.of("a"));//批量清除
        test.invalidateAll();//清除所有

    }
}
