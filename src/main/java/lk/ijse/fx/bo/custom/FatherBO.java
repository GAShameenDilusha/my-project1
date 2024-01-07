package lk.ijse.fx.bo.custom;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dto.FatherDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface FatherBO {
     boolean saveFather(FatherDto dto) throws SQLException, SQLException, ClassNotFoundException;
     List<FatherDto> loadAllFather() throws SQLException, ClassNotFoundException;
     boolean updateFather(FatherDto dto) throws SQLException, ClassNotFoundException;
     FatherDto searchFather(String churchFatherId) throws SQLException, ClassNotFoundException;
     boolean deleteFather(String churchFatherId) throws SQLException, ClassNotFoundException;
}
