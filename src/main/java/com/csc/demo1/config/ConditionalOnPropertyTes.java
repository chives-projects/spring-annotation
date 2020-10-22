package com.csc.demo1.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-10-22 19:50
 * @Version: 1.0
 */

// ConditionalOnProperty控制某个configuration是否生效
@Configuration
@ConditionalOnProperty(prefix = "mystr", name = "enabled", havingValue = "true", matchIfMissing = true)
public class ConditionalOnPropertyTes {
    private String k1;

    public String getK1() {
        return k1;
    }

    public void setK1(String k1) {
        this.k1 = k1;
    }

    @Bean
    public String get() {
        System.out.println("getget");
        return "bbb";
    }
}
