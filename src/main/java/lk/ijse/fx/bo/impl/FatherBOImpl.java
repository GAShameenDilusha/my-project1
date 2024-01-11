package lk.ijse.fx.bo.impl;

import lk.ijse.fx.bo.custom.FatherBO;
import lk.ijse.fx.dao.DAOFactory;
import lk.ijse.fx.dao.SQLUtil;
import lk.ijse.fx.dao.custom.FatherDAO;
import lk.ijse.fx.dto.FatherDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FatherBOImpl implements FatherBO {
    private FatherDAO fatherDAO = (FatherDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.FATHER);

    @Override
    public boolean saveFather(FatherDto dto) throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO father VALUES(?, ?, ?, ?, ?)";
        return SQLUtil.execute(sql, dto.getChurchNo(), dto.getChurchFatherId(), dto.getName(), dto.getStartDate(), dto.getLeaveDate());
    }

    @Override
    public List<FatherDto> loadAllFather() throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM father";
        List<FatherDto> fatherList = new ArrayList<>();

        try (ResultSet resultSet = SQLUtil.execute(sql)) {
            while (resultSet.next()) {
                fatherList.add(new FatherDto(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getString(4),
                        resultSet.getString(5)
                ));
            }
        }

        return fatherList;
    }

    @Override
    public boolean updateFather(FatherDto dto) throws SQLException, ClassNotFoundException {
        String sql = "UPDATE father SET church_no = ?, name = ?, start_date = ?, leave_date = ? WHERE church_father_id = ?";
        return SQLUtil.execute(sql, dto.getChurchNo(), dto.getName(), dto.getStartDate(), dto.getLeaveDate(), dto.getChurchFatherId());
    }

    @Override
    public FatherDto searchFather(String churchFatherId) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM father WHERE church_father_id=?";
        ResultSet resultSet = SQLUtil.execute(sql, churchFatherId);

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

    @Override
    public boolean deleteFather(String churchFatherId) throws SQLException, ClassNotFoundException {
        String sql = "DELETE FROM father WHERE church_father_id = ?";
        return SQLUtil.execute(sql, churchFatherId);
    }
}
