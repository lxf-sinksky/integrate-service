package com.yuepong.integrate.systemic.entity.datasource;

import lombok.Builder;
import lombok.Data;

/**
 * @ClassName DataSourceVo
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.6.10 8:30
 **/
@Data
@Builder
public class DataSourceVo {
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
}
