package lk.ijse.fx.bo.custom;

import lk.ijse.fx.bo.SuperBO;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dto.ChildrenDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ChildrenBO extends SuperBO {
     boolean saveChildren(ChildrenDto dto) throws SQLException, ClassNotFoundException;
     List<ChildrenDto> loadAllChildren() throws SQLException, ClassNotFoundException;

     ChildrenDto searchChildren(String childId) throws SQLException, ClassNotFoundException;

     boolean updateChildren(ChildrenDto dto) throws SQLException, ClassNotFoundException;

     boolean deleteChildren(String childId) throws SQLException, ClassNotFoundException;
}
