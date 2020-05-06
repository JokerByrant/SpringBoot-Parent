package com.sxh.redis.test;

import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnels;
import com.sxh.pojo.UserPojo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.nio.charset.Charset;

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
    private static final String BLOOM_KEY = "BLOOM";
    
    private Logger logger = LoggerFactory.getLogger(RedisTemplateTest.class);

    private static final int capacity = 1000000;
    private static final int key = 999998;
    private BloomFilter<String> bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("Utf-8")), capacity);
    
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
    
    // 测试布隆过滤器
    @Test
    public void BloomFilterTest() {
        UserPojo userPojo = (UserPojo) redisTemplate.opsForHash().get(REDIS_KEY, "1001");
        // 0.从redis获取数据失败，通过布隆过滤器进行过滤
        if (userPojo == null) {
            // 1.首先获取redis中的布隆过滤器
            bloomFilter = (BloomFilter<String>) redisTemplate.opsForValue().get(BLOOM_KEY);

            // 2.获取失败，从sql中获取数据并重新构造过滤器
            if (bloomFilter == null) {
                String sqlValue = REDIS_KEY;
                bloomFilter = BloomFilter.create(Funnels.stringFunnel(Charset.forName("Utf-8")), capacity);
                bloomFilter.put(sqlValue);
                redisTemplate.opsForValue().set(BLOOM_KEY, bloomFilter);
            }
            
            // 3.进行过滤
            if (bloomFilter.mightContain(REDIS_KEY)) {
                userPojo = new UserPojo(1001, "老王", "男");
                redisTemplate.opsForHash().put(REDIS_KEY, userPojo.getId().toString(), userPojo);
                System.out.println(userPojo);
            } else {
                System.out.println("未查询到userPojo！");
            }
        } else {
            System.out.println("从redis中获取userPojo成功！" + userPojo);
        }
    }
}
