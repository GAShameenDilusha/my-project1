package lk.ijse.fx.dto;

public class AttendenceDto {
    private String FamilyNo;
    private String Purpose;
    private String ArrangedTime;
    private String LeaveTime;
    private String Date;

    public AttendenceDto(){
    }

    public AttendenceDto(String familyNo, String purpose, String arrangedTime, String leaveTime, String date) {
        this.FamilyNo = familyNo;
        this.Purpose = purpose;
        this.ArrangedTime = arrangedTime;
        this.LeaveTime = leaveTime;
        this.Date = date;
    }

    public String getFamilyNo() {
        return FamilyNo;
    }

    public void setFamilyNo(String familyNo) {
        FamilyNo = familyNo;
    }

    public String getPurpose() {
        return Purpose;
    }

    public void setPurpose(String purpose) {
        Purpose = purpose;
    }

    public String getArrangedTime() {
        return ArrangedTime;
    }

    public void setArrangedTime(String arrangedTime) {
        ArrangedTime = arrangedTime;
    }

    public String getLeaveTime() {
        return LeaveTime;
    }

    public void setLeaveTime(String leaveTime) {
        LeaveTime = leaveTime;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    @Override
    public String toString() {
        return "AttendenceDto{" +
                "FamilyNo='" + FamilyNo + '\'' +
                ", Purpose='" + Purpose + '\'' +
                ", ArrangedTime='" + ArrangedTime + '\'' +
                ", LeaveTime='" + LeaveTime + '\'' +
                ", Date='" + Date + '\'' +
                '}';
    }
}
