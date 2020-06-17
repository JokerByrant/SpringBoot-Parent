package com.sxh.route;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * 接收者
 * 使用交换机和路由
 * @author sxh
 * @date 2020/6/15
 */
public class Recv {
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
        // 创建一个非持久的、唯一的、自动删除的队列
        String queue = channel.queueDeclare().getQueue();
        // 随机生成一个日志级别作为路由标识
        int rand = new Random().nextInt(3);
        String severity  = LOG_LEVEL_ARR[rand];
        // 将交换机和临时队列绑定到通道上，并制定路由键名
        // queue->队列名，logs->交换机名，""->路由键名
        // 绑定了路由键 severity 之后，当前队列只会接收 [设置了指定路由键的发送者] 发出的消息
        channel.queueBind(queue, EXCHANGE_NAME, severity);
        
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
        channel.basicConsume(queue, false, consumer);
    }
}
