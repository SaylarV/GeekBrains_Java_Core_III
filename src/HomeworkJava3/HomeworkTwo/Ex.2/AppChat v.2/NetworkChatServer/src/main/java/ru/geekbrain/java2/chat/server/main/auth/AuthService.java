package ru.geekbrain.java2.chat.server.main.auth;

import javax.annotation.Nullable;
import java.sql.SQLException;

public interface AuthService {

    void start();
    void stop();

    /**
     *
     * @param login
     * @param pass
     * @return nick or null
     */
    @Nullable
    String getNickByLoginPass(String login, String pass);

}
