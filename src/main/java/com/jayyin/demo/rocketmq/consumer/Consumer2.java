package com.jayyin.demo.rocketmq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @description mq消费者
 * @author: jayyin
 * @date: 2019-06-03 13:48
 * @version: 1.0
 */
public class Consumer2 {


    public static void consume(String consumerGroup, String nameSvrAddr, String topic) {
        try {

            DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(consumerGroup);

            consumer.setNamesrvAddr(nameSvrAddr);
            //订阅  (* 匹配所有)
            consumer.subscribe(topic, "*");
            //
            consumer.registerMessageListener((MessageListenerConcurrently) (list, consumeConcurrentlyContext) -> {
                //todo  to consume the messages
                for (MessageExt e :
                        list) {
                    System.out.printf(" ---> %s c2 Receive New Messages: %s %n", Thread.currentThread().getName(), list);
//                    String t = e.getTopic();
                    String body = new String(e.getBody());
                    String tags = e.getTags();
                    System.out.printf("---> c2 Messages: Body= %s tags= %s %n", body, tags);
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            });

            //Launch the consumer instance.
            consumer.start();
            System.out.printf("Consumer2 Started.%n");

        } catch (MQClientException e) {
            e.printStackTrace();
        }


    }

}
