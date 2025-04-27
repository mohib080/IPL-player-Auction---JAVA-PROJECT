package com.example.demo;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class MaxHeightListController {
    private playerCollection collection;

    public void setPlayerCollection(playerCollection collection) {
        this.collection = collection;
    }

    @FXML
    private TextField playerCountryField;

    @FXML
    private TextField playerClubField;

    @FXML
    private ScrollPane playerScrollPane;

    @FXML
    private Button backToMainButton;

    @FXML
    private TextArea playerDetailsArea;


    @FXML
    private void onEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchByMaxHeight();
        }
    }

    @FXML
    public void searchByMaxHeight() {
        if (collection == null) {
            playerDetailsArea.setText("Error: Player collection not initialized.");
            return;
        }
        String club = playerClubField.getText().trim();
        List<player> players = collection.searchByMaxHeight(club);

        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(10));

        if (players == null || players.isEmpty()) {
            TextArea textArea = new TextArea("No players found for this club.");
            contentBox.getChildren().add(textArea);
        } else {
            for (player p : players) {

                Image image;
                try (InputStream imageStream = getClass().getResourceAsStream("/images/profile" + p.getName() + ".jpg")) {
                    if (imageStream != null) {
                        image = new Image(imageStream);
                    } else {

                        InputStream defaultStream = getClass().getResourceAsStream("/images/profile/default.jpg");
                        if (defaultStream == null) {
                            throw new FileNotFoundException("Default image not found in /images/default.jpg");
                        }
                        image = new Image(defaultStream);
                    }
                } catch (IOException e) {
                    System.err.println("Error loading image: " + e.getMessage());
                    image = null;
                }


                ImageView playerImageView = new ImageView(image);
                playerImageView.setFitHeight(260);
                playerImageView.setFitWidth(250);

                TextArea playerDetailsArea = new TextArea(p.toString());
                playerDetailsArea.setPrefHeight(250);
                playerDetailsArea.setPrefWidth(400);
                playerDetailsArea.setStyle("-fx-font-size: 20px");

                HBox playerBox = new HBox(20, playerImageView, playerDetailsArea);
                playerBox.setPadding(new Insets(10));


                contentBox.getChildren().add(playerBox);
            }
        }

        playerScrollPane.setContent(contentBox);
    }

    @FXML
    public void goBackToMain() {
        try {
            Scene homeScene = AdminController.homepageController.playerSearchMenuButton.getScene();
            Stage stage = (Stage) backToMainButton.getScene().getWindow();
            stage.setScene(homeScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}