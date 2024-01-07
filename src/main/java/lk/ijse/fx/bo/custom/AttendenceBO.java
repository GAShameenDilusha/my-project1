package lk.ijse.fx.bo.custom;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dto.AttendenceDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface AttendenceBO {
     boolean saveAttendence(AttendenceDto dto) throws SQLException, ClassNotFoundException;
     List<AttendenceDto> loadAllAttendence() throws SQLException, ClassNotFoundException;
     boolean updateAttendence(AttendenceDto dto) throws SQLException, ClassNotFoundException;
     AttendenceDto searchAttendence(String familyNo) throws SQLException, ClassNotFoundException;
     boolean deleteAttendence(String familyNo) throws SQLException, ClassNotFoundException;
}
