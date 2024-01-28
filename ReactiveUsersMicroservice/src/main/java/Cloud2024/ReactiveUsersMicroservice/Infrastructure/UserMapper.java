package Cloud2024.ReactiveUsersMicroservice.Infrastructure;

import Cloud2024.ReactiveUsersMicroservice.Domain.UserEntity;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserMapper {

    public ModelMapper modelMapper(){
        var userMapper =  new ModelMapper();
        userMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        TypeMap<UserEntity, UserBoundary> entityToBoundaryMapper = userMapper.createTypeMap(UserEntity.class, UserBoundary.class);
        entityToBoundaryMapper.addMappings(
                mapper -> {
                    mapper.map(src -> generateRandomAsterisks(), UserBoundary::setPassword);
                    mapper.map(UserEntity::getFirstName, (dest, v) -> dest.getName().setFirst((String) v));
                    mapper.map(UserEntity::getLastName, (dest, v) -> dest.getName().setLast((String) v));
//                    mapper.map(src -> src.getDeptId(), UserBoundary::setDepartment);
                }
        );

        TypeMap<UserBoundary, UserEntity> boundaryToEntityMapper = userMapper.createTypeMap(UserBoundary.class, UserEntity.class);
        boundaryToEntityMapper.addMappings(
                mapper -> {
                    mapper.map(src -> src.getName().getFirst(), UserEntity::setFirstName);
                    mapper.map(src -> src.getName().getLast(), UserEntity::setLastName);
//                    mapper.map(UserBoundary::getDepartment, (dest, v) -> dest.setDeptId((String) v));
                }
        );
        return userMapper;
    }


    private String generateRandomAsterisks() {
        Random random = new Random();
        int num = random.nextInt(13) + 8; // Generate a random number between 8 and 20
        StringBuilder sb = new StringBuilder(num);
        for (int i = 0; i < num; i++) {
            sb.append('*');
        }
        return sb.toString();
    }
}
