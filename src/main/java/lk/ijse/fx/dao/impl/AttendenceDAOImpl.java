package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.AttendenceDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendenceDAOImpl implements AttendenceDAO {
        public boolean saveAttendence(AttendenceDto dto) throws SQLException, ClassNotFoundException {
            // Get the current date
            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);

            return SQLUtil.execute("INSERT INTO attendence VALUES(?, ?, ?, ?, ?)",
                            dto.getFamilyNo(),dto.getPurpose(),dto.getArrangedTime(),dto.getLeaveTime(),sqlDate);
        }










        public List<AttendenceDto> loadAllAttendence() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM attendence");

            List<AttendenceDto> attendenceList = new ArrayList<>();
            while (resultSet.next()) {
                attendenceList.add(new AttendenceDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
            return attendenceList;

        }



        public boolean updateAttendence(AttendenceDto dto) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("UPDATE attendence SET purpose = ?, arranged_time = ?, leave_time = ?, date = ? WHERE family_no = ?"
                    ,dto.getPurpose(),dto.getArrangedTime(),dto.getLeaveTime(),dto.getDate(),dto.getFamilyNo());
        }






        public AttendenceDto searchCustomer(String familyNo) throws SQLException, ClassNotFoundException {
            AttendenceDto dto = null;

            ResultSet resultSet = SQLUtil.execute("SELECT * FROM attendence WHERE family_no=?");


            if (resultSet.next()) {
                String attendence_familyNo = resultSet.getString(1);
                String attendence_purpose = resultSet.getString(2);
                String attendence_arrangedTime = resultSet.getString(3);
                String attendence_leaveTime = resultSet.getString(4);
                String attendence_date = resultSet.getString(5);

                dto = new AttendenceDto(attendence_familyNo, attendence_purpose, attendence_arrangedTime, attendence_leaveTime, attendence_date);
            }
            return dto;

        }


        public boolean deleteAttendance(String familyNo) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("DELETE FROM attendence WHERE family_no = ?");
        }
}



