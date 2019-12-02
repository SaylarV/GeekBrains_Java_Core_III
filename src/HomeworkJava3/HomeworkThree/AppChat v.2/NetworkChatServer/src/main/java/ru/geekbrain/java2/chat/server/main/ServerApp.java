package ru.geekbrain.java2.chat.server.main;

import ru.geekbrain.java2.chat.server.main.auth.BaseAuthService;

import java.io.IOException;
import java.sql.SQLException;

public class ServerApp {

    public static void main(String[] args) {
        BaseAuthService.connection();
        new MyServer();
    }
}
