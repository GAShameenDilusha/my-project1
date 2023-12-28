package lk.ijse.fx.controller;

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
import lk.ijse.fx.dto.ChildrenDto;
import lk.ijse.fx.dto.tm.ChildrenTm;
import lk.ijse.fx.model.ChildrenModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ChildrenTableController {
    @FXML
    private AnchorPane root;

    @FXML
    private TableColumn<?, ?> colAction;

    @FXML
    private TableColumn<?, ?> colFamilyNo;

    @FXML
    private TableColumn<?, ?> colChildId;

    @FXML
    private TableColumn<?, ?> colChildName;

    @FXML
    private TableColumn<?, ?> colBirthday;

    @FXML
    private TableColumn<?, ?> colComplimentaryDate;

    @FXML
    private TableColumn<?, ?> colDate;


    @FXML
    private TableView<ChildrenTm> tblChildren;
    private ChildrenModel childModel = new ChildrenModel();

    public void initialize() {
        setCellValueFactory();
        loadAllChildren();
    }

    private void setCellValueFactory() {
        colFamilyNo.setCellValueFactory(new PropertyValueFactory<>("FamilyNo"));
        colChildId.setCellValueFactory(new PropertyValueFactory<>("ChildId"));
        colChildName.setCellValueFactory(new PropertyValueFactory<>("ChildName"));
        colBirthday.setCellValueFactory(new PropertyValueFactory<>("Birthday"));
        colComplimentaryDate.setCellValueFactory(new PropertyValueFactory<>("ComplimentaryDate"));
        colDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colAction.setCellValueFactory(new PropertyValueFactory<>("btn"));
    }


    private void loadAllChildren() {
        ObservableList<ChildrenTm> obList = FXCollections.observableArrayList();
        try {
            List<ChildrenDto> dtoList = childModel.loadAllChildren();

            for (ChildrenDto dto : dtoList) {
                Button deleteButton = new Button("Delete");
                deleteButton.setOnAction(event -> {
                    try {
                        removeChildren(dto.getChildId());
                    } catch (SQLException e) {
                        throw new RuntimeException(e);
                    }
                });
                obList.add(new ChildrenTm(
                        dto.getFamilyNo(),
                        dto.getChildId(),
                        dto.getChildName(),
                        dto.getBirthday(),
                        dto.getComplimentaryDate(),
                        dto.getDate(),
                        deleteButton
                ));
            }
            tblChildren.setItems(obList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/children_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Children Form");
        stage.centerOnScreen();
    }


    private void removeChildren(String childId) throws SQLException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this Child?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();


        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDeleted = childModel.deleteChildren(childId);


            if (isDeleted) {
                Alert successAlert = new Alert(Alert.AlertType.INFORMATION);
                successAlert.setHeaderText(null);
                successAlert.setContentText("Child deleted successfully!");
                successAlert.showAndWait();


                loadAllChildren(); // Refresh the table after deletion
            } else {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText(null);
                errorAlert.setContentText("Failed to delete child");
                errorAlert.showAndWait();
            }
        }

    }
}