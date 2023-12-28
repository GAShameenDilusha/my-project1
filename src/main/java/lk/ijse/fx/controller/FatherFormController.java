package lk.ijse.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.dto.FatherDto;
import lk.ijse.fx.dto.tm.FatherTm;
import lk.ijse.fx.model.FatherModel;

import java.awt.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FatherFormController {


    public TextField txtLeaveDate;
    public TextField txtStartDate;
    public TextField txtName;
    public TextField txtChurchFatherId;
    public TextField txtChurchNo;
    public TextField txtChurchFatherId1;
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<FatherTm> tblFather;

    private FatherModel farModel = new FatherModel();



    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }


    @FXML
   void btnSaveOnAction(ActionEvent event) throws IOException {
        String church_no = txtChurchNo.getText();
        String church_father_id = txtChurchFatherId.getText();
        String name = txtName.getText();
        String start_date = txtStartDate.getText();
        String leave_date = txtLeaveDate.getText();

        var dto = new FatherDto(church_no, church_father_id, name, start_date, leave_date);


        try {
            boolean isSaved = farModel.saveFather(dto);
            if (isSaved) {
                new Alert(Alert.AlertType.CONFIRMATION, "Father saved!").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/father_table.fxml"));   //me kotasa (mekath ekka line 6)mn damme next window ekata yamataya
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Father Table");
        stage.centerOnScreen();
        
        
        boolean isFatherValid = validateFather();
        if(isFatherValid){
            //perform save action
        }
    }

    private boolean validateFather() {
        // Validate church_no
        String church_no = txtChurchNo.getText();
        Pattern churchPattern = Pattern.compile("P[1-3]");
        Matcher churchMatcher = churchPattern.matcher(church_no);
        boolean churchMatches = churchMatcher.matches();

        if (!churchMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid churchNo").show();
            return false;
        }


        //Validate church_father_id
        String church_father_id = txtChurchFatherId.getText();
        Pattern churchFatherIdPattern = Pattern.compile("[P][F]\\d{3,}");
        Matcher churchFatherIdMatcher = churchFatherIdPattern.matcher(church_father_id);
        boolean churchFatherIdMatches = churchFatherIdMatcher.matches();

        if (!churchFatherIdMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid ChurchFatherId").show();
            return false;
        }


        //Validate Name
        String name = txtName.getText();
        Pattern namePattern = Pattern.compile("[A-Za-z].{3,}");
        Matcher nameMatcher = namePattern.matcher(name);
        boolean nameMatches = nameMatcher.matches();

        if (!nameMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid name").show();
            return false;
        }

        return true;
    }


    @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String newChurchNo = txtChurchNo.getText();
            String churchFatherId = txtChurchFatherId.getText();
            String newName = txtName.getText();
            String newStartDate = txtStartDate.getText();
            String newLeaveDate = txtLeaveDate.getText();

            var dto = new FatherDto(newChurchNo, churchFatherId, newName, newStartDate, newLeaveDate);

            try {
                boolean isUpdated = farModel.updateFather(dto);
                if (isUpdated) {
                    new Alert(Alert.AlertType.CONFIRMATION, "Father updated!").show();
                    clearFields();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Failed to update father").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        }








@FXML
void btnSearchOnAction(ActionEvent event) {
   String churchFatherId = txtChurchFatherId1.getText();


   try {
       FatherDto customerDto = farModel.searchCustomer(churchFatherId);
       if (customerDto != null) {
           txtChurchNo.setText(customerDto.getChurchNo());
           txtChurchFatherId.setText(customerDto.getChurchFatherId());
           txtName.setText(customerDto.getName());
           txtStartDate.setText(customerDto.getStartDate());
           txtLeaveDate.setText(customerDto.getLeaveDate());
       } else {
           new Alert(Alert.AlertType.INFORMATION, "father not found").show();
       }
   } catch (SQLException e) {
       new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
   }
}







@FXML
  void btnClearOnAction(ActionEvent event) {
      txtChurchNo.setText("");
      txtChurchFatherId.setText("");
      txtName.setText("");
      txtStartDate.setText("");
      txtLeaveDate.setText("");
      txtChurchFatherId1.setText("");
  }


  private void clearFields() {
      txtChurchNo.setText("");
      txtChurchFatherId.setText("");
      txtName.setText("");
      txtStartDate.setText("");
      txtLeaveDate.setText("");
      txtChurchFatherId1.setText("");
  }




    @FXML
    void btnNextOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/vehicle_form.fxml"));   //me kotasa (mekath ekka line 6)mn damme next window ekata yamataya
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Vehicle Form");
        stage.centerOnScreen();
    }

}

