package com.csc.demo1.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Description: ApplicationContextAwareProcessor
 * ApplicationContextAwareProcessor是一个Spring内部工具，它实现了接口BeanPostProcessor,
 * 用于向实现了如下某种Aware接口的bean设置ApplicationContext中相应的属性:
 * EnvironmentAware
 * EmbeddedValueResolverAware
 * ResourceLoaderAware
 * ApplicationEventPublisherAware
 * MessageSourceAware
 * ApplicationContextAware
 * 如果bean实现了以上接口中的某几个，那么这些接口方法调用的先后顺序就是上面接口排列的先后顺序。
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-10-26 9:50
 * @Version: 1.0
 */
@Component
public class ApplicationContextAwareProcessorTest implements EnvironmentAware, ApplicationContextAware {
    private ApplicationContext applicationContext;
    private Environment environment;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("applicationContext");
        this.applicationContext = applicationContext;
    }

    @Override
    public void setEnvironment(Environment environment) {
        System.out.println("environment");
        String[] activeProfiles = environment.getActiveProfiles();
        String property = environment.getProperty("spring.application.name");
        this.environment = environment;
    }
}
