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
import lk.ijse.fx.bo.BOFactory;
import lk.ijse.fx.bo.custom.EventBO;
import lk.ijse.fx.bo.custom.PaymentBO;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.PaymentDAO;
import lk.ijse.fx.dao.impl.AttendenceDAOImpl;
import lk.ijse.fx.dao.impl.PaymentDAOImpl;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.AttendenceDto;
import lk.ijse.fx.dto.ChildrenDto;
import lk.ijse.fx.dto.PaymentDto;
import lk.ijse.fx.dto.tm.AttendenceTm;
import lk.ijse.fx.dto.tm.ChildrenTm;
import lk.ijse.fx.dto.tm.PaymentTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentFormController {
    @FXML
    public TextField txtChurchNo;
    public TextField txtFamilyNo;
    public TextField txtDivisionNo;
    public TextField txtFee;

    public TextField txtFamilyNo1;

    public TextField txtDate;

    public TableView tblPayment;
    @FXML
    private AnchorPane root;

    @FXML
    private TableView<PaymentTm> tblChildren;

    PaymentBO paymentBO= (PaymentBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PAYMENT);
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
        String family_no = txtFamilyNo.getText();
        String division_no = txtDivisionNo.getText();
        String fee = txtFee.getText();
        String date = txtDate.getText();

        var dto = new PaymentDto(church_no, family_no, division_no, fee,date);


        PaymentDto paymentDto=new PaymentDto(church_no, family_no, division_no, fee,date);
        try {
            boolean isSaved =paymentBO.savePayment(paymentDto);
            if (isSaved){
                tblPayment.getItems().add(new PaymentTm(church_no, family_no, division_no, fee,date));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/payment_table.fxml"));   //me kotasa (mekath ekka line 6)mn damme next window ekata yamataya
        Scene scene = new Scene(anchorPane);
        Stage stage = (Stage) root.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Payment table");
        stage.centerOnScreen();


        boolean isPaymentValid = validatePayment();
        if (isPaymentValid) {
            //perform save action
            new Alert(Alert.AlertType.CONFIRMATION, "Payment saved!").show();
        }
    }

    private boolean validatePayment() {
        // Validate church_no
        String church_no = txtChurchNo.getText();
        Pattern churchPattern = Pattern.compile("P[1-3]");
        Matcher churchMatcher = churchPattern.matcher(church_no);
        boolean churchMatches = churchMatcher.matches();

        if (!churchMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid churchNo").show();
            return false;
        }


        //Validate family_no
        String family_no = txtFamilyNo.getText();
        Pattern familyPattern = Pattern.compile("\\d{1,}");
        Matcher familyMatcher = familyPattern.matcher(family_no);
        boolean familyMatches = familyMatcher.matches();

        if (!familyMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid familyNo").show();
            return false;
        }


        // Validate division_no
        String division_no = txtDivisionNo.getText();
        Pattern divisionPattern = Pattern.compile("[ABCD]");
        Matcher divisionMatcher = divisionPattern.matcher(division_no);
        boolean divisionMatches = divisionMatcher.matches();

        if (!divisionMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid divisionNo").show();
            return false;
        }



        //Valid fee
        String fee = txtFee.getText();
        Pattern feePattern = Pattern.compile("\\d{1,}");
        Matcher feeMatcher = feePattern.matcher(fee);
        boolean feeMatches = feeMatcher.matches();

        if (!feeMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid fee").show();
            return false;
        }



        return true;
    }
























    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String newChurchNo = txtChurchNo.getText();
        String familyNo = txtFamilyNo.getText();
        String newDivisionNo = txtDivisionNo.getText();
        String newFee = txtFee.getText();
        String newDate = txtDate.getText();

        var dto = new PaymentDto(newChurchNo, familyNo, newDivisionNo, newFee,newDate);

        boolean isUpdated = paymentBO.updatePayment(dto);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Payment updated!").show();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update payemnt").show();
        }
    }


    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String familyNo = txtFamilyNo1.getText();

        try {
            PaymentDto paymentDto = paymentBO.searchPayment(familyNo);  // Use the instance to call the method

            if (paymentDto != null) {
                txtChurchNo.setText(paymentDto.getChurchNo());
                txtFamilyNo.setText(paymentDto.getFamilyNo());
                txtDivisionNo.setText(paymentDto.getDivisionNo());
                txtFee.setText(paymentDto.getFee());
                txtDate.setText(paymentDto.getDate());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Payment not found").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    @FXML
    void btnClearOnAction(ActionEvent event) {
        clearFields();
    }

    private void clearFields() {
        txtChurchNo.clear();
        txtFamilyNo.clear();
        txtDivisionNo.clear();
        txtFee.clear();
        txtDate.clear();
        txtFamilyNo1.setText("");
    }


    @FXML
    void btnReportOnAction(ActionEvent event) throws JRException, SQLException {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/payment.jrxml");
            if (resourceAsStream != null) {
                JasperDesign jasperDesign = JRXmlLoader.load(resourceAsStream);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                        null,
                        DbConnection.getInstance().getConnection()
                );
                JasperViewer.viewReport(jasperPrint, false);
            } else {
                System.out.println("Resource not found: /report/payment.jrxml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
