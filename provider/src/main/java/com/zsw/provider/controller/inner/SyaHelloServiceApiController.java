package com.zsw.provider.controller.inner;

import com.zsw.provider.api.SyaHelloServiceApi;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zsw
 * @date 2021/4/25 17:30
 * @description :
 */
// 以后这个实现类可以SayHelloServiceApiHystrix 分开到两个项目
@ApiOperation("Api")
@RestController
public class SyaHelloServiceApiController implements SyaHelloServiceApi {
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
