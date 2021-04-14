package com.zsw.provider.controller.mq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * @author zsw
 * @date 2021/3/11 17:48
 * @description : 测试rabbitmq
 */
@RestController
@Api(" Rabbitmq")
public class RabbitMqController {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    RedisTemplate redisTemplate;

    @ApiOperation("发送消息到消息队列 DirectExchange模式")
    @PostMapping("/send")
    public String send() {
        String msg = "hello";
        String msgId = UUID.randomUUID().toString();
        rabbitTemplate.convertAndSend("myDirectExchange", "my.direct.routing", msg,new CorrelationData(msgId)); // 指定 交换机和路由 发送到 myDirectQueue 队列上
        //msgId和message关系保存redis。用于补偿
        ArrayList<String> strings = new ArrayList<>();
        strings.add("myDirectExchange");
        strings.add("my.direct.routing");
        strings.add(msg);
        redisTemplate.opsForValue().set(msgId,strings,3, TimeUnit.MINUTES);
        return "success";
    }

    /**
     * 创建多个队列绑定到扇形交换机，生产者发送一次消息，可以观察到多个处理者都收到了消息
     * @return
     */
    @ApiOperation("FanoutRabbitConfig 模式")
    @PostMapping("/sendByFanout")
    public String sendByFanout() {

        String msg = "hello fanout";
        rabbitTemplate.convertAndSend("fanoutExchange", null, msg);

        return "success";
    }

    /**
     * 创建两个队列，并使用通配符绑定到主题交换机
     */
    @GetMapping("/sendByTopic")
    public String sendByTopic() {

        String msg = "hello topic";
        rabbitTemplate.convertAndSend("myTopicExchange", "topic.01", msg + " topic.01");
        rabbitTemplate.convertAndSend("myTopicExchange", "topic.02", msg + " topic.02");

        return "success";
    }

}
