package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class SearchByNameController {

    private playerCollection collection;

    public void setPlayerCollection(playerCollection c) {
        this.collection = c;
    }

    @FXML
    private TextField playerNameField;

    @FXML
    private HBox playerDetailsBox;

    @FXML
    private Button backToMainButton;

    @FXML
    private ImageView playerImage;

    @FXML
    private TextArea playerDetailsArea;

    @FXML
    private void onEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            searchByName();
        }
    }

    @FXML
    public void searchByName() {
        playerDetailsArea.setText("");
        playerImage.setImage(null);

        if (collection == null) {
            playerDetailsArea.setText("Error: Player collection not initialized.");
            return;
        }

        String name = playerNameField.getText().trim();
        player p = collection.searchByName(name);

        if (p == null) {
            playerDetailsArea.setText("No such player with this name.");
        } else {
            String imageName = p.getName() + ".jpg";
            try (InputStream imageStream = getClass().getResourceAsStream("/images/profile/" + imageName)) {
                if (imageStream != null) {
                    Image image = new Image(imageStream);
                    playerImage.setImage(image);
                } else {
                    InputStream defaultStream = getClass().getResourceAsStream("/images/profile/default.jpg");
                    if (defaultStream == null) {
                        throw new FileNotFoundException("Default image not found in /images/default.jpg");
                    }
                    Image image = new Image(defaultStream);
                    playerImage.setImage(image);
                }
            } catch (IOException e) {
                System.err.println("Error loading image: " + e.getMessage());
            }

            playerDetailsArea.setText(p.toString());
        }
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
            System.err.println("Error navigating back to the main scene: " + e.getMessage());
        }
    }
}