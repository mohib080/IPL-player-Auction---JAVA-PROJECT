package com.example.demo;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Map;

public class CountryWiseCountController {
    private playerCollection collection;

    public void setPlayerCollection(playerCollection collection) {
        this.collection = collection;
        populateCountryWiseCount();
    }

    @FXML
    private TableView<CountryCount> countryCountTable;

    @FXML
    private TableColumn<CountryCount, String> countryColumn;

    @FXML
    private TableColumn<CountryCount, Integer> countColumn;

    @FXML
    private Button backToMainButton;

    public static class CountryCount {
        private final String country;
        private final int count;

        public CountryCount(String country, int count) {
            this.country = country;
            this.count = count;
        }

        public String getCountry() { return country; }
        public int getCount() { return count; }
    }


    @FXML
    public void initialize() {
        countryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        countColumn.setCellValueFactory(new PropertyValueFactory<>("count"));
        countryCountTable.setStyle("-fx-font-size: 18px;");
        countryCountTable.setFixedCellSize(40);

        // Check if collection is already set, then populate data
        if (collection != null) {
            populateCountryWiseCount();
        }
    }

    private void populateCountryWiseCount() {
        if (collection == null) {
            System.out.println("PlayerCollection is not initialized.");
            return;
        }

        Map<String, Integer> countryCounts = collection.CountryWisePlayerCount();
        ObservableList<CountryCount> data = FXCollections.observableArrayList();

        for (Map.Entry<String, Integer> entry : countryCounts.entrySet()) {
            data.add(new CountryCount(entry.getKey(), entry.getValue()));
        }

        countryCountTable.setItems(data);

        double rowHeight = countryCountTable.getFixedCellSize();
        countryCountTable.setPrefHeight(data.size() * rowHeight + 40);
    }

    @FXML
    public void goBackToMain() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/homepage.fxml"));
            Parent root = loader.load();

            Stage stage = (Stage) backToMainButton.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
