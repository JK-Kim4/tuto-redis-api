package com.tutomato.apigateway;

import com.tutomato.apigateway.config.RateLimitConfig;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.ConsumptionProbe;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Slf4j
@RequiredArgsConstructor
@Service
public class RateLimitService {

    private final RateLimitConfig rateLimiterConfig;

    public boolean tryConsume(String remoteAddrKey) {
        Bucket bucket = getOrCreateBucket(remoteAddrKey);

        ConsumptionProbe probe = consumeToken(bucket);

        logConsumption(remoteAddrKey, probe);

        handleNotConsumed(probe);

        return probe.isConsumed();
    }

    private Bucket getOrCreateBucket(String apiKey) {
        return rateLimiterConfig.lettuceBasedProxyManager().builder()
                .build(apiKey, () -> rateLimiterConfig.bucketConfiguration());
    }

    private ConsumptionProbe consumeToken(Bucket bucket) {
        return bucket.tryConsumeAndReturnRemaining(1);
    }

    private void logConsumption(String remoteAddrKey, ConsumptionProbe probe) {
        log.info("API Key: {}, RemoteAddress: {}, tryConsume: {}, remainToken: {}, tryTime: {}",
                remoteAddrKey, remoteAddrKey, probe.isConsumed(), probe.getRemainingTokens(), LocalDateTime.now());
    }

    private void handleNotConsumed(ConsumptionProbe probe) {
        if (!probe.isConsumed()) {
            throw new RateLimiterException(RateLimiterException.TOO_MANY_REQUEST);
        }
    }

    public long getRemainToken(String apiKey) {
        Bucket bucket = getOrCreateBucket(apiKey);
        return bucket.getAvailableTokens();
    }
}
