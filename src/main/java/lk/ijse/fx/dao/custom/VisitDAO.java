package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.VisitDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface VisitDAO {
        public boolean saveVisit(VisitDto dto) throws SQLException;
        public List<VisitDto> loadAllVisit() throws SQLException;
        public boolean updateVisit(VisitDto dto) throws SQLException;
        public VisitDto searchCustomer(String familyNo) throws SQLException;
        public boolean deleteVisit(String familyNo) throws SQLException;




}
