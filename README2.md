用@HystrixCommand fallbackMethod的方式不是很好，因为和业务代码耦合度太高，不利于维护，所以需要解耦，就Feign Hystrix整合

这里需要降低 springcloud 版本 ，要不然 导入的FallbackFactory 包不对 ，大坑
<artifactId>spring-boot-starter-parent</artifactId>  
<version>2.1.6.RELEASE</version>
springcloud 版本
<spring-cloud.version>Greenwich.SR1</spring-cloud.version>

整合 dashboard 坑: 服务有熔断才能有 dashboard 界面
启动dashboard 项目 : 访问 http://localhost:9090/hystrix，
输入需要监控的页面： http://localhost:8772/actuator/hystrix.stream

docker-compose 容器编排：先构建好镜像，再在docker-compose.yml目录下    docker-compose -f docker-compose.yml up -d

整合ElasticSearch： 
参考： https://www.cnblogs.com/balloon72/p/13177872.html 
安装： mkdir -p  /home/elasticsearch/config    mkdir -p  /home/elasticsearch/data   echo "http.host: 0.0.0.0" >> /home/elasticsearch/config/elasticsearch.yml
docker run --name elasticsearch -p 9200:9200 -p 9300:9300  -e "discovery.type=single-node" -e ES_JAVA_OPTS="-Xms64m -Xmx128m" -v /home/elasticsearch/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml -v /home/elasticsearch/data:/usr/share/elasticsearch/data -v /home/elasticsearch/plugins:/usr/share/elasticsearch/plugins -d elasticsearch:7.6.2
------ 如果出现容器秒退，启动失败，查看启用日志，可能需要就给挂载目录赋予权限 如:chmod 777 /home/elasticsearch/plugins

kibana : docker run --name kibana --link=elasticsearch:7.6.2  -p 5601:5601 -d kibana:7.6.2
修改/usr/share/kibana/config/kibana.yml 文件 
# 操作界面语言设置为中文
i18n.locale: "zh-CN"

logstash:linux 内存小了会连接不上ElasticSearch
docker pull logstash-7.6.2
docker run -di -p 5044:5044 -v /home/elk/logstash.conf:/usr/share/logstash/pipeline/logstash.conf --name logstash logstash:7.6.2
logstash.conf：logstash.conf配置
input {
    tcp {
        port => 5044
        mode => "server"
        tags => ["tags"]
                # 输入为json数据
        codec => json_lines
    }
}
filter {

}
output {
        # elasticsearch配置
        elasticsearch {
                hosts => ["http://192.168.2.134:9200"]
                # 索引名称，没有会自动创建
                index => "logstash-%{[appname]}-%{+YYYY.MM.dd}"
        }
}
还有 这个文件 logstash.yml 进入logstash容器修改   hosts => ["http://192.168.2.134:9200"]  要不然连接不上ES
ELK 分布式日志管理系统

整合 xxl-job
                   spring.mail.username=546232194@qq.com
发送邮箱报警的密码：spring.mail.password=ervmowigfhdbbcce

springboot日志 
1 如果不配置则,默认日志输出在控制台
2 打印到日志文件，输出的位置在项目所在文件件位置，在yml中进行配置 logging.file 如下配置  logging:  file: myweb.log
3 打印到日志文件，输出到任意指定位置，需要在resources下创建一个logback-spring.xml，配置好后上面在application.yml中的配置
                                                          


