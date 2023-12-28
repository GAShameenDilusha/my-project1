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
import lk.ijse.fx.dto.RegistrationDto;
import lk.ijse.fx.dto.tm.RegistrationTm;
import lk.ijse.fx.model.RegistrationModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class RegistrationTableController {
    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colAddress;

    @FXML
    private TableColumn<?, ?> colChurchNo;

    @FXML
    private TableColumn<?, ?> colDate;

    @FXML
    private TableColumn<?, ?> colDivisionNo;

    @FXML
    private TableColumn<?, ?> colFamilyNo;

    @FXML
    private TableColumn<?, ?> colFatherId;

    @FXML
    private TableColumn<?, ?> colFatherName;

    @FXML
    private TableColumn<?, ?> colMotherId;

    @FXML
    private TableColumn<?, ?> colMotherName;

    @FXML
    private TableColumn<?, ?> colTel;

    @FXML
    private AnchorPane root;

    @FXML
    private TableView<RegistrationTm> tblRegistration;
    private RegistrationModel regisModel = new RegistrationModel();


    public void initialize() {
        setCellValueFactory();
        loadAllRegistration();
    }

    private void setCellValueFactory() {
        colChurchNo.setCellValueFactory(new PropertyValueFactory<>("churchNo"));
        colDivisionNo.setCellValueFactory(new PropertyValueFactory<>("DivisionNo"));
        colFamilyNo.setCellValueFactory(new PropertyValueFactory<>("FamilyNo"));
        colFatherId.setCellValueFactory(new PropertyValueFactory<>("fatherId"));
        colMotherId.setCellValueFactory(new PropertyValueFactory<>("motherId"));
        colFatherName.setCellValueFactory(new PropertyValueFactory<>("fatherName"));
        colMotherName.setCellValueFactory(new PropertyValueFactory<>("motherName"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
        colTel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    private void loadAllRegistration() {
        ObservableList<RegistrationTm> obList = FXCollections.observableArrayList();
        try {
            List<RegistrationDto> dtoList = regisModel.loadAllRegistration();

            for (RegistrationDto dto : dtoList) {
                JFXButton deleteButton = new JFXButton("Delete");
                deleteButton.setCursor(javafx.scene.Cursor.HAND);
                deleteButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
                deleteButton.setPrefWidth(100);
                deleteButton.setPrefHeight(30);
                deleteButton.setOnAction(event -> {
                    try {
                        removeRegistration(dto.getFamilyNo());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });

                obList.add(new RegistrationTm(
                        dto.getChurchNo(),
                        dto.getDivisionNo(),
                        dto.getFamilyNo(),
                        dto.getFatherId(),
                        dto.getMotherId(),
                        dto.getFatherName(),
                        dto.getMotherName(),
                        dto.getAddress(),
                        dto.getTel(),
                        dto.getDate(),
                        deleteButton
                ));
            }
            tblRegistration.setItems(obList);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/registration_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Registration Form");
        stage.centerOnScreen();
    }

    private void removeRegistration(String familyNo) throws SQLException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this registration?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDeleted = regisModel.deleteRegistration(familyNo);


            if (isDeleted) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setHeaderText(null);
                successAlert.setContentText("Registration deleted successfully!");
                successAlert.showAndWait();


                loadAllRegistration(); // Refresh the table after deletion
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to delete registration");
                errorAlert.showAndWait();
            }
        }


    }
}







