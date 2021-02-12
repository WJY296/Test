package com.wjy.test;



import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wangjingyang
 * @date 2021-01-05 17:15
 */
@MyAnnotation(age = 1)
public class Test {
    @MyAnnotation(age = 1)
    public static void main(String[] args) {
        
    }
}

/**
 * @author 86176
 */
//表示这个注解类可以加在哪里
@Target(value = {ElementType.TYPE,ElementType.METHOD})
@Retention(value = RetentionPolicy.RUNTIME)
@interface MyAnnotation{
    String value() default "wjy";
    int age() ;
}
