package com.yuepong.integrate.systemic.service.impl;

import com.yuepong.integrate.systemic.entity.datasource.DataSourceInfo;
import com.yuepong.integrate.systemic.service.DataSourceService;
import com.yuepong.integrate.systemic.utils.ResourceLoadUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @ClassName DataSourceServiceImpl
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.5.11 9:56
 **/
@Service
@Slf4j
public class DataSourceServiceImpl implements DataSourceService {
    
    /**
     * 获取数据源信息列表
     *
     * @return List<DataSourceInfo>
     */
    @Override
    public List<DataSourceInfo> getDsList() {
        return ResourceLoadUtil.dataSourceInfoList;
    }
    
    public DataSourceInfo getDs(String beanName) {
        return ResourceLoadUtil.dataSourceInfoList.stream().filter(s -> Objects.equals(s.getName(), beanName)).findFirst().orElse(null);
    }
    
    
}
