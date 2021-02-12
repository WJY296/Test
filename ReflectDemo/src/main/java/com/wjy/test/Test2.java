package com.wjy.test;


import lombok.Data;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author wangjingyang
 * @date 2021-01-05 22:45
 */
public class Test2 {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        /**
         *获取Class类的三种方式
         */
        User user = new User();
        Class c1 = Class.forName("com.wjy.test.User");
        Class c2 = User.class;
        Class c3 = user.getClass();
        Class c4 = c3.getSuperclass();
        System.out.println(c1.getName());
        System.out.println(c2.getName());
        System.out.println(c3.getName());
        System.out.println(c4.getName());

    }
}
@Data
class SuperUser{}
@Data
class User extends SuperUser{
    private String name;
    private Integer age;
}
