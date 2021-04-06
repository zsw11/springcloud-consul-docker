package com.zsw.springcloudgetway.getway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Mono;

@SpringBootApplication  // 不能加入springMVC 依赖 Spring Cloud网关不兼容
public class GetwayApplication {
    @Bean
    public UriKeyResolver uriKeyResolver() {
        return new UriKeyResolver();
    }

    public static void main(String[] args) {
        SpringApplication.run(GetwayApplication.class, args);
    }

    @Bean
    public RouteLocator myRoutes(RouteLocatorBuilder builder) {
        // getway使用方式二
        String httpUri = "http://localhost:8771/";
        return builder.routes()
//                .route(p -> p
//                        .path("/")
////                        .filters(f -> f.addRequestHeader("Hello", "World"))
//                        .uri(httpUri))
                .route(p -> p
                        .path("/user/get")
                        .filters(f -> f.addRequestParameter("pageNum", "1").addRequestParameter("pageSize","10")).uri(httpUri))
                .route(p -> p
                        .path("/")
                        .filters(f -> f
                                .hystrix(config -> config
                                        .setFallbackUri("forward:/fallback")))
                        .uri(httpUri))
                .build();
    }

    @RequestMapping("/fallback")
    public Mono<String> fallback() {
        return Mono.just("fallback");
    }

}
