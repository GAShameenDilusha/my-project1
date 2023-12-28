package lk.ijse.fx.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.FatherDto;
import lk.ijse.fx.model.ChurchModel;
import lk.ijse.fx.dto.ChurchDto;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ChurchFormController {
    public Label lblA;
    public Label lblB;
    public Label lblC;
    public Label lblD;
    @FXML
    private AnchorPane root;

    @FXML
    private ComboBox<ChurchDto> cmbChurch;

    @FXML
    private TextField txtA;

    @FXML
    private TextField txtB;

    @FXML
    private TextField txtC;

    @FXML
    private TextField txtD;

    private ChurchModel churchModel;

    public void initialize() {
        churchModel = new ChurchModel();
        loadChurches();
    }

    private void loadChurches() {
        try {
            List<ChurchDto> churches = churchModel.getAllChurches();
            cmbChurch.setItems(FXCollections.observableArrayList(churches));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void cmbChurchOnAction(ActionEvent event) {
        ChurchDto selectedChurch = cmbChurch.getValue();
        if (selectedChurch != null) {
            lblA.setText(selectedChurch.getA()+"");
            lblB.setText(selectedChurch.getB()+"");
            lblC.setText(selectedChurch.getC()+"");
            lblD.setText(selectedChurch.getD()+"");
        }
    }



    @FXML
    void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    @FXML
    void btnAddFamilyOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/registration_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration Form");
        stage.centerOnScreen();
    }
}



