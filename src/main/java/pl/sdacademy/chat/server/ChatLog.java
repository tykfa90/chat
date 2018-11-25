package pl.sdacademy.chat.server;

import pl.sdacademy.chat.model.ChatMessage;
import pl.sdacademy.chat.model.DatedChatMessage;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ChatLog {
    /*
    kolekcja z użytkownikami
     */
    private Map<Socket, ObjectOutputStream> registeredClients;
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public ChatLog() {
        registeredClients = new ConcurrentHashMap<>();
    }

    public boolean register(Socket client) {
        try {
            ObjectOutputStream streamToClient = new ObjectOutputStream(client.getOutputStream());
            registeredClients.put(client, streamToClient);
            return true;
        } catch (IOException e) {
            System.out.println("### Someone tried to connect, but was rejected. ###");
            return false;
        }
    }

    public boolean unregister(Socket client) {
        ObjectOutputStream connectionToClient = registeredClients.remove(client);
        if (connectionToClient != null) {
            try {
                connectionToClient.close();
                return true;
            } catch (IOException e) {

            }
        }
        return false;
    }

    public void acceptMessage(ChatMessage message) {
        DatedChatMessage datedChatMessage = new DatedChatMessage(message);
        printMessage(datedChatMessage);
    }

    public void printMessage(DatedChatMessage datedChatMessage) {
        System.out.println("<" + dateFormatter.format(datedChatMessage.getReceiveDate()) + "> <" + datedChatMessage.getAuthor() + ">: " + datedChatMessage.getMessage());
    }

    private void updateClients(DatedChatMessage datedChatMessage) {
        Set<Map.Entry<Socket, ObjectOutputStream>> allEntries = registeredClients.entrySet();
        for (Map.Entry<Socket, ObjectOutputStream> entry : allEntries) {
            ObjectOutputStream connectionToClient = entry.getValue();
            try {
                connectionToClient.writeObject(datedChatMessage);
                connectionToClient.flush();
            } catch (IOException e) {
                unregister(entry.getKey());
            }
        }
    }
}
