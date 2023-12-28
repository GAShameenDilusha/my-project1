package lk.ijse.fx.model;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.AttendenceDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AttendenceModel {
    public boolean saveAttendence(AttendenceDto dto) throws SQLException {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);

        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO attendence VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getFamilyNo());
        pstm.setString(2, dto.getPurpose());
        pstm.setString(3, dto.getArrangedTime());
        pstm.setString(4, dto.getLeaveTime());
        pstm.setDate(5, sqlDate);  // Use setDate to set the date



        boolean isSaved = pstm.executeUpdate() > 0;


        return isSaved;
    }










    public List<AttendenceDto> loadAllAttendence() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM attendence";
        PreparedStatement pstm = connection.prepareStatement(sql);


        List<AttendenceDto> attendenceList = new ArrayList<>();


        ResultSet resultSet = pstm.executeQuery();
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



    public boolean updateAttendence(AttendenceDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE attendence SET purpose = ?, arranged_time = ?, leave_time = ?, date = ? WHERE family_no = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getPurpose());
        pstm.setString(2, dto.getArrangedTime());
        pstm.setString(3, dto.getLeaveTime());
        pstm.setDate(2, Date.valueOf(LocalDate.parse(dto.getDate()))); // Parse and use setDate
        pstm.setString(5, dto.getFamilyNo());


        return pstm.executeUpdate() > 0;
    }






    public AttendenceDto searchCustomer(String familyNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM attendence WHERE family_no=?";


        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, familyNo);


        ResultSet resultSet = pstm.executeQuery();


        AttendenceDto dto = null;


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


    public boolean deleteAttendance(String familyNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "DELETE FROM attendence WHERE family_no = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, familyNo);

            return pstm.executeUpdate() > 0;
        }
    }

}
