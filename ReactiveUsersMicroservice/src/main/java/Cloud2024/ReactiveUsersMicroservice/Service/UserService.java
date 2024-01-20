package Cloud2024.ReactiveUsersMicroservice.Service;

import Cloud2024.ReactiveUsersMicroservice.Domain.UserEntity;
import Cloud2024.ReactiveUsersMicroservice.Domain.UserRepository;
import Cloud2024.ReactiveUsersMicroservice.Infrastructure.UserMapper;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;

import org.springframework.stereotype.Service;
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
}
