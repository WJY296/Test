package com.wjy.redis;

import jdk.nashorn.internal.ir.CallNode;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class Test_Redis {
    public static void main(String[] args) {
        //连接池相关配置
        JedisPoolConfig config = new JedisPoolConfig();
        //最大空闲数
        config.setMaxIdle(8);
        //最大连接数
        config.setMaxTotal(20);

        JedisPool jedisPool = new JedisPool(config, "redis17", 6379, 2000, "password");

        //从连接池中获取连接
        Jedis jedis1 = jedisPool.getResource();

        //操作数据
        jedis1.set("ss", "sd");

        //用完归还连接
        jedis1.close();
        //关闭连接池
        jedisPool.close();


    }
}
