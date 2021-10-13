package com.csc.demo1.ioc;

import com.csc.demo1.redis.RedisDataSourceAutoConfiguration;
import com.csc.demo1.redis.RedisDataSourceProperties;
import com.csc.demo1.redis.factory.RedisDbFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @PackageName: com.csc.demo1.ioc
 * @Author: 陈世超
 * @Create: 2021-10-08 11:22
 * @Version: 1.0
 */
@Component
public class MyBeanDefinitionRegistryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Autowired
    RedisDataSourceProperties redisDataSourceAutoConfiguration;

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

        Map<String, RedisTemplate> restCache = RedisDbFactory.getRestCache();
        RootBeanDefinition redisTemplateBeanDefinition = new RootBeanDefinition(RedisTemplate.class);
        Map<String, StringRedisTemplate> stringCache = RedisDbFactory.getStringCache();
        RootBeanDefinition stringRedisTemplateBeanDefinition = new RootBeanDefinition(StringRedisTemplate.class);
        for (Map.Entry<String, RedisTemplate> redisTemplateEntry : restCache.entrySet()) {
            beanDefinitionRegistry.registerBeanDefinition(redisTemplateEntry.getKey(), redisTemplateBeanDefinition);
        }
        for (Map.Entry<String, StringRedisTemplate> redisTemplateEntry : stringCache.entrySet()) {
            beanDefinitionRegistry.registerBeanDefinition(redisTemplateEntry.getKey(), stringRedisTemplateBeanDefinition);
        }
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }
}


