package lk.ijse.fx.dto;

public class FatherDto {
    private String ChurchNo;
    private String ChurchFatherId;
    private String Name;
    private String StartDate;
    private String LeaveDate;

    public FatherDto(){}

    public FatherDto(String churchNo, String churchFatherId, String name, String startDate, String leaveDate) {
        this.ChurchNo = churchNo;
        this.ChurchFatherId = churchFatherId;
        this.Name = name;
        this.StartDate = startDate;
        this.LeaveDate = leaveDate;
    }

    public String getChurchNo() {
        return ChurchNo;
    }

    public void setChurchNo(String churchNo) {
        ChurchNo = churchNo;
    }

    public String getChurchFatherId() {
        return ChurchFatherId;
    }

    public void setChurchFatherId(String churchFatherId) {
        ChurchFatherId = churchFatherId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        StartDate = startDate;
    }

    public String getLeaveDate() {
        return LeaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        LeaveDate = leaveDate;
    }

    @Override
    public String toString() {
        return "FatherDto{" +
                "ChurchNo='" + ChurchNo + '\'' +
                ", ChurchFatherId='" + ChurchFatherId + '\'' +
                ", Name='" + Name + '\'' +
                ", StartDate='" + StartDate + '\'' +
                ", LeaveDate='" + LeaveDate + '\'' +
                '}';
    }
}
