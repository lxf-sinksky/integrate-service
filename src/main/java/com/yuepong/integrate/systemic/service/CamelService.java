package com.yuepong.integrate.systemic.service;

import org.apache.camel.Route;
import org.apache.camel.ServiceStatus;

/**
 * @ClassName CamelService
 * @Description Camel相关服务类
 * @Author lixuefei
 * @Date 2022.4.12 13:34
 **/
public interface CamelService {

    /**
     * 获取路由
     *
     * @param routeId:路由id
     * @return Route:路由
     */
    Route getRoute(String routeId);

    /**
     * 获取路由说明
     *
     * @param routeId:路由id
     * @return RouteDescription:路由说明
     */
    String getRouteDescription(String routeId);

    /**
     * 获取路由状态
     *
     * @param routeId:路由id
     * @return ServiceStatus
     */
    ServiceStatus getRouteStatus(String routeId);

    /**
     * 启动路由
     *
     * @param routeId:路由id
     * @return Enum<ServiceStatus>
     */
    Enum<ServiceStatus> startRoute(String routeId);

    /**
     * 终止路由
     *
     * @param routeId:路由id
     * @return Enum<ServiceStatus>
     */
    Enum<ServiceStatus> stopRoute(String routeId);

    /**
     * 暂停路由
     *
     * @param routeId:路由id
     * @return Enum<ServiceStatus>
     */
    Enum<ServiceStatus> suspendRoute(String routeId);

    /**
     * 恢复路由
     *
     * @param routeId:路由id
     * @return Enum<ServiceStatus>
     */
    Enum<ServiceStatus> resumeRoute(String routeId);

    /**
     * 删除路由
     *
     * @param routeId:路由id
     * @return Enum<ServiceStatus>
     */
    Boolean removeRoute(String routeId);
}
