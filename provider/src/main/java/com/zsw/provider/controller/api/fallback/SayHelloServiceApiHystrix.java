package com.zsw.provider.controller.api.fallback;

import com.zsw.provider.controller.api.SyaHelloServiceApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @author zsw
 * @date 2021/1/28 16:38
 * @description :
 */
@Component
public class SayHelloServiceApiHystrix implements FallbackFactory<SyaHelloServiceApi> {

    @Override
    public SyaHelloServiceApi create(Throwable cause) {
        return new SyaHelloServiceApi() {
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
