package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class AdminController {

    @FXML
    Button AdminButton;
    private playerCollection collection;
    public static HomepageController homepageController;
    public static ClubManagerController ClubManagerController;

    public void setPlayerCollection(playerCollection collection) {
        this.collection = collection;
    }
    @FXML
    public void loadHomePage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("homepage.fxml"));
            Parent root = loader.load();

            homepageController = loader.getController();
            homepageController.setPlayerCollection(this.collection);

            Stage stage = (Stage) AdminButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void loadClubManagerPage() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Club Manager.fxml"));
            Parent root = loader.load();

            ClubManagerController = loader.getController();
           ClubManagerController.setPlayerCollection(this.collection);

            Stage stage = (Stage) AdminButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
