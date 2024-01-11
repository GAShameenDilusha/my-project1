package lk.ijse.fx.dao.impl;

import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.RegistrationDAO;
import lk.ijse.fx.dto.RegistrationDto;
import lk.ijse.fx.entity.Attendence;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistrationDAOImpl implements RegistrationDAO {
    @Override
    public boolean save(RegistrationDto dto) throws SQLException, ClassNotFoundException {
        return false;
    }



    @Override
    public List<RegistrationDto> loadAll() throws SQLException, ClassNotFoundException {
        try (ResultSet resultSet = SQLUtil.execute("SELECT * FROM registration")) {
            List<RegistrationDto> registrationList = new ArrayList<>();
            while (resultSet.next()) {
                registrationList.add(new RegistrationDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5),
                        resultSet.getString(6),
                        resultSet.getString(7),
                        resultSet.getString(8),
                        resultSet.getString(9),
                        resultSet.getString(10)
                ));
            }
            return registrationList;
        }
    }




    @Override
    public boolean update(RegistrationDto dto) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute("UPDATE registration SET church_no = ?, division_no = ?, father_id = ?, mother_id = ?, father_name = ?, mother_name = ?, address = ?, tel = ?, date = ? WHERE family_no = ?",
                dto.getChurchNo(), dto.getDivisionNo(), dto.getFatherId(), dto.getMotherId(), dto.getFatherName(), dto.getMotherName(), dto.getAddress(), dto.getTel(), dto.getDate(), dto.getFamilyNo());
    }





    @Override
    public RegistrationDto search(String familyNo) throws SQLException, ClassNotFoundException {
            ResultSet resultSet = SQLUtil.execute("SELECT * FROM registration WHERE family_no=?");

            RegistrationDto dto = null;

            if (resultSet.next()) {
                String registration_churchNo = resultSet.getString(1);
                String registration_divisionNo = resultSet.getString(2);
                String registration_familyNo = resultSet.getString(3);
                String registration_fatherId = resultSet.getString(4);
                String registration_motherId = resultSet.getString(5);
                String registration_fatherName = resultSet.getString(6);
                String registration_motherName = resultSet.getString(7);
                String registration_address = resultSet.getString(8);
                String registration_tel = resultSet.getString(9);
                String registration_date = resultSet.getString(10);

                dto = new RegistrationDto(registration_churchNo, registration_divisionNo, registration_familyNo, registration_fatherId, registration_motherId, registration_fatherName, registration_motherName, registration_address, registration_tel, registration_date);
            }
            return dto;
        }




    @Override
    public boolean delete(String familyNo) throws SQLException, ClassNotFoundException {
        return false;
    }






}




