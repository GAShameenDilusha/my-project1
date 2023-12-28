package lk.ijse.fx.dto.tm;

import com.jfoenix.controls.JFXButton;
import javafx.scene.control.Button;
public class FatherTm {
    private String ChurchNo;
    private String ChurchFatherId;
    private String Name;
    private String StartDate;
    private String LeaveDate;
   private JFXButton btnDelete;


   {
      // Initialize the delete button
      btnDelete = new JFXButton("Delete");
      btnDelete.setCursor(javafx.scene.Cursor.HAND);
      btnDelete.setStyle("-fx-background-color: #ff0000; -fx-text-fill: #ffffff");
      btnDelete.setPrefWidth(100);
      btnDelete.setPrefHeight(30);
   }


   public FatherTm(){}

   public FatherTm(String churchNo, String churchFatherId, String name, String startDate, String leaveDate, JFXButton btn) {
      this.ChurchNo = churchNo;
      this.ChurchFatherId = churchFatherId;
      this.Name = name;
      this.StartDate = startDate;
      this.LeaveDate = leaveDate;
      this.btnDelete = btn;
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

   public JFXButton getBtnDelete() {
      return btnDelete;
   }


   @Override
   public String toString() {
      return "FatherTm{" +
              "ChurchNo='" + ChurchNo + '\'' +
              ", ChurchFatherId='" + ChurchFatherId + '\'' +
              ", Name='" + Name + '\'' +
              ", StartDate='" + StartDate + '\'' +
              ", LeaveDate='" + LeaveDate + '\'' +
              '}';
   }
}
