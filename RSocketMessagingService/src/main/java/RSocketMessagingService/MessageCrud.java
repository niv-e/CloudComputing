package RSocketMessagingService;

import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.data.repository.query.Param;

import reactor.core.publisher.Flux;

public interface MessageCrud extends ReactiveMongoRepository<MessageEntity, String> {
	public Flux<MessageEntity> findByMessageIdLike (
			@Param("pattern") String pattern);

	@Query("{ 'externalReferences': ?0 }")
	Flux<MessageEntity> findByExternalReferencesContains(ExternalReferenceBoundary reference);

}
