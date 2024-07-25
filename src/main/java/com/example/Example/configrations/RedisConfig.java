package com.example.Example.configrations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {
    @Bean// Redis bağlantı fabrikasını oluşturur
    public RedisConnectionFactory redisConnectionFactory() {
        return new LettuceConnectionFactory();//istemci kütüphanesi
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> template = new RedisTemplate<>();//Template oluşturyor
        template.setConnectionFactory(redisConnectionFactory());//bağlantı fabrikasını ayarlar
        template.setKeySerializer(new StringRedisSerializer());//// Anahtar serileştiricisini ayarlar
        template.setValueSerializer(new StringRedisSerializer());// Değer serileştiricisini ayarlar
        return template;
    }
}
