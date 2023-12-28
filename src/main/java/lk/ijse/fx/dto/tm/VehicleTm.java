package lk.ijse.fx.dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;

public class VehicleTm {
    private String ChurchFatherId;
    private String Date;
    private String Category;
    private String Discription;
    private JFXButton btnDelete;


    {
        // Initialize the delete button
        btnDelete = new JFXButton("Delete");
        btnDelete.setCursor(javafx.scene.Cursor.HAND);
        btnDelete.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
        btnDelete.setPrefWidth(100);
        btnDelete.setPrefHeight(30);
    }


    public VehicleTm(){
    }


    public VehicleTm(String churchFatherId, String date, String category, String discription, JFXButton btn) {
        this.ChurchFatherId = churchFatherId;
        this.Date = date;
        this.Category = category;
        this.Discription = discription;
        this.btnDelete = btn;
    }
    public String getChurchFatherId() {
        return ChurchFatherId;
    }

    public void setChurchFatherId(String churchFatherId) {
        ChurchFatherId = churchFatherId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        Discription = discription;
    }



    public JFXButton getBtnDelete() {
        return btnDelete;
    }

    @Override
    public String toString() {
        return "VehicleTm{" +
                "ChuchFatherId='" + ChurchFatherId + '\'' +
                ", Date='" + Date + '\'' +
                ", Category='" + Category + '\'' +
                ", Discription='" + Discription + '\'' +
                '}';
    }
}
