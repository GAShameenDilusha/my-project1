package lk.ijse.fx.dto;

public class PaymentDto {
    private String ChurchNo;
    private String FamilyNo;
    private String DivisionNo;
    private String Fee;
    private String Date;



    public PaymentDto(){
    }

    public PaymentDto(String churchNo, String familyNo, String divisionNo, String fee, String date) {
        this.ChurchNo = churchNo;
        this.FamilyNo = familyNo;
        this.DivisionNo = divisionNo;
        this.Fee = fee;
        this.Date = date;
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

    @Override
    public String toString() {
        return "PaymentDto{" +
                "ChurchNo='" + ChurchNo + '\'' +
                ", FamilyNo='" + FamilyNo + '\'' +
                ", DivisionNo='" + DivisionNo + '\'' +
                ", Fee='" + Fee + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }

}
