package com.sxh.helloworld;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送者
 * @author sxh
 * @date 2020/6/15
 */
public class Send {
    private static final String QUEUE_NAME = "hello";
    
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
        // queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
        // 参数1 queue ：队列名
        // 参数2 durable ：是否持久化
        // 参数3 exclusive ：仅创建者可以使用的私有队列，断开后自动删除
        // 参数4 autoDelete : 当所有消费客户端连接断开后，是否自动删除队列
        // 参数5 arguments
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        // 发送消息
        String message = "hello world";
        // basicPublish(String exchange, String routingKey, BasicProperties props, byte[] body)
        // 参数1 exchange ：交换器
        // 参数2 routingKey ： 路由键
        // 参数3 props ： 消息的其他参数
        // 参数4 body ： 消息体
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("a new message [" + message + "] has been sent!!!");
        channel.close();
        connection.close();
    }
}
