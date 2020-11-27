package com.csc.demo1.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @program:
 * @description: 异常捕获自动化配置类
 * @author: csc
 * @create: 2020/10/28
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ExceptionProperties.class)
@ConditionalOnProperty(prefix = "spring.csc.exception", name = "enable", havingValue = "true", matchIfMissing = true)
public class ExceptionAutoConfiguration implements CommandLineRunner {
    private Logger logger = LoggerFactory.getLogger(ExceptionAutoConfiguration.class);
    /**
     * 异常捕获自动化配置bean
     * @return
     */
//    @Bean
    public ExceptionAdviceHandler exceptionAdviceHandler() {
        return new ExceptionAdviceHandler();
    }

    @Override
    public void run(String... args) throws Exception {
        logger.info( "【自动化配置】----异常捕获组件初始化完成...");
    }
}
