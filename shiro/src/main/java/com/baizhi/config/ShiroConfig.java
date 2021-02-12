package com.baizhi.config;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {
    /**
     * 配置过滤器工厂
     * <p>
     * 拦截（过滤）规则
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean factoryBean = new ShiroFilterFactoryBean();
        /**
         * 配置拦截规则  setFilterChainDefinitionMap 设置过滤器链
         * anon 代表匿名可访问 就是不过滤 不登录就可以访问
         * authc 代表认证可访问  需要登录之后才能访问
         * 支持通配符 *
         *
         * 注意：不能出现 /** 的配置 有bug
         */
        Map map = new HashMap();
        map.put("/login", "anon");

        //把过滤规则配置到 ShiroFilterFactoryBean 中
        factoryBean.setFilterChainDefinitionMap(map);

        /**
         * 配置安全管理器
         */
        factoryBean.setSecurityManager(securityManager);

        return factoryBean;
    }

    @Bean
    public DefaultWebSecurityManager securityManager(AuthenRealm authenRealm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
//        需要自定义Realm
        securityManager.setRealm(authenRealm);

        return securityManager;
    }


    @Bean
    public AuthenRealm authenRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        AuthenRealm authenRealm = new AuthenRealm();
        //把校验规则设置到 AuthenRealm 类中
        authenRealm.setCredentialsMatcher(hashedCredentialsMatcher);
        return authenRealm;
    }

    /**
     * 密码校验规则HashedCredentialsMatcher
     */
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher() {
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //指定加密方式为MD5 SHA
        credentialsMatcher.setHashAlgorithmName("MD5");
        //加密次数 散列次数   必须和注册时候散列次数保持一致
        credentialsMatcher.setHashIterations(1024);
        credentialsMatcher.setStoredCredentialsHexEncoded(true);
        return credentialsMatcher;
    }
}
