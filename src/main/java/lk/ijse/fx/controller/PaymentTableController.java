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
import lk.ijse.fx.dto.PaymentDto;
import lk.ijse.fx.dto.tm.PaymentTm;
import lk.ijse.fx.model.PaymentModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class PaymentTableController {
    public TableColumn colChurchNo;
    public TableColumn colFamilyNo;
    public TableColumn colDivisionNo;
    public TableColumn colFee;
    
    public TableColumn colDate;

    public TableColumn colAction;
    public TableView tblPayment;

    @FXML
    private AnchorPane root;

    private PaymentModel payModel = new PaymentModel();



    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/payment_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Payment Form");
        stage.centerOnScreen();
    }


    public void initialize() {
        setCellValueFactory();
        loadAllPayment();
    }


    private void setCellValueFactory(){
        colChurchNo.setCellValueFactory(new PropertyValueFactory<>("churchNo"));
        colFamilyNo.setCellValueFactory(new PropertyValueFactory<>("FamilyNo"));
        colDivisionNo.setCellValueFactory(new PropertyValueFactory<>("DivisionNo"));
        colFee.setCellValueFactory(new PropertyValueFactory<>("fee"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }


    private void loadAllPayment() {
        ObservableList<PaymentTm> obList = FXCollections.observableArrayList();
        try {
            List<PaymentDto> dtoList = payModel.loadAllPayment();


            for (PaymentDto dto : dtoList) {
                JFXButton deleteButton = new JFXButton("Delete");
                deleteButton.setCursor(javafx.scene.Cursor.HAND);
                deleteButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
                deleteButton.setPrefWidth(100);
                deleteButton.setPrefHeight(30);
                deleteButton.setOnAction(event -> {
                    try {
                        removePayment(dto.getFamilyNo());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                obList.add(new PaymentTm(
                        dto.getChurchNo(),
                        dto.getFamilyNo(),
                        dto.getDivisionNo(),
                        dto.getFee(),
                        dto.getDate(),
                        deleteButton
                ));
            }
            tblPayment.setItems(obList);


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void removePayment(String familyNo) throws SQLException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this payment?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDeleted = payModel.deletePayment(familyNo);


            if (isDeleted) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setHeaderText(null);
                successAlert.setContentText("Payment deleted successfully!");
                successAlert.showAndWait();


                loadAllPayment(); // Refresh the table after deletion
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to delete payment");
                errorAlert.showAndWait();
            }
        }
    }

}
