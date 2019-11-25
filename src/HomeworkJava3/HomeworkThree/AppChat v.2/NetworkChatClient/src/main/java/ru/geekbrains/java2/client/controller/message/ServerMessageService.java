package ru.geekbrains.java2.client.controller.message;

import javafx.scene.control.TextArea;
import ru.geekbrains.java2.chat.Message;
import ru.geekbrains.java2.chat.message.PrivateMessage;
import ru.geekbrains.java2.chat.message.PublicMessage;
import ru.geekbrains.java2.client.controller.Network;
import ru.geekbrains.java2.client.controller.PrimaryController;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ServerMessageService implements IMessageService {

    private static final String HOST_ADDRESS_PROP = "server.address";
    private static final String HOST_PORT_PROP = "server.port";

    private String hostAddress;
    private int hostPort;

    private final TextArea chatTextArea;
    private PrimaryController primaryController;
    private boolean needStopServerOnClosed;
    private Network network;
    private static File file;
    public static FileWriter fileWriter;
    public static BufferedWriter bufferedWriter;
    public static FileReader fileReader;
    public static BufferedReader bufferedReader;

    public ServerMessageService(PrimaryController primaryController, boolean needStopServerOnClosed) {
        this.chatTextArea = primaryController.chatTextArea;
        this.primaryController = primaryController;
        this.needStopServerOnClosed = needStopServerOnClosed;
        initialize();
    }

    private void initialize() {
        readProperties();
        startConnectionToServer();
        file = new File("D:\\WORKBITCH\\ОБУЧЕНИЕ\\07_JAVA\\Geekbrains\\Android разработчик\\Java Core. Профессиональный\\HW\\src\\HomeworkJava3\\HomeworkTwo\\Ex.2\\AppChat v.2\\History\\history.txt");
    }

    private void startConnectionToServer() {
        try {
            this.network = new Network(hostAddress, hostPort, this);
        } catch (IOException e) {
            throw new ServerConnectionException("Failed to connect to server", e);
        }
    }

    private void readProperties() {
        Properties serverProperties = new Properties();
        try (InputStream inputStream = getClass().getResourceAsStream("/application.properties")) {
            serverProperties.load(inputStream);
            hostAddress = serverProperties.getProperty(HOST_ADDRESS_PROP);
            hostPort = Integer.parseInt(serverProperties.getProperty(HOST_PORT_PROP));
        } catch (IOException e) {
            throw new RuntimeException("Failed to read application.properties file", e);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid port value", e);
        }
    }

    @Override
    public void sendMessage(Message message) {
        network.send(message.toJson());
    }

    @Override
    public void processRetrievedMessage(Message message) {
        switch (message.command) {
            case AUTH_OK:
                processAuthOk(message);
                break;
            case PRIVATE_MESSAGE: {
                processPrivateMessage(message);
                break;
            }
            case PUBLIC_MESSAGE: {
                processPublicMessage(message);
                break;
            }
            case AUTH_ERROR: {
                primaryController.showAuthError(message.authErrorMessage.errorMsg);
                break;
            }
            case CLIENT_LIST:
                List<String> onlineUserNicknames = message.clientListMessage.online;
                primaryController.refreshUsersList(onlineUserNicknames);
                break;
            default:
                throw new IllegalArgumentException("Unknown command type: " + message.command);
        }
    }

    private void readHistory(){
        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String str;
            List<String> history = new LinkedList<>();
            while ((str = bufferedReader.readLine()) != null){
                history.add(str);
            }
            int numToRead = 100;
            if (history.size() > numToRead) {
                for (int i = history.size()-numToRead; i < history.size(); i++) {
                    chatTextArea.appendText(history.get(i)+'\n');
                }
            } else {
                for (int i = 0; i < history.size(); i++) {
                    chatTextArea.appendText(history.get(i)+'\n');
                }
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeToHistory(String msgToView){
        try {
            fileWriter = new FileWriter(ServerMessageService.file, true);
            bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(msgToView);
            bufferedWriter.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void processPublicMessage(Message message) {
        PublicMessage publicMessage = message.publicMessage;
        String from = publicMessage.from;
        String msg = publicMessage.message;
        String msgToView;
        if (from != null) {
            msgToView = String.format("%s: %s%n", from, msg);
            chatTextArea.appendText(msgToView);
            writeToHistory(msgToView);
        } else {
            msgToView = String.format("%s%n", msg);
            chatTextArea.appendText(msgToView);
            writeToHistory(msgToView);
        }
    }

    private void processPrivateMessage(Message message) {
        PrivateMessage privateMessage = message.privateMessage;
        String from = privateMessage.from;
        String msg = privateMessage.message;
        String msgToView = String.format("%s (private): %s%n", from, msg);
        chatTextArea.appendText(msgToView);
    //    writeToHistory(msgToView);   // в данном приложении запись приватных сообщений в историю реализована, но отключена
    }

    private void processAuthOk(Message message) {
        primaryController.setNickName(message.authOkMessage.nickname);
        primaryController.showChatPanel();
        readHistory();
    }

    @Override
    public void close() throws IOException {
        if (needStopServerOnClosed) {
            sendMessage(Message.serverEndMessage());
        }
        network.close();
    }

}
