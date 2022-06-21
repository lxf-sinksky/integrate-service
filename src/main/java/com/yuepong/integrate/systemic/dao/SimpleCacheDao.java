package com.yuepong.integrate.systemic.dao;

import com.yuepong.integrate.systemic.entity.simplecache.SimpleCache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @ClassName SimpleCacheDao
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.6.14 9:44
 **/
public interface SimpleCacheDao extends JpaRepository<SimpleCache, String> {
    
    SimpleCache getSimpleCacheByNamespaceAndCacheKey(String namespace, String cacheKey);
    
    @Modifying
    @Transactional
    void deleteAllByExpelModelInAndExpelDateIsLessThanEqual(List<String> model, Date currentDate);
    
    @Modifying
    @Transactional
    @Query("delete from SimpleCache cache where cache.namespace = :namespace and cache.cacheKey = :cacheKey")
    void deleteSimpleCacheByNamespaceAndCacheKey(String namespace, String cacheKey);
    
}
