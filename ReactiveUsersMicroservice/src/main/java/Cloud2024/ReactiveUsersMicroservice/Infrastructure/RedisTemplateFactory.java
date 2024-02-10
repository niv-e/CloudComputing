//package Cloud2024.ReactiveUsersMicroservice.Infrastructure;
//
//import Cloud2024.ReactiveUsersMicroservice.Infrastructure.Cache.ICacheable;
//import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
//import Cloud2024.ReactiveUsersMicroservice.ReactiveUsersMicroservice;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory;
//import org.springframework.data.redis.core.ReactiveRedisTemplate;
//import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
//import org.springframework.data.redis.serializer.RedisSerializationContext;
//import org.springframework.data.redis.serializer.RedisSerializer;
//@Configuration
//public class RedisTemplateFactory {
//    @Bean
//    public <String, UserBoundary> ReactiveRedisTemplate<String, Cloud2024.ReactiveUsersMicroservice.Infrastructure.Cache.ICacheable> reactiveRedisTemplate(
//            ReactiveRedisConnectionFactory factory){
//
//        //RedisSerializer<ICacheable> valueSerializer;
//        var valueSerializer = new Jackson2JsonRedisSerializer<>(Cloud2024.ReactiveUsersMicroservice.Infrastructure.Cache.ICacheable.class);
//
//        RedisSerializer<java.lang.String> keySerializer;
//        keySerializer = new Jackson2JsonRedisSerializer<>(java.lang.String.class);
//        RedisSerializationContext.RedisSerializationContextBuilder<String, Cloud2024.ReactiveUsersMicroservice.Infrastructure.Cache.ICacheable> builder =
//                RedisSerializationContext.newSerializationContext(keySerializer);
//        RedisSerializationContext<String, Cloud2024.ReactiveUsersMicroservice.Infrastructure.Cache.ICacheable> context =
//                builder.value(valueSerializer).build();
//        return new ReactiveRedisTemplate<>(factory, context);
//    }
//}
