package Cloud2024.ReactiveUsersMicroservice;
import Cloud2024.ReactiveUsersMicroservice.Domain.UserEntity;
import Cloud2024.ReactiveUsersMicroservice.Infrastructure.UserMapper;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.NameBoundary;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.UserBoundary;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    private UserEntity userEntity;
    private UserBoundary userBoundary;

    @BeforeEach
    public void setUp() {
        userEntity = new UserEntity();
        userEntity.setId("1");
        userEntity.setFirstName("John");
        userEntity.setLastName("Doe");
        userEntity.setEmail("john.doe@example.com");
        userEntity.setPassword("password");
        userEntity.setBirthdate("2000-01-01");
        userEntity.setRecruitDate("2022-01-01");
        userEntity.setRoles(new ArrayList<>(Arrays.asList("ROLE_USER")));

        userBoundary = new UserBoundary();
        userBoundary.setName(new NameBoundary("John", "Doe"));
        userBoundary.setEmail("john.doe@example.com");
        userBoundary.setPassword("********");
        userBoundary.setBirthdate("2000-01-01");
        userBoundary.setRecruitDate("2022-01-01");
        userBoundary.setRoles(new ArrayList<>(Arrays.asList("ROLE_USER")));
    }

    @Test
    public void whenConvertUserEntityToUserBoundary_thenCorrect() {
        UserBoundary boundary = userMapper.modelMapper().map(userEntity, UserBoundary.class);

        assertThat(boundary.getName().getFirst()).isEqualTo(userEntity.getFirstName());
        assertThat(boundary.getName().getLast()).isEqualTo(userEntity.getLastName());
        assertThat(boundary.getEmail()).isEqualTo(userEntity.getEmail());
        assertThat(boundary.getBirthdate()).isEqualTo(userEntity.getBirthdate());
        assertThat(boundary.getRecruitDate()).isEqualTo(userEntity.getRecruitDate());
        assertThat(boundary.getRoles()).isEqualTo(userEntity.getRoles());
    }

    @Test
    public void whenConvertUserBoundaryToUserEntity_thenCorrect() {
        UserEntity entity = userMapper.modelMapper().map(userBoundary, UserEntity.class);

        assertThat(entity.getFirstName()).isEqualTo(userBoundary.getName().getFirst());
        assertThat(entity.getLastName()).isEqualTo(userBoundary.getName().getLast());
        assertThat(entity.getEmail()).isEqualTo(userBoundary.getEmail());
        assertThat(entity.getBirthdate()).isEqualTo(userBoundary.getBirthdate());
        assertThat(entity.getRecruitDate()).isEqualTo(userBoundary.getRecruitDate());
        assertThat(entity.getRoles()).isEqualTo(userBoundary.getRoles());
    }
}
