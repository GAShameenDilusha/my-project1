package lk.ijse.fx.entity;

import lk.ijse.fx.dto.ChildrenDto;

public class Children   {
    private String FamilyNo;
    private String ChildId;
    private String ChildName;
    private String Birthday;
    private String ComplimentaryDate;
    private String Date;

    public Children(String familyNo, String childId, String childName, String birthday, String complimentaryDate, String date) {
        this.FamilyNo = familyNo;
        this.ChildId = childId;
        this.ChildName = childName;
        this.Birthday = birthday;
        this.ComplimentaryDate = complimentaryDate;
        this.Date = date;
    }

    public Children() {
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        this.FamilyNo = familyNo;
    }

    public String getChildId() {
        return ChildId;
    }

    public void setChildId(String childId) {
        this.ChildId = childId;
    }

    public String getChildName() {
        return ChildName;
    }

    public void setChildName(String childName) {
        this.ChildName = childName;
    }

    public String getBirthday() {
        return Birthday;
    }

    public void setBirthday(String birthday) {
        this.Birthday = birthday;
    }

    public String getComplimentaryDate() {
        return ComplimentaryDate;
    }

    public void setComplimentaryDate(String complimentaryDate) {
        this.ComplimentaryDate = complimentaryDate;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}
