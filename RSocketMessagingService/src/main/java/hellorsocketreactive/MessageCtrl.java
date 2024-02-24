package hellorsocketreactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(path = "/messages")
public class MessageCtrl {
	private MessageService messageService;
	
	@Autowired
	public void setMessageService(MessageService messageService) {
		this.messageService = messageService;
	}

	@PostMapping(
		consumes = {MediaType.APPLICATION_JSON_VALUE},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<MessageBoundary> create(
			@RequestBody MessageBoundary message) {
		return messageService.create(message);
	}

	@GetMapping(
		path = {"/{id}"},
		produces = {MediaType.APPLICATION_JSON_VALUE})
	public Mono<MessageBoundary> getMessageById(
			@PathVariable("id") String id) {
		return messageService.getMessageById(id);
	}

	@GetMapping(
		produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<MessageBoundary> getAll() {
		return messageService.getAll();
	}

	@DeleteMapping
	public Mono<Void> cleanup() {
		return messageService.cleanup();
	}
	
	@GetMapping(
		path = {"/byMessage/{pattern}"},
		produces = {MediaType.TEXT_EVENT_STREAM_VALUE})
	public Flux<MessageBoundary> getByMessagePattern(
		@PathVariable("pattern") String pattern) {
		return messageService.getByMessagePattern(pattern);
	}
}
