package com.tutomato.climbinggymapi.redis;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class RedisServiceTest {

    @Autowired
    private StringRedisTemplate redisTemplate;


    @Test
    @Transactional
    public void redis_key_insert_test(){
        String key = "test redis key"; String value = "test redis value";

        final ValueOperations<String, String> stringStringValueOperations = redisTemplate.opsForValue();
        stringStringValueOperations.set(key, value); // redis set 명령어

        String redisValue = redisTemplate.opsForValue().get(key);

        assertEquals(value, redisValue);
    }
}
