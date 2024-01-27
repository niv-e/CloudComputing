package Cloud2024.ReactiveUsersMicroservice.Service;

import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.DepartmentBoundary;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IDepartmentService {
    Mono<DepartmentBoundary> createDepartment(DepartmentBoundary department);

    Mono<DepartmentBoundary> getDepartmentById(String id);
    
    Flux<DepartmentBoundary> getAllDepartments();
    
    Mono<Void> deleteAllDepartments();
}
