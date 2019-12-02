package ru.geekbrains.java2.client.controller.message;

public class ServerConnectionException extends RuntimeException {

    public ServerConnectionException(String message, Throwable cause) {
        super(message, cause);
    }
}
