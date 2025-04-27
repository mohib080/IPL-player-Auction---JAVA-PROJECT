package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class AddplayerController {

    private playerCollection collection;
    @FXML
    private TextArea playerAddDetails;
    public void setPlayerCollection(playerCollection c) {
        this.collection = c;
    }
    @FXML
    private Button backToMainButton;
    @FXML
    private TextField playerNameField;
    @FXML
    private TextField playerCountryField;
    @FXML
    private TextField playerAgeField;
    @FXML
    private TextField playerHeightField;
    @FXML
    private TextField playerClubField;
    @FXML
    private TextField playerPositionField;
    @FXML
    private TextField playerJerseyField;
    @FXML
    private TextField playerSalaryField;
    @FXML
    private void onEnterKeyPressed(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            addPlayer();
        }
    }
    public void addPlayer() {
        try {
            String playerName = playerNameField.getText().trim();
            String playerCountry = playerCountryField.getText();
            int playerAge = Integer.parseInt(playerAgeField.getText());
            double playerHeight = Double.parseDouble(playerHeightField.getText());
            String playerClub = playerClubField.getText();
            String playerPosition = playerPositionField.getText();
            int playerJersey = Integer.parseInt(playerJerseyField.getText());
            int playerSalary = Integer.parseInt(playerSalaryField.getText());

            player newPlayer = new player(playerName, playerCountry, playerAge, playerHeight, playerClub, playerPosition, playerJersey, playerSalary);

            for (player p : collection.playerList) {
                if (p.getName().equalsIgnoreCase(playerName)) {
                    playerAddDetails.setText("Player name already exists");
                    return;
                }
            }

            // Add the player locally
            collection.addPlayer(newPlayer);
            playerAddDetails.setText("Player added successfully");

            // Send request to server to add this player
            sendAddPlayerRequest(newPlayer);

        } catch (NumberFormatException e) {
            playerAddDetails.setText("Invalid input format. Please check your entries.");
        }
    }

    // Method to send the new player details to the server
    private void sendAddPlayerRequest(player newPlayer) {
        try (Socket socket = new Socket("localhost", 12345);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true)) {

            String request = String.format("ADD:%s:%s:%d:%f:%s:%s:%d:%d",
                    newPlayer.getName(), newPlayer.getCountry(), newPlayer.getAge(), newPlayer.getHeight(),
                    newPlayer.getclub(), newPlayer.getPosition(), newPlayer.getJerseyNumber(), newPlayer.getWeeklySalary());

            out.println(request);

        } catch (IOException e) {
            e.printStackTrace();
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
        }
    }
}
