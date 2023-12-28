package lk.ijse.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.dto.ChildrenDto;
import lk.ijse.fx.dto.RegistrationDto;
import lk.ijse.fx.dto.tm.ChildrenTm;
import lk.ijse.fx.dto.tm.RegistrationTm;
import lk.ijse.fx.model.ChildrenModel;
import lk.ijse.fx.model.RegistrationModel;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChildrenFormController {
    public TextField txtDate;

    public TextField txtFamilyNo1;
    public TextField txtChildId;
    public TextField txtChildName;
    public TextField txtBirthday;
    public TextField txtComplimentaryDate;

    public TextField txtFamilyNo;
    public ImageView img;
    public ImageView backImg;
    public TextField txtChildId1;
    public DatePicker txtDate1;
    public DatePicker txtDate2;
    @FXML
    private AnchorPane root;



    @FXML
    private TableView<ChildrenTm> tblChildren;

    private ChildrenModel childModel = new ChildrenModel();




    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/registration_form.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Registration Form");
        stage.centerOnScreen();
    }



    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        String family_no = txtFamilyNo.getText();
        String child_id = txtChildId.getText();
        String child_name = txtChildName.getText();
        String birthday = txtBirthday.getText();
        String complimentary_date = txtComplimentaryDate.getText();
        String date = txtDate.getText();

        var dto = new ChildrenDto(family_no, child_id, child_name, birthday, complimentary_date, date);

        try {
            boolean isSaved = childModel.saveChildren(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Children saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/children_table.fxml"));   //me kotasa (mekath ekka line 6)mn damme next window ekata yamataya
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Children Table");
        stage.centerOnScreen();


        boolean isChildrenValid = validateChildren();
        if (isChildrenValid) {
            //perform save action
        }


    }

    private boolean validateChildren() {
        //Validate family_no
        String family_no = txtFamilyNo.getText();
        Pattern familyPattern = Pattern.compile("\\d{1,}");
        Matcher familyMatcher = familyPattern.matcher(family_no);
        boolean familyMatches = familyMatcher.matches();

        if (!familyMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid familyNo").show();
            return false;
        }


        //Validate child_id
        String child_id = txtChildId.getText();
        Pattern childIdPattern = Pattern.compile("\\d{1,}[/][C]\\d{1,}");
        Matcher childIdMatcher = childIdPattern.matcher(child_id);
        boolean childIdMatches = childIdMatcher.matches();

        if (!childIdMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid childId").show();
            return false;
        }


        //Validate child_name
        String child_name = txtChildName.getText();
        Pattern childNamePattern = Pattern.compile("[A-Za-z]{3,}");
        Matcher childNameMatcher = childNamePattern.matcher(child_name);
        boolean childNameMatches = childNameMatcher.matches();

        if (!childNameMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid childName").show();
            return false;
        }

        return true;
    }


    @FXML
    void btnSearchOnAction(ActionEvent event) {
        String childId = txtChildId1.getText();

        try {
            ChildrenDto customerDto = childModel.searchCustomer(childId);
            if (customerDto != null) {
                txtChildId.setText(customerDto.getChildId());
                txtFamilyNo.setText(customerDto.getFamilyNo());
                txtChildName.setText(customerDto.getChildName());
                txtBirthday.setText(customerDto.getBirthday());
                txtComplimentaryDate.setText(customerDto.getComplimentaryDate());
                txtDate.setText(customerDto.getDate());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "children not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }










    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String newFamilyNo = txtFamilyNo.getText();
        String childId = txtChildId.getText();
        String newChildName = txtChildName.getText();
        String newBirthday = txtBirthday.getText();
        String newComplimentaryDate = txtComplimentaryDate.getText();
        String newDate = txtDate.getText();

        var dto = new ChildrenDto(newFamilyNo, childId, newChildName, newBirthday, newComplimentaryDate, newDate);

        try {
            boolean isUpdated = childModel.updateChildren(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Children updated!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update children").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }







    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtFamilyNo.setText("");
        txtChildId.setText("");
        txtChildName.setText("");
        txtBirthday.setText("");
        txtComplimentaryDate.setText("");
        txtDate.setText("");
        txtChildId1.setText("");
    }

    private void clearFields() {
       txtFamilyNo.setText("");
        txtChildId.setText("");
        txtChildName.setText("");
        txtBirthday.setText("");
        txtComplimentaryDate.setText("");
        txtDate.setText("");
        txtChildId1.setText("");
    }




}
