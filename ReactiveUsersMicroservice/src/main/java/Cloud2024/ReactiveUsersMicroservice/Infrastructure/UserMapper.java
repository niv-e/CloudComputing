package Cloud2024.ReactiveUsersMicroservice.Infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

}
