package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class ClubController {

    @FXML
    private Button SignOutButton;

    @FXML
    private Button buyPlayerButton;

    @FXML
    private ScrollPane playerScrollPane;

    @FXML
    private ImageView clubLogo;

    private String clubName;
    private playerCollection collection;

    public void setClubData(playerCollection collection, String clubName) {
        this.collection = collection;
        this.clubName = clubName;
        loadPlayers();
        loadClubLogo();
    }

    private void loadPlayers() {
        List<player> players = collection.searchByClub(clubName);
        VBox contentBox = new VBox(10);
        contentBox.setPadding(new Insets(10));

        if (players == null || players.isEmpty()) {
            Label noPlayersLabel = new Label("No players found for " + clubName + ".");
            contentBox.getChildren().add(noPlayersLabel);
            playerScrollPane.setContent(contentBox);
            return;
        }

        for (player p : players) {
            HBox playerBox = createPlayerBox(p);
            contentBox.getChildren().add(playerBox);
        }
        playerScrollPane.setContent(contentBox);
    }
    private static final Map<String, String> CLUB_LOGO_MAP = new HashMap<>();

    static {
        CLUB_LOGO_MAP.put("Rajasthan Royals", "rr.jpg");
        CLUB_LOGO_MAP.put("Sunrisers Hyderabad", "srh.jpg");
        CLUB_LOGO_MAP.put("Chennai Super Kings", "csk.jpg");
        CLUB_LOGO_MAP.put("Mumbai Indians", "mi.jpg");
        CLUB_LOGO_MAP.put("Royal Challengers Bangalore", "rcb.jpg");
        CLUB_LOGO_MAP.put("Kolkata Knight Riders", "kkr.jpg");
        CLUB_LOGO_MAP.put("Punjab Kings", "kxip.jpg");
        CLUB_LOGO_MAP.put("Delhi Capitals", "dc.jpg");
        CLUB_LOGO_MAP.put("Gujarat Titans", "gt.jpg");
        CLUB_LOGO_MAP.put("Lucknow Super Giants", "lsg.jpg");
    }


    private HBox createPlayerBox(player p) {
        ImageView playerImageView = new ImageView();
        try (InputStream imageStream = getClass().getResourceAsStream("/images/profile/" + p.getName() + ".jpg")) {
            if (imageStream != null) {
                playerImageView.setImage(new Image(imageStream));
            } else {
                playerImageView.setImage(new Image(getClass().getResourceAsStream("/images/profile/default.jpg")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        playerImageView.setFitHeight(250);
        playerImageView.setFitWidth(260);

        Label playerDetailsLabel = new Label(p.toString());
        playerDetailsLabel.setStyle("-fx-font-size: 20px;");

        Button sellButton = new Button("Sell");
        sellButton.setStyle("-fx-font-size: 20px; -fx-background-color: #007BFF; -fx-text-fill: white;");
        sellButton.setOnAction(event -> {
            String response = Client.sendRequest("SELL:" + clubName + ":" + p.getName());
            System.out.println("Server Response (Sell): " + response);
            p.setClub("NONE");
            loadPlayers();
        });

        return new HBox(10, playerImageView, playerDetailsLabel, sellButton);
    }
    private void loadClubLogo() {
        String logoFileName = CLUB_LOGO_MAP.getOrDefault(clubName, "default.jpg");
        try (InputStream logoStream = getClass().getResourceAsStream("/images/logo/" + logoFileName)) {
            if (logoStream != null) {
                clubLogo.setImage(new Image(logoStream));
            } else {
                System.err.println("Club logo not found for: " + clubName);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void handleBuyPlayerButton() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/AuctionPage.fxml"));
            Parent root = loader.load();
            AuctionPageController controller = loader.getController();
            controller.setAuctionData(collection, clubName);

            Stage auctionStage = new Stage();
            auctionStage.setScene(new Scene(root));
            auctionStage.setTitle("Auction Page - " + clubName);

            auctionStage.setOnHidden(event -> {
                loadPlayers(); // Refresh the club's player list
            });

            auctionStage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading AuctionPage.fxml: " + e.getMessage());
        }
    }



    public void BacktoClubManager() {
        try {
            Scene clubManagerScene = AdminController.ClubManagerController.clubChoiceBox.getScene();
            Stage stage = (Stage) SignOutButton.getScene().getWindow();
            stage.setScene(clubManagerScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error navigating back to the Club Manager scene: " + e.getMessage());
        }
    }
}