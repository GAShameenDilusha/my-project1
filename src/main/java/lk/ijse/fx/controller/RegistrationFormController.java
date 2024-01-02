package lk.ijse.fx.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.RegistrationDAO;
import lk.ijse.fx.dao.impl.AttendenceDAOImpl;
import lk.ijse.fx.dao.impl.RegistrationDAOImpl;
import lk.ijse.fx.dto.AttendenceDto;
import lk.ijse.fx.dto.RegistrationDto;
import lk.ijse.fx.dto.tm.AttendenceTm;
import lk.ijse.fx.dto.tm.RegistrationTm;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class RegistrationFormController implements Initializable {

    @FXML
    private TextField txtFamilyNo1;
    @FXML
    private ImageView imgRegistration;
    @FXML
    private ImageView BackImg;
    @FXML
    private Label lblFamilyNo;
    @FXML
    private Label lblFatherId;
    @FXML
    private Label lblMotherId;
    @FXML
    private AnchorPane root;

    @FXML
    private TextField txtAddress;
    @FXML
    private TextField txtChurchNo;
    @FXML
    private TextField txtDate;
    @FXML
    private TextField txtDivisionNo;
    @FXML
    private TextField txtFatherName;
    @FXML
    private TextField txtMotherName;
    @FXML
    private TextField txtTel;
    @FXML
    private TextField txtSearch;
    @FXML
    private TableView<RegistrationTm> tblRegistration;
    private RegistrationDto dto = new RegistrationDto();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            RegistrationDAO registrationDAO = new RegistrationDAOImpl();
            int nextFamilyNo = registrationDAO.getNextFamilyNo();
            lblFamilyNo.setText(String.valueOf(nextFamilyNo));
            dto.setFamilyNo(String.valueOf(nextFamilyNo));

            String formattedFatherId = String.format("F%03d", nextFamilyNo);
            lblFatherId.setText(formattedFatherId);
            dto.setFatherId(formattedFatherId);

            String formattedMotherId = String.format("M%03d", nextFamilyNo);
            lblMotherId.setText(formattedMotherId);
            dto.setMotherId(formattedMotherId);

        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws IOException, SQLException {
        boolean shouldAutoGenerate = true;

        if (!shouldAutoGenerate) {
            // Get user entered values
            String family_no = lblFamilyNo.getText();
            String father_id = lblFatherId.getText();
            String mother_id = lblMotherId.getText();

            dto.setFamilyNo(family_no);
            dto.setFatherId(father_id);
            dto.setMotherId(mother_id);
        }

        String church_no = txtChurchNo.getText();
        String division_no = txtDivisionNo.getText();
        String family_no = lblFamilyNo.getText();
        String father_id = lblFatherId.getText();
        String mother_id = lblMotherId.getText();
        String father_name = txtFatherName.getText();
        String mother_name = txtMotherName.getText();
        String address = txtAddress.getText();
        String tel = txtTel.getText();
        String date = txtDate.getText();

        dto = new RegistrationDto(church_no, division_no, family_no, father_id, mother_id, father_name, mother_name, address, tel, date);

        RegistrationDAO registrationDAO=new RegistrationDAOImpl();
        try {
            boolean isSaved =registrationDAO.saveRegistration(dto);
            if (isSaved){
                tblRegistration.getItems().add(new RegistrationTm(dto.getChurchNo(), dto.getDivisionNo(), dto.getFamilyNo(), dto.getFatherId(), dto.getMotherId(), dto.getFatherName(), dto.getMotherName(), dto.getAddress(), dto.getTel(), dto.getDate()));
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/registration_table.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Registration Table");
        stage.centerOnScreen();

        boolean isRegistrationValid = validateRegistration();
        if (isRegistrationValid) {
            // Perform save action
        }
    }

    private boolean validateRegistration() {
        // Validation code...

        return true;
    }

    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String familyNo = txtFamilyNo1.getText();

        RegistrationDAO registrationDAO = new RegistrationDAOImpl();
        try {
            RegistrationDto registrationDto = registrationDAO.searchCustomer(familyNo);
            if (registrationDto != null) {
                lblFamilyNo.setText(registrationDto.getFamilyNo());
                txtChurchNo.setText(registrationDto.getChurchNo());
                txtDivisionNo.setText(registrationDto.getDivisionNo());
                lblFatherId.setText(registrationDto.getFatherId());
                lblMotherId.setText(registrationDto.getMotherId());
                txtFatherName.setText(registrationDto.getFatherName());
                txtMotherName.setText(registrationDto.getMotherName());
                txtAddress.setText(registrationDto.getAddress());
                txtTel.setText(registrationDto.getTel());
                txtDate.setText(registrationDto.getDate());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Registration not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void btnUpdateOnAction(ActionEvent event) {
        String familyNo = lblFamilyNo.getText();
        String newChurchNo = txtChurchNo.getText();
        String newDivisionNo = txtDivisionNo.getText();
        String newFatherId = lblFatherId.getText();
        String newMotherId = lblMotherId.getText();
        String newFatherName = txtFatherName.getText();
        String newMotherName = txtMotherName.getText();
        String newAddress = txtAddress.getText();
        String newTel = txtTel.getText();
        String newDate = txtDate.getText();

        var dto = new RegistrationDto(newChurchNo, newDivisionNo, familyNo, newFatherId, newMotherId, newFatherName, newMotherName, newAddress, newTel, newDate);

        RegistrationDAO registrationDAO = new RegistrationDAOImpl();
        try {
            boolean isUpdated = registrationDAO.updateRegistration(dto);
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
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtChurchNo.clear();
        txtDivisionNo.clear();
        lblFamilyNo.setText("");
        lblFatherId.setText("");
        lblMotherId.setText("");
        txtFatherName.clear();
        txtMotherName.clear();
        txtAddress.clear();
        txtTel.clear();
        txtDate.clear();
        txtFamilyNo1.clear();
    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }

    @FXML
    void btnAddChildOnAction(ActionEvent event) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/children_form.fxml"));
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Children Form");
        stage.centerOnScreen();
    }
}
