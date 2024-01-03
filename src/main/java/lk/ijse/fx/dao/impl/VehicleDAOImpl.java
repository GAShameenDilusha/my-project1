package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.VehicleDAO;
import lk.ijse.fx.db.DbConnection;
import lk.ijse.fx.dto.VehicleDto;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class VehicleDAOImpl implements VehicleDAO {
        public boolean saveVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
            // Get the current date
            LocalDate currentDate = LocalDate.now();
            Date sqlDate = Date.valueOf(currentDate);

            return SQLUtil.execute("INSERT INTO vehicle VALUES(?, ?, ?, ?)",
                    dto.getChurchFatherId(),sqlDate,dto.getCategory(),dto.getDiscription());
        }





        public List<VehicleDto> loadAllVehicle() throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM vehicle");

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





        public boolean updateVehicle(VehicleDto dto) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("UPDATE vehicle SET date = ?, category = ?, discription = ? WHERE church_father_id = ?"
                    ,dto.getDate(),dto.getCategory(),dto.getDiscription(),dto.getChurchFatherId());
        }





        public VehicleDto searchCustomer(String churchFatherId) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM vehicle WHERE church_father_id=?");

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





        public boolean deleteVehicle(String churchFatherId) throws SQLException, ClassNotFoundException {
            return SQLUtil.execute("DELETE FROM vehicle WHERE church_father_id = ?");

            }
        }







