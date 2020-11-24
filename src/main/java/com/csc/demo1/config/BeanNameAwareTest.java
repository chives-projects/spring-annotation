package com.csc.demo1.config;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-10-26 10:01
 * @Version: 1.0
 */
@Component
public class BeanNameAwareTest implements BeanNameAware {
    public BeanNameAwareTest() {
        System.out.println("NormalBean constructor");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("[BeanNameAware] " + name);
    }
}
