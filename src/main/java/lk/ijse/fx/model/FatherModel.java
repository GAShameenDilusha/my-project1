package lk.ijse.fx.model;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.FatherDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FatherModel {
    public boolean saveFather(FatherDto dto) throws SQLException, SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO father VALUES(?, ?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getChurchNo());
        pstm.setString(2, dto.getChurchFatherId());
        pstm.setString(3, dto.getName());
        pstm.setString(4, dto.getStartDate());
        pstm.setString(5, dto.getLeaveDate());


        boolean isSaved = pstm.executeUpdate() > 0;


        return isSaved;
    }


    public List<FatherDto> loadAllFather() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM father";
        PreparedStatement pstm = connection.prepareStatement(sql);


        List<FatherDto> fatherList = new ArrayList<>();


        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            fatherList.add(new FatherDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)

            ));
        }
        return fatherList;
    }






        public boolean updateFather(FatherDto dto) throws SQLException {
            Connection connection = DbConnection.getInstance().getConnection();

            String sql = "UPDATE father SET church_no = ?, name = ?, start_date = ?, leave_date = ? WHERE church_father_id = ?";
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, dto.getChurchNo());
            pstm.setString(2, dto.getName());
            pstm.setString(3, dto.getStartDate());
            pstm.setString(4, dto.getLeaveDate());
            pstm.setString(5, dto.getChurchFatherId());

            return pstm.executeUpdate() > 0;
        }








    public FatherDto searchCustomer(String churchFatherId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM father WHERE church_father_id=?";

        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, churchFatherId);

        ResultSet resultSet = pstm.executeQuery();

        FatherDto dto = null;

        if (resultSet.next()) {
            String father_churchNo = resultSet.getString(1);
            String father_churchFatherId = resultSet.getString(2);
            String father_name = resultSet.getString(3);
            String father_startDate = resultSet.getString(4);
            String father_leaveDate = resultSet.getString(5);

            dto = new FatherDto(father_churchNo, father_churchFatherId, father_name, father_startDate, father_leaveDate);
        }
        return dto;
    }


    public boolean deleteFather(String churchFatherId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM father WHERE church_father_id = ?";


        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, churchFatherId);
            return pstm.executeUpdate() > 0;
        }
    }



}
