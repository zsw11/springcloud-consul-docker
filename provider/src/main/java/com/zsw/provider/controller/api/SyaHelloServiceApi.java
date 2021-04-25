package com.zsw.provider.controller.api;


import com.zsw.provider.controller.api.fallback.SayHelloServiceApiHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zsw
 * @date 2021/3/10 17:29
 * @description :
 */
@Service
@FeignClient(name = "provider01",fallbackFactory = SayHelloServiceApiHystrix.class) //调用的服务名称
public interface SyaHelloServiceApi {

    @GetMapping(value = "/hello")
    String sayHello();

    @GetMapping("/")
     String index();

    @GetMapping("/csrf")
     String csrf();
}
