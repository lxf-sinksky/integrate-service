package com.yuepong.integrate.systemic.controller;

import com.yuepong.integrate.common.result.ResponseResult;
import com.yuepong.integrate.systemic.camel.utils.SimpleCacheUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName SimpleCacheController
 * @Description TODO
 * @Author lixuefei
 * @Date 2022.6.14 15:14
 **/
@RestController
@RequestMapping("cache")
public class SimpleCacheController {
    
    @GetMapping("cache")
    public ResponseResult getPoolCache(String namespace, String key) {
        return ResponseResult.success("查询成功", SimpleCacheUtil.getCache(namespace, key));
    }
    
    @GetMapping("cachePool")
    public ResponseResult getCachePool() {
        return ResponseResult.success(SimpleCacheUtil.getPoolCaches());
    }
    
    @PostMapping("putCache")
    public ResponseResult putCache(String namespace, String key, String value) {
        SimpleCacheUtil.putCache(namespace, key, value);
        return ResponseResult.success();
    }
    
    
}
