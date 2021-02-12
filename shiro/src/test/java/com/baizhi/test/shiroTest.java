package com.baizhi.test;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.subject.Subject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class shiroTest {
    /**
     * 假数据 模拟用户在浏览器输入的账号密码
     */
    private String username = "zhangsan";
    private String password = "123456";
    @Autowired
    private UserDao userDao;

    /**
     * 认证
     */
    @Test
    public void test1() {
        /**
         * SecurityManager 安全管理器 shiro核心API
         * 目前来说 就认为在安全管理器中封装了 配置文件的所有数据
         */
//        1.读取配置文件
        IniSecurityManagerFactory factory = new IniSecurityManagerFactory("classpath:shiro.ini");

        SecurityManager securityManager = factory.getInstance();


//        2.将安全管理器 赋值给 SecurityUtils
        SecurityUtils.setSecurityManager(securityManager);

//        3. 获取主体  主体不能通过new获取 在项目的任何位置获取主体都要通过工具类
        Subject subject = SecurityUtils.getSubject();

//        4.将用户浏览器输入的账号密码 给Subject
//        将账号密码封装到token中
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        /**
         * Token 令牌  封装认证数据
         *
         * login 通过抛异常的方式告诉程序员有没有登录成功
         * 没有异常就是登录成功
         * UnknownAccountException 账号不存在
         * IncorrectCredentialsException 密码不正确
         *      Credential 凭证信息 就是密码的意思
         *      Principal  身份信息 就是账号的意思
         */
        try {

            subject.login(token);
            System.out.println("认证成功");
        } catch (Exception e) {
            System.out.println("账号或密码错误");
        }
    }

    @Test
    public void test2() {
        int i = userDao.insert(new User(null, "wjy", "123456", "4562"));
        System.out.println(i);
    }
}
