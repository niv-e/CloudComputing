package Cloud2024.ReactiveUsersMicroservice.Presentation;

import org.springframework.http.MediaType;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import Cloud2024.ReactiveUsersMicroservice.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;


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

    @GetMapping("/{email}")
    public Mono<UserBoundary> getUserByEmailAndPassword(
            @PathVariable String email,
            @RequestParam String password) {
        return this.userService.getUserByEmailAndPassword(email, password)
                .log();
    }

    @GetMapping
    public Flux<UserBoundary> getAllUsers() {
        return userService.getAllUsers().log();
    }

    @GetMapping(params = {"criteria=byDomain", "value"})
    public Flux<UserBoundary> getUsersByDomain(@RequestParam String value) {
        return userService.getUsersByDomain(value).log();
    }
}
