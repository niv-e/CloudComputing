package RSocketMessagingService;

import java.util.Arrays;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "MESSAGES")
public class MessageEntity {
	@Id
	private String messageId;
	private String publishedTimestamp;
	private String messageType;
	private String summary;
	private ExternalReferenceBoundary[] externalReferences;
	private Map<String, Object> messageDetails;
	
	public MessageEntity() {
	}

	public String getMessageId() {
		return messageId;
	}

	public void setMessageId(String messageId) {
		this.messageId = messageId;
	}

	public String getPublishedTimestamp() {
		return publishedTimestamp;
	}

	public void setPublishedTimestamp(String publishedTimestamp) {
		this.publishedTimestamp = publishedTimestamp;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public ExternalReferenceBoundary[] getExternalReferences() {
		return externalReferences;
	}

	public void setExternalReferences(ExternalReferenceBoundary[] externalReferences) {
		this.externalReferences = externalReferences;
	}

	public Map<String, Object> getMessageDetails() {
		return messageDetails;
	}

	public void setMessageDetails(Map<String, Object> messageDetails) {
		this.messageDetails = messageDetails;
	}

	@Override
	public String toString() {
		return "MessageEntity{" +
				"messageId='" + messageId + '\'' +
				", publishedTimestamp='" + publishedTimestamp + '\'' +
				", messageType='" + messageType + '\'' +
				", summary='" + summary + '\'' +
				", externalReferences=" + Arrays.toString(externalReferences) +
				", messageDetails=" + messageDetails +
				'}';
	}
}
