package com.yuepong.integrate.systemic.service.impl;

import com.yuepong.integrate.common.utils.GeneralUtils;
import com.yuepong.integrate.systemic.dao.SimpleCacheDao;
import com.yuepong.integrate.systemic.entity.simplecache.SimpleCache;
import com.yuepong.integrate.systemic.exception.SimpleCacheException;
import com.yuepong.integrate.systemic.service.SimpleCacheService;
import com.yuepong.integrate.systemic.camel.utils.SimpleCacheUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @ClassName SimpleCacheServiceImpl
 * @Description 简易缓存服务实现类
 * @Author lixuefei
 * @Date 2022.5.26 10:41
 **/
@Service
public class SimpleCacheServiceImpl implements SimpleCacheService {
    
    @Autowired
    private SimpleCacheDao simpleCacheDao;
    
    /**
     * 创建一个永不过期的缓存
     * 如果key已经存在，则更新旧的缓存value值
     *
     * @param namespace:名称空间
     * @param key:键
     * @param value:值
     */
    @Override
    @Transactional
    public SimpleCache putCache(String namespace, String key, String value) {
        // 参数校验
        if (namespace == null || namespace.length() == 0) {
            throw new SimpleCacheException("The namespace variable was not found in the headers message");
        }
        if (key == null || key.length() == 0) {
            throw new SimpleCacheException("The cacheKey variable was not found in the headers message");
        }
        // 删除旧缓存
        simpleCacheDao.deleteSimpleCacheByNamespaceAndCacheKey(namespace, key);
        
        // 添加新缓存
        SimpleCache newCache = new SimpleCache();
        newCache.setNamespace(namespace);
        newCache.setCacheKey(key);
        newCache.setCacheValue(value);
        newCache.setCreateTime(new Date());
        newCache.setOutOfTime(null);
        newCache.setExpelDate(null);
        newCache.setExpelModel(SimpleCacheUtil.NOEXPIRY);
        return simpleCacheDao.save(newCache);
    }
    
    @Override
    @Transactional
    public SimpleCache putExpiryCache(String namespace, String key, String value, Long outOfTime) {
        // 如果没有设置过期时间，默认过期时间为1小时
        if (outOfTime == null) {
            outOfTime = SimpleCacheUtil.HOUR;
        }
        // 校验参数
        if (namespace == null || namespace.length() == 0) {
            throw new SimpleCacheException("The namespace variable was not found in the headers message");
        }
        if (key == null || key.length() == 0) {
            throw new SimpleCacheException("The cacheKey variable was not found in the headers message");
        }
        if (outOfTime <= 0L) {
            throw new IllegalArgumentException("outOfTime must be a positive value");
        }
        // 删除旧缓存
        simpleCacheDao.deleteSimpleCacheByNamespaceAndCacheKey(namespace, key);
        
        SimpleCache simpleCache = new SimpleCache();
        simpleCache.setNamespace(namespace);
        simpleCache.setCacheKey(key);
        simpleCache.setCacheValue(value);
        simpleCache.setCreateTime(new Date());
        simpleCache.setExpelDate(GeneralUtils.overtimeDate(new Date(), outOfTime));
        simpleCache.setOutOfTime(outOfTime);
        simpleCache.setExpelModel(SimpleCacheUtil.TIMETOLIVE);
        return simpleCacheDao.save(simpleCache);
        
    }
    
    @Override
    @Transactional
    public SimpleCache putHotCache(String namespace, String key, String value, Long outOfTime) {
        // 如果没有设置过期时间，默认过期时间为1小时
        if (outOfTime == null) {
            outOfTime = SimpleCacheUtil.HOUR;
        }
        // 校验参数
        if (namespace == null || namespace.length() == 0) {
            throw new SimpleCacheException("The namespace variable was not found in the headers message");
        }
        if (key == null || key.length() == 0) {
            throw new SimpleCacheException("key cannot be empty!");
        }
        if (outOfTime <= 0L) {
            throw new IllegalArgumentException("Timeout must be a positive value");
        }
        // 删除旧缓存
        simpleCacheDao.deleteSimpleCacheByNamespaceAndCacheKey(namespace, key);
        
        SimpleCache simpleCache = new SimpleCache();
        simpleCache.setNamespace(namespace);
        simpleCache.setCacheKey(key);
        simpleCache.setCacheValue(value);
        simpleCache.setCreateTime(new Date());
        simpleCache.setExpelDate(GeneralUtils.overtimeDate(new Date(), outOfTime));
        simpleCache.setOutOfTime(outOfTime);
        simpleCache.setExpelModel(SimpleCacheUtil.TIMETOIDLE);
        return simpleCacheDao.save(simpleCache);
    }
    
    /**
     * 获取所有缓存信息
     *
     * @return 所有缓存信息
     */
    @Override
    public List<SimpleCache> selectAll() {
        return simpleCacheDao.findAll();
    }
    
    @Override
    @Transactional
    public void expelCache() {
        List<String> models = new ArrayList<>();
        models.add(SimpleCacheUtil.TIMETOLIVE);
        models.add(SimpleCacheUtil.TIMETOIDLE);
        simpleCacheDao.deleteAllByExpelModelInAndExpelDateIsLessThanEqual(models, new Date());
    }
    
    @Override
    public void resetExpelDate(SimpleCache cache, Date date) {
        Long outTime = cache.getOutOfTime();
        cache.setExpelDate(GeneralUtils.overtimeDate(date, outTime));
        simpleCacheDao.save(cache);
    }
    
    
}
