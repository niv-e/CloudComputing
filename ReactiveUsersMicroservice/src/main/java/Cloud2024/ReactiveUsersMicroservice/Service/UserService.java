package Cloud2024.ReactiveUsersMicroservice.Service;

import Cloud2024.ReactiveUsersMicroservice.Domain.UserEntity;
import Cloud2024.ReactiveUsersMicroservice.Domain.UserRepository;
import Cloud2024.ReactiveUsersMicroservice.Infrastructure.UserMapper;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.DepartmentBoundary;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService implements IUserService {

    private UserRepository userRepository;
    private UserMapper userMapper;

    public UserService(UserRepository userRepository,UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public Mono<UserBoundary> createUser(UserBoundary user) {
        return this.userRepository
                .save(userMapper.modelMapper().map(user, UserEntity.class))
                .map(entity -> userMapper.modelMapper().map(entity, UserBoundary.class));
    }

    @Override
    public Mono<UserBoundary> getUserByEmailAndPassword(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password)
                .map(entity -> userMapper.modelMapper().map(entity, UserBoundary.class));
    }

    @Override
    public Flux<UserBoundary> getAllUsers() {
        return userRepository.findAll()
                .map(userEntity -> userMapper.modelMapper().map(userEntity, UserBoundary.class));
    }

    @Override
    public Flux<UserBoundary> getUsersByCriteria(String criteria, String value) {
        Flux<UserEntity> userEntityFlux;
        switch (criteria) {
            case "byLastname" -> userEntityFlux = userRepository.findByLastName(value);
//            case "byMinimumAge" -> userEntityFlux = userRepository.findByMinimumAge(value);
            case "byDepartmentId" -> userEntityFlux = userRepository.findByDepartmentDeptId(value);
            case "byDomain" -> userEntityFlux = userRepository.findByEmailEndingWith(value);
            default -> {
                return Flux.empty();
            }
        }

        return userEntityFlux
                .map(entity -> userMapper.modelMapper().map(entity, UserBoundary.class));
    }

    @Override
    public Mono<UserBoundary> updateUserDepartment(String email, DepartmentBoundary department){
        //validate user
        //validate department
        //update department of user
        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteAllUsers(){
        return this.userRepository
                .deleteAll();
    }
}
