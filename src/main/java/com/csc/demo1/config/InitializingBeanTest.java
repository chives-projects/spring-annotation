package com.csc.demo1.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-11-24 16:53
 * @Version: 1.0
 */
@Component
public class InitializingBeanTest implements InitializingBean {
    @Override
    public void afterPropertiesSet() {
        System.out.println("[InitializingBean] InitializingBeanTest");
    }
}