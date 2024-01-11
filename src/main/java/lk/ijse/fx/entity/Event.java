package lk.ijse.fx.entity;

import lk.ijse.fx.dto.EventDto;

public class Event   {
    private String FamilyNo;
    private String EventName;
    private String Date;
    private String Time;
    private String Discription;
    private String EstimatedBudget;
    private String Cost;

    public Event(String familyNo, String eventName, String date, String time, String discription, String estimatedBudget, String cost) {
        this.FamilyNo = familyNo;
        this.EventName = eventName;
        this.Date = date;
        this.Time = time;
        this.Discription = discription;
        this.EstimatedBudget = estimatedBudget;
        this.Cost = cost;
    }

    public Event() {
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        this.FamilyNo = familyNo;
    }

    public String getEventName() {
        return EventName;
    }

    public void setEventName(String eventName) {
        this.EventName = eventName;
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

    public String getEstimatedBudget() {
        return EstimatedBudget;
    }

    public void setEstimatedBudget(String estimatedBudget) {
        this.EstimatedBudget = estimatedBudget;
    }

    public String getCost() {
        return Cost;
    }

    public void setCost(String cost) {
        this.Cost = cost;
    }
}
