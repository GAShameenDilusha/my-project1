package lk.ijse.fx.dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;

public class AttendenceTm {
    private String FamilyNo;
    private String Purpose;
    private String ArrangedTime;
    private String LeaveTime;
    private String Date;
    private JFXButton btnDelete;


    {
        // Initialize the delete button
        btnDelete = new JFXButton("Delete");
        btnDelete.setCursor(javafx.scene.Cursor.HAND);
        btnDelete.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
        btnDelete.setPrefWidth(100);
        btnDelete.setPrefHeight(30);
    }


    public AttendenceTm(){
    }

    public AttendenceTm(String familyNo, String purpose, String arrangedTime, String leaveTime, String date, JFXButton btn) {
        this.FamilyNo = familyNo;
        this.Purpose = purpose;
        this.ArrangedTime = arrangedTime;
        this.LeaveTime = leaveTime;
        this.Date = date;
        this.btnDelete = btn;
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        FamilyNo = familyNo;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getArrangedTime() {
        return ArrangedTime;
    }

    public void setArrangedTime(String arrangedTime) {
        ArrangedTime = arrangedTime;
    }

    public String getLeaveTime() {
        return LeaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        LeaveTime = leaveTime;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public JFXButton getBtnDelete() {
        return btnDelete;
    }


    @Override
    public String toString() {
        return "AttendenceTm{" +
                "FamilyNo='" + FamilyNo + '\'' +
                ", Purpose='" + Purpose + '\'' +
                ", ArrangedTime='" + ArrangedTime + '\'' +
                ", LeaveTime='" + LeaveTime + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
