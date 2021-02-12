package com.wjy;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
public class Test {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @org.junit.Test
    public void test() {


    }
}
