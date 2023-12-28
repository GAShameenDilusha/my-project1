package lk.ijse.fx.dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;

public class PaymentTm {
    private String ChurchNo;
    private String FamilyNo;
    private String DivisionNo;
    private String Fee;
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


    public PaymentTm(){}

    public PaymentTm(String churchNo, String familyNo, String divisionNo, String fee, String date, JFXButton btn) {
        this.ChurchNo = churchNo;
        this.FamilyNo = familyNo;
        this.DivisionNo = divisionNo;
        this.Fee = fee;
        this.Date = date;
        this.btnDelete = btn;
    }

    public String getChurchNo() {
        return ChurchNo;
    }

    public void setChurchNo(String churchNo) {
        ChurchNo = churchNo;
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        FamilyNo = familyNo;
    }

    public String getDivisionNo() {
        return DivisionNo;
    }

    public void setDivisionNo(String divisionNo) {
        DivisionNo = divisionNo;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String fee) {
        Fee = fee;
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
        return "PaymentTm{" +
                "ChurchNo='" + ChurchNo + '\'' +
                ", FamilyNo='" + FamilyNo + '\'' +
                ", DivisionNo='" + DivisionNo + '\'' +
                ", Fee='" + Fee + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
