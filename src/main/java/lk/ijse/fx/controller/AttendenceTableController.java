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
import lk.ijse.fx.dto.AttendenceDto;
import lk.ijse.fx.dto.tm.AttendenceTm;
import lk.ijse.fx.model.AttendenceModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class AttendenceTableController {

    public TableColumn colFamilyNo;
    public TableColumn colPurpose;
    public TableColumn colArrangedTime;
    public TableColumn colLeaveTime;

    public TableColumn colDate;

    public TableColumn colAction;
    public TableView tblAttendance;

    @FXML
    private AnchorPane root;

    private AttendenceModel attenModel = new AttendenceModel();

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/attendence_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Attendence Form");
        stage.centerOnScreen();
    }

    public void initialize() {
        setCellValueFactory();
        loadAllAttendence();
    }


    private void setCellValueFactory(){
        colFamilyNo.setCellValueFactory(new PropertyValueFactory<>("FamilyNo"));
        colPurpose.setCellValueFactory(new PropertyValueFactory<>("purpose"));
        colArrangedTime.setCellValueFactory(new PropertyValueFactory<>("arrangedTime"));
        colLeaveTime.setCellValueFactory(new PropertyValueFactory<>("leaveTime"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }


    private void loadAllAttendence() {
        ObservableList<AttendenceTm> obList = FXCollections.observableArrayList();
        try {
            List<AttendenceDto> dtoList = attenModel.loadAllAttendence();


            for (AttendenceDto dto : dtoList) {
                JFXButton deleteButton = new JFXButton("Delete");
                deleteButton.setCursor(javafx.scene.Cursor.HAND);
                deleteButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
                deleteButton.setPrefWidth(100);
                deleteButton.setPrefHeight(30);
                deleteButton.setOnAction(event -> {
                    try {
                        removeAttendance(dto.getFamilyNo());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                obList.add(new AttendenceTm(
                        dto.getFamilyNo(),
                        dto.getPurpose(),
                        dto.getArrangedTime(),
                        dto.getLeaveTime(),
                        dto.getDate(),
                        deleteButton
                ));
            }
            tblAttendance.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void removeAttendance(String familyNo) throws SQLException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this attendance?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDeleted = attenModel.deleteAttendance(familyNo);

            if (isDeleted) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setHeaderText(null);
                successAlert.setContentText("Attendance deleted successfully!");
                successAlert.showAndWait();

                loadAllAttendence(); // Refresh the table after deletion
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to delete attendance");
                errorAlert.showAndWait();
            }
        }
    }

}
