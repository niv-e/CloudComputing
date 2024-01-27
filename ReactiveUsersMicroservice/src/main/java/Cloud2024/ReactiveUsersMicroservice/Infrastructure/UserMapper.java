package Cloud2024.ReactiveUsersMicroservice.Infrastructure;

import Cloud2024.ReactiveUsersMicroservice.Domain.UserEntity;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class UserMapper {

    public ModelMapper modelMapper(){
        var userMapper =  new ModelMapper();
        var userMap = new PropertyMap<UserEntity, UserBoundary>() {
            protected void configure() {
                map().setPassword(generateRandomAsterisks());
            }
        };

        userMapper.addMappings(userMap);
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
