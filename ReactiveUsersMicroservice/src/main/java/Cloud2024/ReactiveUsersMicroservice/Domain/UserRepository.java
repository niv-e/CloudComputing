package Cloud2024.ReactiveUsersMicroservice.Domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;



@Repository
public interface UserRepository extends ReactiveMongoRepository<UserEntity,String> {

    Flux<UserEntity> findByLastName(String lastName);
    Flux<UserEntity> findByMinimumAge(String minimumAge);
    Flux<UserEntity> findByDepartmentId(String departmentId);
    Mono<UserEntity> findByEmailAndPassword(String email, String password);
    Flux<UserEntity> findByEmailEndingWith(String domain);


}
