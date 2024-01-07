package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.FatherBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.FatherDAO;
import lk.ijse.fx.dto.FatherDto;
import lk.ijse.fx.entity.Father;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FatherBOImpl implements FatherBO {
    FatherDAO fatherDAO= (FatherDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FATHER);

    @Override
    public boolean saveFather(FatherDto dto) throws SQLException, SQLException, ClassNotFoundException {
        return fatherDAO.save(new Father(dto.getChurchNo(),dto.getChurchFatherId(),dto.getName(),dto.getStartDate(),dto.getLeaveDate()));

    }




    @Override
    public List<FatherDto> loadAllFather() throws SQLException, ClassNotFoundException {
        ArrayList<Father> fathers=fatherDAO.loadAll();
        ArrayList<FatherDto> fatherDtos=new ArrayList<>();
        for (Father father:fathers) {
            fatherDtos.add(new FatherDto(father.getChurchNo(),father.getChurchFatherId(),father.getName(),father.getStartDate(),father.getLeaveDate()));
        }
        return fatherDtos;

    }





    @Override
    public boolean updateFather(FatherDto dto) throws SQLException, ClassNotFoundException {
        return fatherDAO.update(new Father(dto.getChurchNo(),dto.getChurchFatherId(),dto.getName(),dto.getStartDate(),dto.getLeaveDate()));
    }







    @Override
    public FatherDto searchFather(String churchFatherId) throws SQLException, ClassNotFoundException {
        Father father=fatherDAO.search(churchFatherId);
        FatherDto fatherDto=new FatherDto(father.getChurchNo(),father.getChurchFatherId(),father.getName(),father.getStartDate(),father.getLeaveDate());
        return fatherDto;

    }



    @Override
    public boolean deleteFather(String churchFatherId) throws SQLException, ClassNotFoundException {
        return fatherDAO.delete(churchFatherId);
    }
}
