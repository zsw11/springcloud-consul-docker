package com.zsw.comsumer.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.zsw.comsumer.service.hystric.SayHelloServiceHystrix;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zsw
 * @date 2021/3/10 17:29
 * @description :
 */
@Service
@FeignClient(name = "provider01",fallbackFactory = SayHelloServiceHystrix.class) //调用的服务名称
public interface SyaHelloService {

    @GetMapping(value = "/hello")
    String sayHello();

    @GetMapping("/")
     String index();

    @GetMapping("/csrf")
     String csrf();
}
