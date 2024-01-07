package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.EventBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.EventDAO;
import lk.ijse.fx.dto.EventDto;
import lk.ijse.fx.entity.Event;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventBOImpl implements EventBO {
    EventDAO eventDAO= (EventDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EVENT);
    @Override
    public boolean saveEvent(EventDto dto) throws SQLException, ClassNotFoundException {
        return eventDAO.save(new Event(dto.getFamilyNo(),dto.getEventName(),dto.getDate(),dto.getTime(),dto.getDiscription(),dto.getEstimatedBudget(),dto.getCost()));
    }



    @Override
    public List<EventDto> loadAllEvent() throws SQLException, ClassNotFoundException {
        ArrayList<Event> events=eventDAO.loadAll();
        ArrayList<EventDto> eventDtos=new ArrayList<>();
        for (Event event:events) {
            eventDtos.add(new EventDto(event.getFamilyNo(),event.getEventName(),event.getDate(),event.getTime(),event.getDiscription(),event.getEstimatedBudget(),event.getCost()));
        }
        return eventDtos;

    }



    @Override
    public boolean updateEvent(EventDto dto) throws SQLException, ClassNotFoundException {
        return eventDAO.update(new Event(dto.getFamilyNo(),dto.getEventName(),dto.getDate(),dto.getTime(),dto.getDiscription(),dto.getEstimatedBudget(),dto.getCost()));

    }






    @Override
    public EventDto searchEvent(String familyNo) throws SQLException, ClassNotFoundException {
        Event event=eventDAO.search(familyNo);
        EventDto eventDto=new EventDto(event.getFamilyNo(),event.getEventName(),event.getDate(),event.getTime(),event.getDiscription(),event.getEstimatedBudget(),event.getCost());
        return eventDto;
    }




    @Override
    public boolean deleteEvent(String familyNo) throws SQLException, ClassNotFoundException {
       return eventDAO.delete(familyNo);
    }
}
