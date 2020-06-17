package com.sxh.route;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;

/**
 * 发送者---使用路由
 * 路由需要配合交换机进行使用
 * @author sxh
 * @date 2020/6/15
 */
public class Send {
    private static final String EXCHANGE_NAME = "exchange_route";
    private static final String[] LOG_LEVEL_ARR = {"debug", "info", "error"};
    
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
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 发送消息
        for (int i = 0; i < 10; i++) {
            int rand = new Random().nextInt(3);
            String severity  = LOG_LEVEL_ARR[rand]; // 随机产生一个日志级别作为路由标识 
            
            String message = "[ " + LOG_LEVEL_ARR[rand] + " ] ----> Ceshi00" + i;
            channel.basicPublish(EXCHANGE_NAME, severity, null, message.getBytes());
            System.out.println("a new message 【" + message + "】 has been sent!!!");
        }
                
        channel.close();
        connection.close();
    }
}
