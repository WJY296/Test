package com.wjy.service.impl;

import com.wjy.service.UserService;
import com.wjy.util.RedisPool;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.UUID;

/**
 * @author wangjingyang
 * @date 2021-01-24 15:29
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public Boolean test()  {
        //获取jedis连接
        Jedis jedis = RedisPool.getJedis();
        String s = UUID.randomUUID().toString();
        boolean test = false;
        //获取分布式锁
        test = RedisPool.tryGetDistributedLock(jedis, "test", s, 5000);
        //如果获取失败重复去获取
        while (test==false){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            test = RedisPool.tryGetDistributedLock(jedis, "test", s, 5000);
        }

        //如果拿到锁执行业务代码
        if(test){
            System.out.println("拿到锁--->");
            System.out.println("执行业务代码");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //执行完毕删除锁
            RedisPool.releaseDistributedLock(jedis,"test",s);
            return true;
        }
        //关闭jedis连接
        jedis.close();
        return false;
    }

    @Override
    public Boolean test2() {
        Jedis jedis = RedisPool.getJedis();
        jedis.rename("wjy","wjynew");
        jedis.close();
        return null;
    }

}
