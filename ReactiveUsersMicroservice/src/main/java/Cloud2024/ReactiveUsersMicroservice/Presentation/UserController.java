package Cloud2024.ReactiveUsersMicroservice.Presentation;

import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import Cloud2024.ReactiveUsersMicroservice.Service.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/users"})
public class UserController {
    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<UserBoundary> createUser (
            @RequestBody UserBoundary user) {
        return this.userService
                .createUser(user)
                .log();
    }
}
