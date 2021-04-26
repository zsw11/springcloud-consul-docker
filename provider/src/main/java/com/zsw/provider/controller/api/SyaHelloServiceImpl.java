package com.zsw.provider.controller.api;

import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zsw
 * @date 2021/4/25 17:30
 * @description :
 */
// 以后这个实现类可以SayHelloServiceApiHystrix 分开到两个项目
@ApiOperation("Api")
@RestController
public class SyaHelloServiceImpl implements SyaHelloServiceApi  {
    @Override
    public String sayHello() {
        return "say Api hello";
    }

    @Override
    public String index() {
        return "index";
    }

    @Override
    public String csrf() {
        return "index";
    }
}
