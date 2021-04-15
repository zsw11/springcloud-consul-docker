package com.zsw.provider.controller.mq;

import com.rabbitmq.client.Channel;
import com.zsw.provider.config.rabbitmq.RabbitConfig;
import com.zsw.provider.config.rabbitmq.RabbitQueueAndExchange;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;


/**
 * @author zsw
 * @date 2021/3/11 17:54
 * @description :  创建消息处理者DirectReceiver
 */
@Component
@Slf4j
public class DirectReceiver {

    @RabbitHandler
    @RabbitListener(queues = "myDirectQueue") // 队列
    public void process(Message msg, Channel channel) throws IOException {
        try{
            // 先幂等性判断 是否重复消费
//            int a = 1 / 0;  //抛异常
            System.out.println("消费到消息"+msg);
            // 手动确认 删除队列消息
            channel.basicAck(msg.getMessageProperties().getDeliveryTag(), true);
        }catch (Exception e){
            log.error("消费消息失败了,error："+ msg);
            // 放到死信队列
            channel.basicNack(msg.getMessageProperties().getDeliveryTag(), false,false);
            // 将消息重新放回队列  multiple=false 不从队列删除
//            channel.basicNack(msg.getMessageProperties().getDeliveryTag(),false,true);
        }

    }

    //监听死信队列
    @RabbitListener(queues = {RabbitQueueAndExchange.DEAD_QUEUE})
    public void receiver(Message msg, Channel channel) throws IOException {
        // 手动确认 删除队列消息
        channel.basicAck(msg.getMessageProperties().getDeliveryTag(), true);
        System.out.println("dead queue 收到消息>>>>>>>>>"+msg);
    }

    // 处理三个队列的消息
    @RabbitHandler
    @RabbitListener(queues = "fanoutQueueA")
    public void processA(Message msg) {
        System.out.println("fanoutQueueA " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanoutQueueB")
    public void processB(Message msg) {
        System.out.println("fanoutQueueB " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "fanoutQueueC")
    public void processC(Message msg) {
        System.out.println("fanoutQueueC " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "myTopicQueue_01")
    public void process_01(Message msg) {
        System.out.println("myTopicQueue_01 " + msg);
    }

    @RabbitHandler
    @RabbitListener(queues = "myTopicQueue_02")
    public void process_02(Message msg) {
        System.out.println("myTopicQueue_02 " + msg);
    }
}
