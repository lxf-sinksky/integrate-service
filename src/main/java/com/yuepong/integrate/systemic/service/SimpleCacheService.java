package com.yuepong.integrate.systemic.service;

import com.yuepong.integrate.systemic.entity.simplecache.SimpleCache;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SimpleCacheService
 * @Description 简单缓存服务类
 * @Author lixuefei
 * @Date 2022.5.26 10:40
 **/
public interface SimpleCacheService {
    
    
    /**
     * 创建一个永不过期的缓存
     * 如果key已经存在，则更新旧的缓存value值
     *
     * @param key:键
     * @param value:值
     */
    SimpleCache putCache(String namespace, String key, String value);
    
    /**
     * 创建一个有驱逐时间的缓存
     * 如果key已经存在，则更新旧的缓存value值
     *
     * @param key:键
     * @param value:值
     */
    SimpleCache putExpiryCache(String namespace, String key, String value, Long outOfTime);
    
    /**
     * 创建一个有驱逐时间的缓存，每当该缓存被访问时，如果该缓存未被驱逐，则重置驱逐时间
     * 如果key已经存在，则更新旧的缓存value值
     *
     * @param key:键
     * @param value:值
     */
    SimpleCache putHotCache(String namespace, String key, String value, Long outOfTime);
    
    /**
     * 获取所有缓存信息
     *
     * @return
     */
    List<SimpleCache> selectAll();
    
    /**
     * 清除过期缓存
     */
    void expelCache();
    
    /**
     * 重置缓存驱逐时间
     *
     * @param cache 缓存实体对象
     * @param date  当前时间
     */
    void resetExpelDate(SimpleCache cache, Date date);
}
