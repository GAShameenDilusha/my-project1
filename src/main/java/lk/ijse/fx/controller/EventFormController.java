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
import lk.ijse.fx.bo.custom.ChildrenBO;
import lk.ijse.fx.bo.custom.EventBO;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.EventDAO;
import lk.ijse.fx.dao.impl.AttendenceDAOImpl;
import lk.ijse.fx.dao.impl.EventDAOImpl;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.AttendenceDto;
import lk.ijse.fx.dto.EventDto;
import lk.ijse.fx.dto.tm.EventTm;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EventFormController {
    public TextField txtDiscription;

    public TextField txtFamilyNo;

    public TextField txtDate;
    public TextField txtTime;
    public TextField txtEstimatedBudget;
    public TextField txtCost;
    public TextField txtEventName;

    public TextField txtFamilyNo1;
    public TableView tblEvent;
    @FXML
    private AnchorPane root;

    EventBO eventBO= (EventBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.EVENT);

    public void btnBackOnAction(ActionEvent actionEvent) throws IOException {
        AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/dashboard.fxml"));
        Stage stage = (Stage) root.getScene().getWindow();

        stage.setScene(new Scene(anchorPane));
        stage.setTitle("Dashboard");
        stage.centerOnScreen();
    }



    @FXML
    void btnSaveOnAction(ActionEvent event) {
        String family_no = txtFamilyNo.getText();
        String event_name = txtEventName.getText();
        String date = txtDate.getText();
        String time = txtTime.getText();
        String discription = txtDiscription.getText();
        String estimated_budget = txtEstimatedBudget.getText();
        String cost = txtCost.getText();

        EventDto eventDto = new EventDto(family_no, event_name, date, time, discription, estimated_budget, cost);

        try {
            boolean isSaved = eventBO.saveEvent(eventDto);

            if (isSaved) {
                tblEvent.getItems().add(new EventTm(family_no, event_name, date, time, discription, estimated_budget, cost));
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        try {
            AnchorPane anchorPane = FXMLLoader.load(getClass().getResource("/view/event_table.fxml"));
            Scene scene = new Scene(anchorPane);
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Event Table");
            stage.centerOnScreen();
        } catch (IOException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }

        boolean isEventValid = validateEvent();
        if (isEventValid) {
            // perform save action
            new Alert(Alert.AlertType.CONFIRMATION, "Event saved!").show();
        }
    }


    private boolean validateEvent() {
        //Validate family_no
        String family_no = txtFamilyNo.getText();
        Pattern familyPattern = Pattern.compile("\\d{1,}");
        Matcher familyMatcher = familyPattern.matcher(family_no);
        boolean familyMatches = familyMatcher.matches();

        if (!familyMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid familyNo").show();
            return false;
        }


        //Validate EventName
        String event_name = txtEventName.getText();
        Pattern eventNamePattern = Pattern.compile("[A-Za-z]{3,}");
        Matcher eventNameMatcher = eventNamePattern.matcher(event_name);
        boolean eventNameMatches = eventNameMatcher.matches();

        if (!eventNameMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid eventName").show();
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
        String discription = txtDiscription.getText();
        Pattern discriptionPattern = Pattern.compile("[A-Za-z]{3,}");
        Matcher discriptionMatcher = discriptionPattern.matcher(discription);
        boolean discriptionMatches = discriptionMatcher.matches();

        if (!discriptionMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid discription").show();
            return false;
        }


        //Valid estimatedBudget
        String estimatedBudget = txtEstimatedBudget.getText();
        Pattern estimatedBudgetPattern = Pattern.compile("\\d{1,}");
        Matcher estimatedBudgetMatcher = estimatedBudgetPattern.matcher(estimatedBudget);
        boolean estimatedBudgetMatches = estimatedBudgetMatcher.matches();

        if (!estimatedBudgetMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid estimatedBudget").show();
            return false;
        }


        //Valid cost
        String cost = txtCost.getText();
        Pattern costPattern = Pattern.compile("\\d{1,}");
        Matcher costMatcher = costPattern.matcher(cost);
        boolean costMatches = costMatcher.matches();

        if (!costMatches) {
            new Alert(Alert.AlertType.ERROR, "Invalid cost").show();
            return false;
        }



        return true;
    }







    @FXML
    void btnUpdateOnAction(ActionEvent event) throws SQLException, ClassNotFoundException {
        String familyNo = txtFamilyNo.getText();
        String newEventName = txtEventName.getText();
        String newDate = txtDate.getText();
        String newTime = txtTime.getText();
        String newDisciption = txtDiscription.getText();
        String newEstimatedBugdet = txtEstimatedBudget.getText();
        String newCost = txtCost.getText();



        var dto = new EventDto(familyNo, newEventName, newDate, newTime, newDisciption, newEstimatedBugdet, newCost);

        boolean isUpdated = eventBO.updateEvent(dto);
        if (isUpdated) {
            new Alert(Alert.AlertType.CONFIRMATION, "Event updated!").show();
            clearFields();
        } else {
            new Alert(Alert.AlertType.ERROR, "Failed to update event").show();
        }
    }





    @FXML
    void txtSearchOnAction(ActionEvent event) {
        String familyNo = txtFamilyNo1.getText();

        try {
            EventDto eventDto = eventBO.searchEvent(familyNo);  // Use the instance to call the method

            if (eventDto != null) {
                txtFamilyNo.setText(eventDto.getFamilyNo());
                txtEventName.setText(eventDto.getEventName());
                txtDate.setText(eventDto.getDate());
                txtTime.setText(eventDto.getTime());
                txtDiscription.setText(eventDto.getDiscription());
                txtEstimatedBudget.setText(eventDto.getEstimatedBudget());
                txtCost.setText(eventDto.getCost());
            } else {
                new Alert(Alert.AlertType.INFORMATION, "Event not found").show();
            }
        } catch (SQLException | ClassNotFoundException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }



    @FXML
    void btnClearOnAction(ActionEvent event) {
        txtFamilyNo.setText("");
        txtEventName.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtDiscription.setText("");
        txtEstimatedBudget.setText("");
        txtCost.setText("");
        txtFamilyNo1.setText("");
    }




    private void clearFields() {
        txtFamilyNo.setText("");
        txtEventName.setText("");
        txtDate.setText("");
        txtTime.setText("");
        txtDiscription.setText("");
        txtEstimatedBudget.setText("");
        txtCost.setText("");
        txtFamilyNo1.setText("");
    }

    @FXML
    void btnReportOnAction(ActionEvent event) throws JRException, SQLException {
        try {
            InputStream resourceAsStream = getClass().getResourceAsStream("/report/event.jrxml");
            if (resourceAsStream != null) {
                JasperDesign jasperDesign = JRXmlLoader.load(resourceAsStream);
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport,
                        null,
                        DbConnection.getInstance().getConnection()
                );
                JasperViewer.viewReport(jasperPrint, false);
            } else {
                System.out.println("Resource not found: /report/event.jrxml");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}