package com.yuepong.integrate.systemic.service.impl;

import com.yuepong.integrate.common.utils.GeneralUtils;
import com.yuepong.integrate.systemic.dao.AnchorPointDao;
import com.yuepong.integrate.systemic.entity.anchorpoint.AnchorPoint;
import com.yuepong.integrate.systemic.exception.AnchorPointException;
import com.yuepong.integrate.systemic.service.AnchorPointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @ClassName AnchorPointServiceImpl
 * @Description 锚点服务实现类
 * @Author lixuefei
 * @Date 2022.6.15 11:50
 **/
@Service
public class AnchorPointServiceImpl implements AnchorPointService {
    
    @Autowired
    private AnchorPointDao anchorPointDao;
    
    @Override
    public String getAnchor(String namespace) {
        // 参数校验
        if (namespace == null || namespace.length() == 0) {
            throw new AnchorPointException("The namespace variable was not found in the headers message");
        }
        AnchorPoint anchorPoint = anchorPointDao.getByNamespace(namespace);
        if (anchorPoint == null) {
            return null;
        } else {
            return anchorPoint.getAnchorValue();
        }
    }
    
    @Override
    public void setAnchor(String namespace, String value) {
        // 参数校验
        if (namespace == null || namespace.length() == 0) {
            throw new AnchorPointException("The namespace variable was not found in the headers message");
        }
        if (value == null || value.length() == 0) {
            value = GeneralUtils.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        }
        anchorPointDao.deleteByNamespace(namespace);
        
        AnchorPoint anchorPoint = new AnchorPoint();
        anchorPoint.setNamespace(namespace);
        anchorPoint.setAnchorValue(value);
        anchorPointDao.save(anchorPoint);
    }
}
