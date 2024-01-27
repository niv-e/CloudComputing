package Cloud2024.ReactiveUsersMicroservice.Domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;



@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity,String> {

    public Mono<UserEntity> findByEmailAndPassword(String email, String password);
    public Flux<UserEntity> findByEmailEndingWith(String domain);


}
