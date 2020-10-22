package com.csc.demo1.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-09-29 14:06
 * @Version: 1.0
 */
@Configuration
// EnableConfigurationProperties相当于把使用 @ConfigurationProperties 的类进行了一次注入
@EnableConfigurationProperties(EnableConfigurationPropertiesTes.class)
public class EnableConfigurationPropertiesConf {
    @Resource
    private EnableConfigurationPropertiesTes enableConfigurationPropertiesTes;
    @Bean
    public String TesStrC(){
        System.out.println(enableConfigurationPropertiesTes.getK1());
        return "";
    }

//    @Bean
//    @ConditionalOnMissingBean
//    TesStr tesStr(){
//        return new TesStr();
//    }
}
