package Cloud2024.ReactiveUsersMicroservice.Service;

import Cloud2024.ReactiveUsersMicroservice.Domain.UserEntity;
import Cloud2024.ReactiveUsersMicroservice.Infrastructure.UserMapper;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;

import org.springframework.dao.DuplicateKeyException;
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
    public Mono<UserBoundary> createUser(UserBoundary user)  {
        return this.userRepository.findByEmail(user.getEmail())
                .flatMap(existingUser -> Mono.error(new DuplicateKeyException("User with email : " + existingUser.getEmail() + "already exists")))
                .next()
                .switchIfEmpty(userRepository.save(userMapper.modelMapper().map(user, UserEntity.class)))
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
            case "byMinimumAge" -> userEntityFlux = this.userRepository.findAll().filter(user -> user.calculateAge() >= Integer.parseInt(value));
            case "byDepartmentId" -> userEntityFlux = userRepository.findByDeptId(value);
            case "byDomain" -> userEntityFlux = userRepository.findByEmailEndingWith(value);
            default -> {
                return Flux.empty();
            }
        }
        return userEntityFlux
                .map(entity -> userMapper.modelMapper().map(entity, UserBoundary.class));
    }

    @Override
    public Mono<Void> deleteAllUsers(){
        return this.userRepository
                .deleteAll();
    }
}
