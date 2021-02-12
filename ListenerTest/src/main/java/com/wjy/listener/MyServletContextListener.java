package com.wjy.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 监听器
 *
 * @author 86176
 */

public class MyServletContextListener implements HttpSessionListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(MyServletContextListener.class);

    private static AtomicInteger userCount = new AtomicInteger(0);

    /**
     * 创建session时触发
     */
    @Override
    public synchronized void sessionCreated(HttpSessionEvent se) {
        userCount.getAndIncrement();
        se.getSession().setAttribute("sessionCount", userCount.get());

        LOGGER.info("【在线人数】人数增加为:{}", userCount.get());
    }

    /**
     * 销毁时触发
     *
     * @param se
     */
    @Override
    public synchronized void sessionDestroyed(HttpSessionEvent se) {
        userCount.getAndDecrement();
        se.getSession().setAttribute("sessionCount", userCount.get());

        LOGGER.info("【在线人数】人数减少为:{}", userCount.get());


    }
}
