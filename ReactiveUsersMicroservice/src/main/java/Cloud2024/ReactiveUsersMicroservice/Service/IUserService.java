package Cloud2024.ReactiveUsersMicroservice.Service;

import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import reactor.core.publisher.Mono;

public interface IUserService {
    Mono<UserBoundary> createUser(UserBoundary user);
}
