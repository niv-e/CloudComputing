package Cloud2024.ReactiveUsersMicroservice.Infrastructure.Cache;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.List;

public class NoOpCacheOperation<ICacheable> implements IReactiveCacheOperation<ICacheable> {
    @Override
    public Flux<ICacheable> GetValues(String key) {
        return Flux.empty();
    }

    @Override
    public Mono<ICacheable> GetValue(String key) {
        return Mono.empty();
    }

    @Override
    public Mono<Boolean> SetValue(String key, ICacheable value) {
        return Mono.just(false);
    }

    @Override
    public Mono<Long> SetValues(String key, List<ICacheable> value) { return Mono.just(Long.valueOf(-1)); }
}
