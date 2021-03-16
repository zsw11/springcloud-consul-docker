package com.zsw.provider.controller.inner;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zsw
 * @date 2021/3/10 17:56
 * @description :
 */
@RestController
@Api("hello测试")
public class SayController {

    @GetMapping("/hello")
    public String sayHello() {
        return "hello";
    }

    //给swagger2用的,防止报4040
    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/csrf")
    public String csrf() {
        return "index";
    }

}
