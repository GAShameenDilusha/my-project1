package lk.ijse.fx.dto.tm;

import javafx.scene.control.Button;

public class ChildrenTm {
    private String FamilyNo;
    private String ChildId;
    private String ChildName;
    private String Birthday;
    private String ComplimentaryDate;
    private String Date;
    private Button btn;




    public ChildrenTm(){
    }

    public ChildrenTm(String familyNo, String childId, String childName, String birthday, String complimentaryDate, String date, Button btn) {
        this.FamilyNo = familyNo;
        this.ChildId = childId;
        this.ChildName = childName;
        this.Birthday = birthday;
        this.ComplimentaryDate = complimentaryDate;
        this.Date = date;
        this.btn = btn;
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

    public Button getBtn() {
        return btn;
    }

    public void setBtn(Button btn) {
        this.btn = btn;
    }

    @Override
    public String toString() {
        return "ChildrenTm{" +
                "FamilyNo='" + FamilyNo + '\'' +
                ", ChildId='" + ChildId + '\'' +
                ", ChildName='" + ChildName + '\'' +
                ", Birthday='" + Birthday + '\'' +
                ", ComplimentaryDate='" + ComplimentaryDate + '\'' +
                ", Date='" + Date + '\'' +
                ", btn=" + btn +
                '}';
    }
}