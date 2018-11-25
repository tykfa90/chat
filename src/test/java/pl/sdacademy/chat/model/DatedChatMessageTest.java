package pl.sdacademy.chat.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DatedChatMessageTest {

    @Test
    public void shouldHaveSetAuthor() {
        // Given
        ChatMessage chatMessage = new ChatMessage("Kostek", "Ugabuga");
        // When
        DatedChatMessage message = new DatedChatMessage(chatMessage);
        // Then
        assertEquals(message.getMessage(), "Ugabuga");
        assertEquals(message.getAuthor(), "Kostek");
        assertNotNull(message.getReceiveDate());
    }

}