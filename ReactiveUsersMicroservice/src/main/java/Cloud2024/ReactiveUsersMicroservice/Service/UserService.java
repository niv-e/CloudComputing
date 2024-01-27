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
    public Flux<UserBoundary> getUsersByCriteria(String criteria, String value) {
        Flux<UserEntity> userEntityFlux;
        switch (criteria) {
            case "byLastname":
                userEntityFlux = userRepository.findByLastName(value);
                break;
            case "byMinimumAge":
                userEntityFlux = userRepository.findByMinimumAge(value);
                break;
            case "byDepartmentId":
                userEntityFlux = userRepository.findByDepartmentId(value);
                break;
            default:
                return Flux.empty();
        }

        return userEntityFlux
                .map(entity -> userMapper.modelMapper().map(entity, UserBoundary.class));
    }
    public Mono<Void> updateUserDepartment(String email, DepartmentBoundary department){
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
