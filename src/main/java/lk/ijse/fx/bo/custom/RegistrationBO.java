package lk.ijse.fx.bo.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.RegistrationDto;

import java.sql.*;
import java.time.LocalDate;

public interface RegistrationBO {
         boolean deleteRegistration(String familyNo) throws SQLException;
         private void incrementDivisionCount(Connection connection, String divisionNo) throws SQLException;
         private String getChurchNoForDivision(String divisionNo);
         public boolean saveRegistration(RegistrationDto dto) throws SQLException;
         int getNextFamilyNo() throws SQLException;


}
