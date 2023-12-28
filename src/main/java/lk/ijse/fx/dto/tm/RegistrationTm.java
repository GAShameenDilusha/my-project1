package lk.ijse.fx.dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;

public class RegistrationTm {
    private String ChurchNo;
    private String DivisionNo;
    private String FamilyNo;
    private String FatherId;
    private String MotherId;
    private String FatherName;
    private String MotherName;
    private String Address;
    private String Tel;
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



    public RegistrationTm(){
    }

    public RegistrationTm(String churchNo, String divisionNo, String familyNo, String fatherId, String motherId, String fatherName, String motherName, String address, String tel, String date, JFXButton btn) {
        this.ChurchNo = churchNo;
        this.DivisionNo = divisionNo;
        this.FamilyNo = familyNo;
        this.FatherId = fatherId;
        this.MotherId = motherId;
        this.FatherName = fatherName;
        this.MotherName = motherName;
        this.Address = address;
        this.Tel = tel;
        this.Date = date;
        this.btnDelete = btn;
    }

    public String getChurchNo() {
        return ChurchNo;
    }

    public void setChurchNo(String churchNo) {
        ChurchNo = churchNo;
    }

    public String getDivisionNo() {
        return DivisionNo;
    }

    public void setDivisionNo(String divisionNo) {
        DivisionNo = divisionNo;
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        FamilyNo = familyNo;
    }

    public String getFatherId() {
        return FatherId;
    }

    public void setFatherId(String fatherId) {
        FatherId = fatherId;
    }

    public String getMotherId() {
        return MotherId;
    }

    public void setMotherId(String motherId) {
        MotherId = motherId;
    }

    public String getFatherName() {
        return FatherName;
    }

    public void setFatherName(String fatherName) {
        FatherName = fatherName;
    }

    public String getMotherName() {
        return MotherName;
    }

    public void setMotherName(String motherName) {
        MotherName = motherName;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getTel() {
        return Tel;
    }

    public void setTel(String tel) {
        Tel = tel;
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
        return "RegistrationTm{" +
                "ChurchNo='" + ChurchNo + '\'' +
                ", DivisionNo='" + DivisionNo + '\'' +
                ", FamilyNo='" + FamilyNo + '\'' +
                ", FatherId='" + FatherId + '\'' +
                ", MotherId='" + MotherId + '\'' +
                ", FatherName='" + FatherName + '\'' +
                ", MotherName='" + MotherName + '\'' +
                ", Address='" + Address + '\'' +
                ", Tel='" + Tel + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }


}
