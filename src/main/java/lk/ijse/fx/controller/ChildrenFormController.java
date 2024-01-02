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
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.ChildrenDAO;
import lk.ijse.fx.dao.impl.ChildrenDAOImpl;
import lk.ijse.fx.dto.AttendenceDto;
import lk.ijse.fx.dto.ChildrenDto;
import lk.ijse.fx.dto.tm.ChildrenTm;

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



        ChildrenDAO childrenDAO=new ChildrenDAOImpl();
        ChildrenDto childrenDto=new ChildrenDto(family_no,child_id,child_name, birthday, complimentary_date, date);
        try {
            boolean isSaved =childrenDAO.saveChildren(childrenDto);
            if (isSaved){
                tblChildren.getItems().add(new ChildrenTm(family_no, child_id, child_name, birthday, complimentary_date, date));
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
            ChildrenDAO childrenDAO = new ChildrenDAOImpl();  // Create an instance of the DAO
            ChildrenDto childrenDto = childrenDAO.searchCustomer(childId);  // Use the instance to call the method

            if (childrenDto != null) {
                txtChildId.setText(childrenDto.getChildId());
                txtFamilyNo.setText(childrenDto.getFamilyNo());
                txtChildName.setText(childrenDto.getChildName());
                txtBirthday.setText(childrenDto.getBirthday());
                txtComplimentaryDate.setText(childrenDto.getComplimentaryDate());
                txtDate.setText(childrenDto.getDate());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Children not found").show();
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

        ChildrenDAO childrenDAO =new ChildrenDAOImpl();
        try {
            childrenDAO.updateChildren(new ChildrenDto(newFamilyNo, childId, newChildName, newBirthday, newComplimentaryDate,newDate));

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
