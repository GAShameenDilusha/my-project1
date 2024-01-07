package lk.ijse.fx.bo.custom;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dto.VisitDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface VisitBO {
     boolean saveVisit(VisitDto dto) throws SQLException, ClassNotFoundException;
     List<VisitDto> loadAllVisit() throws SQLException, ClassNotFoundException;
     boolean updateVisit(VisitDto dto) throws SQLException, ClassNotFoundException;
     VisitDto searchVisit(String familyNo) throws SQLException, ClassNotFoundException;
     boolean deleteVisit(String familyNo) throws SQLException, ClassNotFoundException;
}
