package Cloud2024.ReactiveUsersMicroservice.Infrastructure;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
