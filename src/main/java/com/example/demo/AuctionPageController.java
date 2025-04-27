package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.InputStream;
import java.util.List;

public class AuctionPageController {

    @FXML
    private ScrollPane buyablePlayersScrollPane;

    @FXML
    private VBox contentBox;

    @FXML
    private Button backButton;

    private String clubName;
    private playerCollection collection;
    public void setAuctionData(playerCollection collection, String clubName) {
        this.collection = collection;
        this.clubName = clubName;
        loadBuyablePlayers();
    }
    private void loadBuyablePlayers() {
        contentBox.getChildren().clear();
        String buyablePlayersResponse = Client.sendRequest("GET_BUYABLE:" + clubName);
        if (buyablePlayersResponse == null || buyablePlayersResponse.trim().isEmpty()) {
            Label noPlayersLabel = new Label("No players available to buy.");
            noPlayersLabel.setStyle("-fx-font-size: 18px; -fx-font-weight: bold;");
            contentBox.getChildren().add(noPlayersLabel);
            return;
        }

        String[] buyablePlayers = buyablePlayersResponse.split(",");

        for (String playerName : buyablePlayers) {
            player p = collection.searchByName(playerName);

            if (p != null) {
                HBox playerBox = createPlayerBox(p);
                contentBox.getChildren().add(playerBox);
            }
        }
    }
    private HBox createPlayerBox(player p) {
        ImageView playerImageView = new ImageView();
        try (InputStream imageStream = getClass().getResourceAsStream("/images/profile/" + p.getName() + ".jpg")) {
            if (imageStream != null) {
                playerImageView.setImage(new Image(imageStream));
            } else {
                playerImageView.setImage(new Image(getClass().getResourceAsStream("/images/profile/default.jpg")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        playerImageView.setFitHeight(250);
        playerImageView.setFitWidth(260);

        Label playerDetailsLabel = new Label(p.toString());
        playerDetailsLabel.setStyle("-fx-font-size: 20px;");

        Button buyButton = new Button("Buy");
        buyButton.setStyle("-fx-font-size: 20px; -fx-background-color: #28a745; -fx-text-fill: white;");
        buyButton.setOnAction(event -> {
            String response = Client.sendRequest("BUY:" + clubName + ":" + p.getName());
            System.out.println("Server Response (Buy): " + response);
            p.setClub(clubName);
            loadBuyablePlayers();
        });

        HBox playerBox = new HBox(10, playerImageView, playerDetailsLabel, buyButton);
        playerBox.setStyle("-fx-border-color: black; -fx-border-width: 1; -fx-background-color: #f9f9f9;");
        playerBox.setSpacing(10);
        return playerBox;
    }
    @FXML
    private void handleBackToClub() {
        try {
            Stage stage = (Stage) backButton.getScene().getWindow();
            stage.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}