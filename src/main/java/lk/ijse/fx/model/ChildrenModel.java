package lk.ijse.fx.model;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.ChildrenDto;
import lk.ijse.fx.dto.RegistrationDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ChildrenModel {
    public boolean saveChildren(ChildrenDto dto) throws SQLException {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);


        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO children VALUES(?, ?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getFamilyNo());
        pstm.setString(2, dto.getChildId());
        pstm.setString(3, dto.getChildName());
        pstm.setString(4, dto.getBirthday());
        pstm.setString(5, dto.getComplimentaryDate());
        pstm.setDate(6, sqlDate);  // Use setDate to set the date



        boolean isSaved = pstm.executeUpdate() > 0;

        return isSaved;
    }


    public List<ChildrenDto> loadAllChildren() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM children";
        PreparedStatement pstm = connection.prepareStatement(sql);

        List<ChildrenDto> childrenList = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            childrenList.add(new ChildrenDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)

            ));
        }
        return childrenList;
    }





     public ChildrenDto searchCustomer(String childId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM children WHERE child_id=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, childId);

        ResultSet resultSet = pstm.executeQuery();

        ChildrenDto dto = null;

        if (resultSet.next()) {
            String children_childId = resultSet.getString(1);
            String children_familyNo = resultSet.getString(2);
            String children_childName = resultSet.getString(3);
            String children_birthday = resultSet.getString(4);
            String children_complimentaryDate = resultSet.getString(5);
            String children_date = resultSet.getString(6);

            dto = new ChildrenDto(children_childId, children_familyNo, children_childName, children_birthday, children_complimentaryDate, children_date);
        }
        return dto;
    }












        public boolean updateChildren(ChildrenDto dto) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE children SET family_no = ?, child_name = ?, birthday = ?, complimentary_date = ?, date = ? WHERE child_id = ?";
            try (PreparedStatement pstm = connection.prepareStatement(sql)) {
                pstm.setString(1, dto.getFamilyNo());
                pstm.setString(2, dto.getChildName());
                pstm.setString(3, dto.getBirthday());
                pstm.setString(4, dto.getComplimentaryDate());
                pstm.setDate(5, Date.valueOf(LocalDate.parse(dto.getDate()))); // Parse and use setDate
                pstm.setString(6, dto.getChildId());

                return pstm.executeUpdate() > 0;
            }
        }



    public boolean deleteChildren(String childId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM children WHERE child_id = ?";


        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, childId);
            return pstm.executeUpdate() > 0;
        }
    }

}



