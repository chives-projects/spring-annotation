package com.csc.demo1.ioc;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @PackageName: com.csc.demo1.ioc
 * @Author: 陈世超
 * @Create: 2021-10-08 13:48
 * @Version: 1.0
 */
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        if (beanFactory.containsBeanDefinition(StringUtils.join("spring.redis-", RedisProperties.class.getName()))) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(StringUtils.join("spring.redis-", RedisProperties.class.getName()));
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        }
        if (beanFactory.containsBeanDefinition("redisTemplate")) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition("redisTemplate");
            beanDefinition.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        }
    }
}
