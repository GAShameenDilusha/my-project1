package lk.ijse.fx.bo.custom;

import lk.ijse.fx.bo.SuperBO;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dto.EventDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface EventBO extends SuperBO {
     boolean saveEvent(EventDto dto) throws SQLException, ClassNotFoundException;
     List<EventDto> loadAllEvent() throws SQLException, ClassNotFoundException;
     boolean updateEvent(EventDto dto) throws SQLException, ClassNotFoundException;
     EventDto searchEvent(String familyNo) throws SQLException, ClassNotFoundException;
     boolean deleteEvent(String familyNo) throws SQLException, ClassNotFoundException;
}
