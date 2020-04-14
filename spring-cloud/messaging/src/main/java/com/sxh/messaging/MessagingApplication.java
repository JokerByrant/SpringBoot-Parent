package com.sxh.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.support.MessageBuilder;

/**
 * @author sxh
 * @date 2020/4/10
 */
@SpringBootApplication
@EnableBinding({Source.class, Sink.class}) // 启用Stream，Source代表输出通道，Sink代表输出通道
public class MessagingApplication {
    public static void main(String[] args) {
        SpringApplication.run(MessagingApplication.class, args);
    }
    
    @Bean
    public CustomRunner customRunner() {
        return new CustomRunner();
    }
    
    public static class CustomRunner implements CommandLineRunner{
        @Autowired
        private Source source;
        
        @Override
        public void run(String... args) throws Exception {
            int count = 5;
            for (int index = 1; index <= 5; index++) {
                // 推送消息
                source.output().send(MessageBuilder.withPayload("msg-" + index).build());
            }
        }
    }
}
