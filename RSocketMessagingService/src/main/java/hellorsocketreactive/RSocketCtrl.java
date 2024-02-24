package hellorsocketreactive;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Controller
public class RSocketCtrl {
	private MessageService messages;
	private Log logger = LogFactory.getLog(RSocketCtrl.class);
	
	@Autowired
	public void setMessages(MessageService messages) {
		this.messages = messages;
	}


	// java -jar rsc-0.9.1.jar --request --route=publish-message-req-resp --data="{\"messageId\":\"123\",\"publishedTimestamp\":\"2024-02-11T15:22:13.730+0000\",\"messageType\":\"reminderNotification\",\"summary\":\"makesureyouturnoffallthelightsbeforeyouleave\",\"externalReferences\":[{\"service\":\"originService\",\"externalServiceId\":\"abc-123-def-456-7890\"}],\"messageDetails\":{\"attr1\":\"youcanaddanyattributetothemessageDetails\",\"attr2\":{\"subAttr\":\"everytypeofsub-attributeisvalidwithinthemessagedetails\",\"subAttr2\":[\"even\",\"arrays\",\"of\",\"any\",{\"value\":\"type\"},42]}}}" --debug tcp://localhost:7001
	@MessageMapping("publish-message-req-resp")
	public Mono<MessageBoundary> create(
			@Payload MessageBoundary message) {
		this.logger.debug("invoking: publish-message-req-resp");
		return messages.create(message);
	}

	// java -jar rsc-0.9.1.jar --stream --route=get-all-messages-req-stream --debug tcp://localhost:7001
	@MessageMapping("getAll-req-stream")
	public Flux<MessageBoundary> getAll() {
		this.logger.debug("invoking: getAll-req-stream");
		return messages.getAll();
	}

	// java -jar rsc-0.9.1.jar --channel --route=get-by-patterns-channel --data=- --debug tcp://localhost:7001
	// input: {"pattern":"e"}
	@MessageMapping("getMessagesByIds-channel")
	public Flux<MessageBoundary> getMessagesByIds(
			Flux<IdBoundary> messageIds) {
		this.logger.debug("invoking: getMessagesByIds-channel");
		return messageIds
				.flatMap(messageId->messages.getMessageById(messageId.getMessageId()));
	}

	// java -jar rsc-0.9.1.jar --fnf --route=deleteAll-fire-and-forget --debug tcp://localhost:7001
	@MessageMapping("deleteAll-fire-and-forget")
	public Mono<Void> cleanup() {
		this.logger.debug("invoking: deleteAll-fire-and-forget");
		return messages.cleanup();
	}

//	@MessageMapping("get-one-message-req-resp")
//	public Mono<MessageBoundary> getMessageById(
//			@Payload IdWrapper idWrapper) {
//		this.logger.debug("invoking: get-one-message-req-resp");
//		return messages.getMessageById(idWrapper.getId());
//	}
}
