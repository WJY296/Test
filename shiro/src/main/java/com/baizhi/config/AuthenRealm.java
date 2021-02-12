package com.baizhi.config;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthenRealm extends AuthenticatingRealm {
    @Autowired
    private UserDao userDao;

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        /**
         * 1.从令牌中获取账号
         */
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String username = usernamePasswordToken.getUsername();

        /**
         * 2.通过数据库查询
         */
        User user = userDao.selectOne(
                new QueryWrapper<User>()
                        .eq("username", username)
        );

        //System.out.println("aaaaaaaaaaaaa"+user);

        /**
         * 3.查询数据不为null 封装info返回
         */
        if (user != null) {
            /**
             * 参数1 账号
             * 参数2 密码
             * 参数3 当前Realm对象的名字
             */
            ByteSource bytes = ByteSource.Util.bytes(user.getSalt());

            return new SimpleAuthenticationInfo(
                    user.getUsername(),
                    user.getPassword(),
                    bytes,
                    this.getName()
            );
        }

        return null;
    }
}
