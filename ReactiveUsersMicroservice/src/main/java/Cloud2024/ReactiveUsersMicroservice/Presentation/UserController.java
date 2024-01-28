package Cloud2024.ReactiveUsersMicroservice.Presentation;

import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.DepartmentBoundary;
import org.springframework.http.MediaType;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import Cloud2024.ReactiveUsersMicroservice.Service.UserService;
import org.springframework.stereotype.Controller;
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
    public Mono<UserBoundary> createUser (
            @RequestBody UserBoundary user) {
        return this.userService
                .createUser(user)
                .log();
    }

    @GetMapping(
            path = "/criteria",
            produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<UserBoundary> getUsersByCriteria(
            @RequestParam("criteria") String criteria,
            @RequestParam("value") String value){
        return this.userService
                .getUsersByCriteria(criteria, value)
                .log();
    }

    @PutMapping(
            path = "{email}/department",
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<UserBoundary> updateUserDepartment(
            @PathVariable String email,
            @RequestBody DepartmentBoundary departmentBoundary){
        return this.userService
                .updateUserDepartment(email, departmentBoundary)
                .log();
    }

    @DeleteMapping
    public Mono<Void> deleteAllUsers() {
        return this.userService
                .deleteAllUsers()
                .log();
    }

    @GetMapping("/{email}")
    public Mono<UserBoundary> getUserByEmailAndPassword(
            @PathVariable String email,
            @RequestParam String password) {
        return this.userService.getUserByEmailAndPassword(email, password)
                .log();
    }

    @GetMapping(produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<UserBoundary> getAllUsers() {
        return this.userService.getAllUsers().log();
    }

}
