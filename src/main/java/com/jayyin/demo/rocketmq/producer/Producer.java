package com.jayyin.demo.rocketmq.producer;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;

import java.io.UnsupportedEncodingException;

/**
 * @description
 *  mq 生产者
 *
 * @author: jayyin
 * @date: 2019-06-03 11:52
 * @version: 1.0
 */
public class Producer {

    //the name_server of the mq
    public final static String nameSvr = "localhost:9876";

    public final static String defaultProducerGroup = "com_jayyin_rcmq_sync_produce";


    public static void main(String[] a){
        syncProduce(defaultProducerGroup);
    }

    /**
     * 生产方式1 - 可靠的同步生产方式
     * 适用场景： 可靠的同步传输用于广泛的场景，如重要的【通知消息，短信通知，短信营销系统】 等。
     *
     * @param producerGroup
     */
    public static void syncProduce(String producerGroup) {
        DefaultMQProducer producer = null;
        String group = producerGroup;
        if (producerGroup == null)
            group = defaultProducerGroup;

        try {

            producer = new DefaultMQProducer(group);
            producer.setNamesrvAddr(nameSvr);
            //launch the instance
            producer.start();


            for (int i = 0; i < 10; i++) {
                Message message = new Message(
                        "TopicTest",
                        "TagA",
                        ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET));

                //send message
                SendResult sendResult = producer.send(message);

                System.out.printf("%s%n", sendResult);
            }

        } catch (MQClientException e) {
            e.printStackTrace();
            System.out.println(e.getErrorMessage());
        } catch (UnsupportedEncodingException | RemotingException | MQBrokerException | InterruptedException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        producer.shutdown();
    }




}
