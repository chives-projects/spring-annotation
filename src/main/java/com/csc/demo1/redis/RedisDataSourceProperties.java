package com.csc.demo1.redis;

import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @description: Redis多数据源配置文件
 */
@ConfigurationProperties(prefix = "spring.csc.redis")
public class RedisDataSourceProperties {
    /**
     * 默认配置
     */
    public static final String DEFAULT_CONFIG = "default";
    /**
     * 是否开启数据源组件, 默认：true
     */
    private boolean enabled = true;
    /**
     * 默认配置
     */
    private String defaultConfig = DEFAULT_CONFIG;
    /**
     * 多数据源配置
     */
    private Map<String, RedisProperties> config = new HashMap<>();

    public Map<String, RedisProperties> getConfig() {
        return config;
    }

    public String getDefaultConfig() {
        return defaultConfig;
    }

    public void setDefaultConfig(String defaultConfig) {
        this.defaultConfig = defaultConfig;
    }

    public void setConfig(Map<String, RedisProperties> config) {
        this.config = config;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public RedisProperties getDefaultDataSource() {
        return this.config.get(this.getDefaultConfig());
    }
}
