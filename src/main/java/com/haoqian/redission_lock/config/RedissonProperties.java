package com.haoqian.redission_lock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "redisson")
public class RedissonProperties {

    private int timeout = 3000; // 连接超时时间

    private String address;     // 单机redis地址

    private String password;    // redis密码

    private int database = 0;   // 使用的redis库号

    private int connectionPoolSize = 64;  // 连接池大小

    private int connectionMinimumIdleSize = 10; // 连接池中的最小空闲连接

    private int slaveConnectionPoolSize = 250;  // 从节点连接池大小

    private int masterConnectionPoolSize = 250; // 主节点连接池大小

    private String masterName; // 主节点名称

    private String[] sentinelAddresses; // 集群地址

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public int getSlaveConnectionPoolSize() {
        return slaveConnectionPoolSize;
    }

    public void setSlaveConnectionPoolSize(int slaveConnectionPoolSize) {
        this.slaveConnectionPoolSize = slaveConnectionPoolSize;
    }

    public int getMasterConnectionPoolSize() {
        return masterConnectionPoolSize;
    }

    public void setMasterConnectionPoolSize(int masterConnectionPoolSize) {
        this.masterConnectionPoolSize = masterConnectionPoolSize;
    }

    public String[] getSentinelAddresses() {
        return sentinelAddresses;
    }

    public void setSentinelAddresses(String[] sentinelAddresses) {
        this.sentinelAddresses = sentinelAddresses;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getDatabase() {
        return this.database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getConnectionPoolSize() {
        return connectionPoolSize;
    }

    public void setConnectionPoolSize(int connectionPoolSize) {
        this.connectionPoolSize = connectionPoolSize;
    }

    public int getConnectionMinimumIdleSize() {
        return connectionMinimumIdleSize;
    }

    public void setConnectionMinimumIdleSize(int connectionMinimumIdleSize) {
        this.connectionMinimumIdleSize = connectionMinimumIdleSize;
    }
}