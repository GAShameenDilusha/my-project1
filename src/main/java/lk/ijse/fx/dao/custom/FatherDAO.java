package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.FatherDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface FatherDAO {
     boolean saveFather(FatherDto dto) throws SQLException, SQLException;
     List<FatherDto> loadAllFather() throws SQLException;
     boolean updateFather(FatherDto dto) throws SQLException;
     FatherDto searchCustomer(String churchFatherId) throws SQLException;
     boolean deleteFather(String churchFatherId) throws SQLException;

}