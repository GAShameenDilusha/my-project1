package lk.ijse.fx.dto;

public class VisitDto {
    private String FamilyNo;
    private String ChurchFatherId;
    private String Date;
    private String Time;
    private String Discription;

    public VisitDto(){
    }

    public VisitDto(String familyNo, String churchFatherId, String date, String time, String discription) {
        this.FamilyNo = familyNo;
        this.ChurchFatherId = churchFatherId;
        this.Date = date;
        this.Time = time;
        this.Discription = discription;
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        FamilyNo = familyNo;
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

    @Override
    public String toString() {
        return "VisitDto{" +
                "FamilyNo='" + FamilyNo + '\'' +
                ", ChurchFatherId='" + ChurchFatherId + '\'' +
                ", Date='" + Date + '\'' +
                ", Time='" + Time + '\'' +
                ", Discription='" + Discription + '\'' +
                '}';
    }
}
