package lk.ijse.fx.controller;

import com.jfoenix.controls.JFXButton;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.dto.EventDto;
import lk.ijse.fx.dto.tm.EventTm;
import lk.ijse.fx.model.EventModel;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EventTableController {
    public TableColumn colFamilyNo;
    public TableColumn colEventName;

    public TableColumn colDate;
    public TableColumn colTime;
    public TableColumn colDescription;
    public TableColumn colEstimatedBudget;
    public TableColumn colCost;

    public TableColumn colAction;
    public TableView tblEvent;

    @FXML
    private AnchorPane root;
    private EventModel eveModel = new EventModel();


    public void initialize() {
        setCellValueFactory();
        loadAllEvent();
    }


    private void setCellValueFactory() {
        colFamilyNo.setCellValueFactory(new PropertyValueFactory<>("FamilyNo"));
        colEventName.setCellValueFactory(new PropertyValueFactory<>("EventName"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("Time"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("Discription"));
        colEstimatedBudget.setCellValueFactory(new PropertyValueFactory<>("EstimatedBudget"));
        colCost.setCellValueFactory(new PropertyValueFactory<>("Cost"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }


    private void loadAllEvent() {
        ObservableList<EventTm> obList = FXCollections.observableArrayList();
        try {
            List<EventDto> dtoList = eveModel.loadAllEvent();


            for (EventDto dto : dtoList) {
                JFXButton deleteButton = new JFXButton("Delete");
                deleteButton.setCursor(javafx.scene.Cursor.HAND);
                deleteButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
                deleteButton.setPrefWidth(100);
                deleteButton.setPrefHeight(30);
                deleteButton.setOnAction(event -> {
                    try {
                        removeEvent(dto.getFamilyNo());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });


                obList.add(new EventTm(
                        dto.getFamilyNo(),
                        dto.getEventName(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getDiscription(),
                        dto.getEstimatedBudget(),
                        dto.getCost(),
                        deleteButton

                ));
            }
            tblEvent.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/event_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Event Form");
        stage.centerOnScreen();
    }

    private void removeEvent(String familyNo) throws SQLException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this event?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDeleted = eveModel.deleteEvent(familyNo);


            if (isDeleted) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setHeaderText(null);
                successAlert.setContentText("Event deleted successfully!");
                successAlert.showAndWait();


                loadAllEvent(); // Refresh the table after deletion
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to delete Event");
                errorAlert.showAndWait();
            }
        }


    }
}