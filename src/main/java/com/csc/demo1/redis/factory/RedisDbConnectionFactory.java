package com.csc.demo1.redis.factory;

import com.csc.demo1.redis.entity.ConnectionInfo;
import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.cluster.ClusterClientOptions;
import io.lettuce.core.cluster.ClusterTopologyRefreshOptions;
import io.lettuce.core.resource.DefaultClientResources;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettucePoolingClientConfiguration;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Objects;

/**

 * @description: Redis连接工厂类


 */
public class RedisDbConnectionFactory {
    private static final RedisDbConnectionFactory INSTANCE = new RedisDbConnectionFactory();

    /**
     * 创建连接工厂类
     *
     * @param redisConfiguration 连接配置
     * @return
     */
    public static RedisConnectionFactory createLettuceConnectionFactory(RedisConfiguration redisConfiguration, RedisProperties properties) {
        //redis客户端配置
        LettucePoolingClientConfiguration.LettucePoolingClientConfigurationBuilder builder = LettucePoolingClientConfiguration.builder();
        // 连接池配置
        builder.poolConfig(INSTANCE.getPoolConfig(properties.getLettuce().getPool()));
        if (properties.isSsl()) {
            builder.useSsl();
        }
        if (StringUtils.hasText(properties.getUrl())) {
            INSTANCE.customizeConfigurationFromUrl(builder, properties);
        }
        // Redis客户端读取超时时间
        if (Objects.nonNull(properties.getTimeout())) {
            builder.commandTimeout(properties.getTimeout());
        }
        // 关闭连接池超时时间
        if (properties.getLettuce() != null) {
            RedisProperties.Lettuce lettuce = properties.getLettuce();
            if (lettuce.getShutdownTimeout() != null && !lettuce.getShutdownTimeout().isZero()) {
                builder.shutdownTimeout(properties.getLettuce().getShutdownTimeout());
            }
        }
        if (StringUtils.hasText(properties.getClientName())) {
            builder.clientName(properties.getClientName());
        }
        builder.clientOptions(INSTANCE.createClientOptions(properties));
        builder.clientResources(DefaultClientResources.create());
        // 根据配置和客户端配置创建连接
        LettuceConnectionFactory factory = new LettuceConnectionFactory(redisConfiguration, builder.build());
        // 创建Redis连接
        factory.afterPropertiesSet();

        return factory;
    }

    /**
     * 获取连接池配置
     *
     * @param properties
     * @return
     */
    private GenericObjectPoolConfig<?> getPoolConfig(RedisProperties.Pool properties) {
        GenericObjectPoolConfig<?> config = new GenericObjectPoolConfig<>();
        if (properties == null) {
            return config;
        }
        config.setMaxTotal(properties.getMaxActive());
        config.setMaxIdle(properties.getMaxIdle());
        config.setMinIdle(properties.getMinIdle());
        if (properties.getTimeBetweenEvictionRuns() != null) {
            config.setTimeBetweenEvictionRunsMillis(properties.getTimeBetweenEvictionRuns().toMillis());
        }
        if (properties.getMaxWait() != null) {
            config.setMaxWaitMillis(properties.getMaxWait().toMillis());
        }
        return config;
    }

    private void customizeConfigurationFromUrl(LettuceClientConfiguration.LettuceClientConfigurationBuilder builder, RedisProperties properties) {
        ConnectionInfo connectionInfo = ConnectionInfo.parseUrl(properties.getUrl());
        if (connectionInfo.isUseSsl()) {
            builder.useSsl();
        }
    }

    private ClientOptions createClientOptions(RedisProperties properties) {
        ClientOptions.Builder builder = initializeClientOptionsBuilder(properties);
        Duration connectTimeout = properties.getConnectTimeout();
        if (connectTimeout != null) {
            builder.socketOptions(SocketOptions.builder().connectTimeout(connectTimeout).build());
        }
        return builder.timeoutOptions(TimeoutOptions.enabled()).build();
    }

    private ClientOptions.Builder initializeClientOptionsBuilder(RedisProperties properties) {
        if (properties.getCluster() != null) {
            ClusterClientOptions.Builder builder = ClusterClientOptions.builder();
            RedisProperties.Lettuce.Cluster.Refresh refreshProperties = properties.getLettuce().getCluster().getRefresh();
            ClusterTopologyRefreshOptions.Builder refreshBuilder = ClusterTopologyRefreshOptions.builder().dynamicRefreshSources(refreshProperties.isDynamicRefreshSources());
            if (refreshProperties.getPeriod() != null) {
                refreshBuilder.enablePeriodicRefresh(refreshProperties.getPeriod());
            }

            if (refreshProperties.isAdaptive()) {
                refreshBuilder.enableAllAdaptiveRefreshTriggers();
            }

            return builder.topologyRefreshOptions(refreshBuilder.build());
        } else {
            return ClientOptions.builder();
        }
    }
}
