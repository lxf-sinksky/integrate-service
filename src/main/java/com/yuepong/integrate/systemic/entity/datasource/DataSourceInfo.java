package com.yuepong.integrate.systemic.entity.datasource;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName DataSourceInfo
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.5.11 9:57
 **/
@Data
@Builder
public class DataSourceInfo {
    /**
     * 数据源名称
     */
    private String name;
    /**
     * 数据源类型
     */
    private String type;
    /**
     * 主机地址:端口号
     */
    private String address;
    /**
     * 数据库名称
     */
    private String dataBaseName;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    
    
    public DataSourceVo convertToVo() {
        return DataSourceVo.builder()
                .name(name)
                .type(type)
                .address(address)
                .dataBaseName(dataBaseName)
                .username(username)
                .build();
    }
    
}
