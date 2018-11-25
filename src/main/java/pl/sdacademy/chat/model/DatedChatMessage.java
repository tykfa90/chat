package pl.sdacademy.chat.model;

import java.time.LocalDateTime;

public class DatedChatMessage extends ChatMessage{
    private final LocalDateTime receiveDate;

    public DatedChatMessage(ChatMessage chatMessage) {
        super(chatMessage.getAuthor(), chatMessage.getMessage());

        this.receiveDate = LocalDateTime.now();
    }

    public LocalDateTime getReceiveDate() {
        return receiveDate;
    }
}
