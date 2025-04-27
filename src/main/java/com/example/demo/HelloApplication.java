package com.example.demo;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    private playerCollection collection;
    public static AdminController adminController;
    @Override
    public void start(Stage stage) throws IOException {
        collection = new playerCollection();
        collection.collectPlayers();
        System.out.println("Player collection size: " + collection.playerList.size());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("admin.fxml"));
        Parent root = loader.load();

        adminController = loader.getController();

        adminController.setPlayerCollection(collection);

        Scene scene = new Scene(root, 1104, 762);
        stage.setTitle("Admin Page");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
