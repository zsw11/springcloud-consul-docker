package com.zsw.provider.config.rabbitmq;

import com.alibaba.fastjson.JSON;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zsw
 * @date 2021/3/11 18:17
 * @description :  rabbitmq 生产者配置回调函数
 */
@Configuration
public class RabbitConfig {

    @Autowired
    RedisTemplate redisTemplate;

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        // 开启Mandatory, 才能触发回调函数，无论消息推送结果如何都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            // 当消息发送到交换机（exchange）时，该方法被调用.如果达到了交换机 ack=true,没有则为false
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback: "+"相关数据：" + correlationData);
                System.out.println("ConfirmCallback: "+"数据到交换机确认情况：" + ack);
                System.out.println("ConfirmCallback: "+"原因：" + cause);
                // 发到交换机失败
                if (!ack) {
                    // 从缓存中获取数据，进行补偿重试机制
                    ArrayList<String> msg = (ArrayList<String>) redisTemplate.opsForValue().get(correlationData.getId());
                    rabbitTemplate.convertAndSend(msg.get(0), msg.get(1), msg.get(2));
                    redisTemplate.delete(correlationData.getId());
                    System.out.println(msg);
                }
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            // 当消息从交换机到队列失败时，该方法被调用。（若成功，则不调用）
            // 上面的confirm方法也会被调用，且ack = true
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback: "+"消息：" + message);
                System.out.println("ReturnCallback: "+"回应码：" + replyCode);
                System.out.println("ReturnCallback: "+"回应信息：" + replyText);
                System.out.println("ReturnCallback: "+"交换机：" + exchange);
                System.out.println("ReturnCallback: "+"路由键：" + routingKey);
                // 补偿
                rabbitTemplate.convertAndSend(exchange, routingKey, message);
            }
        });

        return rabbitTemplate;
    }

//    @Bean
//    public org.springframework.amqp.rabbit.connection.ConnectionFactory connectionFactory() {
//        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
//        //设置服务地址
//        connectionFactory .setHost("192.168.51.34");
////        connectionFactory .setHost("localhost");
//        //端口
//        connectionFactory .setPort(5672);
//        //设置账号信息，用户名、密码、vhost
////        connectionFactory .setVirtualHost("testhost");
//        connectionFactory .setUsername("guest");
//        connectionFactory .setPassword("guest");
//
//        return connectionFactory;
//    }

}
