package lk.ijse.fx.entity;

import lk.ijse.fx.dto.FatherDto;

public class Father   {
    private String ChurchNo;
    private String ChurchFatherId;
    private String Name;
    private String StartDate;
    private String LeaveDate;

    public Father(String churchNo, String churchFatherId, String name, String startDate, String leaveDate) {
        this.ChurchNo = churchNo;
        this.ChurchFatherId = churchFatherId;
        this.Name = name;
        this.StartDate = startDate;
        this.LeaveDate = leaveDate;
    }

    public Father() {
    }

    public String getChurchNo() {
        return ChurchNo;
    }

    public void setChurchNo(String churchNo) {
        this.ChurchNo = churchNo;
    }

    public String getChurchFatherId() {
        return ChurchFatherId;
    }

    public void setChurchFatherId(String churchFatherId) {
        this.ChurchFatherId = churchFatherId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getStartDate() {
        return StartDate;
    }

    public void setStartDate(String startDate) {
        this.StartDate = startDate;
    }

    public String getLeaveDate() {
        return LeaveDate;
    }

    public void setLeaveDate(String leaveDate) {
        this.LeaveDate = leaveDate;
    }
}
