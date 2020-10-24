package com.csc.demo1.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-10-24 16:20
 * @Version: 1.0
 */
@Component
public class BeanFactoryAwareTest implements BeanFactoryAware {
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("[TestBeanFactoryAware] " +
                beanFactory.getBean(BeanFactoryAwareTest.class).getClass().getSimpleName());
    }
}
