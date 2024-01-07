package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.ChurchBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.AttendenceDAO;
import lk.ijse.fx.dao.custom.ChurchDAO;
import lk.ijse.fx.dto.ChurchDto;
import lk.ijse.fx.entity.Church;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChurchBOImpl implements ChurchBO {
    ChurchDAO churchDAO= (ChurchDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CHURCH);
    public List<ChurchDto> getAllChurches() {
        ArrayList<Church> churches=churchDAO.getAllChurches();
        ArrayList<ChurchDto> churchDtos=new ArrayList<>();
        for (Church church:churches) {
            churchDtos.add(new ChurchDto(church.getChurch_no(),church.getA(),church.getB(),church.getC(),church.getD()));
        }
        return churchDtos;


    }
}
