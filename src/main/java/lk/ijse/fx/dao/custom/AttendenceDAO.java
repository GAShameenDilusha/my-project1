package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.AttendenceDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface AttendenceDAO {
           boolean saveAttendence(AttendenceDto dto) throws SQLException;
           List<AttendenceDto> loadAllAttendence() throws SQLException;
           boolean updateAttendence(AttendenceDto dto) throws SQLException;
           AttendenceDto searchCustomer(String familyNo) throws SQLException;
           boolean deleteAttendance(String familyNo) throws SQLException;
     }


