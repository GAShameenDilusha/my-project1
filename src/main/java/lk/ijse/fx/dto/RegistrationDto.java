package lk.ijse.fx.dto;

public class RegistrationDto {
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

    public RegistrationDto(){
    }

    public RegistrationDto(String churchNo, String divisionNo, String familyNo, String fatherId, String motherId, String fatherName, String motherName, String address, String tel, String date) {
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

    @Override
    public String toString() {
        return "RegistrationDto{" +
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
