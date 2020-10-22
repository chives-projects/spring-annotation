package com.csc.demo1.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-09-29 10:18
 * @Version: 1.0
 */
@Component
@ConfigurationProperties(prefix = "my")
public class ConfigurationPropertiesTes implements InitializingBean {
    private Map<String,String> map;

    public Map<String, String> getMap() {
        return map;
    }

    public void setMap(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        // can do other thing
        System.out.println(map.size());
    }
}
