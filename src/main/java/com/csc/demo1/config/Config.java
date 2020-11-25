package com.csc.demo1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.DefaultLifecycleProcessor;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-11-25 13:43
 * @Version: 1.0
 */
@Configuration
public class Config {
    @Bean("lifecycleProcessor")  //需指定bean的id：lifecycleProcessor
    public DefaultLifecycleProcessor initDefaultLifeCycleProcessor(){
        DefaultLifecycleProcessor defaultLifecycleProcessor=new DefaultLifecycleProcessor();
        defaultLifecycleProcessor.setTimeoutPerShutdownPhase(3000L);

        return defaultLifecycleProcessor;
    }
}
