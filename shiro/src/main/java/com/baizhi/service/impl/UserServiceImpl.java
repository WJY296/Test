package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.SimpleTimeZone;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void regist(String username, String password) {
        User user = new User();
        user.setUsername(username);

        //1.生成随机盐值
        String salt = UUID.randomUUID().toString().substring(0, 4);
        user.setSalt(salt);

        //2. 对明文进行加密处理
        Md5Hash md5Hash = new Md5Hash(password, salt, 1024);
        user.setPassword(md5Hash.toHex());

        userDao.insert(user);
    }


}
