package lk.ijse.fx.model;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.VehicleDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleModel {
    public boolean saveVehicle(VehicleDto dto) throws SQLException {
        // Get the current date
        LocalDate currentDate = LocalDate.now();
        Date sqlDate = Date.valueOf(currentDate);

        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "INSERT INTO vehicle VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = connection.prepareStatement(sql);


        pstm.setString(1, dto.getChurchFatherId());
        pstm.setDate(2, sqlDate);  // Use setDate to set the date
        pstm.setString(3, dto.getCategory());
        pstm.setString(4, dto.getDiscription());



        boolean isSaved = pstm.executeUpdate() > 0;


        return isSaved;
    }










    public List<VehicleDto> loadAllVehicle() throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM vehicle";
        PreparedStatement pstm = connection.prepareStatement(sql);


        List<VehicleDto> vehicleList = new ArrayList<>();


        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            vehicleList.add(new VehicleDto(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            ));
        }


        return vehicleList;
    }





    public boolean updateVehicle(VehicleDto dto) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "UPDATE vehicle SET date = ?, category = ?, discription = ? WHERE church_father_id = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setDate(1, Date.valueOf(LocalDate.parse(dto.getDate()))); // Parse and use setDate
        pstm.setString(2, dto.getCategory());
        pstm.setString(3, dto.getDiscription());
        pstm.setString(4, dto.getChurchFatherId());

        return pstm.executeUpdate() > 0;
    }



    public VehicleDto searchCustomer(String churchFatherId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();


        String sql = "SELECT * FROM vehicle WHERE church_father_id=?";


        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, churchFatherId);


        ResultSet resultSet = pstm.executeQuery();


        VehicleDto dto = null;


        if (resultSet.next()) {
            String vehicle_churchFatherId= resultSet.getString(1);
            String vehicle_date = resultSet.getString(2);
            String vehicle_category = resultSet.getString(3);
            String vehicle_discrition = resultSet.getString(4);

            dto = new VehicleDto(vehicle_churchFatherId, vehicle_date, vehicle_category, vehicle_discrition);
        }
        return dto;
    }



    public boolean deleteVehicle(String churchFatherId) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        String sql = "DELETE FROM vehicle WHERE church_father_id = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, churchFatherId);
            return pstm.executeUpdate() > 0;
        }
    }



}
