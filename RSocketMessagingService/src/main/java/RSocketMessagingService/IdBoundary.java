package RSocketMessagingService;

public class IdBoundary {
    private String messageId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    @Override
    public String toString() {
        return "IdBoundary{" +
                "messageId='" + messageId + '\'' +
                '}';
    }
}
