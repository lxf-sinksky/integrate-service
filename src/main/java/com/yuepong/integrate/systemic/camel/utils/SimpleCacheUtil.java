package com.yuepong.integrate.systemic.camel.utils;

import com.yuepong.integrate.common.utils.GeneralUtils;
import com.yuepong.integrate.systemic.entity.simplecache.SimpleCache;
import com.yuepong.integrate.systemic.service.SimpleCacheService;
import com.yuepong.integrate.systemic.service.StaticSimpleCacheService;
import org.apache.camel.Header;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SimpleCacheUtil
 * @Description 简单缓存工具类
 * @Author lixuefei
 * @Date 2022.5.26 10:35
 **/
@Component("SimpleCacheUtil")
@DependsOn("StaticSimpleCacheService")
public class SimpleCacheUtil {
    
    // 缓存永远不会过期
    public static final String NOEXPIRY = "no expiry";
    // 缓存将在创建后的固定持续时间后过期
    public static final String TIMETOLIVE = "time-to-live";
    // 缓存将在最后一次访问后的固定持续时间后过期
    public static final String TIMETOIDLE = "time-to-idle";
    
    
    public static final Long SECOND = 1000L;
    public static final Long MINUTE = 60000L;
    public static final Long HOUR = 3600000L;
    public static final Long DAY = 86400000L;
    
    private static final SimpleCacheService simpleCacheService = StaticSimpleCacheService.staticSimpleCacheService;
    
    // 缓存池
    public static List<SimpleCache> simpleCachePool = new ArrayList<>();
    
    // 程序启动时初始化数据库中的缓存到缓存池中
    @PostConstruct
    public void init() {
        simpleCachePool = simpleCacheService.selectAll();
    }
    
    
    /**
     * 根据namespace和cacheKey获取缓存值
     *
     * @param namespace: 名称空间
     * @param cacheKey:  缓存键
     * @return
     */
    public static String getCache(@Header("namespace") String namespace, @Header("cacheKey") String cacheKey) {
        return getPoolCache(namespace, cacheKey);
    }
    
    
    /**
     * 创建一个永不过期的缓存
     * 如果key已经存在，则更新旧的缓存value值
     *
     * @param namespace: 名称空间
     * @param key:       缓存键
     * @param value:     缓存值
     */
    public static void putCache(@Header("namespace") String namespace, @Header("cacheKey") String key, @Header("cacheValue") String value) {
        SimpleCache simpleCache = simpleCacheService.putCache(namespace, key, value);
        removePoolCache(namespace, key);
        simpleCachePool.add(simpleCache);
    }
    
    /**
     * 创建一个带有驱逐时间的缓存，缓存将在时间到达之后被驱逐
     * 如果key已经存在，则更新旧的缓存value值
     *
     * @param namespace: 名称空间
     * @param key:       缓存键
     * @param value:     缓存值
     * @param outOfTime: 驱逐时间
     */
    public static void putExpiryCache(@Header("namespace") String namespace, @Header("cacheKey") String key, @Header("cacheValue") String value, @Header("outOfTime") Long outOfTime) {
        SimpleCache simpleCache = simpleCacheService.putExpiryCache(namespace, key, value, outOfTime);
        removePoolCache(namespace, key);
        simpleCachePool.add(simpleCache);
    }
    
    /**
     * 创建一个带有驱逐时间的缓存，缓存将在时间到达之后被驱逐
     * 如果key已经存在，则更新旧的缓存value值
     *
     * @param namespace: 名称空间
     * @param key:       缓存键
     * @param value:     缓存值
     * @param outOfTime: 驱逐时间
     */
    public static void putHotCache(@Header("namespace") String namespace, @Header("cacheKey") String key, @Header("cacheValue") String value, @Header("outOfTime") Long outOfTime) {
        SimpleCache simpleCache = simpleCacheService.putHotCache(namespace, key, value, outOfTime);
        removePoolCache(namespace, key);
        simpleCachePool.add(simpleCache);
    }
    
    private static String getPoolCache(String namespace, String cacheKey) {
        try {
            SimpleCache simpleCache = simpleCachePool.stream().filter(cache -> {
                if (cache.getNamespace().equals(namespace) && cache.getCacheKey().equals(cacheKey)) {
                    if (!cache.getExpelModel().equals(SimpleCacheUtil.NOEXPIRY) && cache.getExpelDate().getTime() <= System.currentTimeMillis()) {
                        removePoolCache(namespace, cacheKey);
                        return false;
                    } else {
                        if (cache.getExpelModel().equals(SimpleCacheUtil.TIMETOIDLE)) {
                            Date date = new Date();
                            simpleCacheService.resetExpelDate(cache, date);
                            resetExpelDate(cache.getId(), date);
                        }
                        return true;
                    }
                } else {
                    return false;
                }
            }).findFirst().get();
            return simpleCache.getCacheValue();
        } catch (RuntimeException e) {
            return null;
        }
    }
    
    private static void resetExpelDate(String id, Date date) {
        for (SimpleCache simpleCache : simpleCachePool) {
            if (simpleCache.getId().equals(id)) {
                Long outTime = simpleCache.getOutOfTime();
                simpleCache.setExpelDate(GeneralUtils.overtimeDate(date, outTime));
            }
        }
    }
    
    public static void expelCache() {
        simpleCacheService.expelCache();
        expelPoolCache();
    }
    
    private static void expelPoolCache() {
        simpleCachePool.removeIf(cache -> !cache.getExpelModel().equals(SimpleCacheUtil.NOEXPIRY) && cache.getExpelDate().getTime() <= System.currentTimeMillis());
    }
    
    private static void removePoolCache(String namespace, String key) {
        simpleCachePool.removeIf(cache -> cache.getNamespace().equals(namespace) && cache.getCacheKey().equals(key));
    }
    
    public static List<SimpleCache> getPoolCaches() {
        return simpleCachePool;
    }
    
    
}
