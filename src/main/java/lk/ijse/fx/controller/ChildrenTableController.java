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
import lk.ijse.fx.bo.BOFactory;
import lk.ijse.fx.bo.custom.AttendenceBO;
import lk.ijse.fx.bo.custom.ChildrenBO;
import lk.ijse.fx.dao.custom.ChildrenDAO;
import lk.ijse.fx.dao.impl.ChildrenDAOImpl;
import lk.ijse.fx.dto.ChildrenDto;
import lk.ijse.fx.dto.tm.ChildrenTm;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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

    ChildrenBO childrenBO= (ChildrenBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CHILDREN);
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

            List<ChildrenDto> allChildren = childrenBO.loadAllChildren();

            for (ChildrenDto c : allChildren) {
                Button deleteButton = new Button("Delete");
                deleteButton.setOnAction(event -> {
                    try {
                        removeChildren(c.getChildId());
                    } catch (SQLException | ClassNotFoundException e) {
                        throw new RuntimeException(e);
                    }
                });

                obList.add(new ChildrenTm(
                        c.getFamilyNo(),
                        c.getChildId(),
                        c.getChildName(),
                        c.getBirthday(),
                        c.getComplimentaryDate(),
                        c.getDate(),
                        deleteButton
                ));
            }

            tblChildren.setItems(obList);

        } catch (SQLException | ClassNotFoundException e) {
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


    private void removeChildren(String childId) throws SQLException, ClassNotFoundException {
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setHeaderText(null);
        confirmationAlert.setContentText("Are you sure you want to delete this Child?");
        Optional<ButtonType> result = confirmationAlert.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            boolean isDeleted = childrenBO.deleteChildren(childId);

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