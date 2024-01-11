package lk.ijse.fx.entity;

import lk.ijse.fx.dto.VisitDto;

public class Visit   {
    private String FamilyNo;
    private String ChurchFatherId;
    private String Date;
    private String Time;
    private String Discription;


    public Visit(String familyNo, String churchFatherId, String date, String time, String discription) {
        this.FamilyNo = familyNo;
        this.ChurchFatherId = churchFatherId;
        this.Date = date;
        this.Time = time;
        this.Discription = discription;
    }

    public Visit() {
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        this.FamilyNo = familyNo;
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

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }

    public String getDiscription() {
        return Discription;
    }

    public void setDiscription(String discription) {
        this.Discription = discription;
    }
}
