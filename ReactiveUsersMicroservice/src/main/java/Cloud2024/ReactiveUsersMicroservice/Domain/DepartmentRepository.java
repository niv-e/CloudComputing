package Cloud2024.ReactiveUsersMicroservice.Domain;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends ReactiveMongoRepository<DepartmentEntity, String> {
}
