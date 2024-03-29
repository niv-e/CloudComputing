package Cloud2024.ReactiveUsersMicroservice.Service;

import Cloud2024.ReactiveUsersMicroservice.Domain.UserEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;



@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity,String> {

    Flux<UserEntity> findByLastName(String lastName);
    Flux<UserEntity> findByDeptId(String deptId);
    Mono<UserEntity> findByEmailAndPassword(String email, String password);
    Flux<UserEntity> findByEmailEndingWith(String domain);
    Flux<UserEntity> findByEmail(String email);
}
