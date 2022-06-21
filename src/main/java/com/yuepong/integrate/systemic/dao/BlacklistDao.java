package com.yuepong.integrate.systemic.dao;

import com.yuepong.integrate.systemic.entity.blacklist.Blacklist;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName BlacklistDao
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.6.14 9:44
 **/
public interface BlacklistDao extends JpaRepository<Blacklist, String> {
    
    Blacklist getByNamespaceAndBlackKey(String namespace, String blackKey);
    
}
