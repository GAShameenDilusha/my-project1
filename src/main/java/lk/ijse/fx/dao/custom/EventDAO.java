package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.EventDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface EventDAO {
         boolean saveEvent(EventDto dto) throws SQLException;
         List<EventDto> loadAllEvent() throws SQLException;
         boolean updateEvent(EventDto dto) throws SQLException;
         EventDto searchCustomer(String familyNo) throws SQLException;
         boolean deleteEvent(String familyNo) throws SQLException;


    }

