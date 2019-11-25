package ru.geekbrains.java2.client.controller.message;

import ru.geekbrains.java2.chat.Message;

import java.io.Closeable;
import java.io.IOException;

public interface IMessageService extends Closeable {

    void sendMessage(Message message);

    void processRetrievedMessage(Message message);

    @Override
    default void close() throws IOException {
        //Do nothing
    }
}
