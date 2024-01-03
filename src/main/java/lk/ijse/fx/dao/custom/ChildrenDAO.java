package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.ChildrenDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface ChildrenDAO {
         boolean saveChildren(ChildrenDto dto) throws SQLException, ClassNotFoundException;
         List<ChildrenDto> loadAllChildren() throws SQLException, ClassNotFoundException;

         ChildrenDto searchCustomer(String childId) throws SQLException, ClassNotFoundException;

         boolean updateChildren(ChildrenDto dto) throws SQLException, ClassNotFoundException;

         boolean deleteChildren(String childId) throws SQLException, ClassNotFoundException;

    }







