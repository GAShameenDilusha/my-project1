package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.AttendenceDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface AttendenceDAO {
           boolean saveAttendence(AttendenceDto dto) throws SQLException, ClassNotFoundException;
           List<AttendenceDto> loadAllAttendence() throws SQLException, ClassNotFoundException;
           boolean updateAttendence(AttendenceDto dto) throws SQLException, ClassNotFoundException;
           AttendenceDto searchCustomer(String familyNo) throws SQLException, ClassNotFoundException;
           boolean deleteAttendance(String familyNo) throws SQLException, ClassNotFoundException;
     }


