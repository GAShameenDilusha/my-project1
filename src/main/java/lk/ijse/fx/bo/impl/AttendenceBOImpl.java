package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.AttendenceBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dto.AttendenceDto;
import lk.ijse.fx.entity.Attendence;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AttendenceBOImpl implements AttendenceBO {
    private AttendenceDAO attendenceDAO = (AttendenceDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ATTENDENCE);

    @Override
    public boolean saveAttendence(AttendenceDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO attendence VALUES(?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getFamilyNo(), dto.getPurpose(), dto.getArrangedTime(), dto.getLeaveTime(), dto.getDate());
    }

    @Override
    public List<AttendenceDto> loadAllAttendence() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM attendence";
        List<AttendenceDto> attendenceList = new ArrayList<>();

        try {
            ResultSet resultSet = SQLUtil.execute(sql);

            while (resultSet.next()) {
                attendenceList.add(new AttendenceDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
        } finally {
            // Close any resources if necessary
        }

        return attendenceList;
    }

    @Override
    public boolean updateAttendence(AttendenceDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE attendence SET purpose = ?, arranged_time = ?, leave_time = ?, date = ? WHERE family_no = ?";
        return SQLUtil.execute(sql, dto.getPurpose(), dto.getArrangedTime(), dto.getLeaveTime(), dto.getDate(), dto.getFamilyNo());
    }

    @Override
    public AttendenceDto searchAttendence(String familyNo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM attendence WHERE family_no=?";
        ResultSet resultSet = SQLUtil.execute(sql, familyNo);

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

    @Override
    public boolean deleteAttendence(String familyNo) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM attendence WHERE family_no = ?";
        return SQLUtil.execute(sql, familyNo);
    }
}
