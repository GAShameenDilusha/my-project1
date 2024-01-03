package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.EventDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface EventDAO {
         boolean saveEvent(EventDto dto) throws SQLException, ClassNotFoundException;
         List<EventDto> loadAllEvent() throws SQLException, ClassNotFoundException;
         boolean updateEvent(EventDto dto) throws SQLException, ClassNotFoundException;
         EventDto searchCustomer(String familyNo) throws SQLException, ClassNotFoundException;
         boolean deleteEvent(String familyNo) throws SQLException, ClassNotFoundException;


    }

