package lk.ijse.fx.model;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.EventDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EventModel {
    public boolean saveEvent(EventDto dto) throws SQLException {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);


        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO event VALUES(?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getFamilyNo());
        pstm.setString(2, dto.getEventName());
        pstm.setDate(3, sqlDate);  // Use setDate to set the date
        pstm.setString(4, dto.getTime());
        pstm.setString(5, dto.getDiscription());
        pstm.setString(6, dto.getEstimatedBudget());
        pstm.setString(7, dto.getCost());


        boolean isSaved = pstm.executeUpdate() > 0;


        return isSaved;
    }










    public List<EventDto> loadAllEvent() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM event";
        PreparedStatement pstm = connection.prepareStatement(sql);


        List<EventDto> eventList = new ArrayList<>();


        ResultSet resultSet = pstm.executeQuery();
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




    public boolean updateEvent(EventDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE event SET event_name = ?, date = ?, time = ?, discription = ?, estimated_budget = ?, cost = ? WHERE family_no = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getEventName());
        pstm.setDate(2, Date.valueOf(LocalDate.parse(dto.getDate()))); // Parse and use setDate
        pstm.setString(3, dto.getTime());
        pstm.setString(4, dto.getDiscription());
        pstm.setString(5, dto.getEstimatedBudget());
        pstm.setString(6, dto.getCost());
        pstm.setString(7, dto.getFamilyNo());


        return pstm.executeUpdate() > 0;
    }







    public EventDto searchCustomer(String familyNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM event WHERE family_no=?";


        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, familyNo);


        ResultSet resultSet = pstm.executeQuery();


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

    public boolean deleteEvent(String familyNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM event WHERE family_no = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, familyNo);
            return pstm.executeUpdate() > 0;
        }
    }


}