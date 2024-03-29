package Cloud2024.ReactiveUsersMicroservice.Service;

import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.DepartmentBoundary;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<UserBoundary> createUser(UserBoundary user);
    Flux<UserBoundary> getUsersByCriteria(String criteria, String value);
    Mono<Void> deleteAllUsers();
    Mono<UserBoundary> getUserByEmailAndPassword(String email, String password);
    Flux<UserBoundary> getAllUsers();
}
