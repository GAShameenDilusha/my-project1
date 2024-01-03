package lk.ijse.fx.dao.custom;

import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.VehicleDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public interface VehicleDAO {
        public boolean saveVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException;
        public List<VehicleDto> loadAllVehicle() throws SQLException, ClassNotFoundException;
        public boolean updateVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException;
        public VehicleDto searchCustomer(String churchFatherId) throws SQLException, ClassNotFoundException;
        public boolean deleteVehicle(String churchFatherId) throws SQLException, ClassNotFoundException;


}
