package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.EventDAO;
import lk.ijse.fx.dto.EventDto;
import lk.ijse.fx.entity.Attendence;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventDAOImpl implements EventDAO {
        @Override
        public boolean save(EventDto dto) throws SQLException, ClassNotFoundException {
            // Get the current date
            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);

            return SQLUtil.execute("INSERT INTO event VALUES(?, ?, ?, ?, ?, ?, ?)",
                    dto.getFamilyNo(), dto.getFamilyNo(), dto.getEventName(), sqlDate, dto.getTime(), dto.getDiscription(), dto.getEstimatedBudget(), dto.getCost());

        }




        @Override
        public List<EventDto> loadAll() throws SQLException, ClassNotFoundException {
            List<EventDto> eventList = new ArrayList<>();

            ResultSet resultSet = SQLUtil.execute("SELECT * FROM event");
            while (resultSet.next()) {
                eventList.add(new EventDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7)

                ));
            }
            return eventList;
        }

        @Override
        public boolean update(EventDto dto) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("UPDATE event SET event_name = ?, date = ?, time = ?, discription = ?, estimated_budget = ?, cost = ? WHERE family_no = ?"
                    ,dto.getEventName(),dto.getDate(),dto.getTime(),dto.getDiscription(),dto.getEstimatedBudget(),dto.getCost(),dto.getFamilyNo());
        }





        @Override
        public EventDto search(String familyNo) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM event WHERE family_no=?");

            EventDto dto = null;

            if (resultSet.next()) {
                String event_familyNo = resultSet.getString(1);
                String event_eventName = resultSet.getString(2);
                String event_date = resultSet.getString(3);
                String event_time = resultSet.getString(4);
                String event_discription = resultSet.getString(5);
                String event_estimatedBudget = resultSet.getString(6);
                String event_cost = resultSet.getString(7);

                dto = new EventDto(event_familyNo, event_eventName, event_date, event_time, event_discription, event_estimatedBudget, event_cost);
            }
            return dto;
        }




        @Override
        public boolean delete(String familyNo) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("DELETE FROM event WHERE family_no = ?");
            }

}




