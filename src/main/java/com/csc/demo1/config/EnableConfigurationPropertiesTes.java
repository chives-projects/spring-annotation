package com.csc.demo1.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-09-29 14:05
 * @Version: 1.0
 */

// 与EnableConfigurationProperties配合使用，
@ConfigurationProperties("mystr")
public class EnableConfigurationPropertiesTes {
    private String k1;

    public String getK1() {
        return k1;
    }

    public void setK1(String k1) {
        this.k1 = k1;
    }

    @Bean
    public String ccc() {
        System.out.println("cccc");
        return "bbb";
    }
}
