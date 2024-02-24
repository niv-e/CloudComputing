package hellorsocketreactive;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface MessageService {
	public Mono<MessageBoundary> create (
			MessageBoundary message);
	
	public Mono<MessageBoundary> getMessageById (
			String id);
	
	public Flux<MessageBoundary> getAll ();
	
	public Mono<Void> cleanup();
	
	public Flux<MessageBoundary> 
		getByMessagePattern (String pattern);
}
