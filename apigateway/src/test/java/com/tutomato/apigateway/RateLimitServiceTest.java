package com.tutomato.apigateway;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RateLimitServiceTest {

    @Autowired
    private RateLimitService rateLimiterService;

    @Autowired
    private RedisTemplate redisTemplate;

    @BeforeEach
    void setUp() {
        redisTemplate.getConnectionFactory().getConnection().flushAll();
    }

    @DisplayName("처리율 제한에 걸리지 않으면 true를 반환한다.")
    @Test
    void return_true_if_not_rate_limit() {
        // given
        String key = "127.0.0.1";

        // when
        boolean result = rateLimiterService.tryConsume(key);

        // then
        Assertions.assertThat(result).isTrue();
    }

    @DisplayName("남은 토큰 개수를 반환한다.")
    @Test
    void return_remain_token() {
        // given
        String key = "127.0.0.1";

        // when

        rateLimiterService.tryConsume(key);
        rateLimiterService.tryConsume(key);

        long remainToken = rateLimiterService.getRemainToken(key);

        // then
        Assertions.assertThat(remainToken).isEqualTo(18);
    }

    @DisplayName("처리율 제한에 걸리면 예외를 발생시킨다.")
    @Test
    void throw_exception_if_rate_limit() {
        // given
        String key = "127.0.0.1";

        // when
        for (int i = 0; i < 20; i++) {
            rateLimiterService.tryConsume(key);
        }

        // then
        Assertions.assertThatThrownBy(() -> rateLimiterService.tryConsume(key))
                .isInstanceOf(RateLimiterException.class)
                .hasMessage(RateLimiterException.TOO_MANY_REQUEST);
    }
}
