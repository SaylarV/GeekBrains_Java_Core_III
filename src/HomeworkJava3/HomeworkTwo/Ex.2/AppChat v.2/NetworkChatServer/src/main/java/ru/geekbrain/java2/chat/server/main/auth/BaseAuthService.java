package ru.geekbrain.java2.chat.server.main.auth;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BaseAuthService implements AuthService {
    private static Connection conn;
    private static Statement stmt;
    private static ResultSet rs;
    private static List<Entry> entries = new ArrayList<>(); //= Arrays.asList();

    public static void connection(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:mainDB.db");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM log_pass");
            String l;
            String p;
            String n;
            int i = 0;
            while (rs.next()){
                l = rs.getString("Login");
                p = rs.getString("Password");
                n = rs.getString("Nick");
                entries.add(new Entry(l+"",p+"",n+""));
                System.out.println(entries.get(i));
                i++;
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public static void disconnect(){
        try {
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static class Entry {
        private String login;
        private String password;
        private String nick;

        public Entry(String login, String password, String nick) {
            this.login = login;
            this.password = password;
            this.nick = nick;
        }
    }

    @Override
    public void start() {
        System.out.println("Auth service is running");
    }

    @Override
    public void stop() {
        System.out.println("Auth service has stopped");
    }

    @Override
    public String getNickByLoginPass(String login, String pass) {
        for (Entry entry : entries) {
            if (entry.login.equals(login) && entry.password.equals(pass)) {
                return entry.nick;
            }
        }
        return null;
    }

}
