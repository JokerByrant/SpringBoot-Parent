package com.sxh.redis.test;

import com.sxh.pojo.UserPojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试类，测试redis
 * @author sxh
 * @date 2020/4/14
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    
    private static final String REDIS_KEY = "JOKER";
    
    private Logger logger = LoggerFactory.getLogger(RedisTemplateTest.class);
    
    @Test
    public void testAdd() {
        UserPojo userPojo = new UserPojo(1001, "张三", "男");
        redisTemplate.opsForHash().put(REDIS_KEY, userPojo.getId().toString(), userPojo);
    }

    @Test
    public void testGet() {
        UserPojo userPojo = (UserPojo) redisTemplate.opsForHash().get(REDIS_KEY, "1001");
        logger.info(userPojo.toString());
    }

    @Test
    public void testDelete() {
        redisTemplate.opsForHash().delete(REDIS_KEY, "1001");
    }
}
