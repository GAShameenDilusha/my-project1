package lk.ijse.fx.entity;

import lk.ijse.fx.dto.VehicleDto;

public class Vehicle   {
    private String ChurchFatherId;
    private String Date;
    private String Category;
    private String Discription;


    public Vehicle(String churchFatherId, String date, String category, String discription) {
        ChurchFatherId = churchFatherId;
        Date = date;
        Category = category;
        Discription = discription;
    }

    public Vehicle() {
    }

    public String getChurchFatherId() {
        return ChurchFatherId;
    }

    public void setChurchFatherId(String churchFatherId) {
        this.ChurchFatherId = churchFatherId;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        this.Category = category;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        this.Discription = discription;
    }
}
