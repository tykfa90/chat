package pl.sdacademy.chat.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DatedChatMessageTest {

    @Test
    public void shouldHaveSetAuthor() {
        // Given
        ChatMessage chatMessage = new ChatMessage("Kostek", "Ugabuga");
        DatedChatMessage message = new DatedChatMessage(chatMessage);
        // When

        // Then
        assertEquals(message.getMessage(), "Ugabuga");
        assertEquals(message.getAuthor(), "Kostek");
        assertNotNull(message.getReceiveDate());
    }

}