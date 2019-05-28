package com.example.xmljpademo.config;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.util.Arrays;
import java.util.List;

@Configuration
@Profile("redis-cluster")
public class RedisClusterConfig {

    @Bean
    public JedisConnectionFactory redisConnectionFactory() {

        List<String> nodes = Arrays.asList(
                "192.168.21.225:7000",
                "192.168.21.225:7001",
                "192.168.21.225:7002",
                "192.168.21.225:7003",
                "192.168.21.225:7004",
                "192.168.21.225:7005");
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(nodes);

        return new JedisConnectionFactory(clusterConfiguration);
    }

//    @Bean
//    @Profile("redis-standalone")
//    public JedisConnectionFactory redisStandAloneConnectionFactory() {
//        RedisStandaloneConfiguration standaloneConfiguration = new RedisStandaloneConfiguration("192.168.21.248", 6379);
//        return new JedisConnectionFactory(standaloneConfiguration);
//    }

    @Bean("redisTemplate")
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());

        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setValueSerializer(jsonRedisSerializer);
        template.setHashKeySerializer(jsonRedisSerializer);
        return template;
    }
}
