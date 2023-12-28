package lk.ijse.fx.dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;

public class EventTm {
    private String FamilyNo;
    private String EventName;
    private String Date;
    private String Time;
    private String Discription;
    private String EstimatedBudget;
    private String Cost;
    private JFXButton btnDelete;


    {
        // Initialize the delete button
        btnDelete = new JFXButton("Delete");
        btnDelete.setCursor(javafx.scene.Cursor.HAND);
        btnDelete.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
        btnDelete.setPrefWidth(100);
        btnDelete.setPrefHeight(30);
    }


    public EventTm(){
    }

    public EventTm(String familyNo, String eventName, String date, String time, String discription, String estimatedBudget, String cost, JFXButton btn) {
        this.FamilyNo = familyNo;
        this.EventName = eventName;
        this.Date = date;
        this.Time = time;
        this.Discription = discription;
        this.EstimatedBudget = estimatedBudget;
        this.Cost = cost;
        this.btnDelete = btn;
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        FamilyNo = familyNo;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        EventName = eventName;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }

    public String getEstimatedBudget() {
        return EstimatedBudget;
    }

    public void setEstimatedBudget(String estimatedBudget) {
        EstimatedBudget = estimatedBudget;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        Cost = cost;
    }

    public JFXButton getBtnDelete() {
        return btnDelete;
    }


    @Override
    public String toString() {
        return "EventTm{" +
                "FamilyNo='" + FamilyNo + '\'' +
                ", EventName='" + EventName + '\'' +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                ", Discription='" + Discription + '\'' +
                ", EstimatedBudget='" + EstimatedBudget + '\'' +
                ", Cost='" + Cost + '\'' +
                '}';
    }
}