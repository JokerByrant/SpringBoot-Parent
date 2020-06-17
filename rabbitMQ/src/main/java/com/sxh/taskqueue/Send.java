package com.sxh.taskqueue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送者
 * 测试消息的持久化---关闭rabbitMQ服务后，消息不被清空
 * @author sxh
 * @date 2020/6/15
 */
public class Send {
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
        // a.将队列内容标记为持久化
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        // 配置公平转发
        channel.basicQos(1);
        
        // 发送消息
        for (int i = 0; i < 10; i++) {
            String message = "Ceshi00" + i;
            // b.消息标记为持久化的
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println("a new message [" + message + "] has been sent!!!");
        }
                
        channel.close();
        connection.close();
    }
}
