package lk.ijse.fx.model;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.VisitDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VisitModel {
    public boolean saveVisit(VisitDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        // Get the current date
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);


        String sql = "INSERT INTO visit VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getFamilyNo());
        pstm.setString(2, dto.getChurchFatherId());
        pstm.setDate(3, sqlDate);  // Use setDate to set the date
        pstm.setString(4, dto.getTime());
        pstm.setString(5, dto.getDiscription());




        boolean isSaved = pstm.executeUpdate() > 0;


        return isSaved;
    }










    public List<VisitDto> loadAllVisit() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM visit";
        PreparedStatement pstm = connection.prepareStatement(sql);


        List<VisitDto> visitList = new ArrayList<>();


        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            visitList.add(new VisitDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            ));
        }


        return visitList;
    }





    public boolean updateVisit(VisitDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "UPDATE visit SET church_father_id = ?, date = ?, time = ?, discription = ? WHERE family_no = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, dto.getChurchFatherId());
        pstm.setDate(2, Date.valueOf(LocalDate.parse(dto.getDate()))); // Parse and use setDate
        pstm.setString(3, dto.getTime());
        pstm.setString(4, dto.getDiscription());
        pstm.setString(5, dto.getFamilyNo());

        return pstm.executeUpdate() > 0;
    }






    public VisitDto searchCustomer(String familyNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM visit WHERE family_no=?";


        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, familyNo);


        ResultSet resultSet = pstm.executeQuery();


        VisitDto dto = null;


        if (resultSet.next()) {
            String visit_familyNo = resultSet.getString(1);
            String visit_churchFatherId = resultSet.getString(2);
            String visit_date = resultSet.getString(3);
            String vist_time = resultSet.getString(4);
            String visit_discription = resultSet.getString(5);

            dto = new VisitDto(visit_familyNo, visit_churchFatherId, visit_date, vist_time, visit_discription);
        }
        return dto;
    }


    public boolean deleteVisit(String familyNo) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM visit WHERE family_no = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, familyNo);
            return pstm.executeUpdate() > 0;
        }
    }

}



