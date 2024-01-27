package Cloud2024.ReactiveUsersMicroservice.Service;

import Cloud2024.ReactiveUsersMicroservice.Domain.DepartmentEntity;
import Cloud2024.ReactiveUsersMicroservice.Domain.DepartmentRepository;
import Cloud2024.ReactiveUsersMicroservice.Infrastructure.DepartmentMapper;
import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.DepartmentBoundary;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class DepartmentService implements IDepartmentService {
    private final DepartmentRepository departmentRepository;

    private final DepartmentMapper departmentMapper;

    public DepartmentService(DepartmentRepository departmentRepository, DepartmentMapper departmentMapper) {
        this.departmentRepository = departmentRepository;
        this.departmentMapper = departmentMapper;
    }

    @Override
    public Mono<DepartmentBoundary> createDepartment(DepartmentBoundary department) {
        return this.departmentRepository
                .findById(department.getDeptId())
                .flatMap(existingDepartment -> Mono.error(new DuplicateKeyException("Department already exists.")))
                .switchIfEmpty(departmentRepository.save(departmentMapper.modelMapper().map(department, DepartmentEntity.class)))
                .map(entity -> departmentMapper.modelMapper().map(entity, DepartmentBoundary.class));
    }

    @Override
    public Mono<DepartmentBoundary> getDepartmentById(String id) {
        return this.departmentRepository
                .findById(id)
                .map(entity -> departmentMapper.modelMapper().map(entity, DepartmentBoundary.class));
    }

    @Override
    public Flux<DepartmentBoundary> getAllDepartments() {
        return this.departmentRepository
                .findAll()
                .map(entity -> departmentMapper.modelMapper().map(entity, DepartmentBoundary.class));
    }

    @Override
    public Mono<Void> deleteAllDepartments() {
        return this.departmentRepository.deleteAll();
    }
}
