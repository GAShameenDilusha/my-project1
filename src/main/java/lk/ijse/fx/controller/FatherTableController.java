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
import lk.ijse.fx.bo.BOFactory;
import lk.ijse.fx.bo.custom.EventBO;
import lk.ijse.fx.bo.custom.FatherBO;
import lk.ijse.fx.dao.custom.FatherDAO;
import lk.ijse.fx.dao.impl.FatherDAOImpl;
import lk.ijse.fx.dto.FatherDto;
import lk.ijse.fx.dto.tm.FatherTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FatherTableController {
    @FXML
    public TableView<FatherTm> tblFather;
    @FXML
    public TableColumn<FatherTm, Integer> colChurchNo;
    @FXML
    public TableColumn<FatherTm, Integer> colChurchFatherId;
    @FXML
    public TableColumn<FatherTm, String> colName;
    @FXML
    public TableColumn<FatherTm, String> colStartDate;
    @FXML
    public TableColumn<FatherTm, String> colLeaveDate;
    @FXML
    public TableColumn<FatherTm, Button> colAction;
    @FXML
    private AnchorPane root;

    FatherBO fatherBO= (FatherBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.FATHER);


    public void initialize() {
        setCellValueFactory();
        loadAllFather();
    }

    private void setCellValueFactory() {
        colChurchNo.setCellValueFactory(new PropertyValueFactory<>("ChurchNo"));
        colChurchFatherId.setCellValueFactory(new PropertyValueFactory<>("ChurchFatherId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colStartDate.setCellValueFactory(new PropertyValueFactory<>("StartDate"));
        colLeaveDate.setCellValueFactory(new PropertyValueFactory<>("LeaveDate"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btnDelete"));
    }

    private void loadAllFather() {
        ObservableList<FatherTm> obList = FXCollections.observableArrayList();
        try {
            List<FatherDto> allFather = fatherBO.loadAllFather();

            for (FatherDto dto : allFather) {
                JFXButton deleteButton = new JFXButton("Delete");
                deleteButton.setCursor(javafx.scene.Cursor.HAND);
                deleteButton.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
                deleteButton.setPrefWidth(100);
                deleteButton.setPrefHeight(30);
                deleteButton.setOnAction(event -> {
                    try {
                        removeFather(dto.getChurchFatherId());
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

                obList.add(new FatherTm(
                        dto.getChurchNo(),
                        dto.getChurchFatherId(),
                        dto.getName(),
                        dto.getStartDate(),
                        dto.getLeaveDate(),
                        deleteButton
                ));
            }

            tblFather.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }




    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/father_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Father Form");
        stage.centerOnScreen();
    }



    private void removeFather(String churchFatherId) throws SQLException, ClassNotFoundException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this father?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDeleted = fatherBO.deleteFather(churchFatherId);

            if (isDeleted) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setHeaderText(null);
                successAlert.setContentText("Father deleted successfully!");
                successAlert.showAndWait();

                loadAllFather(); // Refresh the table after deletion
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to delete father");
                errorAlert.showAndWait();
            }
        }
    }

}
