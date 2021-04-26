package com.zsw.comsumer.controller.outer;

import com.zsw.provider.controller.api.SyaHelloServiceApi;
import com.zsw.provider.controller.api.SyaHelloServiceImpl;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author zsw
 * @date 2021/3/10 17:35
 * @description : 测试 hello
 */
@RestController
@RequestMapping("/user")
public class HelloController {

    // 服务名
//    @Value("${provider}")
    private static final String REST_URL_PRE = "provider01";

//    // 基于Feign 测试
//    @Resource
//    private SyaHelloService syaHelloService;

    @Resource
    private SyaHelloServiceImpl syaHelloServiceImpl;

    // 基于 Ribbon 测试
    @Resource
    private RestTemplate restTemplate;

//    @GetMapping("/feign/hello")
//    public String sayHello(){
//        return syaHelloService.sayHello();
//    }

    @GetMapping("/ribbon/hello")
    public String sayHelloRest(){
        return restTemplate.getForObject("http://"+REST_URL_PRE + "/hello",String.class);
    }

    /**
     * 通过api 的方式调用业务，把业务和消费者分离
     * @return
     */
    @GetMapping("/api/hello")
    public String sayHelloByApi(){
        return syaHelloServiceImpl.sayHello();
    }
}
