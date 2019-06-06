package com.jayyin.demo.rocketmq;

import com.jayyin.demo.rocketmq.consumer.Consumer;
import com.jayyin.demo.rocketmq.consumer.Consumer2;
import com.jayyin.demo.rocketmq.producer.Producer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class RocketmqDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(RocketmqDemoApplication.class, args);

        Consumer.consume(Producer.defaultProducerGroup, Producer.nameSvr, "TopicTest");

        Consumer2.consume("com_jayyin_rcmq_sync_produce_2", Producer.nameSvr, "TopicTest");

        //同步
//        Producer.syncProduce(Producer.defaultProducerGroup);



    }



}
