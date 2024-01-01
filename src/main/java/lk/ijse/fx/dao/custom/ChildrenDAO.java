package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.ChildrenDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ChildrenDAO {
         boolean saveChildren(ChildrenDto dto) throws SQLException;
         List<ChildrenDto> loadAllChildren() throws SQLException;

         ChildrenDto searchCustomer(String childId) throws SQLException;

         boolean updateChildren(ChildrenDto dto) throws SQLException;

         boolean deleteChildren(String childId) throws SQLException;

    }







