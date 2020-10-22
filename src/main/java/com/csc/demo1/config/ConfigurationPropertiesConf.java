package com.csc.demo1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description:
 * @PackageName: com.csc.demo1.config
 * @Author: 陈世超
 * @Create: 2020-09-29 13:51
 * @Version: 1.0
 */
@Configuration
public class ConfigurationPropertiesConf {
    @Autowired
    private ConfigurationPropertiesTes configurationPropertiesTes;

    @Bean("aaa")
    public ConfigurationPropertiesTes tes(){
        System.out.println("----"+configurationPropertiesTes.getMap().size());
        return new ConfigurationPropertiesTes();
    }
}
