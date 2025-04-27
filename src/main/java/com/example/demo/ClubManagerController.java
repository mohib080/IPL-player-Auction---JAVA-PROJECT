package com.example.demo;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClubManagerController {

    @FXML
    Button SignOutButton;

    @FXML
    ChoiceBox<String> clubChoiceBox; // Replaces usernameField

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button backtoAdminButton;

    private playerCollection collection;

    private Map<String, String> userCredentials = new HashMap<>();

    public void setPlayerCollection(playerCollection collection) {
        this.collection = collection;
    }

    @FXML
    public void initialize() {
        clubChoiceBox.setItems(FXCollections.observableArrayList(
                "Chennai Super Kings",
                "Kolkata Knight Riders",
                "Gujarat Titans",
                "Rajasthan Royals",
                "Punjab Kings",
                "Royal Challengers Bangalore",
                "Mumbai Indians",
                "Lucknow Super Giants",
                "Sunrisers Hyderabad",
                "Delhi Capitals"
        ));

        clubChoiceBox.setValue("Choose A Club");

        userCredentials.put("Chennai Super Kings", "csk");
        userCredentials.put("Kolkata Knight Riders", "kkr");
        userCredentials.put("Gujarat Titans", "gt");
        userCredentials.put("Rajasthan Royals", "rr");
        userCredentials.put("Punjab Kings", "kxip");
        userCredentials.put("Royal Challengers Bangalore", "rcb");
        userCredentials.put("Mumbai Indians", "mi");
        userCredentials.put("Lucknow Super Giants", "lsg");
        userCredentials.put("Sunrisers Hyderabad", "srh");
        userCredentials.put("Delhi Capitals", "dc");
    }

    @FXML
    private void handleLoginButtonAction() {
        String selectedClub = clubChoiceBox.getValue();
        String password = passwordField.getText();

        if (selectedClub.equals("Enter Club Name") || password.isEmpty()) {
            showAlert("Error", "Please select a club name and enter a password!", Alert.AlertType.ERROR);
            return;
        }

        if (userCredentials.containsKey(selectedClub) && userCredentials.get(selectedClub).equals(password)) {
            loadPage(selectedClub);
        } else {
            showAlert("Login Failed", "Invalid Club Name or Password!", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void onEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            handleLoginButtonAction();
        }
    }

    private void loadPage(String clubName) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/club.fxml"));
            Parent root = loader.load();
            ClubController controller = loader.getController();
            controller.setClubData(this.collection, clubName);

            Stage stage = (Stage) clubChoiceBox.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("Error", "Failed to load the club page. Please check the configuration.", Alert.AlertType.ERROR);
        }
    }

    private void showAlert(String title, String message, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    public void goBackToAdmin() {
        try {
            Scene adminScene = HelloApplication.adminController.AdminButton.getScene();

            Stage stage = (Stage) backtoAdminButton.getScene().getWindow();
            stage.setScene(adminScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
