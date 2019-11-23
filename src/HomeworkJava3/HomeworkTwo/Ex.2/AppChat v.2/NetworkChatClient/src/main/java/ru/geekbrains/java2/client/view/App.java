package ru.geekbrains.java2.client.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ru.geekbrains.java2.client.controller.PrimaryController;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setTitle("Сетевой чат");
        stage.getIcons().add(new Image("/images/stage_icon.png"));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/primary.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);

        PrimaryController controller = loader.getController();
        stage.setOnHidden(e -> controller.shutdown());
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}