package com.wavesdev.cache.config;

/**
 * Created by pranavan on 3/12/18.
 */

import com.hazelcast.config.*;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.spring.cache.HazelcastCacheManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class HazelCastCacheConfig {

    @Value("${cache.hazelcast.config.timeToLiveSeconds}")
    private Integer timeToLiveSeconds;
    @Value("${cache.hazelcast.config.maxIdleSeconds}")
    private Integer maxIdleSeconds;
    @Value("${cache.hazelcast.config.maxSize}")
    private Integer maxSize;
    private EvictionPolicy evictionPolicy=EvictionPolicy.LRU;


    @Bean
    public Config cacheConfig(){
        Config config = new Config();
        config.setInstanceName("hazelcast-cache");
        MapConfig mapConfig =getMapConfig(timeToLiveSeconds,evictionPolicy,maxIdleSeconds,maxSize);
        config.getMapConfigs().put("product",mapConfig);
        return config;
    }
    private MapConfig getMapConfig(Integer timeToLiveSeconds,EvictionPolicy evictionPolicy,Integer maxIdleSeconds,Integer maxSize){
        MapConfig mapConfig = new MapConfig();
        mapConfig.setTimeToLiveSeconds(timeToLiveSeconds);
        mapConfig.setEvictionPolicy(evictionPolicy);
        mapConfig.setMaxIdleSeconds(maxIdleSeconds);
        mapConfig.setInMemoryFormat(InMemoryFormat.OBJECT);
        mapConfig.setMaxSizeConfig(new MaxSizeConfig(maxSize, MaxSizeConfig.MaxSizePolicy.FREE_HEAP_SIZE));
        return mapConfig;

    }


    @Bean(name="hazecastManager")
    @Primary
    public CacheManager cacheManager() {
        Config config=cacheConfig();
        HazelcastInstance hazelcastInstance=Hazelcast.newHazelcastInstance(config);

        return new HazelcastCacheManager(hazelcastInstance);
    }

    }
