package com.zsw.comsumer.service.hystric;

import com.zsw.comsumer.service.SyaHelloService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author zsw
 * @date 2021/1/28 16:38
 * @description :
 */
@Component
public class SayHelloServiceHystrix implements FallbackFactory<SyaHelloService> {

    @Override
    public SyaHelloService create(Throwable cause) {
        return new SyaHelloService() {
            @Override
            public String sayHello() {
                System.out.println("服务熔断降级");
                return "服务熔断降级";
            }

            @Override
            public String index() {
                System.out.println("服务熔断降级");
                return "服务熔断降级";
            }

            @Override
            public String csrf() {
                System.out.println("服务熔断降级");
                return "服务熔断降级";
            }
        };
    }
}
