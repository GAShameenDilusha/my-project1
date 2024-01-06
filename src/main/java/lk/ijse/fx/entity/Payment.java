package lk.ijse.fx.entity;

public class Payment {
    private String ChurchNo;
    private String FamilyNo;
    private String DivisionNo;
    private String Fee;
    private String Date;

    public Payment(String churchNo, String familyNo, String divisionNo, String fee, String date) {
        this.ChurchNo = churchNo;
        this.FamilyNo = familyNo;
        this.DivisionNo = divisionNo;
        this.Fee = fee;
        this.Date = date;
    }

    public Payment() {
    }

    public String getChurchNo() {
        return ChurchNo;
    }

    public void setChurchNo(String churchNo) {
        this.ChurchNo = churchNo;
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        this.FamilyNo = familyNo;
    }

    public String getDivisionNo() {
        return DivisionNo;
    }

    public void setDivisionNo(String divisionNo) {
        this.DivisionNo = divisionNo;
    }

    public String getFee() {
        return Fee;
    }

    public void setFee(String fee) {
       this.Fee = fee;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}
