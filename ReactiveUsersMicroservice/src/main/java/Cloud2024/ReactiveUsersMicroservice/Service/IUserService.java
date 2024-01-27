package Cloud2024.ReactiveUsersMicroservice.Service;

import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

public interface IUserService {
    public Mono<UserBoundary> createUser(UserBoundary user);
    public Mono<UserBoundary> getUserByEmailAndPassword(String email, String password);
    public Flux<UserBoundary> getAllUsers();

    public Flux<UserBoundary> getUsersByDomain(String domain);

}
