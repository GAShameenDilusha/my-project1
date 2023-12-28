package lk.ijse.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.dto.VisitDto;
import lk.ijse.fx.dto.tm.VisitTm;
import lk.ijse.fx.model.VisitModel;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VisitFormController {
    public TextField txtFamilyNo;
    public TextField txtChurchFatherId;

    public TextField txtDate;
    public TextField txtTime;
    public TextField txtDescription;

    public TextField txtFamilyNo1;


    @FXML
    private AnchorPane root;

    private VisitModel visiModel = new VisitModel();
    private VisitModel visitModel;
    private JColorChooser tblVisit;

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        String family_no = txtFamilyNo.getText();
        String church_father_id = txtChurchFatherId.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();
        String discription = txtDescription.getText();


        var dto = new VisitDto(family_no, church_father_id, date, time, discription);


        try {
            boolean isSaved = visiModel.saveVisit(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "visit saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/visit_table.fxml"));   //me kotasa (mekath ekka line 6)mn damme next window ekata yamataya
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Visit Table");
        stage.centerOnScreen();

        boolean isVisitValid = validateVisit();
        if (isVisitValid)  {
            //perform save action
        }

    }

    private boolean validateVisit() {
        //Validate family_no
        String family_no = txtFamilyNo.getText();
        Pattern familyPattern = Pattern.compile("\\d{1,}");
        Matcher familyMatcher = familyPattern.matcher(family_no);
        boolean familyMatches = familyMatcher.matches();

        if (!familyMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid familyNo").show();
            return false;
        }


        //Validate church_father_id
        String church_father_id = txtChurchFatherId.getText();
        Pattern churchFatherIdPattern = Pattern.compile("^PF\\d{3,}");
        Matcher churchFatherIdfMatcher = churchFatherIdPattern.matcher(church_father_id);
        boolean churchFatherIdMatches = churchFatherIdfMatcher.matches();

        if (!churchFatherIdMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid ChurchFatherId").show();
            return false;
        }



        //Validate time
        String time = txtTime.getText();
        Pattern timePattern = Pattern.compile("\\d{1,}.\\d{1,}[A-Za-z]{2}");
        Matcher timeMatcher = timePattern.matcher(time);
        boolean timeMatches = timeMatcher.matches();

        if (!timeMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid time").show();
            return false;
        }


        //Validate discription
        String discription = txtDescription.getText();
        Pattern discriptionPattern = Pattern.compile("[A-Za-z]{3,}");
        Matcher discriptionMatcher = discriptionPattern.matcher(discription);
        boolean discriptionMatches = discriptionMatcher.matches();

        if (!discriptionMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid discription").show();
            return false;
        }

        return true;
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String familyNo = txtFamilyNo.getText();
        String newChurchFatherId = txtChurchFatherId.getText();
        String newDate = txtDate.getText();
        String newTime = txtTime.getText();
        String newDiscription = txtDescription.getText();


        var dto = new VisitDto(familyNo, newChurchFatherId, newDate, newTime, newDiscription);


        try {
            boolean isUpdated = visiModel.updateVisit(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration updated!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update visit").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String familyNo = txtFamilyNo1.getText();


        try {
            VisitDto customerDto = visiModel.searchCustomer(familyNo);
            if (customerDto != null) {
                txtFamilyNo.setText(customerDto.getFamilyNo());
                txtChurchFatherId.setText(customerDto.getChurchFatherId());
                txtDate.setText(customerDto.getDate());
                txtTime.setText(customerDto.getTime());
                txtDescription.setText(customerDto.getDiscription());

            } else {
                new Alert(Alert.AlertType.INFORMATION, "visit not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtFamilyNo.setText("");
        txtChurchFatherId.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtDescription.setText("");
        txtFamilyNo1.setText("");
    }




    private void clearFields() {
        txtFamilyNo.setText("");
        txtChurchFatherId.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtDescription.setText("");
        txtFamilyNo1.setText("");
    }



}
