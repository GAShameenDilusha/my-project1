package lk.ijse.fx.entity;

import lk.ijse.fx.dto.AttendenceDto;

public class Attendence  {
    private String FamilyNo;
    private String Purpose;
    private String ArrangedTime;
    private String LeaveTime;
    private String Date;

    public Attendence(String familyNo, String purpose, String arrangedTime, String leaveTime, String date) {
        this.FamilyNo = familyNo;
        this.Purpose = purpose;
        this.ArrangedTime = arrangedTime;
        this.LeaveTime = leaveTime;
        this.Date = date;
    }

    public Attendence() {
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        this.FamilyNo = familyNo;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        this.Purpose = purpose;
    }

    public String getArrangedTime() {
        return ArrangedTime;
    }

    public void setArrangedTime(String arrangedTime) {
        this.ArrangedTime = arrangedTime;
    }

    public String getLeaveTime() {
        return LeaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        this.LeaveTime = leaveTime;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        this.Date = date;
    }
}
