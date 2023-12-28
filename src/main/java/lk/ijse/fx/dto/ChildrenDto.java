package lk.ijse.fx.dto;

public class ChildrenDto {
    private String FamilyNo;
    private String ChildId;
    private String ChildName;
    private String Birthday;
    private String ComplimentaryDate;
    private String Date;




    public ChildrenDto(){}

    public ChildrenDto(String familyNo, String childId, String childName, String birthday, String complimentaryDate, String date) {
        this.FamilyNo = familyNo;
        this.ChildId = childId;
        this.ChildName = childName;
        this.Birthday = birthday;
        this.ComplimentaryDate = complimentaryDate;
        this.Date = date;
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        FamilyNo = familyNo;
    }

    public String getChildId() {
        return ChildId;
    }

    public void setChildId(String childId) {
        ChildId = childId;
    }

    public String getChildName() {
        return ChildName;
    }

    public void setChildName(String childName) {
        ChildName = childName;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        Birthday = birthday;
    }

    public String getComplimentaryDate() {
        return ComplimentaryDate;
    }

    public void setComplimentaryDate(String complimentaryDate) {
        ComplimentaryDate = complimentaryDate;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "ChildrenDto{" +
                "FamilyNo='" + FamilyNo + '\'' +
                ", ChildId='" + ChildId + '\'' +
                ", ChildName='" + ChildName + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", ComplimentaryDate='" + ComplimentaryDate + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
