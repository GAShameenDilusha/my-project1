package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.EventBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.EventDAO;
import lk.ijse.fx.dto.EventDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EventBOImpl implements EventBO {
    private EventDAO eventDAO = (EventDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EVENT);

    @Override
    public boolean saveEvent(EventDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO event VALUES(?, ?, ?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getFamilyNo(), dto.getEventName(), dto.getDate(), dto.getTime(), dto.getDiscription(), dto.getEstimatedBudget(), dto.getCost());
    }

    @Override
    public List<EventDto> loadAllEvent() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM event";
        List<EventDto> eventList = new ArrayList<>();

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
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
        }

        return eventList;
    }

    @Override
    public boolean updateEvent(EventDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE event SET event_name = ?, date = ?, time = ?, discription = ?, estimated_budget = ?, cost = ? WHERE family_no = ?";
        return SQLUtil.execute(sql, dto.getEventName(), dto.getDate(), dto.getTime(), dto.getDiscription(), dto.getEstimatedBudget(), dto.getCost(), dto.getFamilyNo());
    }

    @Override
    public EventDto searchEvent(String familyNo) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM event WHERE family_no=?";
        ResultSet resultSet = SQLUtil.execute(sql, familyNo);

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
    public boolean deleteEvent(String familyNo) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM event WHERE family_no = ?";
        return SQLUtil.execute(sql, familyNo);
    }
}
