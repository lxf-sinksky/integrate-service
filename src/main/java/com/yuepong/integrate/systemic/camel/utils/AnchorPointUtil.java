package com.yuepong.integrate.systemic.camel.utils;

import com.yuepong.integrate.systemic.service.AnchorPointService;
import com.yuepong.integrate.systemic.service.StaticAnchorPointService;
import org.apache.camel.Header;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

/**
 * @ClassName AnchorUtil
 * @Description 锚点工具类
 * @Author lixuefei
 * @Date 2022.6.15 11:43
 **/
@Component("AnchorPointUtil")
@DependsOn("StaticAnchorPointService")
public class AnchorPointUtil {
    
    private static final AnchorPointService anchorPointService = StaticAnchorPointService.staticAnchorPointService;
    
    /**
     * 获取锚点值
     *
     * @param namespace: 命名空间
     * @return 锚点值
     */
    public static String getAnchor(@Header("namespace") String namespace) {
        return anchorPointService.getAnchor(namespace);
    }
    
    /**
     * 设置锚点，如果Headers中没有定义anchorValue变量，则默认锚点为当前时间的字符串(格式为: yyyy-MM-dd hh:mm:ss)
     *
     * @param namespace: 命名空间
     * @param value:     锚点值
     */
    public static void setAnchor(@Header("namespace") String namespace, @Header("anchorValue") String value) {
        anchorPointService.setAnchor(namespace, value);
    }
    
}
