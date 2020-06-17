package com.sxh.exchange;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 发送者---使用交换机
 * 通过测试发现，使用交换机处理消息无法将生产者写入的消息持久，只能将消息实时传送给在线的消费者
 * @author sxh
 * @date 2020/6/15
 */
public class Send {
    private static final String EXCHANGE_NAME = "hello_exchange";
    
    public static void main(String[] args) throws IOException, TimeoutException {
        // 创建连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // 设置连接主机名
        factory.setHost("localhost");
        // 创建一个新连接
        Connection connection = factory.newConnection();
        // 创建通道
        Channel channel = connection.createChannel();
        // 配置交换器，logs->交换器名，fanout->交换器类型
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 发送消息
        for (int i = 0; i < 10; i++) {
            String message = "Ceshi00" + i;
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            System.out.println("a new message [" + message + "] has been sent!!!");
        }
                
        channel.close();
        connection.close();
    }
}
