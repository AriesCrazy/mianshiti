package com.example.config;


import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.*;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.Duration;

@Configuration
@EnableCaching
public class CacheConfig extends CachingConfigurerSupport {


    @Resource
    private RedisConnectionFactory factory;


    // 自定义key前缀，例如：cacheName=product.selectOne -> product.selectOne.
    //SEPARATOR=#
    @Bean
    public CacheKeyPrefix cacheKeyPrefix() {
        return cacheName -> cacheName + ".";
    }


    //key生成器
    @Bean
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            if (ObjectUtil.isBasicType(params[0]) || StrUtil.isEmptyIfStr(params[0])) {
                for (Object param : params) {
                    sb.append(param.toString());//基本类型、字符串类型 key直接拼接
                }
            } else {
                sb.append(JSONUtil.toJsonStr(params[0]));//对象类型拼接json字符串 {}
            }
            return sb.toString();
        };
    }

    @Bean//序列化器
    public Jackson2JsonRedisSerializer<Object> serializer() {
        Jackson2JsonRedisSerializer<Object> serializer = new Jackson2JsonRedisSerializer<>(Object.class);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        objectMapper.configure(MapperFeature.USE_ANNOTATIONS, false);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        objectMapper.activateDefaultTyping(objectMapper.getPolymorphicTypeValidator(), ObjectMapper.DefaultTyping.NON_FINAL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.registerModule(new Jdk8Module())
                .registerModule(new JavaTimeModule())
                .registerModule(new ParameterNamesModule());
        serializer.setObjectMapper(objectMapper);
        return serializer;
    }


    @Bean
    public RedisCacheManager redisCacheManager(Jackson2JsonRedisSerializer<Object> serializer) {

        RedisSerializationContext<Object, Object> serializationContext = RedisSerializationContext.fromSerializer(serializer);
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(serializationContext.getValueSerializationPair())//指定redis序列化器
                .computePrefixWith(cacheKeyPrefix());//指定key前缀
        return new CustomRedisCacheManager(RedisCacheWriter.nonLockingRedisCacheWriter(factory), redisCacheConfiguration);

    }


    static class CustomRedisCacheManager extends RedisCacheManager {
        public static final String SEPARATOR = "#";

        public CustomRedisCacheManager(RedisCacheWriter cacheWriter, RedisCacheConfiguration defaultCacheConfiguration) {
            super(cacheWriter, defaultCacheConfiguration);
        }

        @Override
        protected RedisCache createRedisCache(String name, RedisCacheConfiguration cacheConfig) {
            //name=product.selectOne#1800
            String[] arr = StringUtils.delimitedListToStringArray(name, SEPARATOR);
            name = arr[0];//name=product.selectOne
            if (arr.length > 1) {
                long ttl = Long.parseLong(arr[1]);
                cacheConfig = cacheConfig.entryTtl(Duration.ofSeconds(ttl));
            }
            return super.createRedisCache(name, cacheConfig);
        }
    }

}