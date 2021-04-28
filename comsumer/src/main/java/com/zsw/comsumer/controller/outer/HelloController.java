package com.zsw.comsumer.controller.outer;

import com.zsw.provider.controller.api.UploadFeignClient;
import com.zsw.provider.controller.api.UserApi;
import com.zsw.provider.entity.ResultResp;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author zsw
 * @date 2021/3/10 17:35
 * @description : 测试 hello
 */
@ApiOperation("hello")
@RestController
@RequestMapping("/user")
public class HelloController {

    // 服务名
//    @Value("${provider}")
    private static final String REST_URL_PRE = "provider01";

//    // 基于Feign 测试
//    @Resource
//    private SyaHelloService syaHelloService;

//    @Resource
//    private SyaHelloServiceImpl syaHelloServiceImpl;

    // 基于 Ribbon 测试
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private UploadFeignClient uploadFeignClient;

    @Resource
    UserApi userApi;

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
//    @GetMapping("/api/hello")
//    public String sayHelloByApi(){
//        return syaHelloServiceImpl.sayHello();
//    }

    /**
     * 使用feign上传
     * @param file
     * @return
     */
    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam(value = "file") MultipartFile file) {
        String s = uploadFeignClient.handleFileUpload(file);
        System.out.println("file-------------"+s);
        return s;
    }

    @GetMapping("/api/getUser")
    public ResultResp getUser(){
        return userApi.getUser(1,10);
    }
}
