package lk.ijse.fx.bo.custom;

import lk.ijse.fx.bo.SuperBO;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dto.VehicleDto;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface VehicleBO extends SuperBO {
     boolean saveVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException;
     List<VehicleDto> loadAllVehicle() throws SQLException, ClassNotFoundException;
     boolean updateVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException;
     VehicleDto searchVehicle(String churchFatherId) throws SQLException, ClassNotFoundException;
     boolean deleteVehicle(String churchFatherId) throws SQLException, ClassNotFoundException;
}
