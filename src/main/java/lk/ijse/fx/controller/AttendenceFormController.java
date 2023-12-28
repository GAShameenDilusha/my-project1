package lk.ijse.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.dto.AttendenceDto;
import lk.ijse.fx.model.AttendenceModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AttendenceFormController {
    public TextField txtFamilyNo;
    public TextField txtPurpose;
    public TextField txtArrangedTime;
    public TextField txtLeaveTime;

    public TextField txtDate;
    public TextField txtFamilyNo1;

    @FXML
    private AnchorPane root;

    private AttendenceModel attenModel = new AttendenceModel();


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard Form");
        stage.centerOnScreen();
    }


    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        String family_no = txtFamilyNo.getText();
        String purpose = txtPurpose.getText();
        String arranged_time = txtArrangedTime.getText();
        String leave_time = txtLeaveTime.getText();
        String date = txtDate.getText();


        var dto = new AttendenceDto(family_no, purpose, arranged_time, leave_time, date);


        try {
            boolean isSaved = attenModel.saveAttendence(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "attendence saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }


        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/attendence_table.fxml"));   //me kotasa (mekath ekka line 6)mn damme next window ekata yamataya
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Attendence Table");
        stage.centerOnScreen();


        boolean isAttendence = validateAttendence();
        if(isAttendence){
            //perform save action
        }
    }

    private boolean validateAttendence() {
        //Validate family_no
        String family_no = txtFamilyNo.getText();
        Pattern familyPattern = Pattern.compile("\\d{1,}");
        Matcher familyMatcher = familyPattern.matcher(family_no);
        boolean familyMatches = familyMatcher.matches();

        if (!familyMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid familyNo").show();
            return false;
        }


        //Validate purpose
        String purpose = txtPurpose.getText();
        Pattern purposePattern = Pattern.compile("[A-Za-z]{3,}");
        Matcher purposeMatcher = purposePattern.matcher(purpose);
        boolean purposeMatches = purposeMatcher.matches();

        if (!purposeMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid purpose").show();
            return false;
        }

        //Validate arrangedTime
        String arrangedtime = txtArrangedTime.getText();
        Pattern arrangedTimePattern = Pattern.compile("\\d{1,}.\\d{1,}[A-Za-z]{2}");
        Matcher arrangedTimeMatcher = arrangedTimePattern.matcher(arrangedtime);
        boolean arrangedTimeMatches = arrangedTimeMatcher.matches();

        if (!arrangedTimeMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid arrangedTime").show();
            return false;
        }


        //Validate LeaveTime
        String leavetime = txtLeaveTime.getText();
        Pattern leaveTimePattern = Pattern.compile("\\d{1,}.\\d{1,}[A-Za-z]{2}");
        Matcher leaveTimeMatcher = leaveTimePattern.matcher(leavetime);
        boolean leaveTimeMatches = leaveTimeMatcher.matches();

        if (!leaveTimeMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid leaveTime").show();
            return false;
        }

        return true;
    }






    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String familyNo = txtFamilyNo.getText();
        String newPurpose = txtPurpose.getText();
        String newArrangedTime = txtArrangedTime.getText();
        String newLeaveTime = txtLeaveTime.getText();
        String newDate = txtDate.getText();


        var dto = new AttendenceDto(familyNo, newPurpose, newArrangedTime, newLeaveTime, newDate);


        try {
            boolean isUpdated = attenModel.updateAttendence(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Attendence updated!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update attendence").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String familyNo = txtFamilyNo1.getText();


        try {
            AttendenceDto customerDto = attenModel.searchCustomer(familyNo);
            if (customerDto != null) {
                txtFamilyNo.setText(customerDto.getFamilyNo());
                txtPurpose.setText(customerDto.getPurpose());
                txtArrangedTime.setText(customerDto.getArrangedTime());
                txtLeaveTime.setText(customerDto.getLeaveTime());
                txtDate.setText(customerDto.getDate());

            } else {
                new Alert(Alert.AlertType.INFORMATION, "attendence not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtFamilyNo.setText("");
        txtPurpose.setText("");
        txtArrangedTime.setText("");
        txtLeaveTime.setText("");
        txtDate.setText("");
        txtFamilyNo1.setText("");
    }




    private void clearFields() {
        txtFamilyNo.setText("");
        txtPurpose.setText("");
        txtArrangedTime.setText("");
        txtLeaveTime.setText("");
        txtDate.setText("");
        txtFamilyNo1.setText("");
    }

}
