package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.stage.Stage;

public class HomepageController {

    @FXML
    MenuButton playerSearchMenuButton;
    @FXML
    private Button backtoAdminButton;

    private playerCollection collection;

    public void setPlayerCollection(playerCollection collection) {
        this.collection = collection;
    }

    @FXML
    public void loadSearchByName() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchByName.fxml"));
            Parent root = loader.load();
            SearchByNameController controller = loader.getController();
            controller.setPlayerCollection(this.collection);

            Stage stage = (Stage) playerSearchMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void loadSearchByCountryClub() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchByClubCountry.fxml"));
            Parent root = loader.load();
            SearchByCountryClubController controller = loader.getController();
            controller.setPlayerCollection(this.collection);

            Stage stage = (Stage) playerSearchMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadSearchByPosition() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchByPosition.fxml"));
            Parent root = loader.load();
            SearchByPositionController controller = loader.getController();
            controller.setPlayerCollection(this.collection);

            Stage stage = (Stage) playerSearchMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadSearchBySalaryRange() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("SearchBySalaryRange.fxml"));
            Parent root = loader.load();


            SearchBySalaryRangeController controller = loader.getController();
            controller.setPlayerCollection(this.collection);

            Stage stage = (Stage) playerSearchMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadCountryWisePlayerCount() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CountryWisePlayerCount.fxml"));
            Parent root = loader.load();
            CountryWiseCountController controller = loader.getController();
            controller.setPlayerCollection(this.collection);
            Stage stage = (Stage) playerSearchMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadAddPlayer() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("AddPlayer.fxml"));
            Parent root = loader.load();

            AddplayerController controller = loader.getController();
            controller.setPlayerCollection(this.collection);

            Stage stage = (Stage) playerSearchMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadMaxSalaryList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MaxSalaryList.fxml"));
            Parent root = loader.load();
            MaxSalaryListController controller = loader.getController();
            controller.setPlayerCollection(this.collection);
            Stage stage = (Stage) playerSearchMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    public void loadMaxAgeList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MaxAgeList.fxml"));
            Parent root = loader.load();
            MaxAgeListController controller = loader.getController();
            controller.setPlayerCollection(this.collection);

            Stage stage = (Stage) playerSearchMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void loadMaxHeightList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("MaxHeightList.fxml"));
            Parent root = loader.load();

            MaxHeightListController controller = loader.getController();
            controller.setPlayerCollection(this.collection);

            Stage stage = (Stage) playerSearchMenuButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void goBackToAdmin() {
        try {
            Scene adminScene = HelloApplication.adminController.AdminButton.getScene();

            Stage stage = (Stage) playerSearchMenuButton.getScene().getWindow();
            stage.setScene(adminScene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
