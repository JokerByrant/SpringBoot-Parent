import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.*;

/**
 * 使用Spring Integration 拓展 Spring Messaging
 * @author sxh
 * @date 2020/4/10
 */
public class AppTest {


    // 实现MessageHandler，处理MessageChannel中的消息
    public class SimpleMessageHandlerA implements MessageHandler {
        private Logger logger = LoggerFactory.getLogger(SimpleMessageHandlerA.class);

        @Override
        public void handleMessage(Message<?> message) throws MessagingException {
            logger.info("=====================广播开始=========================");
            logger.info("receive message throw A Method: " + message.getPayload());
            logger.info("=====================广播结束=========================");
        }
    }

    public class SimpleMessageHandlerB implements MessageHandler {
        private Logger logger = LoggerFactory.getLogger(SimpleMessageHandlerB.class);

        @Override
        public void handleMessage(Message<?> message) throws MessagingException {
            logger.info("=====================广播开始=========================");
            logger.info("receive message throw B method : " + message.getPayload());
            logger.info("=====================广播结束=========================");
        }
    }

    /**
     * 单播消息分发器
     */
    @Test
    public void DirectChannelTest() {
        // 1.新建一个订阅服务channel
        SubscribableChannel channel = new DirectChannel();
        // DirectChannel内部有个UnicastingDispatcher类型消息分发器，但它是个单播的分发器，默认采用轮询的方式分发消息

        // 2.新建一个处理服务channel的方式A
        SimpleMessageHandlerA A = new SimpleMessageHandlerA();
        // 新建一个处理服务channel的方式B
        SimpleMessageHandlerB B = new SimpleMessageHandlerB();

        // 3.小明订阅了服务channel，并且想让消息以A方式发送给自己
        channel.subscribe(A);
        // 小红订阅了服务channel，并且想让消息以B方式发送给自己
        channel.subscribe(B);
        
        // 4.channel服务开始推送消息，由于采用的是单播消息分发器，并且使用了默认的负载均衡策略，所以会使用轮询的方式将下面三条消息推送出去
        channel.send(MessageBuilder.withPayload("msgfrom sxh A").build());
        channel.send(MessageBuilder.withPayload("msgfrom sxh B").build());
        channel.send(MessageBuilder.withPayload("msgfrom sxh C").build());
    }

    /**
     * 广播消息分发器
     */
    @Test
    public void PublishSubscribeChannelTest() {
        SubscribableChannel channel = new PublishSubscribeChannel();
        // SubscribableChannel使用的是一个广播消息分发器

        SimpleMessageHandlerA A = new SimpleMessageHandlerA();
        SimpleMessageHandlerB B = new SimpleMessageHandlerB();

        channel.subscribe(A);
        channel.subscribe(B);

        // A和B都会受到下面三条消息
        channel.send(MessageBuilder.withPayload("msgfrom sxh A").build());
        channel.send(MessageBuilder.withPayload("msgfrom sxh B").build());
        channel.send(MessageBuilder.withPayload("msgfrom sxh C").build());
    }
}
