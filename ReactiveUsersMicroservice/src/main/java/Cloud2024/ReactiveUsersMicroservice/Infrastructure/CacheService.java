package Cloud2024.ReactiveUsersMicroservice.Infrastructure;

import Cloud2024.ReactiveUsersMicroservice.Infrastructure.Cache.*;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;

//@Service
public class CacheService<ICacheable> implements ICacheService {
   // @Value("${spring.cache.type:NONE}")
    private String cacheType;
    private boolean cacheEnabled;
    private ReactiveRedisTemplate<String, UserBoundary> cache;

    public CacheService(ReactiveRedisTemplate<String, UserBoundary> cache) {
        this.cache = cache;
    }

    @PostConstruct
    public void init() {
        cacheEnabled = "redis".equalsIgnoreCase(cacheType);
    }

    @Override
    public IReactiveCacheOperation doOperation() {
        if(!cacheEnabled){
            return new NoOpCacheOperation();
        }
        return new ReactiveCacheOperation(this.cache);
    }
}

