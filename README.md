# MySpringCloud -consul -docker-redis-swagger-rabbitmq-validation
搭建微服务项目-服务注册、服务发现专题demo 注册中心使用consul
MySpringCloud-consul -docker 集群
搭建微服务项目-服务注册、服务发现专题demo

搭建docker 脚本如下：
docker run --name consul1 -d -v /home/consul/data:/consul/data -v /home/consul/config:/consul/config -p 8500:8500 consul agent -node server-1 -server -bootstrap-expect 2 -ui -bind=0.0.0.0 -client=0.0.0.0

docker inspect --format='{{.NetworkSettings.IPAddress}}' consul1  #查看端口

docker run --name consul2 -d -v /home/consul/data:/consul/data2 -v /home/consul/config:/consul/config2 -p 8501:8500  consul agent -node server-2  -server -ui -bind=0.0.0.0 -client=0.0.0.0 -join 172.18.0.6
docker run --name consul3 -d -v /home/consul/data:/consul/data3 -v /home/consul/config:/consul/config3 -p 8502:8500  consul agent -node server-3  -server -ui -bind=0.0.0.0 -client=0.0.0.0 -join 172.18.0.6
docker run --name consul5 -d -v /home/consul/data:/consul/data5 -v /home/consul/config:/consul/config5 -p 8505:8500 consul agent -node client-1 -client -ui -bind=0.0.0.0 -client=0.0.0.0 -join 172.18.0.6

docker exec -it consul1 consul members   # 查看集群信息

项目启动访问：http://192.168.85.128:8500/ui/dc1/services 默认 consul1主节点

坑点：注意join 加入节点的ip地址。-bootstrap-expect 2 这个只能在默认主节点加，推举自己为主节点要先启动。其他服务挂载路径不能一样，要不然会无法搭建集群， 启动一个client个 3个server端

启动项目： 服务提供者swagger地址http://localhost:8771/swagger-ui.html 服务消费者 swagger 地址 http://localhost:8772/swagger-ui.html

下载rabbitmq 配置完启动项目，访问地址： http://localhost:15672/#/exchanges
rabbitmq 使用了3中交换机模式demo， 直连交换机，扇形交换机 ，主题交换机

整合reids validtion

微服务网关是介于客户端和服务器端之间的中间层，所有的外部请求都会先经过微服务网关。
 ![Alt text](picture\zuul.png)
 此时，微服务网关封装了应用程序的内部结构，客户端只须跟网关交互，而无须直接调用特定微服务的接口。这样，开发就可以得到简化。不仅如此，使用微服务网关还有以下优点：
 
 易于监控。可在微服务网关收集监控数据并将其推送到外部系统进行分析。
 易于认证。可在微服务网关上进行认证，然后再将请求转发到后端的微服务，而无须在每个微服务中进行认证。
 减少了客户端与各个微服务之间的交互次数。
 
 Spring Cloud Gateway
 