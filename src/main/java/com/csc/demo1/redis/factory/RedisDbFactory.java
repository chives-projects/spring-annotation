package com.csc.demo1.redis.factory;

import com.csc.demo1.ioc.IOCContext;
import com.csc.demo1.redis.RedisDataSourceProperties;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.text.MessageFormat;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @description: Redis数据源
 */
public class RedisDbFactory {
    /**
     * 字符串前缀
     */
    private static final String PREFIX_STRING = "StringRedisTemplate";
    /**
     * RestTemplate对象前缀
     */
    private static final String PREFIX_REST = "RedisTemplate";
    /**
     * StringRedisTemplate对象缓存
     */
    private static final Map<String, StringRedisTemplate> stringCache = new ConcurrentHashMap<>();
    /**
     * RedisTemplate对象缓存
     */
    private static final Map<String, RedisTemplate> restCache = new ConcurrentHashMap<>();

    public static final RedisDbFactory INSTANCE = new RedisDbFactory();

    public static Map<String, StringRedisTemplate> getStringCache() {
        return stringCache;
    }

    public static Map<String, RedisTemplate> getRestCache() {
        return restCache;
    }

    /**
     * 获取Redis默认字符串模板
     *
     * @return
     */
    public static StringRedisTemplate getStringRedisTemplate() {
        return getStringRedisTemplate(RedisDataSourceProperties.DEFAULT_CONFIG);
    }

    /**
     * 获取Redis模板对戏
     * IOCContext.getBean("acdataStringRedisTemplate")
     * RedisDbFactory.getStringRedisTemplate("acdata")
     * @param redisMark 数据源标识
     * @return
     */
    public static StringRedisTemplate getStringRedisTemplate(String redisMark) {
        /**
         * 获取缓存key
         */
        String key = INSTANCE.getStringRedisTemplateBeanName(redisMark);
        if (stringCache.containsKey(key)) {
            return stringCache.get(key);
        }
        if (!IOCContext.containsBean(key)) {
            throw new IllegalArgumentException("Redis数据库标识对应的数据库不存在");
        }
        StringRedisTemplate stringRedisTemplate = IOCContext.getBean(key, StringRedisTemplate.class);
        stringCache.put(key, stringRedisTemplate);
        return stringRedisTemplate;
    }

    /**
     * 获取Redis默认字符串模板
     *
     * @return
     */
    public static RedisTemplate getRedisTemplate() {
        return getRedisTemplate(RedisDataSourceProperties.DEFAULT_CONFIG);
    }

    /**
     * 获取Redis模板对戏
     *
     * @param redisMark 数据源标识
     * @return
     */
    public static RedisTemplate getRedisTemplate(String redisMark) {
        /**
         * 获取缓存key
         */
        String key = INSTANCE.getRedisTemplateBeanName(redisMark);
        if (restCache.containsKey(key)) {
            return restCache.get(key);
        }
        if (!IOCContext.containsBean(key)) {
            throw new IllegalArgumentException("Redis数据库标识对应的数据库不存在");
        }
        RedisTemplate redisTemplate = IOCContext.getBean(key, RedisTemplate.class);
        restCache.put(key, redisTemplate);
        return redisTemplate;
    }

    /**
     * 获取StringRedisTemplate对象bean名称
     *
     * @param redisMark
     * @return
     */
    public String getStringRedisTemplateBeanName(String redisMark) {
        if (Objects.isNull(redisMark)) {
            throw new IllegalArgumentException("Redis数据库标识不可为空");
        }
        return MessageFormat.format("{0}{1}", redisMark, PREFIX_STRING);
    }

    /**
     * 获取RedisTemplate对象bean名称
     *
     * @param redisMark
     * @return
     */
    public String getRedisTemplateBeanName(String redisMark) {
        if (Objects.isNull(redisMark)) {
            throw new IllegalArgumentException("Redis数据库标识不可为空");
        }
        return MessageFormat.format("{0}{1}", redisMark, PREFIX_REST);
    }
}
