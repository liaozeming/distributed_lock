package com.haoqian.redission_lock.config;

import com.haoqian.redission_lock.distributedLock.DistributedLocker;
import com.haoqian.redission_lock.distributedLock.RedissonDistributedLocker;
import com.haoqian.redission_lock.Utils.RedissonLockUtil;
import io.micrometer.core.instrument.util.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@ConditionalOnClass(Config.class) // org.redisson.config.Config
@EnableConfigurationProperties(RedissonProperties.class) // 自定义redisson配置类
public class RedissonAutoConfiguration {

    @Autowired
    private RedissonProperties redissonProperties;

    /**
     * 哨兵模式自动装配
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "redisson.master-name")
    RedissonClient redissonSentinelClient() {
        Config config = new Config();
        SentinelServersConfig serverConfig = config.useSentinelServers()
                .addSentinelAddress(redissonProperties.getSentinelAddresses())
                .setMasterName(redissonProperties.getMasterName())
                .setTimeout(redissonProperties.getTimeout())
                .setMasterConnectionPoolSize(redissonProperties.getMasterConnectionPoolSize())
                .setSlaveConnectionPoolSize(redissonProperties.getSlaveConnectionPoolSize())
                .setDatabase(redissonProperties.getDatabase());

        if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }
        return Redisson.create(config);
    }

    /**
     * 单机模式自动装配
     *
     * @return
     */
    @Bean
    @ConditionalOnProperty(name = "redisson.address")
    RedissonClient redissonSingleClient() {
        Config config = new Config();
        SingleServerConfig serverConfig = config.useSingleServer()
                .setAddress("redis://" + redissonProperties.getAddress())
                .setTimeout(redissonProperties.getTimeout())
                .setConnectionPoolSize(redissonProperties.getConnectionPoolSize())
                .setConnectionMinimumIdleSize(redissonProperties.getConnectionMinimumIdleSize())
                .setDatabase(redissonProperties.getDatabase());

        if (StringUtils.isNotBlank(redissonProperties.getPassword())) {
            serverConfig.setPassword(redissonProperties.getPassword());
        }

        return Redisson.create(config);
    }

    /**
     * 装配locker类，并将实例注入到 RedissLockUtil 中
     *
     * @return
     */
    @Bean
    DistributedLocker distributedLocker(RedissonClient redissonClient) {
        RedissonDistributedLocker locker = new RedissonDistributedLocker(redissonClient);
        RedissonLockUtil.setLocker(locker);
        return locker;
    }

}