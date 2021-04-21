package com.zsw.provider.config.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zsw
 * @date 2021/4/14 16:32
 * @description : 配置 交换机和 队列
 */
@Configuration
public class RabbitQueueAndExchange {
    //死信交换机
    public static final String X_DEAD_LETTER_EXCHANGE = "x-dead-letter-exchange";
    //死信路由
    public static final String X_DEAD_LETTER_ROUTING_KEY = "x-dead-letter-routing-key";
    //死信队列
    public static final String DEAD_QUEUE = "dead-queue";

    // 把 myDirectExchange 放到这里 测试死信交换机
    public static final String myDirectExchange = "myDirectExchange";

    public static final String myDirectRouting = "my.direct.routing";

    public static final String myDirectQueue = "myDirectQueue";


    /**
     * 创建死信队列
     */
    @Bean
    public Queue getDeadQueue(){
        return new Queue(DEAD_QUEUE);
    }
    //创建死信交换机
    @Bean
    public Exchange getDeadExchange(){
        return ExchangeBuilder.directExchange(X_DEAD_LETTER_EXCHANGE).durable(true).build();
    }
    //队列与延时交换机进行绑定
    @Bean
    public Binding bindDead(){
        return BindingBuilder.bind(getDeadQueue()).to(getDeadExchange()).with(X_DEAD_LETTER_ROUTING_KEY).noargs();
    }

    //创建普通队列
    @Bean
    public Queue getNormalQueue(){
        Map args = new HashMap();
        //当消息发送异常的时候，消息需要路由到的死信交换机和routing-key，这里配是发送至死信队列
        args.put("x-dead-letter-exchange",X_DEAD_LETTER_EXCHANGE);
        args.put("x-dead-letter-routing-key",X_DEAD_LETTER_ROUTING_KEY);
        //创建队列的时候，将死信绑定到队列中
        return QueueBuilder.durable(myDirectQueue).withArguments(args).build();
    }
    //创建普通交换机
    @Bean
    public Exchange getNormalExchange(){
        return ExchangeBuilder.directExchange(myDirectExchange).durable(true).build();
    }
    //普通队列与普通交换机进行绑定
    @Bean
    public Binding bindNormal(){
        return BindingBuilder.bind(getNormalQueue()).to(getNormalExchange()).with(myDirectRouting).noargs();
    }


}
