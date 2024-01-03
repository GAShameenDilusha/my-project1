package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.VisitDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface VisitDAO {
        public boolean saveVisit(VisitDto dto) throws SQLException, ClassNotFoundException;
        public List<VisitDto> loadAllVisit() throws SQLException, ClassNotFoundException;
        public boolean updateVisit(VisitDto dto) throws SQLException, ClassNotFoundException;
        public VisitDto searchCustomer(String familyNo) throws SQLException, ClassNotFoundException;
        public boolean deleteVisit(String familyNo) throws SQLException, ClassNotFoundException;




}
