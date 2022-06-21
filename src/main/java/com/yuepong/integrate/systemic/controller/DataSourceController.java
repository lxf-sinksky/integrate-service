package com.yuepong.integrate.systemic.controller;

import com.yuepong.integrate.common.result.ResponseResult;
import com.yuepong.integrate.systemic.entity.datasource.DataSourceInfo;
import com.yuepong.integrate.systemic.service.DataSourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * @ClassName DataSourceController
 * @Description 数据源操作
 * @Author lixuefei
 * @Date 2021/4/16 16:12
 **/
@Slf4j
@RestController
@RequestMapping("ds")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @GetMapping("list")
    public ResponseResult getDsList() {
        List<DataSourceInfo> list = dataSourceService.getDsList();
        return ResponseResult.success(list);
    }


}
