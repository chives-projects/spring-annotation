package com.csc.demo1.config.consul;

import com.ecwid.consul.v1.ConsulClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.consul.discovery.ConsulDiscoveryProperties;
import org.springframework.cloud.consul.discovery.HeartbeatProperties;
import org.springframework.cloud.consul.discovery.TtlScheduler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @description: Consul自定义配置
 * @author: csc
 * @date: 2020/6/5 1:32 PM
 * @version: 1.0
 */
//@Configuration
public class ConsulConfig {
//    @Autowired(required = false)
    private TtlScheduler ttlScheduler;

//    @Bean
    public MyConsulServiceRegistry consulServiceRegistry(
            ConsulClient consulClient,
            ConsulDiscoveryProperties properties,
            HeartbeatProperties heartbeatProperties) {
        return new MyConsulServiceRegistry(
                consulClient, properties, ttlScheduler, heartbeatProperties);
    }
}
