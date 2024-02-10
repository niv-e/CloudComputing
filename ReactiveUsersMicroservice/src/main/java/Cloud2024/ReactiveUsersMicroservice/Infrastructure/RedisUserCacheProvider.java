package Cloud2024.ReactiveUsersMicroservice.Infrastructure;

import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;


//@Configuration
public class RedisUserCacheProvider {
//    @Autowired
//    RedisConnectionFactory factory;

   // @Bean
    public ReactiveRedisTemplate<String, UserBoundary> reactiveRedisTemplate(ReactiveRedisConnectionFactory factory) {

        Jackson2JsonRedisSerializer<UserBoundary> serializer = new Jackson2JsonRedisSerializer<>(UserBoundary.class);

        RedisSerializationContext.RedisSerializationContextBuilder<String, UserBoundary> builder =
                RedisSerializationContext.newSerializationContext(new StringRedisSerializer());

        RedisSerializationContext<String, UserBoundary> context = builder.value(serializer)
                .build();

        return new ReactiveRedisTemplate<>(factory, context);
    }

}
