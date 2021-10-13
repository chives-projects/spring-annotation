package com.csc.demo1.redis;

import com.csc.demo1.ioc.IOCContext;
import com.csc.demo1.ioc.MyBeanDefinitionRegistryPostProcessor;
import com.csc.demo1.redis.factory.RedisDbConfigurationFactory;
import com.csc.demo1.redis.factory.RedisDbConnectionFactory;
import com.csc.demo1.redis.factory.RedisDbFactory;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.context.AnnotationConfigServletWebServerApplicationContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.instrument.classloading.LoadTimeWeaver;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @description: Redis多数据源配置，参考源码：LettuceConnectionConfiguration
 */
@Configuration
@EnableConfigurationProperties(RedisDataSourceProperties.class)
@ConditionalOnProperty(prefix = "spring.csc.redis", name = "enabled", havingValue = "true", matchIfMissing = true)
@Role(BeanDefinition.ROLE_INFRASTRUCTURE)
public class RedisDataSourceAutoConfiguration implements InitializingBean, DisposableBean, LoadTimeWeaverAware {

    private DefaultListableBeanFactory defaultListableBeanFactory;
    private RedisDataSourceProperties redisDataSourceProperties;

    public RedisDataSourceAutoConfiguration(DefaultListableBeanFactory defaultListableBeanFactory, RedisDataSourceProperties redisDataSourceProperties) {
        this.defaultListableBeanFactory = defaultListableBeanFactory;
        this.redisDataSourceProperties = redisDataSourceProperties;
    }

    @PostConstruct
    public void stringRedisTemplate() {
        Map<String, RedisTemplate> restCache = RedisDbFactory.getRestCache();
        Map<String, StringRedisTemplate> stringCache = RedisDbFactory.getStringCache();
        RootBeanDefinition redisTemplateBeanDefinition = new RootBeanDefinition(RedisTemplate.class);
        RootBeanDefinition stringRedisTemplateBeanDefinition = new RootBeanDefinition(StringRedisTemplate.class);
        Table<String, RedisProperties, RedisConfiguration> table = createConfiguration(redisDataSourceProperties);
        table.rowKeySet().stream().forEach(key -> {
            Map<RedisProperties, RedisConfiguration> dataMap = table.row(key);
            dataMap.forEach((properties, redisConfiguration) -> {
                // 获取StringRedisTemplate对象
                StringRedisTemplate stringRedisTemplate = createStringRedisTemplate(redisConfiguration, properties);
                // 将StringRedisTemplate对象注入IOC容器bean
                String stringRedisTemplateBeanName = RedisDbFactory.INSTANCE.getStringRedisTemplateBeanName(key);
                defaultListableBeanFactory.registerSingleton(stringRedisTemplateBeanName, stringRedisTemplate);
//                defaultListableBeanFactory.registerBeanDefinition(stringRedisTemplateBeanName, stringRedisTemplateBeanDefinition);
                System.out.println(defaultListableBeanFactory.getBean(stringRedisTemplateBeanName));
                stringCache.put(stringRedisTemplateBeanName, stringRedisTemplate);

                // 获取RedisTemplate对象
                RedisTemplate redisTemplate = createRedisTemplate(redisConfiguration, properties);
                // 将RedisTemplate对象注入IOC容器
                String redisTemplateBeanName = RedisDbFactory.INSTANCE.getRedisTemplateBeanName(key);
                defaultListableBeanFactory.registerSingleton(RedisDbFactory.INSTANCE.getRedisTemplateBeanName(key), redisTemplate);
//                defaultListableBeanFactory.registerBeanDefinition(redisTemplateBeanName, redisTemplateBeanDefinition);
                restCache.put(redisTemplateBeanName, redisTemplate);
            });
        });
//        MyBeanDefinitionRegistryPostProcessor beanDefinitionRegistryPostProcessor = new MyBeanDefinitionRegistryPostProcessor();
//        ApplicationContext applicationContext = IOCContext.getApplicationContext();
//        AnnotationConfigServletWebServerApplicationContext  applicationContext1 = (AnnotationConfigServletWebServerApplicationContext) IOCContext.getApplicationContext();
////        AnnotationConfigApplicationContext bean = applicationContext.getBean(AnnotationConfigApplicationContext.class);
//        applicationContext1.addBeanFactoryPostProcessor(beanDefinitionRegistryPostProcessor);
//        applicationContext1.refresh();


    }

    /**
     * 创建 StringRedisTemplate对象
     *
     * @param redisConfiguration 配置类
     * @return
     */
    protected StringRedisTemplate createStringRedisTemplate(RedisConfiguration redisConfiguration, RedisProperties properties) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate(RedisDbConnectionFactory.createLettuceConnectionFactory(redisConfiguration, properties));
        stringRedisTemplate.setKeySerializer(stringSerializer());
        stringRedisTemplate.setValueSerializer(stringSerializer());
        stringRedisTemplate.setHashKeySerializer(stringSerializer());
        stringRedisTemplate.setHashValueSerializer(stringSerializer());
        // bean初始化完成后调用方法，对于StringRedisTemplate可忽略，主要检查key-value序列化对象是否初始化，并标注RedisTemplate已经被初始化
        stringRedisTemplate.afterPropertiesSet();
        return stringRedisTemplate;
    }

    /**
     * 创建 RedisTemplate对象
     *
     * @param redisConfiguration 配置类
     * @return
     */
    protected RedisTemplate createRedisTemplate(RedisConfiguration redisConfiguration, RedisProperties properties) {
        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(RedisDbConnectionFactory.createLettuceConnectionFactory(redisConfiguration, properties));
        redisTemplate.setKeySerializer(stringSerializer());
        redisTemplate.setValueSerializer(jacksonSerializer());
        redisTemplate.setHashKeySerializer(stringSerializer());
        redisTemplate.setHashValueSerializer(jacksonSerializer());
        // bean初始化完成后调用方法，主要检查key-value序列化对象是否初始化，并标注RedisTemplate已经被初始化，否则会报：
        // "template not initialized; call afterPropertiesSet() before using it" 异常
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }

    /**
     * 创建Redis数据源配置key-value映射
     *
     * @param redisDataSourceProperties 配置
     * @return
     */
    protected Table<String, RedisProperties, RedisConfiguration> createConfiguration(RedisDataSourceProperties redisDataSourceProperties) {
        Table<String, RedisProperties, RedisConfiguration> table = HashBasedTable.create();
        Map<String, RedisProperties> redisPropertiesMap = redisDataSourceProperties.getConfig();
        redisPropertiesMap.forEach((key, properties) -> {
            RedisConfiguration redisConfiguration = RedisDbConfigurationFactory.createRedisConfiguration(properties);
            table.put(key, properties, redisConfiguration);
        });
        return table;
    }

    /**
     * 初始化string序列化对象
     */
    protected StringRedisSerializer stringSerializer() {
        return new StringRedisSerializer();
    }

    /**
     * 初始化jackson序列化对象
     */
    protected Jackson2JsonRedisSerializer<Object> jacksonSerializer() {
        //使用Jackson2JsonRedisSerializer来序列化和反序列化redis的value值
        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();

        //指定要序列化的域、field、get和set，以及修饰符范围，ANY是都有包括private和public
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        // objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        // 第一个参数用于验证要反序列化的实际子类型是否对验证器使用的任何条件有效，在反序列化时必须设置，否则报异常
        // 第二个参数设置序列化的类型必须为非final类型，只有少数的类型（String、Boolean、Integer、Double）可以从JSON中正确推断
        objectMapper.activateDefaultTyping(LaissezFaireSubTypeValidator.instance, ObjectMapper.DefaultTyping.NON_FINAL);
        // 解决jackson2无法反序列化LocalDateTime的问题
        objectMapper.registerModule(new JavaTimeModule());

        jackson2JsonRedisSerializer.setObjectMapper(objectMapper);

        return jackson2JsonRedisSerializer;
    }

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void destroy() {
        logger.info("<== 【销毁--自动化配置】----Redis数据库多数据源组件【RedisDataSourceAutoConfiguration】");
    }

    @Override
    public void afterPropertiesSet() {
        logger.info("==> 【初始化--自动化配置】----Redis数据库多数据源组件【RedisDataSourceAutoConfiguration】");
    }

    @Override
    public void setLoadTimeWeaver(LoadTimeWeaver loadTimeWeaver) {

    }
}
