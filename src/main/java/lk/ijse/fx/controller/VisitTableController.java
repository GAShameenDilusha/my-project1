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
import lk.ijse.fx.dto.VisitDto;
import lk.ijse.fx.dto.tm.VisitTm;
import lk.ijse.fx.model.VisitModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class VisitTableController {
    @FXML
    private TableColumn<VisitTm, String> colFamilyNo;
    @FXML
    private TableColumn<VisitTm, String> colChurchFatherId;
    @FXML
    private TableColumn<VisitTm, String> colDate;
    @FXML
    private TableColumn<VisitTm, String> colTime;
    @FXML
    private TableColumn<VisitTm, String> colDescription;
    @FXML
    private TableColumn<VisitTm, JFXButton> colAction;
    @FXML
    private TableView<VisitTm> tblVisit;
    @FXML
    private AnchorPane root;

    private VisitModel visitModel = new VisitModel();

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/visit_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Visit Form");
        stage.centerOnScreen();
    }

    public void initialize() {
        setCellValueFactory();
        loadAllVisit();
    }

    private void setCellValueFactory() {
        colFamilyNo.setCellValueFactory(new PropertyValueFactory<>("familyNo"));
        colChurchFatherId.setCellValueFactory(new PropertyValueFactory<>("churchFatherId"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    private void loadAllVisit() {
        ObservableList<VisitTm> obList = FXCollections.observableArrayList();
        try {
            List<VisitDto> dtoList = visitModel.loadAllVisit();
            for (VisitDto dto : dtoList) {
                JFXButton deleteButton = new JFXButton("Delete");
                deleteButton.setCursor(javafx.scene.Cursor.HAND);
                deleteButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
                deleteButton.setPrefWidth(100);
                deleteButton.setPrefHeight(30);
                deleteButton.setOnAction(event -> {
                    try {
                        removeVisit(dto.getFamilyNo());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                obList.add(new VisitTm(
                        dto.getFamilyNo(),
                        dto.getChurchFatherId(),
                        dto.getDate(),
                        dto.getTime(),
                        dto.getDiscription(),
                        deleteButton
                ));
            }
            tblVisit.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeVisit(String familyNo) throws SQLException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this visit?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDeleted = visitModel.deleteVisit(familyNo);

            if (isDeleted) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setHeaderText(null);
                successAlert.setContentText("Visit deleted successfully!");
                successAlert.showAndWait();

                loadAllVisit(); // Refresh the table after deletion
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to delete visit");
                errorAlert.showAndWait();
            }
        }
    }
}
