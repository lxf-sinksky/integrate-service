package com.yuepong.integrate.systemic.dao;

import com.yuepong.integrate.systemic.entity.anchorpoint.AnchorPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

/**
 * @ClassName AnchorPointDao
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.6.14 9:44
 **/
public interface AnchorPointDao extends JpaRepository<AnchorPoint, String> {
    
    AnchorPoint getByNamespace(String namespace);
    
    @Modifying
    @Transactional
    @Query("delete from AnchorPoint point where point.namespace = :namespace")
    void deleteByNamespace(String namespace);
}
