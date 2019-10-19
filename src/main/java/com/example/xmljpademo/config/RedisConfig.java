package com.example.xmljpademo.config;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfig<K, V> {

    public RedisTemplate<K, V> getRedisTemplate(){
        RedisTemplate<K, V> template = new RedisTemplate<>();
        Jackson2JsonRedisSerializer<Object> jsonRedisSerializer = new Jackson2JsonRedisSerializer<Object>(Object.class);
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());

        template.setValueSerializer(jsonRedisSerializer);
        template.setHashKeySerializer(jsonRedisSerializer);
        return template;
    }
}
