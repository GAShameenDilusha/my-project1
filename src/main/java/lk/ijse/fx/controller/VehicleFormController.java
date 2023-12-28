package lk.ijse.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.dto.VehicleDto;
import lk.ijse.fx.model.RegistrationModel;
import lk.ijse.fx.model.VehicleModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class VehicleFormController {
    public TextField txtChurchFatherId;
    public TextField txtDate;
    public TextField txtCategory;
    public TextField txtDescription;
    public TextField txtChurchFatherId1;

    @FXML
    private AnchorPane root;

    private VehicleModel vehiModel = new VehicleModel();


    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }






    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException {
        String church_father_id = txtChurchFatherId.getText();
        String date = txtDate.getText();
        String category = txtCategory.getText();
        String discription = txtDescription.getText();

        var dto = new VehicleDto(church_father_id, date, category, discription);

        try {
            boolean isSaved = vehiModel.saveVehicle(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "registration saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/vehicle_table.fxml"));   //me kotasa (mekath ekka line 6)mn damme next window ekata yamataya
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Vehicle Table");
        stage.centerOnScreen();


        boolean isVehicleValid = validateVehicle();
        if(isVehicleValid){
            //perform save action
        }
    }

    private boolean validateVehicle() {
        //Validate church_father_id
        String church_father_id = txtChurchFatherId.getText();
        Pattern churchFatherIdPattern = Pattern.compile("[P][F]\\d{3,}");
        Matcher churchFatherIdMatcher = churchFatherIdPattern.matcher(church_father_id);
        boolean churchFatherIdMatches = churchFatherIdMatcher.matches();

        if (!churchFatherIdMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid ChurchFatherId").show();
            return false;
        }


        //Validate category
        String category = txtCategory.getText();
        Pattern categoryPattern = Pattern.compile("[A-Za-z]{3,}");
        Matcher categoryMatcher = categoryPattern.matcher(category);
        boolean categoryMatches = categoryMatcher.matches();

        if (!categoryMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid category").show();
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
        String churchFatherId = txtChurchFatherId.getText();
        String newDate = txtDate.getText();
        String newCategory = txtCategory.getText();
        String newDiscription = txtDescription.getText();

        var dto = new VehicleDto(churchFatherId, newDate, newCategory, newDiscription);


        try {
            boolean isUpdated = vehiModel.updateVehicle(dto);
            if (isUpdated) {
                new Alert(Alert.AlertType.CONFIRMATION, "Registration updated!").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Failed to update registration").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

    }




        @FXML
        void txtSearchOnAction(ActionEvent event) {
            String familyNo = txtChurchFatherId1.getText();


            try {
                VehicleDto customerDto = vehiModel.searchCustomer(familyNo);
                if (customerDto != null) {
                    txtChurchFatherId.setText(customerDto.getChurchFatherId());
                    txtDate.setText(customerDto.getDate());
                    txtCategory.setText(customerDto.getCategory());
                    txtDescription.setText(customerDto.getDiscription());

                } else {
                    new Alert(Alert.AlertType.INFORMATION, "vehicle not found").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtChurchFatherId.setText("");
        txtDate.setText("");
        txtCategory.setText("");
        txtDescription.setText("");
        txtChurchFatherId1.setText("");
    }




    private void clearFields() {
        txtChurchFatherId.setText("");
        txtDate.setText("");
        txtCategory.setText("");
        txtDescription.setText("");
        txtChurchFatherId1.setText("");
    }



    }






