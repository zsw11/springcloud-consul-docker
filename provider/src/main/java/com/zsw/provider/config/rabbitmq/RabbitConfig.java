package com.zsw.provider.config.rabbitmq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zsw
 * @date 2021/3/11 18:17
 * @description :  rabbitmq 生产者配置回调函数
 */
@Configuration
public class RabbitConfig {

    @Bean
    public RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(connectionFactory);

        // 开启Mandatory, 才能触发回调函数，无论消息推送结果如何都强制调用回调函数
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                System.out.println("ConfirmCallback: "+"相关数据：" + correlationData);
                System.out.println("ConfirmCallback: "+"确认情况：" + ack);
                System.out.println("ConfirmCallback: "+"原因：" + cause);
            }
        });

        rabbitTemplate.setReturnCallback(new RabbitTemplate.ReturnCallback() {
            @Override
            public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
                System.out.println("ReturnCallback: "+"消息：" + message);
                System.out.println("ReturnCallback: "+"回应码：" + replyCode);
                System.out.println("ReturnCallback: "+"回应信息：" + replyText);
                System.out.println("ReturnCallback: "+"交换机：" + exchange);
                System.out.println("ReturnCallback: "+"路由键：" + routingKey);
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
