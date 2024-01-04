package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.RegistrationDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface RegistrationDAO {
         List<RegistrationDto> loadAllRegistration() throws SQLException, ClassNotFoundException;
         RegistrationDto searchCustomer(String familyNo) throws SQLException, ClassNotFoundException;
         boolean updateRegistration(RegistrationDto dto) throws SQLException, ClassNotFoundException;
         boolean deleteRegistration(String familyNo) throws SQLException, ClassNotFoundException;

         private void incrementDivisionCount(Connection connection, String divisionNo) throws SQLException {
         }
         private String getChurchNoForDivision(String divisionNo) {
        return null;
    }
        boolean saveRegistration(RegistrationDto dto) throws SQLException, ClassNotFoundException;
         int getNextFamilyNo() throws SQLException, ClassNotFoundException;

    }





