package com.zsw.provider.api;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zsw
 * @date 2021/3/10 17:29
 * @description :
 */
@FeignClient(name = "provider01") //调用的服务名称
public interface SyaHelloServiceApi {

    @GetMapping(value = "api/hello")
    String sayHello();

    @GetMapping("api/")
     String index();

    @GetMapping("api/csrf")
     String csrf();
}
