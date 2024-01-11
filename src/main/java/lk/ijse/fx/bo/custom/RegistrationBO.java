package lk.ijse.fx.bo.custom;

import lk.ijse.fx.bo.SuperBO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.RegistrationDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public interface RegistrationBO extends SuperBO {
      boolean deleteRegistration(String familyNo) throws SQLException, ClassNotFoundException;

      void incrementDivisionCount(Connection connection, String divisionNo) throws SQLException;

      String getChurchNoForDivision(String divisionNo);

      boolean saveRegistration(RegistrationDto dto) throws SQLException;

      int getNextFamilyNo() throws SQLException, ClassNotFoundException;

      RegistrationDto searchRegistration(String familyNo) throws SQLException, ClassNotFoundException;

      boolean updateRegistration(RegistrationDto dto) throws SQLException, ClassNotFoundException;

      List<RegistrationDto> loadAllRegistration() throws SQLException, ClassNotFoundException;

}
