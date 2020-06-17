package com.sxh.taskqueue;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 接收者
 * @author sxh
 * @date 2020/6/15
 */
public class Recv {
    private static final String QUEUE_NAME = "hello_dirable";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置连接主机名
        factory.setHost("localhost");
        // 创建一个新连接
        Connection connection = factory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 指定一个队列
        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        
        System.out.println("Waiting for message now. Please waite...");
        // 创建队列消费者
        Consumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(" [x] Received '" + message + "'");

                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // 每次处理完一个消息后，手动发送一次应答
                    // 这样处理是为了当当前的进程关闭后，不会出现消息丢失的情况
                    channel.basicAck(envelope.getDeliveryTag(),false);
                }
            }
        };
        // 接收消息
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }
}
