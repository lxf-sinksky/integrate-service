package com.yuepong.integrate.systemic.controller;

import com.yuepong.integrate.common.result.ResponseResult;
import com.yuepong.integrate.systemic.service.CamelService;
import org.apache.camel.ServiceStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName CamelController
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.5.13 14:29
 **/
@RestController
@RequestMapping("camel")
public class CamelCommonApi {

    @Autowired
    private CamelService camelService;


    @GetMapping("/route/status/{id}")
    public ResponseResult getRouteStatus(@PathVariable String id) {
        ServiceStatus routeStatus = camelService.getRouteStatus(id);
        return ResponseResult.success(routeStatus);
    }

    /**
     * 启动路由
     * @param id:路由id
     * @return ResponseResult
     */
    @PostMapping("/route/start/{id}")
    public ResponseResult startRoute(@PathVariable String id) {
        Enum<ServiceStatus> serviceStatusEnum = camelService.startRoute(id);
        if ("Started".equals(serviceStatusEnum.name())){
            return ResponseResult.success(serviceStatusEnum);
        }else {
            return ResponseResult.error();
        }
    }

    /**
     * 终止路由
     * @param id:路由id
     * @return ResponseResult
     */
    @PostMapping("/route/stop/{id}")
    public ResponseResult stopRoute(@PathVariable String id) {
        Enum<ServiceStatus> serviceStatusEnum = camelService.stopRoute(id);
        if ("Stopped".equals(serviceStatusEnum.name())){
            return ResponseResult.success(serviceStatusEnum);
        }else {
            return ResponseResult.error();
        }
    }

    /**
     * 暂停路由
     * @param id:路由id
     * @return ResponseResult
     */
    @PostMapping("/route/suspend/{id}")
    public ResponseResult suspendRoute(@PathVariable String id) {
        Enum<ServiceStatus> serviceStatusEnum = camelService.suspendRoute(id);
        if ("Suspended".equals(serviceStatusEnum.name())){
            return ResponseResult.success(serviceStatusEnum);
        }else {
            return ResponseResult.error();
        }
    }

    /**
     * 恢复路由
     * @param id:路由id
     * @return ResponseResult
     */
    @PostMapping("/route/resume/{id}")
    public ResponseResult resumeRoute(@PathVariable String id) {
        Enum<ServiceStatus> serviceStatusEnum = camelService.resumeRoute(id);
        if ("Started".equals(serviceStatusEnum.name())){
            return ResponseResult.success(serviceStatusEnum);
        }else {
            return ResponseResult.error();
        }
    }

    /**
     * 删除路由
     * @param id:路由id
     * @return ResponseResult
     */
    @PostMapping("/route/remove/{id}")
    public ResponseResult removeRoute(@PathVariable String id) {
        Boolean isShutdown = camelService.removeRoute(id);
        if (isShutdown){
            return ResponseResult.success("操作成功！","Shutdown");
        }else {
            return ResponseResult.error();
        }
    }
}
