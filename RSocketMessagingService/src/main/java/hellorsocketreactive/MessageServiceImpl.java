package hellorsocketreactive;

import java.sql.Timestamp;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MessageServiceImpl implements MessageService {
	private MessageCrud messageCrud;
	
	@Autowired
	public void setMessageCrud(MessageCrud messageCrud) {
		this.messageCrud = messageCrud;
	}

	@Override
	public Mono<MessageBoundary> create(
			MessageBoundary message) {
		message.setMessageId(null);
		message.setPublishedTimestamp(new Date().toString());
		
		return Mono.just(message.toEntity())
			.flatMap(this.messageCrud::save)
			.map(MessageBoundary::new)
			.log();
	}

	@Override
	public Mono<MessageBoundary> getMessageById(
			String id) {
		return this.messageCrud
			.findById(id)
			.map(MessageBoundary::new)
			.log();
	}

	@Override
	public Flux<MessageBoundary> getAll() {
		return this.messageCrud
			.findAll()
			.map(MessageBoundary::new)
			.log();
	}

	@Override
	public Mono<Void> cleanup() {
		return this.messageCrud
			.deleteAll()
			.log();
	}
	
	@Override
	public Flux<MessageBoundary> getByMessagePattern(
			String pattern) {
		return this.messageCrud
			.findByMessageIdLike("*" + pattern + "*")
			.map(MessageBoundary::new)
			.log();
	}

	@Override
	public Flux<MessageBoundary> findByExternalReferences(Flux<ExternalReferenceBoundary> externalReference) {
		return
				externalReference
						.flatMap(reference -> this.messageCrud.findByExternalReferencesContains(reference))
						.map(MessageBoundary::new)
						.log();
	}
}
