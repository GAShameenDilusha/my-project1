package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.VehicleBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.VehicleDAO;
import lk.ijse.fx.dto.VehicleDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {
    private VehicleDAO vehicleDAO = (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public boolean saveVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO vehicle VALUES(?, ?, ?, ?)";
        LocalDate currentDate = LocalDate.now();
        return SQLUtil.execute(sql, dto.getChurchFatherId(), currentDate, dto.getCategory(), dto.getDiscription());
    }

    @Override
    public List<VehicleDto> loadAllVehicle() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM vehicle";

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            List<VehicleDto> vehicleList = new ArrayList<>();

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
    }

    @Override
    public boolean updateVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE vehicle SET date = ?, category = ?, discription = ? WHERE church_father_id = ?";
        return SQLUtil.execute(sql, LocalDate.parse(dto.getDate()), dto.getCategory(), dto.getDiscription(), dto.getChurchFatherId());
    }

    @Override
    public VehicleDto searchVehicle(String churchFatherId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM vehicle WHERE church_father_id=?";
        ResultSet resultSet = SQLUtil.execute(sql, churchFatherId);

        if (resultSet.next()) {
            String vehicle_churchFatherId = resultSet.getString(1);
            String vehicle_date = resultSet.getString(2);
            String vehicle_category = resultSet.getString(3);
            String vehicle_discrition = resultSet.getString(4);

            return new VehicleDto(vehicle_churchFatherId, vehicle_date, vehicle_category, vehicle_discrition);
        }

        return null;
    }

    @Override
    public boolean deleteVehicle(String churchFatherId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM vehicle WHERE church_father_id = ?";
        return SQLUtil.execute(sql, churchFatherId);
    }
}
