package Cloud2024.ReactiveUsersMicroservice.Infrastructure.Cache;

import org.springframework.data.redis.core.ReactiveRedisTemplate;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.List;

public class ReactiveCacheOperation<ICacheable> implements IReactiveCacheOperation<ICacheable> {

    private ReactiveRedisTemplate<String,ICacheable> cache;

    public ReactiveCacheOperation(ReactiveRedisTemplate<String, ICacheable> cache) {
        this.cache = cache;
    }

    @Override
    public Flux<ICacheable> GetValues(String key) {
        return cache.opsForList()
                .range(key,0,-1);
    }

    @Override
    public Mono<ICacheable> GetValue(String key) {
        return cache.opsForValue()
                .get(key);
    }

    @Override
    public Mono<Boolean> SetValue(String key, ICacheable value) {
        return cache.opsForValue()
                .set(key,value);
    }

    @Override
    public Mono<Long> SetValues(String key, List<ICacheable> value) {
        return cache.opsForList()
                .leftPushAll(key,value);
    }
}
