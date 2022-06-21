package com.yuepong.integrate.systemic.service;

import com.yuepong.integrate.systemic.entity.datasource.DataSourceInfo;

import java.util.List;

public interface DataSourceService {
    
    /**
     * 获取数据源信息列表
     *
     * @return List<DataSourceInfo>
     */
    List<DataSourceInfo> getDsList();
    
    /**
     * 根据beanName获取数据源信息
     *
     * @param beanName:数据源bean名称
     * @return DataSourceInfo
     */
    DataSourceInfo getDs(String beanName);
}
