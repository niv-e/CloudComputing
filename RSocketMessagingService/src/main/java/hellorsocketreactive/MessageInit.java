package hellorsocketreactive;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import reactor.core.publisher.Flux;

@Component
@Profile("generate-messages")
public class MessageInit implements CommandLineRunner{
	private MessageService messages;
	
	@Autowired
	public void setMessages(MessageService messages) {
		this.messages = messages;
	}

	@Override
	public void run(String... args) throws Exception {
		this.messages.cleanup()
			.block();
		
		Flux.range(1, 8)
			.map(i->{
				MessageBoundary boundary = new MessageBoundary();
				
				//boundary.setMessage("message #" + i);
				return boundary;
			})
			.flatMap(this.messages::create)
			.collectList()
			.block()
			.stream()
			.forEach(System.err::println);
	}
}
