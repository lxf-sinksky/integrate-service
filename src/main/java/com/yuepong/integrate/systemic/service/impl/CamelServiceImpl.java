package com.yuepong.integrate.systemic.service.impl;

import com.yuepong.integrate.systemic.exception.NullRouteException;
import com.yuepong.integrate.systemic.exception.RouteStateControlException;
import com.yuepong.integrate.systemic.service.CamelService;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.Route;
import org.apache.camel.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @ClassName CamelServiceImpl
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.4.12 13:34
 **/
@Service
public class CamelServiceImpl implements CamelService {
    
    @Autowired
    private CamelContext camelContext;
    @Autowired
    private ProducerTemplate template;
    
    
    @Override
    public Route getRoute(String routeId) {
        Route route = camelContext.getRoute(routeId);
        if (route == null) {
            throw new NullRouteException("The route with ID " + routeId + " was not found");
        }
        return route;
    }
    
    /**
     * 获取路由说明
     *
     * @param routeId:路由id
     * @return RouteDescription:路由说明
     */
    @Override
    public String getRouteDescription(String routeId) {
        camelContext.getRoute(routeId).getDescription();
        return null;
        
    }
    
    /**
     * 获取路由状态
     *
     * @param routeId:路由id
     * @return ServiceStatus
     */
    @Override
    public ServiceStatus getRouteStatus(String routeId) {
        if (camelContext.getRoute(routeId) != null) {
            return camelContext.getRouteController().getRouteStatus(routeId);
        } else {
            throw new RouteStateControlException("The route with ID " + routeId + " was not found");
        }
    }
    
    /**
     * 启动路由
     *
     * @param routeId:路由id
     * @return Enum<ServiceStatus>
     */
    @Override
    public Enum<ServiceStatus> startRoute(String routeId) {
        if (camelContext.getRoute(routeId) != null) {
            try {
                camelContext.getRouteController().startRoute(routeId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return camelContext.getRouteController().getRouteStatus(routeId);
        } else {
            throw new RouteStateControlException("The route with ID " + routeId + " was not found");
        }
    }
    
    /**
     * 终止路由
     *
     * @param routeId:路由id
     * @return Enum<ServiceStatus>
     */
    @Override
    public Enum<ServiceStatus> stopRoute(String routeId) {
        if (camelContext.getRoute(routeId) != null) {
            try {
                camelContext.getRouteController().stopRoute(routeId, 3L, TimeUnit.MINUTES);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return camelContext.getRouteController().getRouteStatus(routeId);
        } else {
            throw new RouteStateControlException("The route with ID " + routeId + " was not found");
        }
        
    }
    
    /**
     * 暂停路由
     *
     * @param routeId:路由id
     * @return Enum<ServiceStatus>
     */
    @Override
    public Enum<ServiceStatus> suspendRoute(String routeId) {
        if (camelContext.getRoute(routeId) != null) {
            try {
                camelContext.getRouteController().suspendRoute(routeId, 3L, TimeUnit.MINUTES);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return camelContext.getRouteController().getRouteStatus(routeId);
        } else {
            throw new RouteStateControlException("The route with ID " + routeId + " was not found");
        }
    }
    
    /**
     * 恢复路由
     *
     * @param routeId:路由id
     * @return Enum<ServiceStatus>
     */
    @Override
    public Enum<ServiceStatus> resumeRoute(String routeId) {
        if (camelContext.getRoute(routeId) != null) {
            try {
                camelContext.getRouteController().resumeRoute(routeId);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return camelContext.getRouteController().getRouteStatus(routeId);
        } else {
            throw new RouteStateControlException("The route with ID " + routeId + " was not found");
        }
    }
    
    /**
     * 删除路由
     *
     * @param routeId:路由id
     * @return Enum<ServiceStatus>
     */
    @Override
    public Boolean removeRoute(String routeId) {
        if (camelContext.getRoute(routeId) != null) {
            boolean isShutdown = false;
            ServiceStatus status = camelContext.getRouteController().getRouteStatus(routeId);
            if (status.isStopped()) {
                try {
                    isShutdown = camelContext.removeRoute(routeId);
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return isShutdown;
            } else {
                stopRoute(routeId);
                try {
                    isShutdown = camelContext.removeRoute(routeId);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                return isShutdown;
            }
        } else {
            throw new RouteStateControlException("The route with ID " + routeId + " was not found");
        }
        
    }
}
