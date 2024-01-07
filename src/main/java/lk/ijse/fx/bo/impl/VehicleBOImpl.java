package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.VehicleBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.VehicleDAO;
import lk.ijse.fx.dto.VehicleDto;
import lk.ijse.fx.entity.Vehicle;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleBOImpl implements VehicleBO {
    VehicleDAO vehicleDAO= (VehicleDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.VEHICLE);

    @Override
    public boolean saveVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.save(new Vehicle(dto.getChurchFatherId(),dto.getDate(),dto.getCategory(),dto.getDiscription()));
    }




    @Override
    public List<VehicleDto> loadAllVehicle() throws SQLException, ClassNotFoundException {
        ArrayList<Vehicle> vehicles=vehicleDAO.loadAll();
        ArrayList<VehicleDto> vehicleDtos=new ArrayList<>();
        for (Vehicle vehicle:vehicles) {
            vehicleDtos.add(new VehicleDto(vehicle.getChurchFatherId(),vehicle.getDate(),vehicle.getCategory(),vehicle.getDiscription()));
        }
        return vehicleDtos;


    }




    @Override
    public boolean updateVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
        return vehicleDAO.update(new Vehicle(dto.getChurchFatherId(),dto.getDate(),dto.getCategory(),dto.getDiscription()));
    }




    @Override
    public VehicleDto searchVehicle(String churchFatherId) throws SQLException, ClassNotFoundException {
        Vehicle vehicle=vehicleDAO.search(churchFatherId);
        VehicleDto vehicleDto=new VehicleDto(vehicle.getChurchFatherId(),vehicle.getDate(),vehicle.getCategory(),vehicle.getDiscription());
        return vehicleDto;
    }




    @Override
    public boolean deleteVehicle(String churchFatherId) throws SQLException, ClassNotFoundException {
       return vehicleDAO.delete(churchFatherId);
    }
}
