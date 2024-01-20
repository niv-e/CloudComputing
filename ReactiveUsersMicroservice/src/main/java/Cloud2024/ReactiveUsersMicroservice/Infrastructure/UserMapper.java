package Cloud2024.ReactiveUsersMicroservice.Infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;

public class UserMapper {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
