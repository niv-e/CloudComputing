package Cloud2024.ReactiveUsersMicroservice.Presentation;


import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.MediaType;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import Cloud2024.ReactiveUsersMicroservice.Service.UserService;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
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
    @CachePut(value = "users", key = "#user.email")
    public Mono<UserBoundary> createUser (
            @RequestBody UserBoundary user) {
        return this.userService.createUser(user)
                .log();
    }



    @GetMapping(
            path = "/criteria",
            produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    @Cacheable(cacheNames = "users", key = "#criteria + ':' + #value")
    public Flux<UserBoundary> getUsersByCriteria(
            @RequestParam("criteria") String criteria,
            @RequestParam("value") String value){
        return this.userService
                .getUsersByCriteria(criteria, value)
                .log();
    }

    @DeleteMapping
    @CacheEvict(cacheNames = "users", allEntries = true)
    public Mono<Void> deleteAllUsers() {
        return this.userService
                .deleteAllUsers()
                .log();
    }
    @GetMapping("/{email}")
    @CachePut(value = "users", key = "#email")
    public Mono<UserBoundary> getUserByEmailAndPassword(
            @PathVariable String email,
            @RequestParam String password) {
        return this.userService.getUserByEmailAndPassword(email, password)
                .log();
    }

    @GetMapping(produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    @Cacheable(cacheNames = "users", key = "'allUsers'")
    public Flux<UserBoundary> getAllUsers() {
        return userService.getAllUsers()
                .log();
    }
}
