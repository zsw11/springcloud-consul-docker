用@HystrixCommand fallbackMethod的方式不是很好，因为和业务代码耦合度太高，不利于维护，所以需要解耦，就Feign Hystrix整合

这里需要降低 springcloud 版本 ，要不然 导入的FallbackFactory 包不对 ，大坑
<artifactId>spring-boot-starter-parent</artifactId>   <version>2.1.6.RELEASE</version>
       
<spring-cloud.version>Greenwich.SR1</spring-cloud.version>

整合 dashboard 坑: 服务有熔断才能有 dashboard 界面

启动dashboard 项目 : 访问 http://localhost:9090/hystrix，
输入需要监控的页面： http://localhost:8772/actuator/hystrix.stream

docker-compose 容器编排：先构建好镜像，再在docker-compose.yml目录下    docker-compose -f docker-compose.yml up -d