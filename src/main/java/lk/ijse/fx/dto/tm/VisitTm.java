package lk.ijse.fx.dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;

public class VisitTm {
    private String familyNo;
    private String churchFatherId;
    private String date;
    private String time;
    private String description;

    private JFXButton btnDelete;

    {
        // Initialize the delete button
        btnDelete = new JFXButton("Delete");
        btnDelete.setCursor(javafx.scene.Cursor.HAND);
        btnDelete.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
        btnDelete.setPrefWidth(100);
        btnDelete.setPrefHeight(30);
    }

    public VisitTm() {
    }

    public VisitTm(String familyNo, String churchFatherId, String date, String time, String description, JFXButton btn) {
        this.familyNo = familyNo;
        this.churchFatherId = churchFatherId;
        this.date = date;
        this.time = time;
        this.description = description;
        this.btnDelete = btn;
    }

    public String getFamilyNo() {
        return familyNo;
    }

    public void setFamilyNo(String familyNo) {
        this.familyNo = familyNo;
    }

    public String getChurchFatherId() {
        return churchFatherId;
    }

    public void setChurchFatherId(String churchFatherId) {
        this.churchFatherId = churchFatherId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public JFXButton getBtnDelete() {
        return btnDelete;
    }

    @Override
    public String toString() {
        return "VisitTm{" +
                "familyNo='" + familyNo + '\'' +
                ", churchFatherId='" + churchFatherId + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", description='" + description + '\'' +

                '}';
    }
}
