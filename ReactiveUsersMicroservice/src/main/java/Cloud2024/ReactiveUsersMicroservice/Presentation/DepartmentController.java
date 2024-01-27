package Cloud2024.ReactiveUsersMicroservice.Presentation;

import Cloud2024.ReactiveUsersMicroservice.Presentation.Boundaries.DepartmentBoundary;
import Cloud2024.ReactiveUsersMicroservice.Service.IDepartmentService;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = {"/departments"})
public class DepartmentController {
    private final IDepartmentService departmentService;

    public DepartmentController(IDepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE}, produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<DepartmentBoundary>> createDepartment(@RequestBody DepartmentBoundary department) {
        return departmentService
                .createDepartment(department)
                .map(ResponseEntity::ok)
                .onErrorResume(DuplicateKeyException.class, e -> Mono.just(ResponseEntity.status(HttpStatus.CONFLICT).build()))
                .log();
    }

    @GetMapping(path = "/{deptId}", produces = {MediaType.APPLICATION_JSON_VALUE})
    public Mono<ResponseEntity<DepartmentBoundary>> getDepartmentById(@PathVariable String deptId) {
        return departmentService
                .getDepartmentById(deptId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build())
                .log();
    }

    @GetMapping(produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
    public Flux<DepartmentBoundary> getAllDepartments() {
        return this.departmentService.getAllDepartments().log();
    }
    
    @DeleteMapping
    public Mono<Void> deleteAllDepartments() {
        return this.departmentService.deleteAllDepartments();
    }
}
