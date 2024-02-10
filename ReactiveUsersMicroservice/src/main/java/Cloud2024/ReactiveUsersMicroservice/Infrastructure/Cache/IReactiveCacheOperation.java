package Cloud2024.ReactiveUsersMicroservice.Infrastructure.Cache;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;
import java.util.List;

public interface IReactiveCacheOperation<ICacheable> {
    Flux<ICacheable> GetValues(String key);
    Mono<ICacheable> GetValue(String key);
    Mono<Boolean> SetValue(String key,ICacheable value);
    Mono<Long> SetValues(String key, List<ICacheable> value);
}
