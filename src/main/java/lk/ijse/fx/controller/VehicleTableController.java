package lk.ijse.fx.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.dto.VehicleDto;
import lk.ijse.fx.dto.tm.VehicleTm;
import lk.ijse.fx.model.VehicleModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VehicleTableController {
    public TableColumn colChurchFatherId;
    public TableColumn colDate;
    public TableColumn colCategory;
    public TableColumn colDescription;

    public TableColumn colAction;
    public TableView tblVehicle;
    @FXML
    private AnchorPane root;

    private VehicleModel vehiModel = new VehicleModel();

    public void initialize() {
        setCellValueFactory();
        loadAllVehicle();
    }


    private void setCellValueFactory() {
        colChurchFatherId.setCellValueFactory(new PropertyValueFactory<>("ChurchFatherId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("Category"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Discription"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }


    private void loadAllVehicle() {
        ObservableList<VehicleTm> obList = FXCollections.observableArrayList();
        try {
            List<VehicleDto> dtoList = vehiModel.loadAllVehicle();


            for (VehicleDto dto : dtoList) {
                JFXButton deleteButton = new JFXButton("Delete");
                deleteButton.setCursor(javafx.scene.Cursor.HAND);
                deleteButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
                deleteButton.setPrefWidth(100);
                deleteButton.setPrefHeight(30);
                deleteButton.setOnAction(event -> {
                    try {
                        removeVehicle(dto.getChurchFatherId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                obList.add(new VehicleTm(
                        dto.getChurchFatherId(),
                        dto.getDate(),
                        dto.getCategory(),
                        dto.getDiscription(),
                        deleteButton
                ));
            }
            tblVehicle.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/vehicle_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Vehicle Form");
        stage.centerOnScreen();
    }


    private void removeVehicle(String churchFatherId) throws SQLException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this vehicle?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDeleted = vehiModel.deleteVehicle(churchFatherId);


            if (isDeleted) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setHeaderText(null);
                successAlert.setContentText("Vehicle deleted successfully!");
                successAlert.showAndWait();


                loadAllVehicle(); // Refresh the table after deletion
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to delete vehicle");
                errorAlert.showAndWait();
            }
        }

    }
}
